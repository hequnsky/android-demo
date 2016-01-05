package com.rmc.gaiya.activity;

import com.rmc.gaiya.adapter.Myadpater2;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class GaiyaMusic extends Activity implements OnClickListener {
	private ImageView pause_music,changpian, back_music, add_nature_sound, random, pre_music, next, playlist,like;
	private boolean  isplay=true;
	private ListView muisc_list_info;
	private ListView nature_list;
	private PopupWindow mpopupWindow;
	private PopupWindow mmpopupWindow;
	private boolean iszhuang=true;
	boolean islike=true;
	private Animation operatingAnim, ganAnim,d_ganAnim;
	/*定义AlertDialog的视图*/
	private View View_custom;
	int selectedFruitIndex = 0;
	private ImageView pan_pause;
    ViewFlipper  flipper;
    private ListView lv;
	float startX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 
		setContentView(R.layout.gaiya_music);
		initId();
		flipper.addView(getLayoutView(R.layout.yuan));
		flipper.addView(getLayoutView(R.layout.yuan));
		flipper.addView(getLayoutView(R.layout.yuan));
		flipper.addView(getLayoutView(R.layout.yuan));
		flipper.addView(getLayoutView(R.layout.yuan));

		
		pause_music.setOnClickListener(this);
		back_music.setOnClickListener(this);
		add_nature_sound.setOnClickListener(this);
		pre_music.setOnClickListener(this);
		next.setOnClickListener(this);
		playlist.setOnClickListener(this);
		like.setOnClickListener(this);
	}

	private void initId() {

		
		changpian=(ImageView) findViewById(R.id.changpian);
		pause_music = (ImageView) findViewById(R.id.pause_music);
		pan_pause = (ImageView) findViewById(R.id.pan_pause);
		back_music = (ImageView) findViewById(R.id.back_music);
		random = (ImageView) findViewById(R.id.random);
		add_nature_sound = (ImageView) findViewById(R.id.add_nature_sound);
		pre_music = (ImageView) findViewById(R.id.pre_music);
		next = (ImageView) findViewById(R.id.next);
		playlist = (ImageView) findViewById(R.id.playlist);
		flipper=(ViewFlipper) findViewById(R.id.flipper);
		like=(ImageView) findViewById(R.id.love);


	}
	 public View getLayoutView(int layoutId) {

    LayoutInflater mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View mView = mInflater.inflate(layoutId, null);

    return mView;

   }


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pause_music:

			if (isplay) {
				pause_music.setImageResource(R.drawable.hp_player_btn_pause_normal);
				isplay = false;
			}else{
			pause_music.setImageResource(R.drawable.hp_player_btn_play_normal);
			isplay = true;
			}
			if (iszhuang) {
				
				ganAnim=AnimationUtils.loadAnimation(this, R.anim.rotate_45);
				LinearInterpolator ln = new LinearInterpolator(); 
				ganAnim.setInterpolator(ln);
				ganAnim.setFillAfter(true);
				pan_pause.startAnimation(ganAnim);
				operatingAnim = AnimationUtils.loadAnimation(this, R.anim.rotate); 
				LinearInterpolator lin = new LinearInterpolator(); 
				operatingAnim.setInterpolator(lin); 
				flipper.startAnimation(operatingAnim);
				iszhuang=false;
			}else{
				d_ganAnim=AnimationUtils.loadAnimation(this, R.anim.rotate_45_d);
				LinearInterpolator d_ln = new LinearInterpolator(); 
				d_ganAnim.setInterpolator(d_ln);
				d_ganAnim.setFillAfter(true);
				pan_pause.startAnimation(d_ganAnim);
				flipper.clearAnimation();
				iszhuang=true;
			}
			
			break;
		case R.id.back_music:
			finish();
			this.overridePendingTransition(0, R.anim.activity_close);

			break;
		case R.id.add_nature_sound:
			showAlertDialog();


			break;
		case R.id.pre_music:
			if(operatingAnim!=null){
				
				d_ganAnim=AnimationUtils.loadAnimation(this, R.anim.rotate_45_d);
				LinearInterpolator d_ln = new LinearInterpolator(); 
				d_ganAnim.setInterpolator(d_ln);
				d_ganAnim.setFillAfter(true);
				pan_pause.startAnimation(d_ganAnim);
				flipper.clearAnimation();
				pause_music.setImageResource(R.drawable.hp_player_btn_play_normal);
				isplay=true;
				iszhuang=true;
			}
			   flipper.setInAnimation(this, R.anim.right_in);
			   flipper.setOutAnimation(this, R.anim.right_out);
			flipper.showPrevious();
			break;
		case R.id.next:
			if(operatingAnim!=null){
				d_ganAnim=AnimationUtils.loadAnimation(this, R.anim.rotate_45_d);
				LinearInterpolator d_ln = new LinearInterpolator(); 
				d_ganAnim.setInterpolator(d_ln);
				d_ganAnim.setFillAfter(true);
				pan_pause.startAnimation(d_ganAnim);
				flipper.clearAnimation();
				pause_music.setImageResource(R.drawable.hp_player_btn_play_normal);
				iszhuang=true;
				isplay=true;
			}
			 flipper.setInAnimation(this, R.anim.left_in);
			 flipper.setOutAnimation(this, R.anim.left_out);
			flipper.showNext();

			break;
		case R.id.playlist:
			showPopMenu();

			break;
		case R.id.random:

			break;
		case R.id.love:
			if (islike) {
				like.setImageResource(R.drawable.liked);
				islike=false;
			}else{
				like.setImageResource(R.drawable.like);
				islike=true;
			}
			break;
		default:
			break;
		}
	}
	
	private void showAlertDialog() {
	    
	    final String[] arrayFruit = new String[] { "蛙叫", "蝉叫", "鸟叫", "鸡叫" };
	    Dialog alertDialog = new AlertDialog.Builder(this).
	    	     setTitle("你需要添加哪种自然声？").
	    	     setIcon(R.drawable.ic_launcher)
	    	     .setSingleChoiceItems(arrayFruit, 0, new DialogInterface.OnClickListener() {
	    	  
	    	      @Override
	    	      public void onClick(DialogInterface dialog, int which) {
	    	       selectedFruitIndex = which;
	    	      }
	    	     }).
	    	     setPositiveButton("确认", new DialogInterface.OnClickListener() {

	    	     @Override
	    	      public void onClick(DialogInterface dialog, int which) {
	    	       Toast.makeText(GaiyaMusic.this, arrayFruit[selectedFruitIndex], Toast.LENGTH_SHORT).show();
	    	      }
	    	     }).
	    	     setNegativeButton("取消", new DialogInterface.OnClickListener() {

	    	     @Override
	    	      public void onClick(DialogInterface dialog, int which) {
	    	       // TODO Auto-generated method stub
	    	      }
	    	     }).
	    	     create();
	    	   alertDialog.show();
	    	  }
	    	 
	private void showPopMenu() {
		View view=View.inflate(getApplicationContext(), R.layout.playlist, null);
		muisc_list_info=(ListView) view.findViewById(R.id.muisc_list_info);
		Myadpater2 myadpater2=new Myadpater2(getApplicationContext());
		muisc_list_info.setAdapter(myadpater2);
		view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
		LinearLayout ll=(LinearLayout) view.findViewById(R.id.ll_palylist);
		ll.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
		
		if (mpopupWindow==null) {
			mpopupWindow=new PopupWindow(this);
			mpopupWindow.setWidth(LayoutParams.MATCH_PARENT);
			mpopupWindow.setHeight(LayoutParams.WRAP_CONTENT);
			mpopupWindow.setFocusable(true);
			mpopupWindow.setOutsideTouchable(true);
		}
	
		mpopupWindow.setContentView(view);
		mpopupWindow.showAtLocation(back_music, Gravity.BOTTOM, 0, 0);
		mpopupWindow.update();
		
		muisc_list_info.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mpopupWindow.dismiss();
			}
			
		});
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			startX=event.getX();
			break;
		}
		
		case MotionEvent.ACTION_UP:
		{
			//向右滑动
			if(event.getX()-startX>50)
				
			{
				if (operatingAnim!=null) {
					flipper.clearAnimation();					
				
				 flipper.setInAnimation(this, R.anim.left_in);
				 flipper.setOutAnimation(this, R.anim.left_out);
				 flipper.showNext();
					d_ganAnim=AnimationUtils.loadAnimation(this, R.anim.rotate_45_d);
					LinearInterpolator d_ln = new LinearInterpolator(); 
					d_ganAnim.setInterpolator(d_ln);
					d_ganAnim.setFillAfter(true);
					pan_pause.startAnimation(d_ganAnim);
					pause_music.setImageResource(R.drawable.hp_player_btn_play_normal);
					isplay = true;
					iszhuang=true;
					
				}
			}
			//向左滑动
			if(startX-event.getX()>50)
			{
				if (operatingAnim!=null) {
					flipper.clearAnimation();					
				
				   flipper.setInAnimation(this, R.anim.right_in);
				   flipper.setOutAnimation(this, R.anim.right_out);
				   flipper.showPrevious();
					d_ganAnim=AnimationUtils.loadAnimation(this, R.anim.rotate_45_d);
					LinearInterpolator d_ln = new LinearInterpolator(); 
					d_ganAnim.setInterpolator(d_ln);
					d_ganAnim.setFillAfter(true);
					pan_pause.startAnimation(d_ganAnim);
					pause_music.setImageResource(R.drawable.hp_player_btn_play_normal);
					isplay = true;
					iszhuang=true;

			}
			}
			break;
		}
		}

		return super.onTouchEvent(event);

	}
}

