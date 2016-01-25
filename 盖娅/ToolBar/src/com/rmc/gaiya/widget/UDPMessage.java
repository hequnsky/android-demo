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
	 *  ���͹㲥  ��������IP��ַ�㲥��ȥ��  �ֻ�������������
	 * @author Administrator
	 *
	 */
	public class UDPMessage implements Runnable {

	    private static final String TAG = "UDPMessage";

	    private static final int BUFFERLENGTH = 1024; // �����С

	    private static byte[] receiveBuffer = new byte[BUFFERLENGTH];
	    
	    private static final int POOL_SIZE = 5; // ����CPU�̳߳ش�С
	    private static ExecutorService executor;
	    private Thread receiveUDPThread;
	    
	    private boolean isThreadRunning;
	    
	    private static DatagramSocket UDPSocket;
	    private DatagramPacket receiveDatagramPacket;


	    private static Context mContext;
	    private static UDPMessage instance;
		
	    private UDPMessage() {
	    	 int cpuNums = Runtime.getRuntime().availableProcessors();
	         executor = Executors.newFixedThreadPool(cpuNums * POOL_SIZE); // ����CPU��Ŀ��ʼ���̳߳�

	    }
	    
	    /**
	     * <p>
	     * ��ȡUDPSocketThreadʵ��
	     * <p>
	     * ����ģʽ������Ψһʵ��
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
	    
	    /** ����Socket���� */
	    public void connectUDPSocket() {
	        try {
	            // �󶨶˿�
	            if (UDPSocket == null)
	                UDPSocket = new DatagramSocket(4448);
	        	Log.i("˳��",TAG+ " connectUDPSocket() �󶨶˿ڳɹ�");

	            // �������ݽ��ܰ�
	            if (receiveDatagramPacket == null)
	                receiveDatagramPacket = new DatagramPacket(receiveBuffer, BUFFERLENGTH);

	            startUDPSocketThread();
	        }
	        catch (SocketException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /** ��ʼ�����߳� **/
	    public void startUDPSocketThread() {//��ʼ�����߳�
	        if (receiveUDPThread == null) {
	        	
	            receiveUDPThread = new Thread(this);//run() ���Ǳ����run����
	            
	            receiveUDPThread.start();
	        }
	        isThreadRunning = true;
	        Log.i("˳��",TAG+ " startUDPSocketThread() �߳������ɹ�");
	    }

	    /** ��ͣ�����߳� **/
	    public void stopUDPSocketThread() {
	        isThreadRunning = false;
	        if (receiveUDPThread != null)
	            receiveUDPThread.interrupt();
	        receiveUDPThread = null;
	        instance = null; // �ÿ�, ������̬��������
	        Log.i("˳��",TAG+ " stopUDPSocketThread() �߳�ֹͣ�ɹ�");
	    }
	    


	    /**
	     * ����UDP���ݰ�
	     */
	    public void sendUDPdata(final String message) {
	    
	    	executor.execute(new Runnable() {
				@Override
				public void run() {
					connectUDPSocket();
			     // message = (message == null ? "Hello IdeasAndroid!" : message);
			        //�����������Ķ˿ں�
			        int server_port = 4448;
			        
			        Log.i("˳��", "UDP��������:"+message);
			        
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
			        	Log.i("˳��", "�쳣��Ϣ���뿴...........");
			            e.printStackTrace();
			        }
					
				}
			});
	    	

	    }


	    
	    
	    @Override
	    public void run() {
	        while (isThreadRunning) {
	            try {
	            	//��������
	                UDPSocket.receive(receiveDatagramPacket);
	            }
	            catch (IOException e) {
	                isThreadRunning = false;//���ü����߳�û�п���
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
	            	Log.e("˳��", TAG+ "�޷�����UDP���ݻ��߽��յ���UDP����Ϊ��");
	                continue;
	            }
	         /**�����յ����ֽ�����ת���ַ���*/
	            String UDPListenResStr = "";
	            
	            try {
	                UDPListenResStr = new String(receiveBuffer, 0, receiveDatagramPacket.getLength(),
	                        "UTF-8");
	                Log.e("����", TAG+ UDPListenResStr);
	            }
	            catch (UnsupportedEncodingException e) {
	            	Log.e("˳��", TAG+" ϵͳ��֧��GBK����");
	            }

	           //��Ҫ������ˢ��ʱ ���ĸ��ֻ�������  Ҳ��������ˢ��ʱ����һ�˷�������
	            String senderIp = receiveDatagramPacket.getAddress().getHostAddress();

	            // ÿ�ν�����UDP���ݺ����ó��ȡ�������ܻᵼ���´��յ����ݰ����ضϡ�
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

