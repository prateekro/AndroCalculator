package com.prateek.calculator.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.prateek.calculator.MainActivity;

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
