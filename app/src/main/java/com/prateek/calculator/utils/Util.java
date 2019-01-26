package com.prateek.calculator.utils;

import android.os.Handler;
import android.view.View;

public class Util {

    public static void blink(final View view){
        view.setAlpha(0);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setAlpha(1);
            }
        }, 200);
    }
}
