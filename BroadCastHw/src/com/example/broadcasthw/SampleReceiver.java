package com.example.broadcasthw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SampleReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// intent.getAction()�̖߂�l��String�^��action
		String action = intent.getAction();
		// if����switch�����g��Ȃ��Ă��A���O������΂ǂ�ȑ�����������͂킩��̂ō���͕���Ȃ��B
			Log.i("recive", action);
//			Log.i("recive", intent.getAction());��1�s�ɂ܂Ƃ߂Ă�ok
			
	}	

}
