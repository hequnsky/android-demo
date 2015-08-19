package com.djf.util;

import android.content.Context;
import android.content.DialogInterface;

import com.djf.view.CustomDialog;



public class DialogUtils {

	DialogCallback dialogCallback=new DialogCallback() {
		
	@SuppressWarnings("unused")
	CustomDialog.Builder builders;
		@Override
		public void dialogOk() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void dialogCancel() {
			// TODO Auto-generated method stub
			
		}
	};
	/*CustomDialog.Builder builder = new CustomDialog.Builder(this);
	builder.setMessage("这个就是自定义的提示框");
	builder.setTitle("提示");
	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			//设置你的操作事项
		}
	});

	builder.setNegativeButton("取消",
			new android.content.DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});

	builder.create().show();*/
	
	public DialogUtils(Context ctx,String title,String message,final DialogCallback dialogCallback) {
		// TODO Auto-generated constructor stub
		this.dialogCallback=dialogCallback;
		CustomDialog.Builder builder=new CustomDialog.Builder(ctx);
		
		builder.setTitle(title);
		builder.setMessage(message);
		
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//设置你的操作事项
				dialogCallback.dialogOk();
			}
		});
		
		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						dialogCallback.dialogCancel();
					}
				});
		
		builder.create().show();
	}
	
	public void ShowDialog(){
		
	}
	
	public interface DialogCallback{
		public void dialogOk();
		public void dialogCancel();
	}
	
}
