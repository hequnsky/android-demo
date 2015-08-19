package com.djf.activity;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.djf.bean.BaseBean;
import com.djf.constants.HttpConstants;
import com.djf.db.SharedPreferenceDb;
import com.djf.moodinn.R;
import com.djf.view.TitleBar;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class UserFeedbackActivity extends BaseActivity {

	private TitleBar titleBar;
	private EditText etName;
	private EditText etContact;
	private EditText etContent;
	private Button btnOk;
	private FinalHttp finalHttp;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.user_reedback);
		
		initViews();
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		titleBar=(TitleBar)findViewById(R.id.userTitleBar);
		
		titleBar.showLeft("建议", getResources().getDrawable(R.drawable.app_back), new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		 if(!"0".equals(String.valueOf(new SharedPreferenceDb(UserFeedbackActivity.this).getChangeTheme()))){
			 titleBar.setBackgroundColor(new SharedPreferenceDb(UserFeedbackActivity.this).getChangeTheme());
		 }else{
			 titleBar.setBackgroundColor(getResources().getColor(R.color.red));
		 }
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		etName=(EditText)findViewById(R.id.etName);
		etContact=(EditText)findViewById(R.id.etQQ);
		etContent=(EditText)findViewById(R.id.etSuggest);
		btnOk=(Button)findViewById(R.id.btnOk);
		
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				if(!etContent.getText().toString().trim().isEmpty()){
					finalHttp=new FinalHttp();
					AjaxParams p=new AjaxParams();
					p.put("name", etName.getText().toString().trim());
					p.put("contact", etContact.getText().toString().trim());
					p.put("content", etContent.getText().toString().trim());
					initProgressDialog();
					finalHttp.post(HttpConstants.HTTP_SUMBIT_FEEDBACK, p, new AjaxCallBack<Object>() {
						@Override
						public void onLoading(long count, long current) {
							
						};
						
						@Override
						public void onFailure(Throwable t, int errorNo, String strMsg) {
							close();
						};
						
						@Override
						public void onSuccess(Object t) {
							
							BaseBean b=JSON.parseObject(String.valueOf(t), BaseBean.class);
							
							if(b.getRespcode().equals("0")){
								Toast.makeText(UserFeedbackActivity.this, "谢谢你的宝贵意见", Toast.LENGTH_LONG).show();
								finish();
								close();
							}else{
								close();
								Toast.makeText(UserFeedbackActivity.this, "提交意见失败", Toast.LENGTH_LONG).show();
							}
						};
					});
					
				}else{
					Toast.makeText(UserFeedbackActivity.this, "亲，给点建议我吧", Toast.LENGTH_LONG).show();
				}
				
				
			}
		});
	}
}
