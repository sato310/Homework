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
		
		//�@�{�^����3�����i�ȑO�����TouchTheNumber�̂悤�Ƀ{�^�����������͂��̂������y�ł��j
		// �{�^���z��̃C���X�^���X
		Button[] buttons = new Button[3];
		// id�z��̃C���X�^���X
		int[] ids = new int[] { R.id.dialButton, R.id.mailButton,
				R.id.mapButton };
		// for����findViewById��setOnClickListener���܂Ƃ߂Ď����B
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = (Button) findViewById(ids[i]);
			buttons[i].setOnClickListener(this);
		}
		// SCREEN_ON��Manifest.xml�ɓo�^���Ă���M�ł��Ȃ��u���[�h�L���X�g�A�N�V�����Ȃ̂Ń\�[�X�R�[�h���ɋL�q
		// IntentFilter�̃C���X�^���X�����
		IntentFilter filter = new IntentFilter();
		// filter.addAction�Ńu���[�h�L���X�g�A�N�V�������`
		filter.addAction(Intent.ACTION_SCREEN_ON);
		// registerReceiver�iBroadCastReceiver,IntentFilter�j�Ńu���[�h�L���X�g�A�N�V�����������B
		registerReceiver(receiver, filter);

	}
	// �{�^���N���b�N�A�N�V����
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// �d�b���|����{�^���̏���
		case R.id.dialButton:
			// Intent.ACTION_CALL�Ŕ��M�������o����iManifest.xml��permission�̋L�q���K�v�j
			intent.setAction(Intent.ACTION_CALL);
			// Uri���w��B�d�b�̎���Uri.parse("tel:�ԍ�")
			data = Uri.parse("tel:117");
			// intent.setData(Uri)�ŃC���e���g��uri�̃f�[�^��n���B
			intent.setData(data);
			// intent���s�i���̃A�v���P�[�V�������N�������Ă���j�B
			startActivity(intent);
			break;
			
		//�@���[���𑗂�{�^���̏���
		case R.id.mailButton:
			// ��̃R�[�h�Ƃقړ����B�iIntent.ACTION_SENDTO�ő���A�N�V�����j
			intent.setAction(Intent.ACTION_SENDTO);
			// mailto:�A�h���X
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
		// �\�[�X�R�[�h�Œ�`�������V�[�o�[���~������B
		unregisterReceiver(receiver);
	}

}
