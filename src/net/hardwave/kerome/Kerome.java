package net.hardwave.kerome;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Kerome extends Activity {
	private TextView recipientText;
	private SharedPreferences myPrefs;
	/** Called when the activity is first created. */
	private SeekBar quittingTimeBar;
	private TextView quittingTimeText;
	private TextView mailBodyText;
	
	private MailGenerator gen;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		myPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		recipientText = (TextView) this.findViewById(R.id.recipient_text);
		quittingTimeBar = (SeekBar) this.findViewById(R.id.quitting_time_bar);
		quittingTimeText = (TextView) this.findViewById(R.id.quitting_time_text);
		mailBodyText = (TextView) this.findViewById(R.id.mail_body_text);
		
		gen = new MailGenerator(getResources());
		
		quittingTimeBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener () {
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				setQuittingTime(progress);
				
				gen.setTime(progress);
				mailBodyText.setText(gen.generateBody());
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO 
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO 
			}
			
		});
	}
	
	private void setQuittingTime(int progress) {
		Calendar now = new GregorianCalendar();
		now.add(Calendar.MINUTE, progress);
		now.add(Calendar.MINUTE, -now.get(Calendar.MINUTE) % 10);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		quittingTimeText.setText(format.format(now.getTime()));
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		recipientText.setText(myPrefs.getString(getString(R.string.prefs_recipient), ""));
		setQuittingTime(quittingTimeBar.getProgress());
	}

	public void sendMail(View v) {
		String toAddr = myPrefs.getString(getString(R.string.prefs_recipient), "");
		
		final Intent intent;
		if ("action_send".equals(myPrefs.getString(getString(R.string.prefs_intent_action), ""))) {
			intent = new Intent(Intent.ACTION_SEND);
			intent.setType("plain/text");
			intent.putExtra(Intent.EXTRA_EMAIL, new String[] {toAddr});
		} else {
			intent = new Intent(Intent.ACTION_SENDTO);
			intent.setData(Uri.parse("mailto:" + toAddr));
		}
		
		intent.putExtra(Intent.EXTRA_SUBJECT, "GOING HOME");
		intent.putExtra(Intent.EXTRA_TEXT, mailBodyText.getText());
		startActivity(Intent.createChooser(intent, "Send mail..."));
	}

	@Override
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
//		 Builder dialog = new AlertDialog.Builder(null);
//		 dialog.setTitle("Help Dialog");
//		 dialog.create();
//		 .setIcon(R.drawable.alert_dialog_icon)
//		 .setTitle("Help Dialog")
//		 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//		 public void onClick(DialogInterface dialog, int whichButton) {
//		 //code for OK
//		 }
//		 })
//		 .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//		 public void onClick(DialogInterface dialog, int whichButton) {
//		 //code for Cancel
//		 }
//		 })
//		 .create();
	}
}