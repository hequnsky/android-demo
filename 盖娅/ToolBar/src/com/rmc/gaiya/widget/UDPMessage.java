package com.rmc.gaiya.widget;


	import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.util.Log;

	/**
	 *  发送广播  将机顶盒IP地址广播出去，  手机控制器来连接
	 * @author Administrator
	 *
	 */
	public class UDPMessage implements Runnable {

	    private static final String TAG = "UDPMessage";

	    private static final int BUFFERLENGTH = 1024; // 缓冲大小

	    private static byte[] receiveBuffer = new byte[BUFFERLENGTH];
	    
	    private static final int POOL_SIZE = 5; // 单个CPU线程池大小
	    private static ExecutorService executor;
	    private Thread receiveUDPThread;
	    
	    private boolean isThreadRunning;
	    
	    private static DatagramSocket UDPSocket;
	    private DatagramPacket receiveDatagramPacket;


	    private static Context mContext;
	    private static UDPMessage instance;
		
	    private UDPMessage() {
	    	 int cpuNums = Runtime.getRuntime().availableProcessors();
	         executor = Executors.newFixedThreadPool(cpuNums * POOL_SIZE); // 根据CPU数目初始化线程池

	    }
	    
	    /**
	     * <p>
	     * 获取UDPSocketThread实例
	     * <p>
	     * 单例模式，返回唯一实例
	     * 
	     * @param paramApplication
	     * @return instance
	     */
	    public static UDPMessage getInstance(Context context) {
	        if (instance == null) {
	            mContext = context;
	            instance = new UDPMessage();
	        }
	        return instance;
	    }
	    
	    /** 建立Socket连接 */
	    public void connectUDPSocket() {
	        try {
	            // 绑定端口
	            if (UDPSocket == null)
	                UDPSocket = new DatagramSocket(4448);
	        	Log.i("顺序",TAG+ " connectUDPSocket() 绑定端口成功");

	            // 创建数据接受包
	            if (receiveDatagramPacket == null)
	                receiveDatagramPacket = new DatagramPacket(receiveBuffer, BUFFERLENGTH);

	            startUDPSocketThread();
	        }
	        catch (SocketException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /** 开始监听线程 **/
	    public void startUDPSocketThread() {//开始监听线程
	        if (receiveUDPThread == null) {
	        	
	            receiveUDPThread = new Thread(this);//run() 就是本类的run方法
	            
	            receiveUDPThread.start();
	        }
	        isThreadRunning = true;
	        Log.i("顺序",TAG+ " startUDPSocketThread() 线程启动成功");
	    }

	    /** 暂停监听线程 **/
	    public void stopUDPSocketThread() {
	        isThreadRunning = false;
	        if (receiveUDPThread != null)
	            receiveUDPThread.interrupt();
	        receiveUDPThread = null;
	        instance = null; // 置空, 消除静态变量引用
	        Log.i("顺序",TAG+ " stopUDPSocketThread() 线程停止成功");
	    }
	    


	    /**
	     * 发送UDP数据包
	     */
	    public void sendUDPdata(final String message) {
	    
	    	executor.execute(new Runnable() {
				@Override
				public void run() {
					connectUDPSocket();
			     // message = (message == null ? "Hello IdeasAndroid!" : message);
			        //服务器监听的端口号
			        int server_port = 4448;
			        
			        Log.i("顺序", "UDP发送数据:"+message);
			        
			        InetAddress targetAddr = null;
			        try {
			        	targetAddr = InetAddress.getByName("255.255.255.255");
			            
			        } catch (UnknownHostException e) {
			            e.printStackTrace();
			        }
			        int msg_length = message.length();
			        byte[] messageByte = message.getBytes();
			        DatagramPacket p = new DatagramPacket(messageByte, msg_length, targetAddr,
			                server_port);
			        
			        try {
			        	UDPSocket.send(p);
			            
			        } catch (Exception e) {
			        	Log.i("顺序", "异常信息必须看...........");
			            e.printStackTrace();
			        }
					
				}
			});
	    	

	    }


	    
	    
	    @Override
	    public void run() {
	        while (isThreadRunning) {
	            try {
	            	//接收数据
	                UDPSocket.receive(receiveDatagramPacket);
	            }
	            catch (IOException e) {
	                isThreadRunning = false;//设置监听线程没有开启
	                receiveDatagramPacket = null;
	                if (UDPSocket != null) {
	                    UDPSocket.close();
	                    UDPSocket = null;
	                }
	                receiveUDPThread = null;
	               
	                e.printStackTrace();
	                break;
	            }

	            if (receiveDatagramPacket.getLength() == 0) {
	            	Log.e("顺序", TAG+ "无法接收UDP数据或者接收到的UDP数据为空");
	                continue;
	            }
	         /**将接收到的字节数据转成字符串*/
	            String UDPListenResStr = "";
	            
	            try {
	                UDPListenResStr = new String(receiveBuffer, 0, receiveDatagramPacket.getLength(),
	                        "UTF-8");
	                Log.e("代码", TAG+ UDPListenResStr);
	            }
	            catch (UnsupportedEncodingException e) {
	            	Log.e("顺序", TAG+" 系统不支持GBK编码");
	            }

	           //主要看下拉刷新时 是哪个手机操作的  也就是下拉刷新时由哪一端发送数据
	            String senderIp = receiveDatagramPacket.getAddress().getHostAddress();

	            // 每次接收完UDP数据后，重置长度。否则可能会导致下次收到数据包被截断。
	            if (receiveDatagramPacket != null) {
	                receiveDatagramPacket.setLength(BUFFERLENGTH);
	            }

	        }

	        receiveDatagramPacket = null;
	        if (UDPSocket != null) {
	            UDPSocket.close();
	            UDPSocket = null;
	        }
	        receiveUDPThread = null;

	    }


	}

