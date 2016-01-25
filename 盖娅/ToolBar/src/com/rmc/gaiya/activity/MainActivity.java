package com.rmc.gaiya.activity;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rmc.gaiya.bean.Bee;
import com.rmc.gaiya.bean.GV;
import com.rmc.gaiya.tools.ImgTools;
import com.rmc.gaiya.widget.CustomListView;
import com.rmc.gaiya.widget.CustomerViewPage;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;




public class MainActivity extends ActionBarActivity{
	ImgTools imgTools=new ImgTools();
	OkHttpClient client = new OkHttpClient();
	Response response = null;
   String Result=null;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	ImageView mian_play;
    boolean ispause=false;
    boolean control=true;
	private CustomerViewPage viewPage;
	private List<View> views;
	private LinearLayout drawr_cancel;
	private LinearLayout drawr_about;
	private LinearLayout lookip;
	private LinearLayout iplist;
	private LinearLayout collect;
    LinearLayout li;
    DrawerLayout drawer ;
    private CustomListView mListView;
    private List<Bee> listdata=new ArrayList<>();
    private int  currenposition;
    List<com.rmc.gaiya.bean.Info> InfoBean = new ArrayList<com.rmc.gaiya.bean.Info>();
    List<com.rmc.gaiya.bean.MediaList> MediaBean=new ArrayList<com.rmc.gaiya.bean.MediaList>();
	List<com.rmc.gaiya.bean.ModeList> ModeBean= new ArrayList<com.rmc.gaiya.bean.ModeList>();
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Toolbar boolBar = (Toolbar)findViewById(R.id.toolbar);
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
		boolBar.setTitle("盖娅光音");
		boolBar.setTitleTextColor(getResources().getColor(R.color.title_color));
		setSupportActionBar(boolBar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, boolBar,R.string.drawer_open, R.string.drawer_close);
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		initID();
		initViewpager();
		initListView();
		initOnClickListener();
		
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	


	private void initListView() {
		new Pay().execute(com.rmc.gaiya.common.Config.HomeURL);
				
	}


	private void initOnClickListener() {
	/**
	 * 进入音乐播放器事件 
	 * 
	 * */	

     li.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),GaiyaMusic.class);
				startActivity(intent);
				  MainActivity.this.overridePendingTransition(R.anim.activity_open,0);  
			}
		});
     /**
      * 底部播放按钮的点击事件
      * */
     mian_play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ispause) {
					mian_play.setImageResource(R.drawable.hp_miniplayer_btn_pause_normal);
					ispause=false;
					
				}else{
					mian_play.setImageResource(R.drawable.hp_miniplayer_btn_play_normal);
					ispause=true;
				}
			}
		});
     iplist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDrawerLayout.closeDrawer(Gravity.LEFT);
				Intent device=new Intent(MainActivity.this,DeviceActivity.class);
				startActivity(device);
			}
		});
		collect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDrawerLayout.closeDrawer(Gravity.LEFT);
				Intent collect=new Intent(MainActivity.this,GaiyaListInfo.class);
				startActivity(collect);
			}
		});
		drawr_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDrawerLayout.closeDrawer(Gravity.LEFT);
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);// 返回登入界面
			}
		});
		drawr_about.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDrawerLayout.closeDrawer(Gravity.LEFT);
				Intent intent = new Intent(getApplicationContext(), GaiYaAbout.class);
				startActivity(intent);
			}
		});
		lookip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDrawerLayout.closeDrawer(Gravity.LEFT);
				Intent i=new Intent(MainActivity.this,LookFor_IPActivity.class);
				startActivity(i);
				
			}
		});
     
     
     
	}


	private void initID() {
		mListView=(CustomListView) findViewById(R.id.mian_listview);
		li=(LinearLayout) findViewById(R.id.buttom);
		mian_play=(ImageView) findViewById(R.id.main_paly);
		iplist=(LinearLayout) findViewById(R.id.ip_list);
		drawr_cancel = (LinearLayout) findViewById(R.id.drawr_cancel);
		drawr_about = (LinearLayout) findViewById(R.id.drawr_about);
		lookip = (LinearLayout) findViewById(R.id.lookip);
		collect=(LinearLayout) findViewById(R.id.collect);
	}


	/**
	 * 
	 * 初始化广告轮播条
	 **/
	private void initViewpager() {
		viewPage = (CustomerViewPage) findViewById(R.id.vp);
		views = new ArrayList<View>();
		ImageView imageView1 = new ImageView(this);
		ImageView imageView2 = new ImageView(this);
		ImageView imageView3 = new ImageView(this);
		ImageView imageView4 = new ImageView(this);
		ImageView imageView5 = new ImageView(this);
		ImageView imageView6 = new ImageView(this);
		ImageView imageView7 = new ImageView(this);
		imageView1.setBackgroundResource(R.drawable.vp1);
		views.add(imageView1);
		imageView2.setBackgroundResource(R.drawable.vp2);
		views.add(imageView2);
		imageView3.setBackgroundResource(R.drawable.vp3);
		views.add(imageView3);
		imageView4.setBackgroundResource(R.drawable.vp4);
		views.add(imageView4);
		imageView5.setBackgroundResource(R.drawable.vp5);
		views.add(imageView5);
		imageView6.setBackgroundResource(R.drawable.vp6);
		views.add(imageView6);
		imageView7.setBackgroundResource(R.drawable.vp7);
		views.add(imageView7);
		viewPage.setViewPageViews(views);
	}
