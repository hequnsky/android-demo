package com.djf.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ant.liao.GifView;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.djf.adapter.ListViewAdapter;
import com.djf.bean.BaseBean;
import com.djf.bean.MoodInBean;
import com.djf.bean.MoodInfoBean;
import com.djf.bean.UpdateBean;
import com.djf.bean.UserBean;
import com.djf.callback.ResultCallback;
import com.djf.constants.HttpConstants;
import com.djf.db.SharedPreferenceDb;
import com.djf.listview.XListView;
import com.djf.listview.XListView.IXListViewListener;
import com.djf.loadimage.AsynImageLoader;
import com.djf.moodinn.R;
import com.djf.receiver.MyPushMessageReceiver;
import com.djf.util.DialogUtils;
import com.djf.util.DialogUtils.DialogCallback;
import com.djf.util.HttpUtil;
import com.djf.util.SdCardUtil;
import com.djf.util.TimeUtil;
import com.djf.util.UpdateManager;
import com.djf.util.UpdateService;
import com.djf.util.Utils;
import com.djf.view.AlwaysMarqueeTextView;
import com.djf.view.CircleImageView;
import com.djf.view.TitleBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.slidingmenu.lib.SlidingMenu;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MainActivity extends BaseActivity implements OnClickListener,
		IXListViewListener {

	private SlidingMenu menu;
	private ImageView ivroate;
	private View slidView;
	private LayoutInflater layoutInflater;
	private CircleImageView userIcon;
	private RelativeLayout layoutFeedback;
	private RelativeLayout layoutSetting;
	private RelativeLayout layoutUserHelp;
	private RelativeLayout layoutMessage;
	private RelativeLayout layoutVersionUpdate;
	private UserBean userBean;
	private TextView tvUserName;
	private RelativeLayout layoutStr;
	private AlwaysMarqueeTextView alwaysStr;

	private XListView myListView;
	
	private Context context;

	private List<MoodInfoBean> allMoodInfo = new ArrayList<MoodInfoBean>();

	private ListViewAdapter myListViewAdapter = null;
	private int page = 1;

	MoodInfoBean moodInfo;

	// 刷新
	private int LOAD_REFRESH = 0;

	// 查看更多
	private int LOAD_MORE = 1;

	AsynImageLoader asynImageLoader = new AsynImageLoader();
	
	String fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
	
	private UpdateManager updateManager;
	
	

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		layoutInflater = LayoutInflater.from(this);
		setContentView(R.layout.main);
		context = this;
		PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY,
				Utils.getMetaValue(MainActivity.this, "api_key"));
		Intent updateServiceIntent = new Intent(this, UpdateService.class);
		startService(updateServiceIntent);
		initData();
		initViews();

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		TitleBar titleBar = (TitleBar) findViewById(R.id.mainTitleBar);
		titleBar.showLeftAndRight("心情驿站",
				getResources().getDrawable(R.drawable.menu), getResources()
						.getDrawable(R.drawable.edit), new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						menu.toggle();
						Animation operatingAnim = AnimationUtils.loadAnimation(
								MainActivity.this, R.anim.tip);
						LinearInterpolator lin = new LinearInterpolator();
						operatingAnim.setInterpolator(lin);
						if (operatingAnim != null) {
							ivroate.startAnimation(operatingAnim);
						}
					}
				}, new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						SkipActivityforClass(MainActivity.this,
								WriteMoodActivity.class);
						if (new SharedPreferenceDb(MainActivity.this)
								.getAnimation() == true) {
							overridePendingTransition(
									R.anim.scale_translate_rotate,
									R.anim.my_alpha_action);
						}

					}
				});

		if (!"0".equals(String
				.valueOf(new SharedPreferenceDb(MainActivity.this)
						.getChangeTheme()))) {
			titleBar.setBackgroundColor(new SharedPreferenceDb(
					MainActivity.this).getChangeTheme());
		} else {
			titleBar.setBackgroundColor(getResources().getColor(R.color.red));
		}

	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();

		layoutStr = (RelativeLayout) findViewById(R.id.layoutButtom);
		alwaysStr = (AlwaysMarqueeTextView) findViewById(R.id.alwaysStr);

		initSinldingMenu();

		myListView = (XListView) findViewById(R.id.myListView);
		myListView.setPullLoadEnable(true);

		loadData(LOAD_REFRESH);
		myListView.setXListViewListener(this);

	}

	public void loadData(final int code) {
		
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("date", TimeUtil.getCurrentDate()));
		if (code == LOAD_MORE) {
			params.add(new BasicNameValuePair("page", String.valueOf(page)));
		} else if (code == LOAD_REFRESH) {
			page = 1;
			params.add(new BasicNameValuePair("page", String.valueOf(page)));
		}
		initProgressDialog();
		HttpUtil.doPost(HttpConstants.HTTP_ALL_DIARYS, params, new ResultCallback() {
			
			@Override
			public void getReslt(String result) {
				// TODO Auto-generated method stub
				
				if(result!=null && !"1".equals(result)){
					BaseBean bean = JSON.parseObject(result,BaseBean.class);

					if (bean.getRespcode().equals("0")) {
						 Type type = new TypeToken<MoodInBean>() {
						 }.getType();
						Gson gson=new Gson();
						
						MoodInBean json = gson.fromJson(result, type);
						
						List<MoodInfoBean> allData=json.getData();
						
						if (allData.size() > 0) {
							final List<MoodInfoBean> allMoodInfoList = new ArrayList<MoodInfoBean>();
							page++;
							if (code == LOAD_REFRESH) {
								allMoodInfo.clear();
								for (int i = 0; i < allData.size(); i++) {
									moodInfo = new MoodInfoBean();
									moodInfo.setAllComment(allData.get(i)
											.getAllComment());
									moodInfo.setContent(allData.get(i)
											.getContent());
									moodInfo.setDate(allData.get(i)
											.getDate());
									moodInfo.setId(allData.get(i).getId());
									moodInfo.setImgfive(allData.get(i)
											.getImgfive());
									moodInfo.setImgfour(allData.get(i)
											.getImgfour());
									moodInfo.setImgone(allData.get(i)
											.getImgone());
									moodInfo.setImgsix(allData.get(i)
											.getImgsix());
									moodInfo.setImgthree(allData.get(i)
											.getImgthree());
									moodInfo.setImgtwo(allData.get(i)
											.getImgtwo());
									moodInfo.setTime(allData.get(i)
											.getTime());
									moodInfo.setUserid(allData.get(i)
											.getUserid());
									moodInfo.setUsreIcon(allData.get(i)
											.getUsreIcon());
									moodInfo.setContent(allData.get(i)
											.getContent());
									moodInfo.setUserName(allData.get(i).getUserName());
									
									moodInfo.setAge(allData.get(i).getAge());
									allMoodInfoList.add(moodInfo);

								}
								for(MoodInfoBean moodInfoBean:allMoodInfoList){
									allMoodInfo.add(moodInfoBean);
								}
								
								myListViewAdapter = new ListViewAdapter(MainActivity.this, allMoodInfo);
								myListView.setAdapter(myListViewAdapter);
							} else if (code == LOAD_MORE) {
								for (int i = 0; i < allData.size(); i++) {
									moodInfo = new MoodInfoBean();
									moodInfo.setAllComment(allData.get(i)
											.getAllComment());
									moodInfo.setContent(allData.get(i)
											.getContent());
									moodInfo.setDate(allData.get(i)
											.getDate());
									moodInfo.setId(allData.get(i).getId());
									moodInfo.setImgfive(allData.get(i)
											.getImgfive());
									moodInfo.setImgfour(allData.get(i)
											.getImgfour());
									moodInfo.setImgone(allData.get(i)
											.getImgone());
									moodInfo.setImgsix(allData.get(i)
											.getImgsix());
									moodInfo.setImgthree(allData.get(i)
											.getImgthree());
									moodInfo.setImgtwo(allData.get(i)
											.getImgtwo());
									moodInfo.setTime(allData.get(i)
											.getTime());
									moodInfo.setUserid(allData.get(i)
											.getUserid());
									moodInfo.setUsreIcon(allData.get(i)
											.getUsreIcon());
									moodInfo.setContent(allData.get(i)
											.getContent());
									moodInfo.setUserName(allData.get(i).getUserName());
									moodInfo.setAge(allData.get(i).getAge());
									allMoodInfo.add(moodInfo);

								}
								myListViewAdapter.notifyDataSetChanged();
							}

							onLoad();
							close();
						} else {
							close();
							Toast.makeText(MainActivity.this, "没有更多数据",
									Toast.LENGTH_LONG).show();
							onLoad();
						}

						
					} else {
						close();
						Toast.makeText(MainActivity.this, "失败",
								Toast.LENGTH_LONG).show();
					}

				}else{
					close();
					Toast.makeText(MainActivity.this, "服务器响应失败", Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	/**
	 * 关闭进度
	 */
	private void onLoad() {
		myListView.stopRefresh();
		myListView.stopLoadMore();
		myListView.setRefreshTime("刚刚");
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(MyPushMessageReceiver.MESSAGE_ACTION);
		registerReceiver(messageReceiver, intentFilter);
		UserBean u = (UserBean) getIntent().getSerializableExtra("user");
		this.userBean = u;

	}

	private void initSinldingMenu() {

		slidView = layoutInflater.inflate(R.layout.slidingmenu, null);
		ivroate = (ImageView) slidView.findViewById(R.id.iv_roate);
		userIcon = (CircleImageView) slidView.findViewById(R.id.userIcon);
		tvUserName = (TextView) slidView.findViewById(R.id.username);

		layoutFeedback = (RelativeLayout) slidView
				.findViewById(R.id.layout_feedback);
		layoutFeedback.setOnClickListener(this);
		layoutSetting = (RelativeLayout) slidView
				.findViewById(R.id.layout_setting);
		layoutSetting.setOnClickListener(this);
		layoutUserHelp = (RelativeLayout) slidView
				.findViewById(R.id.layoutUserHelp);
		layoutUserHelp.setOnClickListener(this);
		layoutVersionUpdate = (RelativeLayout) slidView
				.findViewById(R.id.layoutVersionUpdate);
		layoutVersionUpdate.setOnClickListener(this);

		layoutMessage = (RelativeLayout) slidView
				.findViewById(R.id.layoutMessage);
		layoutMessage.setOnClickListener(this);

		userIcon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new SelectPopuWindow(MainActivity.this, slidView);
			}
		});

		if (userBean.getUserIcon() != null) {
			asynImageLoader.showImageAsyn(userIcon, HttpConstants.HTTP_REQUEST
					+ userBean.getUserIcon(), R.drawable.ic_launcher);
		}

		tvUserName.setText(userBean.getName());

		// TODO Auto-generated method stub
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.slidingmenu);
		menu.setBehindOffsetRes(R.dimen.slidingmenu);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(slidView);

	}

	public class SelectPopuWindow extends PopupWindow {

		private Context context;

		public SelectPopuWindow(Context mContext, View parent) {

			this.context = mContext;
			View view = View
					.inflate(mContext, R.layout.item_popupwindows, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_2));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();

			Button bt1 = (Button) view
					.findViewById(R.id.item_popupwindows_camera);
			Button bt2 = (Button) view
					.findViewById(R.id.item_popupwindows_Photo);
			Button bt3 = (Button) view
					.findViewById(R.id.item_popupwindows_cancel);
			
			/**
			 * 拍照
			 */
			bt1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),fileName));
					openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
					startActivityForResult(openCameraIntent, 1);
					dismiss();
				}
			});
			bt2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					if (SdCardUtil.checkSdCard() == true) {
						Intent intent = new Intent(Intent.ACTION_PICK);
						intent.setType("image/*");// 相片类型
						startActivityForResult(intent, 2);
					} else {
						Toast.makeText(MainActivity.this, "SD卡不存在",
								Toast.LENGTH_LONG).show();
					}

					dismiss();
				}
			});
			bt3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dismiss();
				}
			});

		}
	}
	
	

	public void saveImageToFile(Bitmap bitmap) {
		
		FileOutputStream fos = null;
		String fileName = SdCardUtil.getSdPath() + SdCardUtil.FILEDIR + "/"
				+ SdCardUtil.FILEUSER + "/icon" + "/" + "userIcon"
				+ String.valueOf(System.currentTimeMillis()) + ".png";

		File f = new File(fileName);
		if (f.exists()) {
			f.delete();
		}
		try {
			fos = new FileOutputStream(fileName);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				if (bitmap != null) {
					bitmap.recycle();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				Toast.makeText(MainActivity.this, fileName + "上传成功",
						Toast.LENGTH_LONG).show();
				initProgressDialog();
				AjaxParams ap = new AjaxParams();
				try {
					ap.put("file", new File(fileName));
					ap.put("id", userBean.getId());
					FinalHttp fh = new FinalHttp();
					fh.post(HttpConstants.HTTP_UPDATE_USERICON, ap,
							new AjaxCallBack<Object>() {

								@Override
								public void onFailure(Throwable t, int errorNo,
										String strMsg) {
									// TODO Auto-generated method stub
									super.onFailure(t, errorNo, strMsg);
									Toast.makeText(MainActivity.this, "修改图像失败",
											Toast.LENGTH_LONG).show();
									close();
								}

								@Override
								public void onSuccess(Object t) {
									// TODO Auto-generated method stub
									super.onSuccess(t);
									close();
									Toast.makeText(MainActivity.this,
											String.valueOf(t),
											Toast.LENGTH_LONG).show();
								}

							});
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					close();
				}
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int respCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, respCode, data);

		if (requestCode == 1 && respCode == RESULT_OK) {
			
			Uri uri = null;
			if (data != null) {
				uri = data.getData();
				System.out.println("Data");
			}else{
				uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),fileName));
			}
			
			cropImage(uri, 98, 98, 3);
			
			
		} else if (requestCode == 2 && respCode == RESULT_OK) {
			ContentResolver resolver = getContentResolver();
			Uri uri = data.getData();
			try {
				/*Bitmap bitmap = MediaStore.Images.Media
						.getBitmap(resolver, uri);*/
				cropImage(uri, 98, 98, 3);
				/*Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(bitmap, 88,
						88);
				userIcon.setImageBitmap(resizeBmp);
				saveImageToFile(bitmap);*/
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if(requestCode==3 && respCode==RESULT_OK){
			Bitmap photo = null;
			Uri photoUri = data.getData();
			if (photoUri != null) {
				photo = BitmapFactory.decodeFile(photoUri.getPath());
			}
			if (photo == null) {
				Bundle extra = data.getExtras();
				if (extra != null) {
	                photo = (Bitmap)extra.get("data");  
	                ByteArrayOutputStream stream = new ByteArrayOutputStream();  
	                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
	            }  
			}
			if(photo!=null){
				
				Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(photo, 98, 98);
				userIcon.setImageBitmap(resizeBmp);
				saveImageToFile(photo);
			}
			
		}
	}

	
	//截取图片
	public void cropImage(Uri uri, int outputX, int outputY, int requestCode){
		Intent intent = new Intent("com.android.camera.action.CROP");  
        intent.setDataAndType(uri, "image/*");  
        intent.putExtra("crop", "true");  
        intent.putExtra("aspectX", 1);  
        intent.putExtra("aspectY", 1);  
        intent.putExtra("outputX", outputX);   
        intent.putExtra("outputY", outputY); 
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);  
	    startActivityForResult(intent, requestCode);
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if (view == layoutFeedback) {
			SkipActivityforClass(MainActivity.this, UserFeedbackActivity.class);
			if (new SharedPreferenceDb(MainActivity.this).getAnimation() == true) {
				overridePendingTransition(R.anim.slide_up_in,
						R.anim.slide_down_out);
			}
		} else if (view == layoutSetting) {
			SkipActivityforClass(MainActivity.this, SettingActivity.class);
			if (new SharedPreferenceDb(MainActivity.this).getAnimation() == true) {
				overridePendingTransition(R.anim.my_scale_action,
						R.anim.my_alpha_action);
			}

		} else if (view == layoutUserHelp) {
			SkipActivityforClass(MainActivity.this, UserHelpActivity.class);
			if (new SharedPreferenceDb(MainActivity.this).getAnimation() == true) {
				overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
			}
		} else if (view == layoutMessage) {
			SkipActivityforClass(MainActivity.this, MessageActivity.class);
			if (new SharedPreferenceDb(MainActivity.this).getAnimation() == true) {
				overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
			}
		} else if (view == layoutVersionUpdate) {

			/*isUpdate(context, true);*/
//			new DialogUtils(
//					MainActivity.this,
//					"发现新本",
//					"1修改了部分bug\n2 修改了部分界面\n3增加了童话联系人功能\n4增加了童话联系人功能\n5增加了童话联系人功能",
//					new DialogCallback() {
//
//						@Override
//						public void dialogOk() {
//							// TODO Auto-generated method stub
//							Toast.makeText(MainActivity.this, "确定",
//									Toast.LENGTH_LONG).show();
//						}
//
//						@Override
//						public void dialogCancel() {
//							// TODO Auto-generated method stub
//							Toast.makeText(MainActivity.this, "取消",
//									Toast.LENGTH_LONG).show();
//						}
//					});
		}
	}

	BroadcastReceiver messageReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent action) {
			// TODO Auto-generated method stub
			if (action.getAction().equals(MyPushMessageReceiver.MESSAGE_ACTION)) {
				String message = action.getStringExtra("message");
				if (!message.isEmpty()) {
					layoutStr.setVisibility(View.VISIBLE);
					alwaysStr.setText("系统消息:" + message);
				}
			}
		}
	};
	
	
	/**
	 * 检查是否需要更新
	 * 
	 * @param context
	 */
	public static void isUpdate(final Context context, final boolean isOnclick) {
		final int versionCode = getVersionVode(context, "com.djf.moodinn");// 当前版本号
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", "1"));
		params.add(new BasicNameValuePair("vcode", String.valueOf(versionCode)));
		HttpUtil.doPost(HttpConstants.HTTP_VERSION_UPDATE, params,
				new ResultCallback() {

					@Override
					public void getReslt(String result) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						Type type = new TypeToken<UpdateBean>() {
						}.getType();
						final UpdateBean updateBean = gson.fromJson(result,
								type);
						if (updateBean != null) {
							if (Integer.parseInt(updateBean.getData()
									.getVcode()) > versionCode) {
								new DialogUtils(
										context,
										"发现新本",
										"1修改了部分bug\n2 修改了部分界面\n3增加了童话联系人功能\n4增加了童话联系人功能\n5增加了童话联系人功能",
										new DialogCallback() {

											@Override
											public void dialogOk() {
												// TODO Auto-generated method
												// stub
												Toast.makeText(context, "确定",
														Toast.LENGTH_LONG)
														.show();
												UpdateManager updateManager = new UpdateManager(
														context, updateBean
																.getData());
												updateManager.downloadApk();
											}

											@Override
											public void dialogCancel() {
												// TODO Auto-generated method
												// stub
												Toast.makeText(context, "取消",
														Toast.LENGTH_LONG)
														.show();
											}
										});
							} else {
								if (isOnclick) {
									Toast.makeText(context, "当前为最新版本",
											Toast.LENGTH_LONG).show();
								}

							}
						}
					}
				});
	}

	/**
	 * 获取版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionVode(Context context, String packageName) {
		int versionCode = 1;
		try {
			versionCode = context.getPackageManager().getPackageInfo(
					packageName, 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		loadData(LOAD_REFRESH);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		loadData(LOAD_MORE);
	}
}
