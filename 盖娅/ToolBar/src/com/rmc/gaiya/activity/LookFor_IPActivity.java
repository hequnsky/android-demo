package com.rmc.gaiya.activity;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.google.gson.Gson;
import com.rmc.gaiya.adapter.IPAdapter;
import com.rmc.gaiya.bean.IPBean;
import com.rmc.gaiya.impl.TCPclient;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LookFor_IPActivity extends Activity implements android.view.View.OnClickListener{
	Gson gson=new  Gson();
    //�շ���ͬһ��socket��ֻ������ʱʵ����һ��
   public static DatagramSocket socketUDP;
   public static DatagramSocket resUDP;
    private Thread    thread=null;     //�����߳�
	private Thread    th_send=null;    //�����߳�
	//����ͷ���˶�Ӧ
	private int       portRemoteNum=4448;
	private int       portLocalNum=4448;	
	private String    addressIP="255.255.255.255";
	private String    sendMessage="{\"Command\":\"HelloGaia\"}";
	private String    revData;
	private boolean   flag=true;
	int count=0;
	Timer mTimer=null ;
	Button btooo;
	EditText etooo;
	
	ListView list_ip;
	IPAdapter adapter;
	//����list�������
	List<IPBean> ip = new ArrayList<IPBean>();
	TCPclient clinet=new TCPclient(LookFor_IPActivity.this);
			
	
	
	
	boolean issuccess=true;
	Context mcontext;
	Timer timer;
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
				String tv= clinet.getreceiverData();
				if (tv!=null) {
					etooo.append(tv);
				}
				
			   if(SVProgressHUD.isShowing(mcontext)){
	                SVProgressHUD.dismiss(mcontext);
	                if (ip.size()>0) {
	                
	                	SVProgressHUD.showSuccessWithStatus(LookFor_IPActivity.this, "�������ҳɹ���");
					}else{
						SVProgressHUD.showErrorWithStatus(LookFor_IPActivity.this, "����δ�ҵ��������ԣ�", SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
					}
	            }
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.lookfor_ip);
		 btooo=(Button) findViewById(R.id.ooot);
		try {
			getInstance();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		list_ip=(ListView) findViewById(R.id.list_ip);
		initUDP();
  
		
		 list_ip.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				dialog();
			}
		});
		 btooo.setOnClickListener(this);
   
		
	}
	private void initUDP() {
		 AutoSend();
		 SVProgressHUD.showWithStatus(this, "���ڲ�������	...");
		 timer=new Timer();
		 TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
	
				
				handler.sendEmptyMessage(0);
			}
		};
		 timer.schedule(task, 1000*10);
		
	}
	private void AutoSend() {
		
		 try {
			socketUDP = new DatagramSocket(portLocalNum);
			
			
		} catch (SocketException e) {
		
			e.printStackTrace();
		}
		 // ���������̣߳��ñ��ض˿ڽ���,��Ϊ���ض˿ں�PC���������˿ڵĶ�Ӧ
		 thread = new Thread(revMsg);
		 thread.start();
		 count=0;
		  mTimer = new Timer();
		
		mTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {	
				shut();
				th_send = new Thread(sendMsg);
				th_send.start();
				count++;
				Log.i("INFO", count+"");
			}
		}, 1*500,2*1000);		
	}
	
	
	
	/**
	*�˳���ǰ����
	*
	*/
	public void fanhui(View v){
		socketUDP.close();
		finish();
	}
	
	//�������߳����ݣ����޸Ľ���
    private Handler mhandler = new Handler()
 	{
 		@Override
 		public void handleMessage(Message msg)
 		{
 			 adapter = new IPAdapter(getApplicationContext(), ip);
			 list_ip.setAdapter(adapter);
 		}
 	};  
	private Runnable revMsg = new Runnable() {
    	@Override
    	public void run()
    	{
    		while (flag)
    		{			
    			 
    			byte data[] = new byte[1024];   	
    			DatagramPacket packetR = new DatagramPacket(data, data.length);   		
    			try {
    				  socketUDP.receive(packetR);
    				  revData = new String(packetR.getData(),
    						  packetR.getOffset(),packetR.getLength());
    				  parse(revData);
    				 
    				  
    			      mhandler.sendEmptyMessage(0);
    				} catch (IOException e) {
    					e.printStackTrace();
    				} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}       		
    		}
    	}

	};
	
	
	
	private Runnable sendMsg = new Runnable() {
    	@Override
    	public void run()
    	{
    		sendData();
    	}
		
	};
	
    public void shut(){
    	if (count>=4) {
    	    flag=false;//�����߳�
    		mTimer.cancel();
    	
    		
		}
    }
    /**
     * ����������������
     * @throws JSONException 
     * 
     * */

	protected void parse(String revData) throws JSONException {
		JSONObject jsonObj = new JSONObject(revData);
		String  fanhui=jsonObj.getString("Command");
		if (fanhui.equals("HelloGaia")) {		   
		}else if(fanhui.equals("HostSate")){
			IPBean iplist=new IPBean();
			 JSONObject obj=jsonObj.getJSONObject(	"Param");
			 iplist.setHostMac(obj.getString("HostMac"));
			 iplist.setHostName(obj.getString("HostName"));
			 iplist.setIp(obj.getString("ip"));
			 iplist.setIsConneted(obj.getString("IsConneted"));
			 ip.add(iplist);		
		}	
	}

		
		
		
	
	protected void sendData() {
		
		try {
		    InetAddress serverAddress = InetAddress.getByName(addressIP);
		      
		    byte data [] = sendMessage.getBytes(); 
		    DatagramPacket packetS = new DatagramPacket(data,
		    		data.length,serverAddress,portRemoteNum);	
		    //�ӱ��ض˿ڸ�ָ��IP��Զ�̶˿ڷ����ݰ�
		    socketUDP.send(packetS);
		    } catch (Exception e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			socketUDP.close();
			finish();
			
			
		}
		return super.onKeyDown(keyCode, event);
	}
	

	//��ȡ������application����
	public static DatagramSocket getInstance() throws SocketException{
		if(socketUDP == null){
			socketUDP = new DatagramSocket();
		}
		return socketUDP;
	}
	protected void dialog() {
		  AlertDialog.Builder builder = new Builder(LookFor_IPActivity.this);
		  builder.setMessage("����������");

		  builder.setTitle("��ʾ");

		  builder.setPositiveButton("ȷ��", new OnClickListener() {

		   @Override
		   public void onClick(DialogInterface dialog, int which) {
		    
             try {
				clinet.conn(ip.get(0).getIp(), 4447+"");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		  });

		  builder.setNegativeButton("ȡ��", new OnClickListener() {

		   @Override
		   public void onClick(DialogInterface dialog, int which) {
		    dialog.dismiss();
		   }
		  });

		  builder.create().show();
		 }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ooot:
			initUDP();
			
			break;

		default:
			break;
		}
		
	}

}