//-------------------------------------------------------------------------------------------------//	
	
	public class GridViewAdapter extends BaseAdapter {
		private List<GV> data;
		public GridViewAdapter(List<GV> data) {
			this.data=data;
	
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}


		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			  final ViewHolder holder;
			if (convertView==null) {
				holder=new ViewHolder();
				convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.gridvieitem,
						null);
				holder.gv_img=(ImageView) convertView.findViewById(R.id.gv_img);
				holder.gv_Title=(TextView) convertView.findViewById(R.id.gv_name);
				holder.control=(ImageView) convertView.findViewById(R.id.item_play);
				holder.child_item=(FrameLayout) convertView.findViewById(R.id.child_item);
				convertView.setTag(holder);
				
			}
			else {
				holder = (ViewHolder) convertView.getTag();	
			}
			
			holder.gv_Title.setText(data.get(position).getGv_title());
			String gv_url=data.get(position).getGv_imgs();
			holder.gv_img.setTag(gv_url);
			imgTools.getImgFromNetByUrl(gv_url,holder.gv_img,R.drawable.cai1);
			holder.control.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					if (control) {
						holder.control.setImageResource(R.drawable.hp_miniplayer_btn_pause_normal);;
						control=false;
					}else{
						holder.control.setImageResource(R.drawable.hp_miniplayer_btn_play_normal);;
						control=true; 
						
					}
					currenposition=position;
					
				
					
				}
			});
                  holder.child_item.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent in=new Intent(getApplicationContext(),GaiyaListInfo.class);
					startActivity(in);
				}
			});
			
			return convertView;
		}

	
	
	
	}	
	public class ListviewAdapter extends BaseAdapter {
	


		@Override
		public int getCount() {
	        return listdata.size();
		}

		@Override
		public Object getItem(int position) {
		   return listdata.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressWarnings("unused")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ListHolder viewholder = null ;
			if (viewholder == null) {
				viewholder = new ListHolder();
				convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_listitem, null);
				viewholder.more_more=(TextView) convertView.findViewById(R.id.more_more);
				viewholder.list_imgs=(ImageView) convertView.findViewById(R.id.list_imgs);
				viewholder.list_title=(TextView) convertView.findViewById(R.id.list_title);
				viewholder.gridView=(GridView) convertView.findViewById(R.id.gridView);
				
				convertView.setTag(viewholder);
			} else {
				viewholder = (ListHolder) convertView.getTag();
			}
				viewholder.list_title.setText( listdata.get(position).getList_title());
				String url=listdata.get(position).getList_imgs();
				viewholder.list_imgs.setTag(url);
				imgTools.getImgFromNetByUrl(url,viewholder.list_imgs,R.drawable.cai1);
				viewholder.gridView.setAdapter(new GridViewAdapter(listdata.get(position).getGv()));
				viewholder.more_more.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent inte=new Intent(getApplicationContext(),GaiYaAbout.class);
						startActivity(inte);
									
					}
				});
			return convertView;
		}

	
	}
	
   class ListHolder {
		TextView  more_more;
		TextView list_title;
		ImageView list_imgs;
		GridView gridView;
	}
	  class ViewHolder{
	
		 TextView gv_Title;
		 ImageView gv_img;
		 ImageView control;
		 FrameLayout child_item;
		
	}
	  class Pay extends AsyncTask<String, Void, String> {

			@Override
			protected String doInBackground(String... params) {

				Request request = new Request.Builder().url(com.rmc.gaiya.common.Config.HomeURL).build();

				try {
					response = client.newCall(request).execute();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (response.isSuccessful()) {

					try {
						//取得返回来的json
						Result = response.body().string();
						JSONObject jsonObject = new JSONObject(Result);
						String Message=jsonObject.getString("Message");
						JSONObject jsonObjec=new JSONObject(Message);
						//得到返回码200，成功
						int code=jsonObjec.getInt("State");
						Log.i("55", code+"");
						if (code==200) {
							String Param=jsonObjec.getString("Param");
							Log.i("59", Param);
							JSONObject jsonObje=new JSONObject(Param);
							String content=jsonObje.getString("Content");
							JSONArray JSONArray=new JSONArray(content);
							Log.i("62", content);
							
							//第一层
							
							JSONObject jsonOb=null;
							for (int i = 0; i < JSONArray.length(); i++) {
								jsonOb = JSONArray.getJSONObject(i);
								com.rmc.gaiya.bean.Info info=new com.rmc.gaiya.bean.Info();
								String Id=String.valueOf(jsonOb.getInt("Id"));
								String Name=jsonOb.getString("Name");
								String ImgUrl=jsonOb.getString("ImgUrl");
								String SortId=String.valueOf(jsonOb.getInt("SortId"));
								Log.i("74", SortId);
								info.setId(Id);
								info.setName(Name);
								info.setImgUrl(ImgUrl);
								info.setSortId(SortId);
								InfoBean.add(info);		
								JSONArray Mearr=jsonOb.getJSONArray("MediaList");							
								Log.i("83", Mearr+"");
								JSONArray Moarr=jsonOb.getJSONArray("ModeList");
								Log.i("85", Moarr+"");
								//MediaList第二层
								
									if (Mearr.length()>0&&Mearr!=null) {
									for (int j = 0; j < Mearr.length(); j++) {
										JSONObject js=Mearr.getJSONObject(j);
										com.rmc.gaiya.bean.MediaList media=new com.rmc.gaiya.bean.MediaList();
										String Mo_Id=String.valueOf(js.getInt("Id"));
										String Mo_Name=js.getString("Name");
										String Mo_ImgUrl=js.getString("ImgUrl");
										String MediaSize=Long.toString(js.getLong("MediaSize"));
										Log.i("94", MediaSize);
										String Duration=String.valueOf(js.getInt("Duration"));
										String MediaType=String.valueOf(js.get("MediaType"));
										String IsLike=String.valueOf(js.getInt("IsLike"));
										String MarkUp=js.getString("MarkUp");
										Log.i("96", IsLike);
										media.setDuration(Duration);
										media.setId(Mo_Id);
										media.setImgUrl(Mo_ImgUrl);
										media.setIsLike(IsLike);
										media.setDuration(MediaType);
										media.setName(Mo_Name);
										media.setMediaSize(MediaSize);
										media.setMarkUp(MarkUp);
										MediaBean.add(media);	
									}
									}
									//ModeList第二层
								
									if (Moarr.length()>0&&Moarr!=null) {
									for (int k = 0; k < Moarr.length(); k++) {
										JSONObject jso=Moarr.getJSONObject(k);
										com.rmc.gaiya.bean.ModeList mode=new com.rmc.gaiya.bean.ModeList();
										String M_Id=String.valueOf(jso.getInt("Id"));
										Log.i("113", M_Id);
										String M_Name=jso.getString("Name");
										Log.i("113", M_Name);
										String M_ImgUrl=jso.getString("ImgUrl");
										String M_SortId=String.valueOf(jso.getInt("SortId"));
										mode.setId(M_Id);;
										mode.setImgUrl(M_ImgUrl);
										mode.setName(M_Name);
										mode.setSortId(M_SortId);
										ModeBean.add(mode);
									}
								}
								
							}	
						
						}	

					} catch (IOException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}

				try {
					throw new IOException("Unexpected code " + response);
				} catch (IOException e) {
					e.printStackTrace();
				}					

				return Result;
			}
			@Override
			protected void onPostExecute(String Result) {
				super.onPostExecute(Result);
				int i;
				for(i=0;i<InfoBean.size();i++){
					Bee data=new Bee();
					data.setList_title(InfoBean.get(i).getName());
					data.setList_imgs(InfoBean.get(i).getImgUrl());					
					List<GV> grid=new ArrayList<GV>();
					if (i==0) {
						for(int k=0;k<MediaBean.size();k++){						
							GV g=new GV();
							g.setGv_title(MediaBean.get(k).getName());
							g.setGv_imgs(MediaBean.get(k).getImgUrl());						
							grid.add(g);
						}
					}
					if (i==1) {
						for(int m=0;m<ModeBean.size();m++){
							GV g=new GV();
							g.setGv_title(ModeBean.get(m).getName());
							g.setGv_imgs(ModeBean.get(m).getImgUrl());						
							grid.add(g);
					
					}	
					
					}
					data.setGv(grid);
					
					listdata.add(data);
				}
			 
				 mListView.setAdapter(new ListviewAdapter());
		
				
			}
		}
}
