package com.appstronautstudios.voteforkey;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class VoteForKey {
    public static String getVoteForKey(Activity activity, String key) {
        SharedPreferences preferenceManager = PreferenceManager.getDefaultSharedPreferences(activity);
        return preferenceManager.getString(key, null);
    }

    public static void setVoteForKey(Activity activity, String key, String vote) {
        SharedPreferences preferenceManager = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferenceManager.edit();
        editor.putString(key, vote);
        editor.apply();
    }

    public static void removeVoteForKey(Activity activity, String key) {
        setVoteForKey(activity, key, null);
    }
}
