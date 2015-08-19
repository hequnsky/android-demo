package com.djf.upload;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
 /**
  * 监听上传进度
  * @author JiaYe 2014年7月18日
  *
  */
public class ProgressMultiPartEntity extends MultipartEntity{
	public static final String tag = "ProgressMultiPartEntity";
 
	private final ProgressListener listener;
 
	public ProgressMultiPartEntity(final ProgressListener listener){
		super();
		this.listener = listener;
	}
 
	public ProgressMultiPartEntity(final HttpMultipartMode mode, final ProgressListener listener){
		super(mode);
		this.listener = listener;
	}
 
	public ProgressMultiPartEntity(HttpMultipartMode mode, final String boundary, final Charset charset, final ProgressListener listener){
		super(mode, boundary, charset);
		this.listener = listener;
	}
 
	@Override
	public void writeTo(final OutputStream outstream) throws IOException{
		super.writeTo(new CountingOutputStream(outstream, this.listener));
	}
 
	public static interface ProgressListener{
		void transferred(long num);
		void total(long num);
	}
 
	public static class CountingOutputStream extends FilterOutputStream{
 
		private final ProgressListener listener;
		private long transferred;
 
		public CountingOutputStream(final OutputStream out, final ProgressListener listener){
			super(out);
			this.listener = listener;
			this.transferred = 0;
		}
 
		@Override
		public void write(byte[] b, int off, int len) throws IOException{
			out.write(b, off, len);
			this.transferred += len;
			this.listener.transferred(this.transferred);
		}
 
		@Override
		public void write(int b) throws IOException{
			out.write(b);
			this.transferred++;
			this.listener.transferred(this.transferred);
		}
	}
}