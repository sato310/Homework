package com.example.broadcasthw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SampleReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// intent.getAction()の戻り値はString型のaction
		String action = intent.getAction();
		// if文やswitch文を使わなくても、ログを見ればどんな操作をしたかはわかるので今回は分岐なし。
			Log.i("recive", action);
//			Log.i("recive", intent.getAction());の1行にまとめてもok
			
	}	

}
