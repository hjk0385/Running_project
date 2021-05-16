package com.trainer.courserunner;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import androidx.annotation.Nullable;

import java.util.List;

public class SettingPreferenceFragment extends PreferenceFragment {

    SharedPreferences prefs;

    ListPreference soundPreference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_preference);
        soundPreference=(ListPreference)findPreference("sound_list");

        prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!prefs.getString("sound_list", "").equals("")){
            soundPreference.setSummary(prefs.getString("sound_list", "여성 목소리"));
        }

        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener=new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("sound_list")){
                soundPreference.setSummary(prefs.getString("sound_list", "여성 목소리"));
                
            }
        }
    };

}
