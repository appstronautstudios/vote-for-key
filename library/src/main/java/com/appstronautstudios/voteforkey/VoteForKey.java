package com.appstronautstudios.voteforkey;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.appstronautstudios.voteforkey.utils.Constants;

public class VoteForKey {
    public static String getVoteForKey(Activity activity, String key) {
        String keyWithPrefix = Constants.PREF_KEY_PREFIX + "_" + key;
        SharedPreferences preferenceManager = PreferenceManager.getDefaultSharedPreferences(activity);
        return preferenceManager.getString(keyWithPrefix, null);
    }

    public static void setVoteForKey(Activity activity, String key, String vote) {
        String keyWithPrefix = Constants.PREF_KEY_PREFIX + "_" + key;
        SharedPreferences preferenceManager = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferenceManager.edit();
        editor.putString(keyWithPrefix, vote);
        editor.apply();
    }

    public static void removeVoteForKey(Activity activity, String key) {
        setVoteForKey(activity, key, null);
    }
}
