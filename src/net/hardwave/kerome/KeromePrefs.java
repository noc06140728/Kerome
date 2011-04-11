package net.hardwave.kerome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

public class KeromePrefs extends PreferenceActivity {

	private EditTextPreference recipientEditText;
	private ListPreference usualQuittingTimeList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
		setResult(RESULT_OK, null);
		
		SharedPreferences myPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		recipientEditText = (EditTextPreference) findPreference(getString(R.string.prefs_recipient));
		recipientEditText.setSummary(myPrefs.getString(getString(R.string.prefs_recipient), getString(R.string.recipient_summary)));
		
		recipientEditText.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				preference.setSummary((String)newValue);
				return true;
			}
		});
		
		usualQuittingTimeList = (ListPreference) findPreference(getString(R.string.prefs_usual_quitting_time));
	}

}
