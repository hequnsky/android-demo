package com.djf.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.djf.activity.UserInfoActivity;
import com.djf.activity.WriteMoodActivity;
import com.djf.bean.BaseBean;
import com.djf.bean.Comment;
import com.djf.bean.MoodInfoBean;
import com.djf.callback.ResultCallback;
import com.djf.constants.HttpConstants;
import com.djf.db.SharedPreferenceDb;
import com.djf.dialog.CustomDialogUpd;
import com.djf.loadimage.AsynImageLoader;
import com.djf.moodinn.R;
import com.djf.upload.StringUtils;
import com.djf.util.HttpUtil;
import com.djf.util.LvHeightUtil;
import com.djf.util.UnitTransformUtil;

@SuppressLint("InflateParams")
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ListViewAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;
	private Context context;
	private List<MoodInfoBean> allStr;
	AsynImageLoader asynImageLoader = new AsynImageLoader();
	List<String> allString;
	// 暂且用bitmap加载图片
	private FinalBitmap finalBitmap;
	
	private List<Comment> comList=null;
	
	private MainListViewAdapter pinglunAdapter=null;
	
	//暂且用afinal 网络加载
	private FinalHttp finalHttp=null;
	
	private ListView moodinListView;
	
	private TextView moodinPinglunNums;

	public ListViewAdapter(Context context, List<MoodInfoBean> allStr) {
		// TODO Auto-generated constructor stub
		layoutInflater = LayoutInflater.from(context);
		this.context = context;
		this.allStr = allStr;

		finalBitmap = FinalBitmap.create(context);
		finalBitmap.configLoadingImage(R.drawable.ic_launcher);
		finalBitmap.configBitmapLoadThreadSize(5);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return allStr.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return allStr.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressWarnings("unused")
	@Override
	public View getView(int arg0, View contentView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		RelativeLayout view=null;
		ViewHolder viewHolder;
//		view=(RelativeLayout) contentView;
		if (view == null) {

			view = (RelativeLayout) layoutInflater.inflate(R.layout.main_listview_item,null);
			viewHolder = new ViewHolder();

			viewHolder.ivUserLogo = (ImageView) view
					.findViewById(R.id.iv_leftlogo);
			viewHolder.tvUserName = (TextView) view
					.findViewById(R.id.userName);
			viewHolder.tvUserAge = (TextView) view
					.findViewById(R.id.tvAge);
			viewHolder.moodinTime = (TextView) view
					.findViewById(R.id.sendTime);
			viewHolder.moodinContent = (TextView) view
					.findViewById(R.id.showInfo);
			viewHolder.moodinGridView = (com.djf.view.NoScrollGridView) view
					.findViewById(R.id.myGridView);
			viewHolder.pinglun = (LinearLayout) view.findViewById(R.id.pinglun);
			viewHolder.fenxiang = (LinearLayout) view.findViewById(R.id.fenxiang);
			viewHolder.etPingLunContent=(EditText)view.findViewById(R.id.etPingLunContent);

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		final MoodInfoBean bean = allStr.get(arg0);
		
		moodinPinglunNums = (TextView) view.findViewById(R.id.pinglun_nums);
		moodinListView = (ListView) view.findViewById(R.id.pinglunListView);
		
		viewHolder.moodinContent.setText(bean.getContent());
		viewHolder.moodinTime.setText(bean.getTime());
		viewHolder.tvUserName.setText(bean.getUserName());
		viewHolder.tvUserAge.setText(bean.getAge());

		String image_0 = bean.getImgone();
		String image_1 = bean.getImgtwo();
		String image_2 = bean.getImgthree();
		String image_3 = bean.getImgfour();
		String image_4 = bean.getImgfive();
		String image_5 = bean.getImgsix();

		allString = new ArrayList<String>();
		/*
		 * asynImageLoader.showImageAsyn(viewHolder.ivUserLogo,
		 * HttpConstants.HTTP_REQUEST + bean.getUsreIcon(),
		 * R.drawable.ic_launcher);
		 */

		finalBitmap.display(viewHolder.ivUserLogo, HttpConstants.HTTP_REQUEST
				+ bean.getUsreIcon());

		/**
		 * 对gridview 图片显示做的操作
		 */
		if (image_0 != null) {
			allString.add(image_0);
		}
		if (image_1 != null) {
			allString.add(image_1);
		}
		if (image_2 != null) {
			allString.add(image_2);
		}
		if (image_3 != null) {
			allString.add(image_3);
		}
		if (image_4 != null) {
			allString.add(image_4);
		}
		if (image_5 != null) {
			allString.add(image_5);
		}

		if (allString.size() > 0) {
			
			
			MainGridViewAdapter mainGridViewAdapter = new MainGridViewAdapter(
					context, allString);
			
			//说明有两行
			if(allString.size()>3){
				
				LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) viewHolder.moodinGridView.getLayoutParams(); // 取控件mGrid当前的布局参数
				linearParams.height = UnitTransformUtil.dip2px(context, 240);// 
				viewHolder.moodinGridView.setLayoutParams(linearParams);
				viewHolder.moodinGridView.setVerticalSpacing(1);
			}
			viewHolder.moodinGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			viewHolder.moodinGridView.setAdapter(mainGridViewAdapter);
		}
		// 用户评论
		viewHolder.etPingLunContent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				pinglun(bean.getId());
				List<Comment> dateList=bean.getAllComment();
//				if(dateList.size()!=0){
//					
//				}
				showUpdataDialog(bean.getId(),new SharedPreferenceDb(context).getName(),bean.getAllComment());
			}
		});
		
		
		viewHolder.pinglun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				pinglun(bean.getId());
				showUpdataDialog(bean.getId(),new SharedPreferenceDb(context).getName(),bean.getAllComment());
			}
		});
		
		

		// 用户分享
		viewHolder.fenxiang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "分享", Toast.LENGTH_LONG).show();
			}
		});
		
		

		/**
		 * 对用户评论做的操作
		 */
		  if(null!=bean.getAllComment()&&bean.getAllComment().size()!=0){
			  comList=bean.getAllComment();
			  pinglunAdapter=new MainListViewAdapter(context, comList); 
			  moodinListView.setAdapter(pinglunAdapter);
					  LvHeightUtil.setListViewHeightBasedOnChildren(moodinListView);
					  moodinPinglunNums.setText(bean.getAllComment().size()+"");  
		  }else{
			   moodinPinglunNums.setText("0");
		  }
		  
		  /**
		   * 获取用户资料
		   */
		  viewHolder.ivUserLogo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, bean.getUserid(), Toast.LENGTH_LONG).show();
				Intent intent=new Intent();
				intent.setClass(context, UserInfoActivity.class);
				intent.putExtra("userid", bean.getUserid());
				intent.putExtra("username", bean.getUserName());
				context.startActivity(intent);
			}
		});

		return view;
	}

	public class ViewHolder {

		// 用户图像
		private ImageView ivUserLogo;

		// 用户昵称
		private TextView tvUserName;

		// 用户年龄
		private TextView tvUserAge;

		// 心情时间
		private TextView moodinTime;

		// 心情内容
		private TextView moodinContent;

		// 心情图片
		private com.djf.view.NoScrollGridView moodinGridView;

//		// 心情评论
		private ListView moodinListView;

//		// 显示评论数量
//		private TextView moodinPinglunNums;

		// 评论按钮
		private LinearLayout pinglun;
		
		//评论edittext
		private EditText etPingLunContent;

		// 分享
		private LinearLayout fenxiang;

	}
	
	
	
	protected void showUpdataDialog(final String diaryId,final String UserName,final List<Comment> comList) {

        final CustomDialogUpd.Builder builder = new CustomDialogUpd.Builder(context);
        builder.setTitle("发表评论");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                // TODO Auto-generated method stub
                String  content= builder.getEditText().getText().toString().trim();
                pinglun(diaryId,content,UserName,comList);
                dialog.dismiss();
            }
        });
		// 当点取消按钮时进行登录
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

        builder.create().show();
    }
	
	
	public void pinglun(final String diaryId,final String content,final String UserName,final List<Comment> coList){
		if(content.isEmpty()){
			Toast.makeText(context, "请填入评论内容", Toast.LENGTH_LONG).show();
			return;
		}
		List<NameValuePair> allP=new ArrayList<NameValuePair>();
		allP.add(new BasicNameValuePair("diaryId", diaryId));
		allP.add(new BasicNameValuePair("cUserId", new SharedPreferenceDb(context).getUserId()));
		allP.add(new BasicNameValuePair("content", content));
		
		HttpUtil.doPost(HttpConstants.HTTP_PINGLUN, allP, new ResultCallback() {
			
			@Override
			public void getReslt(String result) {
				// TODO Auto-generated method stub
				if(!result.isEmpty() && !"1".equals(result)){
					BaseBean b=JSON.parseObject(result, BaseBean.class);
					if ("0".equals(b.getRespcode())) {
						Toast.makeText(context, String.valueOf(result),Toast.LENGTH_LONG).show();
						if (coList==null||coList.size() == 0) {
							comList=new ArrayList<Comment>();
							comList.add(new Comment("", diaryId, "", content,"", UserName));
							pinglunAdapter = new MainListViewAdapter(context,comList);
							moodinListView.setAdapter(pinglunAdapter);
							LvHeightUtil.setListViewHeightBasedOnChildren(moodinListView);
							moodinPinglunNums.setText(comList.size() + "");
						} else {
							comList.add(new Comment("", diaryId, "", content,"", UserName));
							pinglunAdapter.notifyDataSetChanged();
						}

					} else {
						Toast.makeText(context, "用户名或密码错误", Toast.LENGTH_LONG).show();
					}
				}else{
					Toast.makeText(context, "服务器响应失败", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
     }
	
}
