package com.example.contentproviderhwsample;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.listView1);

		ContentResolver resolver = getContentResolver();

		String[] projection = new String[] {
				ContactsContract.CommonDataKinds.Phone._ID,
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER };

		Cursor cursor = resolver.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
				null, null, null);
		if (cursor != null && 0 < cursor.getCount()) {
			String[] from = new String[] {
					ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
					ContactsContract.CommonDataKinds.Phone.NUMBER };
			int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					android.R.layout.two_line_list_item, cursor, from, to, 0);
			listView.setAdapter(adapter);
		}
	}
}
