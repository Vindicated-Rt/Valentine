package com.mystery.valentine.Util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Vindicated-Rt
 * 2020-02-03 18:04
 */

public class SharedPreferencesUtil {

    private static final String[] chapterString = {
            "chapter1_1","chapter1_2","chapter1_3","chapter2_1","chapter2_2","chapter2_3"
    };

    public static void clearData(Context context){
        SharedPreferences sp = context.getSharedPreferences("chapter", MODE_PRIVATE);
        for (String s : chapterString) {
            sp.edit().putBoolean(s, false).apply();
        }
    }

    public static void chapterDone(Context context, int chapter){
        SharedPreferences sp = context.getSharedPreferences("chapter", MODE_PRIVATE);
        sp.edit().putBoolean(chapterString[chapter], true).apply();
    }
}
