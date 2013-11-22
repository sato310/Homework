package com.example.broadcasthw;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends Activity implements OnClickListener {
	private Intent intent = new Intent();
	private Uri data;
	private SampleReceiver receiver = new SampleReceiver();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button[] buttons = new Button[3];
		int[] ids = new int[] { R.id.dialButton, R.id.mailButton,
				R.id.mapButton };
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = (Button) findViewById(ids[i]);
			buttons[i].setOnClickListener(this);
		}

		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(receiver, filter);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialButton:
			intent.setAction(Intent.ACTION_CALL);
			data = Uri.parse("tel:117");
			intent.setData(data);
			startActivity(intent);
			break;

		case R.id.mailButton:
			intent.setAction(Intent.ACTION_SENDTO);
			data = Uri.parse("mailto:mail.act1.tak@gmail.com");
			intent.setData(data);
			// putExtra�ł��ꂼ��̓��e�����
			intent.putExtra(Intent.EXTRA_SUBJECT, "�^�C�g������");
			intent.putExtra(Intent.EXTRA_TEXT, "Intent����");
			startActivity(intent);
			break;

		case R.id.mapButton:
			intent.setAction(Intent.ACTION_VIEW);
			// �n�}��uri��("geo:latitude,longitude?q=�n�}�̒��S�Ɏw�肵�����ʒu")geo:0,0�͌��ݒn�̈�
			data = Uri.parse("geo:0,0?q=�����w");
			intent.setData(data);
			startActivity(intent);

			break;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

}
