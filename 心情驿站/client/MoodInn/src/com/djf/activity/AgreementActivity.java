package com.djf.activity;

import com.djf.constants.HttpConstants;
import com.djf.moodinn.R;

import android.os.Bundle;
import android.webkit.WebView;

public class AgreementActivity extends BaseActivity {

	private WebView webView;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		
		setContentView(R.layout.agreement);
		webView=(WebView)findViewById(R.id.myWebView);
		
		webView.loadUrl(HttpConstants.HTTP_AGREEMENT);
		 
	}
	
	
}
