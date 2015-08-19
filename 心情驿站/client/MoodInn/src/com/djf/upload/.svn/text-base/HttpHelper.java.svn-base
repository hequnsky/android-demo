package com.djf.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.djf.upload.ProgressMultiPartEntity.ProgressListener;

import android.os.Handler;
import android.util.Log;
import android.util.TimeFormatException;

/**
 * Http请求类
 * @author planet
 *
 */
public class HttpHelper {
	public static final String TAG = "HttpHelper";
	public static final int TIME_OUT = 5000;
//	private static Handler mHandler;
	private static boolean log = true;

	public static final String server_time_out = "服务器连接超时";
	public static final String connect_failed= "网络连接失败";
	public static final String connect_server_failed= "服务器连接失败";
	public static final String please_check_network= "网络未连接";

	public static void init(Handler handler){
		mHandler = handler;
	}

	public static Handler getHandler() {
		return mHandler;
	}
	
	static Handler mHandler=new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			
		};
	};

	/**
	 * Get请求
	 * @param url
	 * @param params 参数
	 * @param httpHandler
	 */
	/*    public static void asyncGet(final String url, final HashMap<String, String> params, final HttpStringHandler httpHandler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpStringResult result = connect(url, params, "GET");
                //Files.getContentUri(volumeName);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpHandler.handleResponse(result);
                    }
                });
            }
        }).start();
    }*/

	/**
	 * 给http参数列表添加权限验证
	 * @param params
	 */
	public static void wrapAuth(Map<String, String> params){
		/*if(ZHApplication.getUser() != null && App.getUser().getUser_ID() != null && params != null){
			params.put("User_ID", App.getUser().getUser_ID());
			String userkey = App.getUser().getUser_LoginName()
					+ App.getUser().getPasswordMD5()
					+"yibaotong_md5";
			params.put("User_Key", MD5.getInstance().getMD5ofStr(userkey).toLowerCase());
		}*/
	}

	/**
	 * 同步Get请求
	 * @param url
	 * @param params 参数
	 * @param httpHandler
	 */
	/*    public static HttpStringResult syncGet(final String url, final HashMap<String, String> params){
        return connect(url, params, "GET");
    }*/

	/**
	 * Get请求读取字符串
	 * @param url
	 * @param params 参数
	 * @param httpHandler
	 */
	public static void asyncGet(final String url, final List<String> pathes, final HashMap<String, String> params,
			final HashMap<String, String> headers, final HttpStringHandler httpHandler){
		new Thread(new Runnable() {
			@Override
			public void run() {
				final HttpStringResult result = getString(url, pathes, params, headers);
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						httpHandler.handleResponse(result);
					}
				});
			}
		}).start();
	}

	/**
	 * Post请求读取字符串
	 * @param url
	 * @param params 参数
	 * @param httpHandler
	 */
	public static void asyncPost(final String url, final HashMap<String, String> params, final HashMap<String, String> headers, final HttpStringHandler httpHandler){
		new Thread(new Runnable() {
			@Override
			public void run() {
				final HttpStringResult result = postString(url, params, headers);
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						httpHandler.handleResponse(result);
					}
				});
			}
		}).start();
	}

	/**
	 * POST请求
	 * @param url
	 * @param params
	 * @param httpHandler
	 */
	/*    public static void asyncPost(final String url, final HashMap<String, String> params, final HttpStringHandler httpHandler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpStringResult result = connect(url, params, "POST");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpHandler.handleResponse(result);
                    }
                });
            }
        }).start();
    }*/

	/**
	 * Form表单请求
	 * @param url
	 * @param params 参数
	 * @param httpHandler
	 */
	/*public static void asyncFormPost(final String url, final HashMap<String, String> params, final HttpHandler httpHandler){
		asyncFormPost(url, params, null, httpHandler);
	}*/

	/**
	 * Form表单请求
	 * @param url
	 * @param params
	 * @param files
	 * @param httpHandler
	 */
	/*public static void asyncFormPost(final String url, final HashMap<String, String> params, final HashMap<String, File> files, final HttpHandler httpHandler){
		asyncFormPost(url, params, files, httpHandler, null);
	}*/

	/**
	 * Form表单请求
	 * @param url
	 * @param params
	 * @param files
	 * @param httpHandler
	 */
	public static void asyncFormPost(final String url, final HashMap<String, String> params, final HashMap<String, String> headers, final HashMap<String, File> files, final HttpStringHandler httpHandler){
		asyncFormPost(url, params, headers, files, httpHandler, null);
	}

	public static void asyncFormPost(final String url, final HashMap<String, String> params, final HashMap<String, String> headers, final String fileFieldName, final List<File> files, final HttpStringHandler httpHandler){
		asyncFormPost(url, params, headers, fileFieldName, files, httpHandler, null);
	}

	/**
	 * Form表单请求
	 * @param url
	 * @param params
	 * @param files
	 * @param httpHandler
	 * @param progressListener
	 */
	/*public static void asyncFormPost(final String url, final HashMap<String, String> params, final HashMap<String, File> files, final HttpStringHandler httpHandler,
			final ProgressListener progressListener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				final HttpStringHandler result = postForm(url, params, files, progressListener);
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						httpHandler.handleResponse(result);
					}
				});
			}
		}).start();
	}*/

	/**
	 * Form表单请求
	 * @param url
	 * @param params
	 * @param files
	 * @param httpHandler
	 * @param progressListener
	 */
	public static void asyncFormPost(final String url, final HashMap<String, String> params, final HashMap<String, String> headers, final HashMap<String, File> files, final HttpStringHandler httpHandler,
			final ProgressListener progressListener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				final HttpStringResult result = postFormString(url, params, headers, files, progressListener);
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						httpHandler.handleResponse(result);
					}
				});
			}
		}).start();
	}

	public static void asyncFormPost(final String url, final HashMap<String, String> params, final HashMap<String, String> headers, final String fileFieldName, final List<File> files, final HttpStringHandler httpHandler,
			final ProgressListener progressListener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				final HttpStringResult result = postFormString(url, params, headers, fileFieldName, files, progressListener);
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						httpHandler.handleResponse(result);
					}
				});
			}
		}).start();
	}

	/*private static void getResult(HttpResult result, String content, int statusCode){
		HttpStringResult strResult = new HttpStringResult();
		strResult.result = content;
		strResult.resCode = statusCode;
		getResult(result, strResult);
	}*/

	public interface HttpStringHandler{
		void handleResponse(HttpStringResult result);
	}

	/**
	 * 发送请求
	 * @param urlStr
	 * @param params
	 * @param json
	 * @param httpMethod
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	/* private static HttpStringResult connect(String sourceUrl, Map<String,String> params, String httpMethod){
        wrapAuth(params);
        HttpStringResult result = new HttpStringResult();
        try {
            String urlStr = new String(sourceUrl);
            if(httpMethod.equals("GET")){
                urlStr+="?";
                if(params != null){
                    Iterator<Entry<String, String>> it = params.entrySet().iterator();
                    while(it.hasNext()){
                        Entry<String,String> entry = it.next();
                        System.out.println(">>key:"+entry.getKey()+" value="+entry.getValue());
                        urlStr+=entry.getKey()+"="+URLEncoder.encode(entry.getValue(), "utf-8")+"&";
                    }
                    params = null;
                }
            }
            Log.i(TAG, "mthod="+httpMethod+" url="+urlStr);
            result.url = urlStr;

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
            connection.setRequestMethod(httpMethod);
            connection.setDoInput(true);
            connection.setConnectTimeout(TIME_OUT); 
            if(params != null){
                Iterator<Entry<String, String>> it = params.entrySet().iterator();
                while(it.hasNext()){
                    Entry<String,String> entry = it.next();
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                    Log.i(TAG, "post param:"+entry.getValue()+"="+entry.getKey());
                }
            }
            connection.connect();
            int resCode = connection.getResponseCode();
            //读数据
            InputStream in = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;    
            while((len=(in.read(buffer))) != -1){    
                baos.write(buffer,0,len);    
            }
            String retStr = new String(baos.toByteArray());
            in.close();
            if(log)
                Log.i(TAG, retStr+" 状态码:"+resCode);
            //getResult(result, retStr, resCode);
        } catch (MalformedURLException e) {
            result.msg = e.getClass().getSimpleName()+" "+e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            if(e instanceof SocketTimeoutException){
                result.msg = server_time_out;
            }else if(e instanceof ConnectException
                    || e instanceof SocketException){
                result.msg = connect_failed;
            }else if(e instanceof FileNotFoundException){
                result.msg = connect_server_failed;
            }else{
                result.msg = e.getClass().getSimpleName()+" "+e.getMessage();
            }
            e.printStackTrace();
        }
        return result;
    }*/

	/**
	 * GET请求字符串
	 * @param sourceUrl
	 * @param params
	 * @param headers http头
	 * @return
	 */
	private static HttpStringResult getString(String sourceUrl,List<String> pathes, Map<String,String> params,
			Map<String, String> headers){
		wrapAuth(params);
		HttpStringResult ret = new HttpStringResult();
		try {
			String urlStr = new String(sourceUrl);
			if(pathes != null){
				for(String s : pathes){
					urlStr+="/"+s;
				}
			}
			urlStr+="?";
			if(params != null){
				Iterator<Entry<String, String>> it = params.entrySet().iterator();
				while(it.hasNext()){
					Entry<String,String> entry = it.next();
					if(entry.getValue() == null) continue;
					urlStr+=entry.getKey()+"="+URLEncoder.encode(entry.getValue(), "utf-8")+"&";
				}
				params = null;
			}

			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setConnectTimeout(TIME_OUT);
			if(headers != null){
				Iterator<Entry<String, String>> it = headers.entrySet().iterator();
				while(it.hasNext()){
					Entry<String,String> entry = it.next();
					Log.i(TAG, "addHeader: "+entry.getKey()+"="+entry.getValue());
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			//conn.setRequestProperty("User-Agent", "bluemobi-restclient-java-1.0");
			//conn.setRequestProperty("Content-Type", ctype);
			//conn.setRequestProperty("Connection", "Keep-Alive");
			//connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			connection.connect();
			int resCode = connection.getResponseCode();
			ret.resCode = resCode;
			//读数据
			InputStream in = connection.getInputStream();
			//ret.setResult(StringUtils.readString(in));
			ret.result = StringUtils.readString(in);
			if(log){
//				LongLog.i(TAG, "请求:"+urlStr+" \n状态码:"+resCode+" "+ret.result);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			ret.msg = e.getLocalizedMessage();
		} catch (IOException e) {
			e.printStackTrace();
			if(e instanceof SocketTimeoutException){
				ret.setErrorMsg(server_time_out);
			}else if(e instanceof ConnectException
					|| e instanceof SocketException){
				ret.setErrorMsg(connect_failed);
			}else if(e instanceof FileNotFoundException){
				ret.setErrorMsg(connect_server_failed);
			}else{
				ret.setErrorMsg(e.getLocalizedMessage());
			}
		}
		return ret;
	}

	/**
	 * Post请求字符串
	 * @param urlStr
	 * @param params
	 * @return
	 */
	public static HttpStringResult postString(String urlStr, Map<String,String> params, Map<String,String> headers){
		wrapAuth(params);
		HttpStringResult ret = new HttpStringResult();
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(urlStr);
			HttpEntity entity;

			ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
			if(params != null){
				Set<String> keys = params.keySet();
				for(Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = i.next();
					if(params.get(key) == null) continue;
					pairs.add(new BasicNameValuePair(key, params.get(key)));
					Log.i(TAG, "postForm params:"+key+"="+params.get(key));
				}
			}
			entity = new UrlEncodedFormEntity(pairs, "utf-8");
			
			if(headers != null){
				Iterator<Entry<String, String>> it = headers.entrySet().iterator();
				while(it.hasNext()){
					Entry<String,String> entry = it.next();
					Log.i(TAG, "addHeader: "+entry.getKey()+"="+entry.getValue());
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}

			httpPost.setEntity(entity);
			Log.i(TAG, urlStr);
			Log.i(TAG, "post总字节数:"+entity.getContentLength());
			HttpResponse response = client.execute(httpPost);
			//获取状态码
			int res = response.getStatusLine().getStatusCode();
			ret.resCode = res;
			HttpEntity responseEntity = response.getEntity();

			InputStream content = responseEntity.getContent();
			//ret.setResult(StringUtils.readString(content));
			ret.result = StringUtils.readString(content);
//			if(log)
//				LongLog.i(TAG, "\n"+ret.result+"\n 状态码:"+res);
		}catch(TimeFormatException e){
			e.printStackTrace();
			ret.setErrorMsg(e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
			if(e instanceof SocketTimeoutException){
				ret.setErrorMsg(server_time_out);
			}else if(e instanceof ConnectException 
					|| e instanceof SocketException){
				ret.setErrorMsg(connect_failed);
			}else if(e instanceof FileNotFoundException){
				ret.setErrorMsg(connect_server_failed);
			}else{
				ret.setErrorMsg(e.getLocalizedMessage());
			}
		}
		return ret;
	}

	/**
	 * 表单提交
	 * @param urlStr
	 * @param params
	 * @param files
	 * @param progressListener
	 * @return
	 */
	public static HttpStringResult postForm(String urlStr, Map<String,String> params, Map<String,String> headers, HashMap<String, File> files,
			ProgressListener progressListener){
		HttpStringResult result = new HttpStringResult();
		try {
			result = postFormString(urlStr, params, headers, files, progressListener);
		}catch(TimeFormatException e){
			result.msg = e.getClass().getSimpleName()+" "+e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 表单提交
	 * @param urlStr
	 * @param params
	 * @param files
	 * @param progressListener
	 * @return
	 */
	public static HttpStringResult postFormString(String urlStr, Map<String,String> params, Map<String, String> headers, HashMap<String, File> files,
			ProgressListener progressListener){
		wrapAuth(params);
		Log.i(TAG, "postFormString "+urlStr);
		HttpStringResult result = new HttpStringResult();
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(urlStr);
			HttpEntity entity;
			if(files == null && params != null){
				ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
				if(params != null){
					Set<String> keys = params.keySet();
					for(Iterator<String> i = keys.iterator(); i.hasNext();) {
						String key = i.next();
						if(params.get(key) == null) continue;
						pairs.add(new BasicNameValuePair(key, params.get(key)));
						Log.i(TAG, "postForm params:"+key+"="+params.get(key));
					}
				}
				entity = new UrlEncodedFormEntity(pairs, "utf-8");
			}else {
				if(progressListener == null){
					entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
				}else{
					entity = new ProgressMultiPartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, progressListener);
				}
				if(params != null){
					Set<String> keys = params.keySet();
					for(Iterator<String> i = keys.iterator(); i.hasNext();) {
						String key = i.next();
						((MultipartEntity)entity).addPart(key, new StringBody(params.get(key), Charset.forName("utf-8")));
						Log.i(TAG, "postForm params:"+key+"="+params.get(key));
					}
				}
				if(files != null){
					Set<String> keys = files.keySet();
					for(Iterator<String> i = keys.iterator(); i.hasNext();) {
						String key = i.next();
						if(files.get(key) != null){
							Log.i(TAG, "postForm files:"+key+"="+files.get(key).getName());
							((MultipartEntity)entity).addPart(key, new FileBody(files.get(key)));
						}else{
							Log.i(TAG, "postForm files:"+key+"=空文件");
							//文件为空，上传空文件域
							((MultipartEntity)entity).addPart(key, new ByteArrayBody(new byte[0], "file.txt"));
						}
					}
				}
			}
			
			if(headers != null){
				Iterator<Entry<String, String>> it = headers.entrySet().iterator();
				while(it.hasNext()){
					Entry<String,String> entry = it.next();
					Log.i(TAG, "addHeader: "+entry.getKey()+"="+entry.getValue());
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}

			httpPost.setEntity(entity);
			Log.i(TAG, "post总字节数:"+entity.getContentLength());
			if(progressListener != null && files != null){
				progressListener.total(entity.getContentLength());
			}
			HttpResponse response = client.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			result.resCode = response.getStatusLine().getStatusCode();
			InputStream content = responseEntity.getContent();
			//result.setResult(StringUtils.readString(content));
			result.result = StringUtils.readString(content);
//			if(log)
//				LongLog.i(TAG, result.result);
		}catch(TimeFormatException e){
			e.printStackTrace();
			result.setErrorMsg(e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
			if(e instanceof SocketTimeoutException){
				result.setErrorMsg(server_time_out);
			}else if(e instanceof ConnectException
					|| e instanceof SocketException){
				result.setErrorMsg(connect_failed);
			}else if(e instanceof FileNotFoundException){
				result.setErrorMsg(connect_server_failed);
			}else{
				result.setErrorMsg(e.getLocalizedMessage());
			}
		}
		return result;
	}

	public static HttpStringResult postFormString(String urlStr, Map<String,String> params, Map<String, String> headers, String fileFieldName,
			List<File> files,
			ProgressListener progressListener){
		wrapAuth(params);
		Log.i(TAG, "postFormString "+urlStr);
		HttpStringResult result = new HttpStringResult();
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(urlStr);
			HttpEntity entity;
			if(files == null && params != null){
				ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
				if(params != null){
					Set<String> keys = params.keySet();
					for(Iterator<String> i = keys.iterator(); i.hasNext();) {
						String key = i.next();
						if(params.get(key) == null) continue;
						pairs.add(new BasicNameValuePair(key, params.get(key)));
						Log.i(TAG, "postForm params:"+key+"="+params.get(key));
					}
				}
				entity = new UrlEncodedFormEntity(pairs, "utf-8");
			}else {
				if(progressListener == null){
					entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
				}else{
					entity = new ProgressMultiPartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, progressListener);
				}
				if(params != null){
					Set<String> keys = params.keySet();
					for(Iterator<String> i = keys.iterator(); i.hasNext();) {
						String key = i.next();
						((MultipartEntity)entity).addPart(key, new StringBody(params.get(key), Charset.forName("utf-8")));
						Log.i(TAG, "postForm params:"+key+"="+params.get(key));
					}
				}
				if(files != null){
					/*Set<String> keys = files.keySet();
					for(Iterator<String> i = keys.iterator(); i.hasNext();) {
						String key = (String)i.next();
						if(files.get(key) != null){
						    Log.i(TAG, "postForm files:"+key+"="+files.get(key).getName());
						    ((MultipartEntity)entity).addPart(key, new FileBody(files.get(key)));
						}else{
						    Log.i(TAG, "postForm files:"+key+"=空文件");
						    //文件为空，上传空文件域
						    ((MultipartEntity)entity).addPart(key, new ByteArrayBody(new byte[0], "file.txt"));
						}
					}*/
					for(File f : files){
						((MultipartEntity)entity).addPart(fileFieldName, new FileBody(f));
					}
				}
			}

			if(headers != null){
				Iterator<Entry<String, String>> it = headers.entrySet().iterator();
				while(it.hasNext()){
					Entry<String,String> entry = it.next();
					Log.i(TAG, "addHeader: "+entry.getKey()+"="+entry.getValue());
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			
			httpPost.setEntity(entity);
			Log.i(TAG, "post总字节数:"+entity.getContentLength());
			if(progressListener != null && files != null){
				progressListener.total(entity.getContentLength());
			}
			HttpResponse response = client.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			result.resCode = response.getStatusLine().getStatusCode();
			InputStream content = responseEntity.getContent();
			//result.setResult(StringUtils.readString(content));
			result.result = StringUtils.readString(content);
//			if(log)
//				LongLog.i(TAG, result.result);
		}catch(TimeFormatException e){
			e.printStackTrace();
			result.setErrorMsg(e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
			if(e instanceof SocketTimeoutException){
				result.setErrorMsg(server_time_out);
			}else if(e instanceof ConnectException
					|| e instanceof SocketException){
				result.setErrorMsg(connect_failed);
			}else if(e instanceof FileNotFoundException){
				result.setErrorMsg(connect_server_failed);
			}else{
				result.setErrorMsg(e.getLocalizedMessage());
			}
		}
		return result;
	}
}
