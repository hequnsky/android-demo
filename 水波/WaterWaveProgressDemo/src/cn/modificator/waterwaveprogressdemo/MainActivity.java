package cn.modificator.waterwaveprogressdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import cn.modificator.waterwave_progress.WaterWaveProgress;

public class MainActivity extends Activity implements OnClickListener {
	int currentvalue = 0;
	WaterWaveProgress waveProgress;
	Button bt_start, bt_stop;
	// Thread mainThread=new Thread();
	private Handler handler;
	private boolean isStart=true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SeekBar bar = (SeekBar) findViewById(R.id.seekBar1);
		waveProgress = (WaterWaveProgress) findViewById(R.id.waterWaveProgress1);
		bt_start = (Button) findViewById(R.id.bt_start);
		bt_stop = (Button) findViewById(R.id.bt_stop);
		waveProgress.setShowProgress(true);
		waveProgress.animateWave();
		bt_start.setOnClickListener(this);
		bt_stop.setOnClickListener(this);
		bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				setTitle("" + progress);
				waveProgress.setProgress(progress);

			}
		});
		((CheckBox) findViewById(R.id.checkBox1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						waveProgress.setShowProgress(isChecked);
					}
				});
		((CheckBox) findViewById(R.id.checkBox2))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						waveProgress.setShowNumerical(isChecked);
					}
				});
	}

	@SuppressLint("HandlerLeak")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_start:
			if(isStart){
				isStart=false;
			if (handler == null) {
				
				handler = new Handler() {
					public void handleMessage(Message msg) {
						currentvalue = waveProgress.getProgress();
						if (currentvalue < 100) {
							currentvalue++;

						
						waveProgress.setProgress(currentvalue);
						handler.sendEmptyMessageDelayed(0, 3000);
						}
					};
				};

			}
			}
			
			handler.sendEmptyMessageDelayed(0, 3000);

			break;
		case R.id.bt_stop:
			if (handler != null){ 
				
				
			handler.removeCallbacksAndMessages(null);
			isStart=true;
			}
			break;
		}

	}
}
