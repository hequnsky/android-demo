package com.djf.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.djf.activity.CurrentLoactionMapActivity.MyLocationListenner;
import com.djf.adapter.GridViewAdapter;
import com.djf.callback.ResultCallback;
import com.djf.constants.HttpConstants;
import com.djf.db.SharedPreferenceDb;
import com.djf.moodinn.R;
import com.djf.upload.HttpHelper;
import com.djf.upload.HttpHelper.HttpStringHandler;
import com.djf.upload.HttpStringResult;
import com.djf.util.HttpUtil;
import com.djf.util.LogUtil;
import com.djf.util.SdCardUtil;
import com.djf.util.TimeUtil;
import com.djf.view.TitleBar;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class WriteMoodActivity extends BaseActivity implements
		OnItemClickListener,OnClickListener {

	private TitleBar titleBar;
	private GridView gridView;
	private GridViewAdapter gridAdapter;
	List<Bitmap> allImages = new ArrayList<Bitmap>();
	List<String> imageUrl=new ArrayList<String>();
	private boolean selectFlag=false;//选择的状态
	private Dialog dialog;
	private TextView showCurrentPosition;

	private EditText etMood;
	
	String fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
	
	
	// 百度地图 定位相关
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationMode mCurrentMode;
	BitmapDescriptor mCurrentMarker;

	MapView mMapView;
	BaiduMap mBaiduMap;

	// UI相关
	OnCheckedChangeListener radioButtonListener;
	Button requestLocButton;
	boolean isFirstLoc = true;// 是否首次定位
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.writemood);
		initViews();

	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		titleBar = (TitleBar) findViewById(R.id.titleBar);
		titleBar.showLeftImageAndRightStr("写心情", "发表", getResources()
				.getDrawable(R.drawable.app_back), new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		}, new OnClickListener() {

			@SuppressWarnings("static-access")
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if(!etMood.getText().toString().trim().isEmpty()){
					
					HttpHelper h=new HttpHelper();
					HashMap<String,String> p=new HashMap<String, String>();
					p.put("diarycontent", etMood.getText().toString().trim());
					p.put("userid", new SharedPreferenceDb(WriteMoodActivity.this).getUserId());
					
					List<File> allFile=new ArrayList<File>();
					for(int i=0;i<imageUrl.size();i++){
						File f=new File(imageUrl.get(i));
						allFile.add(f);
					}
					
					HttpStringHandler results=new HttpStringHandler() {
						
						@Override
						public void handleResponse(HttpStringResult result) {
							// TODO Auto-generated method stub
							Toast.makeText(WriteMoodActivity.this, result.result, Toast.LENGTH_LONG).show();
							close();
						}
					};
					
					/**
					 * 调用有图片的接口
					 */
					if(allFile.size()>0){
						initProgressDialog();
						h.asyncFormPost(HttpConstants.HTTP_WRITE_MOOD, p, null, "file", allFile, results);
					}
					/**
					 * 调用没有图片的接口
					 */
					else{
						
						List<NameValuePair> allParams=new ArrayList<NameValuePair>();
						allParams.add(new BasicNameValuePair("diarycontent", etMood.getText().toString()));
						allParams.add(new BasicNameValuePair("userid", new SharedPreferenceDb(WriteMoodActivity.this).getUserId()));
						initProgressDialog();
						HttpUtil.doPost(HttpConstants.HTTP_WRITE_MOOD_NO_IMAGE, allParams, new ResultCallback() {
							
							@Override
							public void getReslt(String result) {
								// TODO Auto-generated method stub
								Toast.makeText(WriteMoodActivity.this, result, Toast.LENGTH_LONG).show();
								close();
							}
						});
						
					}
					
					
					
				}else{
					Toast.makeText(WriteMoodActivity.this, "亲写点什么吧!", Toast.LENGTH_LONG).show();
				}
			}
		});
		 if(!"0".equals(String.valueOf(new SharedPreferenceDb(WriteMoodActivity.this).getChangeTheme()))){
			 titleBar.setBackgroundColor(new SharedPreferenceDb(WriteMoodActivity.this).getChangeTheme());
		 }else{
			 titleBar.setBackgroundColor(getResources().getColor(R.color.red));
		 }
		 
		 
		//百度地图
		mCurrentMode = LocationMode.NORMAL;

		// 地图初始化
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setAddrType("all");
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		
		gridView = (GridView) findViewById(R.id.gridView);
		etMood=(EditText)findViewById(R.id.etMood);
		showCurrentPosition=(TextView)findViewById(R.id.showCurrentPosition);
		showCurrentPosition.setOnClickListener(this);
		
		
		allImages.add(BitmapFactory.decodeResource(getResources(), R.drawable.icon_addpic_focused));
		gridAdapter = new GridViewAdapter(WriteMoodActivity.this, allImages);
		gridView.setAdapter(gridAdapter);
		gridView.setOnItemClickListener(this);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		IntentFilter intentFilter=new IntentFilter();
		intentFilter.addAction("delImage");
		registerReceiver(delBroadCast, intentFilter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		if (arg2 == allImages.size() - 1) {
			if (allImages.size() <= 6) {
				new SelectPopuWindow(WriteMoodActivity.this, gridView, arg2);
			} else {
				Toast.makeText(WriteMoodActivity.this, "照片不能超过6张",
						Toast.LENGTH_LONG).show();
			}

		}else{
			Intent intent=new Intent();
			intent.setClass(WriteMoodActivity.this, BrowseImageViewActivity.class);
			intent.putStringArrayListExtra("imageUrl", (ArrayList<String>) imageUrl);
			intent.putExtra("position", arg2);
			startActivity(intent);
			//Toast.makeText(WriteMoodActivity.this, arg2+""+"集合的长度:"+allImages.size(), Toast.LENGTH_LONG).show();
		}
	}

	public class SelectPopuWindow extends PopupWindow {

		private Context context;
		private int ReuestCode;

		public SelectPopuWindow(Context mContext, View parent,
				final int requestCode) {
			this.ReuestCode = requestCode;
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
			bt1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					/*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, requestCode + 1);*/
					
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
					
					if(SdCardUtil.checkSdCard()==true){
						Intent intent = new Intent(Intent.ACTION_PICK);  
					       intent.setType("image/*");//相片类型  
					       startActivityForResult(intent, 7); 
					}else{
						Toast.makeText(WriteMoodActivity.this, "SD卡不存在", Toast.LENGTH_LONG).show();
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

	public void getImaged(Intent data){
		
		Uri uri = null;
		if (data != null) {
			uri = data.getData();
			System.out.println("Data");
		}else{
			uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),fileName));
		}
		
		/*Bundle bundler=data.getExtras();
		Bitmap bitmap = (Bitmap) bundler.get("data");
		Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(bitmap, 88, 88); 
		allImages.add(allImages.size()-1,resizeBmp);*/
		
		
		cropImage(uri, 450, 450, 8);
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (SdCardUtil.checkSdCard() == true) {
			if (requestCode == 1  && resultCode==RESULT_OK) {
				getImaged(data);
			} else if (requestCode == 2 && resultCode==RESULT_OK) {
				getImaged(data);
			} else if (requestCode == 3 && resultCode==RESULT_OK) {
				getImaged(data);
			} else if (requestCode == 4 && resultCode==RESULT_OK) {
				getImaged(data);
			} else if (requestCode == 5 && resultCode==RESULT_OK) {
				getImaged(data);
			} else if (requestCode == 6 && resultCode==RESULT_OK) {
				getImaged(data);
			}else if(requestCode==7 && resultCode==RESULT_OK){
				ContentResolver resolver = getContentResolver();
				Uri uri=data.getData();
				//显得到bitmap图片
				try {
					
					cropImage(uri, 450, 450, 8);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        
			}/**
				裁剪完图片
			*/
			else if(requestCode==8 && resultCode==RESULT_OK){
				
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
					
					Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(photo, 88, 88); 
					allImages.add(allImages.size()-1,resizeBmp);
					gridAdapter.notifyDataSetChanged();
					saveImageToFile(photo);
				}
			}

		} else {
			Toast.makeText(WriteMoodActivity.this, "SD卡不存在,请检查您的sd卡",
					Toast.LENGTH_LONG).show();
		}
	}
	
	public void saveImageToFile(Bitmap bitmap){
		
		FileOutputStream fos = null;
		String fileName=SdCardUtil.getSdPath()+SdCardUtil.FILEDIR+"/"+SdCardUtil.FILEPHOTO+"/"+TimeUtil.getCurrentTimeForImage();
		LogUtil.i("图像的文件路径："+fileName);
		if(!fileName.isEmpty()){
			imageUrl.add(fileName);
		}
		try {
			fos=new FileOutputStream(fileName);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				fos.flush();
				if(bitmap!=null){
					bitmap.recycle();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	BroadcastReceiver delBroadCast=new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			String action=arg1.getAction();
			if(action.equals("delImage")){
				int position=arg1.getExtras().getInt("position");
				Log.i("广播收到的删除下标为:", position+"");
				imageUrl.remove(position);
				allImages.remove(position);
				gridAdapter.notifyDataSetChanged();
				
				for(int i=0;i<imageUrl.size();i++){
					LogUtil.i("图片:"+imageUrl.get(i));
				}
			}
					
		}
	};
	

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==showCurrentPosition){
			
			SkipActivityforClass(WriteMoodActivity.this, CurrentLoactionMapActivity.class);
		}
	}
	
	
	
	/**
	 * 百度地图   定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
			}
			
			if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				String addrStr = location.getAddrStr();
				showCurrentPosition.setText(addrStr);
			}
		}

		@Override
		public void onReceivePoi(BDLocation poiLocation) {
		}
	}
	
	
	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(delBroadCast);
		//地图
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
	}
}
