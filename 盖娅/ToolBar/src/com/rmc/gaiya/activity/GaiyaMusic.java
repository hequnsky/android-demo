package com.rmc.gaiya.activity;



import com.rmc.gaiya.adapter.Myadpater2;
import com.rmc.gaiya.tools.ImgTools;
import com.rmc.gaiya.utils.CommonUtils;
import com.rmc.gaiya.utils.NetImageViewer;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
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

public class GaiyaMusic extends Activity implements OnClickListener, OnTouchListener {
	ImgTools imgTools=new ImgTools();
	private ImageView pause_music,changpian, back_music, add_nature_sound, random, pre_music, next, playlist,like,img_yuan;
	private boolean  isplay=true;
	private ListView muisc_list_info;
	private ListView nature_list;
	private PopupWindow mpopupWindow;
	private boolean iszhuang=true;
	boolean islike=true;
	private Animation operatingAnim, ganAnim,d_ganAnim;
	/*∂®“ÂAlertDialogµƒ ”Õº*/
	private View View_custom;
	int selectedFruitIndex = 0;
	private ImageView pan_pause;
    ViewFlipper  flipper;
    private ListView lv;
	float startX;
	int  urlarrayposition=0;
	String pathArray[]=new  String[]{"http://images.gaiavoicelight.com/test/∏«Ê´“Ù¿÷/3π‚“Ù.jpg @1e_1c_0o_0l_304sh_300h_300w_90q.src"
			,
						"http://images.gaiavoicelight.com/test/∏«Ê´“Ù¿÷/1∑…‘Ω.jpg@1e_1c_0o_0l_304sh_300h_300w_90q.src"
			,
						"http://images.gaiavoicelight.com/test/∏«Ê´“Ù¿÷/14πƒ∂Ø.jpg"
						+ "@1e_1c_0o_0l_304sh_300h_300w_90q.src"
			,
						"http://images.gaiavoicelight.com/test/∏«Ê´“Ù¿÷/10ª∂«ÏÏ¯–ƒ.jpg@1e_1c_0o_0l_304sh_300h_300w_90q.src"
			};
	String urlArray[]=new String[]{"http://images.gaiavoicelight.com/test/∏«Ê´“Ù¿÷/7∫œ“ª÷Æ¬√¥ø“Ù¿÷.jpg",
			"http://images.gaiavoicelight.com/test/∏«Ê´“Ù¿÷/1∑…‘Ω.jpg",
			"http://images.gaiavoicelight.com/test/∏«Ê´“Ù¿÷/14πƒ∂Ø.jpg",
			"http://images.gaiavoicelight.com/test/∏«Ê´“Ù¿÷/10ª∂«ÏÏ¯–ƒ.jpg","http://img.tupianzj.com/uploads/allimg/160103/9-160103160918.jpg","http://img.tupianzj.com/uploads/allimg/160103/9-160103160919.jpg"};



	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//»•µÙ±ÍÃ‚¿∏ 
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
		flipper.setOnTouchListener(this);
//		showImage(urlArray[urlarrayposition]);
		
//	    showPanImage(pathArray[urlarrayposition]);
	}
	

	private void initId() {

		img_yuan=(ImageView) findViewById(R.id.img_yuan);
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
			playMusic();
			break;
		case R.id.back_music:
			finish();
			this.overridePendingTransition(0, R.anim.activity_close);

			break;
		case R.id.add_nature_sound:
			showAlertDialog();


			break;
		case R.id.pre_music:
			if (CommonUtils.isFastDoubleClick()) {
				return;
				
			}else{
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
			
			urlarrayposition--;
			if (isLastArrayPosition()) {						
				showImage(urlArray[urlarrayposition]);
//				showPanImage(pathArray[urlarrayposition]);
			}
			}
			break;
		case R.id.next:
			if (CommonUtils.isFastDoubleClick()) {
				return;
			}else{
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
			urlarrayposition++;
			if (isLastArrayPosition()) {						
				showImage(urlArray[urlarrayposition]);
//				showPanImage(pathArray[urlarrayposition]);
			}
			}
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
	
	private void playMusic() {
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
		
	}
/**
 * ÃÌº”◊‘»ª…˘
 */

	private void showAlertDialog() {
	    
	    final String[] arrayFruit = new String[] { "Õ‹Ω–", "≤ıΩ–", "ƒÒΩ–", "º¶Ω–" };
	    Dialog alertDialog = new AlertDialog.Builder(this).
	    	     setTitle("ƒ„–Ë“™ÃÌº”ƒƒ÷÷◊‘»ª…˘£ø").
	    	     setIcon(R.drawable.zirantiantang)
	    	     .setSingleChoiceItems(arrayFruit, 0, new DialogInterface.OnClickListener() {
	    	  
	    	      @Override
	    	      public void onClick(DialogInterface dialog, int which) {
	    	       selectedFruitIndex = which;
	    	      }
	    	     }).
	    	     setPositiveButton("»∑»œ", new DialogInterface.OnClickListener() {

	    	     @Override
	    	      public void onClick(DialogInterface dialog, int which) {
	    	       Toast.makeText(GaiyaMusic.this, arrayFruit[selectedFruitIndex], Toast.LENGTH_SHORT).show();
	    	      }
	    	     }).
	    	     setNegativeButton("»°œ˚", new DialogInterface.OnClickListener() {

	    	     @Override
	    	      public void onClick(DialogInterface dialog, int which) {
	    	       // TODO Auto-generated method stub
	    	      }
	    	     }).
	    	     create();
	    	   alertDialog.show();
	    	  }
	/*
	 * µØ≥ˆ≤•∑≈¡–±Ì    	 
	 */
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
				View mark=view.findViewById(R.id.music_list_selected);
				mark.setVisibility(View.VISIBLE);
				mpopupWindow.dismiss();
			}
			
		});
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int action=event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN: {
			startX=event.getX();
			break;
		}
		
		case MotionEvent.ACTION_UP:
		{
			if(CommonUtils.isFastDoubleTouch()){}else{
			//œÚ”“ª¨∂Ø
			if(event.getX()-startX>120)
				
			{
				if (operatingAnim!=null) {
				 flipper.clearAnimation();}									
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
					urlarrayposition--;
					if (isLastArrayPosition()) {
//						showPanImage(pathArray[urlarrayposition]);
						showImage(urlArray[urlarrayposition]);
					}
					
					
			}
			//œÚ◊Ûª¨∂Ø
			if(startX-event.getX()>120)
			{
				if (operatingAnim!=null) {
					flipper.clearAnimation();					
				}
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
					urlarrayposition++;
					if (isLastArrayPosition()) {						
						showImage(urlArray[urlarrayposition]);
//						showPanImage(pathArray[urlarrayposition]);
					}
					
			}
		}
	
		}
		}
		return true;
   }
	

public void  showImage(String url){
	NetImageViewer netImageViewer = new NetImageViewer() {

		@Override
		public void onImageLoaded(Bitmap bitmap) {
			Drawable drawable =new BitmapDrawable(bitmap);
//			Bitmap bmp = ImageUtils.drawableToBitmap(drawable) ;
			LinearLayout rootView=(LinearLayout) findViewById(R.id.rootview);
			rootView.setBackgroundDrawable(drawable);	
		}
		
	};
	netImageViewer.downloadImage(url);

}

public void showPanImage(String path){
	imgTools.getImgFromNetByUrl(path,img_yuan,R.drawable.gaiyamusic);
	
}

public boolean  isLastArrayPosition(){
	if (urlarrayposition<0) {
		urlarrayposition=(urlArray.length-1);
		
	}
	if (urlarrayposition>(urlArray.length-1)) {
		urlarrayposition=0;
		
	}
	return true;	
	
}
}

