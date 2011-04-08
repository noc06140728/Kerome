package net.hardwave.kerome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

public class KeromePrefs extends PreferenceActivity {

	private EditTextPreference recipientEditText;
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
				((EditTextPreference)preference).setSummary((String)newValue.toString());
				return true;
			}
		});
	}

}
