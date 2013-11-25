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
		
		//　ボタンを3個生成（以前作ったTouchTheNumberのようにボタンが多い時はこのやり方が楽です）
		// ボタン配列のインスタンス
		Button[] buttons = new Button[3];
		// id配列のインスタンス
		int[] ids = new int[] { R.id.dialButton, R.id.mailButton,
				R.id.mapButton };
		// for文でfindViewByIdとsetOnClickListenerをまとめて実装。
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = (Button) findViewById(ids[i]);
			buttons[i].setOnClickListener(this);
		}
		// SCREEN_ONはManifest.xmlに登録しても受信できないブロードキャストアクションなのでソースコード内に記述
		// IntentFilterのインスタンスを作る
		IntentFilter filter = new IntentFilter();
		// filter.addActionでブロードキャストアクションを定義
		filter.addAction(Intent.ACTION_SCREEN_ON);
		// registerReceiver（BroadCastReceiver,IntentFilter）でブロードキャストアクションを実装。
		registerReceiver(receiver, filter);

	}
	// ボタンクリックアクション
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 電話を掛けるボタンの処理
		case R.id.dialButton:
			// Intent.ACTION_CALLで発信処理が出来る（Manifest.xmlにpermissionの記述が必要）
			intent.setAction(Intent.ACTION_CALL);
			// Uriを指定。電話の時はUri.parse("tel:番号")
			data = Uri.parse("tel:117");
			// intent.setData(Uri)でインテントにuriのデータを渡す。
			intent.setData(data);
			// intent実行（他のアプリケーションを起動させている）。
			startActivity(intent);
			break;
			
		//　メールを送るボタンの処理
		case R.id.mailButton:
			// 上のコードとほぼ同じ。（Intent.ACTION_SENDTOで送るアクション）
			intent.setAction(Intent.ACTION_SENDTO);
			// mailto:アドレス
			data = Uri.parse("mailto:mail.act1.tak@gmail.com");
			intent.setData(data);
			// putExtraでそれぞれの内容を入力
			intent.putExtra(Intent.EXTRA_SUBJECT, "タイトルだよ");
			intent.putExtra(Intent.EXTRA_TEXT, "Intentだよ");
			startActivity(intent);
			break;

		case R.id.mapButton:
			intent.setAction(Intent.ACTION_VIEW);
			// 地図のuriは("geo:latitude,longitude?q=地図の中心に指定したい位置")geo:0,0は現在地の意
			data = Uri.parse("geo:0,0?q=博多駅");
			intent.setData(data);
			startActivity(intent);

			break;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ソースコードで定義したレシーバーを停止させる。
		unregisterReceiver(receiver);
	}

}
