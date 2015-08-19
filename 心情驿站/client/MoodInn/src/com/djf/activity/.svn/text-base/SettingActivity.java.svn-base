package com.djf.activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.djf.db.SharedPreferenceDb;
import com.djf.moodinn.R;
import com.djf.view.ColorPickerDialog;
import com.djf.view.SwitchButton;
import com.djf.view.TitleBar;
public class SettingActivity extends BaseActivity implements OnClickListener {

	private TitleBar titleBar;
	private SwitchButton sAnimation;
	private TextView tvTheme;
	private ColorPickerDialog dialog;
	private LayoutInflater layoutInflater;
	private View mainView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		layoutInflater=LayoutInflater.from(SettingActivity.this);
		mainView=layoutInflater.inflate(R.layout.setting, null);
		setContentView(mainView);
		initViews();
		
		
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		
		
		sAnimation=(SwitchButton)findViewById(R.id.sAnimation);
		
		if(new SharedPreferenceDb(SettingActivity.this).getAnimation()==true){
			sAnimation.setChecked(true);
		}else{
			sAnimation.setChecked(false);
		}
		
		sAnimation.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChencked) {
				// TODO Auto-generated method stub
				
				if(isChencked){
					new SharedPreferenceDb(SettingActivity.this).setOpenAnimation();
				}else{
					new SharedPreferenceDb(SettingActivity.this).setCloseAnimation();
				}
				
			}
		});
		
		tvTheme=(TextView)findViewById(R.id.tvTheme);
		
		tvTheme.setOnClickListener(this);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		titleBar=(TitleBar)findViewById(R.id.settingTitleBar);
		
		titleBar.showLeft("系统设置", getResources().getDrawable(R.drawable.app_back), new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		 if(!"0".equals(String.valueOf(new SharedPreferenceDb(SettingActivity.this).getChangeTheme()))){
			 titleBar.setBackgroundColor(new SharedPreferenceDb(SettingActivity.this).getChangeTheme());
			 tvTheme.setTextColor(new SharedPreferenceDb(SettingActivity.this).getChangeTheme());
		 }else{
			 titleBar.setBackgroundColor(getResources().getColor(R.color.red));
		 }
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==tvTheme){
			
			dialog = new ColorPickerDialog(SettingActivity.this, tvTheme.getTextColors().getDefaultColor(), 
					getResources().getString(R.string.app_name), 
					new ColorPickerDialog.OnColorChangedListener() {
				
				@Override
				public void colorChanged(int color) {
					tvTheme.setTextColor(color);
					new SharedPreferenceDb(SettingActivity.this).setChangeTheme(color);
					titleBar.setBackgroundColor(color);
				}
			});
			dialog.show();
		}
	}
	
}
