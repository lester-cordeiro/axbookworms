package com.astrex.bookworms;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SeekBarPreference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        private ListPreference mPronounce;
        private ListPreference mVoice;
        private SeekBarPreference mRate;
        private static String mVoiceUK = "en-gb-x-gbd-local";
        private static String mVoiceUS = "en-us-x-tpd-network";

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

        private void updateVoices(String locale) {

            Locale loc = Locale.UK;
            if(locale.equals("US")) {
                 loc = Locale.US;
            }

            MainActivity x = MainActivity.getApplicationX();
            Set<Voice> v = x.getVoices();

            List<CharSequence> charSequencesEntries = new ArrayList<CharSequence>();
            List<CharSequence> charSequencesValues = new ArrayList<CharSequence>();
            for (Voice s : v) {
                if(s.getLocale() == loc) {
                    charSequencesEntries.add(s.getName());
                    charSequencesValues.add(s.getName());
                }
            }

            CharSequence[] entries = charSequencesEntries.toArray(new CharSequence[charSequencesEntries.size()]);
            CharSequence[] entryValues = charSequencesValues.toArray(new CharSequence[charSequencesValues.size()]);

            mVoice.setEntries(entries);
            mVoice.setEntryValues(entryValues);
            if(loc == Locale.US) {
                mVoice.setValue(mVoiceUS);
            } else {
                mVoice.setValue(mVoiceUK);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            mPronounce = (ListPreference)  getPreferenceManager().findPreference("pronounce");
            mVoice = (ListPreference)  getPreferenceManager().findPreference("voice");
            mRate = (SeekBarPreference)  getPreferenceManager().findPreference("rate");

            mPronounce.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(Preference pref, Object newVal) {
                    updateVoices(newVal.toString());
                    return true;
                }
            });

            mVoice.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(Preference pref, Object newVal) {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                    String imgSett = prefs.getString("pronounce", "");

                    if(imgSett.equals("US")) {
                        mVoiceUS = newVal.toString();
                    } else {
                        mVoiceUK = newVal.toString();
                    }
                    //MainActivity x = MainActivity.getApplicationX();
                    //x.setVoice(newVal.toString());
                    Log.i("hellox", newVal.toString());
                    return true;
                }
            });

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            String imgSett = prefs.getString("pronounce", "");
            updateVoices(imgSett);
            Log.i("hello", imgSett);

            /*MainActivity x = MainActivity.getApplicationX();
            Set<Voice> v = x.getVoices();

            List<CharSequence> charSequences = new ArrayList<CharSequence>();
            List<CharSequence> charSequences2 = new ArrayList<CharSequence>();
            for (Voice s : v) {
                if((s.getLocale() == Locale.US) || s.getLocale() == Locale.UK) {
                    charSequences.add(s.getName());
                    charSequences2.add(s.getName());
                }
            }

            CharSequence[] entries = charSequences.toArray(new
                    CharSequence[charSequences.size()]);
            CharSequence[] entryValues = charSequences2.toArray(new
                    CharSequence[charSequences2.size()]);

            //CharSequence[] entries = new CharSequence[v.size()];




            //CharSequence[] entries = { "English", "French" };
            //CharSequence[] entryValues = {"1" , "2"};
            mVoice.setEntries(entries);
            //mListPreference.setDefaultValue("1");
            mVoice.setEntryValues(entryValues);*/
            return super.onCreateView(inflater, container, savedInstanceState);
        }


    }
}