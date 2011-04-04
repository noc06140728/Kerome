package net.hardwave.kerome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Kerome extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void sendMail(View v) {
		SharedPreferences myPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String toAddr = myPrefs.getString("to_addr", "");
		
		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {toAddr});
		intent.putExtra(Intent.EXTRA_SUBJECT, "ãAÇËÇ‹Ç∑");
		intent.putExtra(Intent.EXTRA_TEXT, "Ç±ÇÍÇ©ÇÁãAÇËÇ‹Ç∑ÅB");
		startActivity(Intent.createChooser(intent, "Send mail..."));
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.prefs:
			preference();
			break;

		case R.id.help:
			help();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void preference() {
		Intent intent = new Intent(this, net.hardwave.kerome.KeromePrefs.class);
		startActivity(intent);
	}

	private void help() {
		// Builder dialog = new AlertDialog.Builder(null);
		// dialog.setTitle("Help Dialog");
		// dialog.create();
		// .setIcon(R.drawable.alert_dialog_icon)
		// .setTitle("Help Dialog")
		// .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int whichButton) {
		// //code for OK
		// }
		// })
		// .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int whichButton) {
		// //code for Cancel
		// }
		// })
		// .create();
	}
}