package net.hardwave.kerome;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class KeromePrefs extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
		setResult(RESULT_OK, null);
	}

}
