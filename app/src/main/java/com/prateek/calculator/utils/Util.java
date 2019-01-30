package com.prateek.calculator.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.prateek.calculator.MainActivity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

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

    public static String performCalculation(String full) {

        String[] parts = full.split("([\\+\\-\\*\\/])");
        String part1 = parts[0];
        String part2 = parts[1];
        String eval = "";

        if (full.contains("/")) {
            try  {
                eval = (new BigDecimal(part1).divide(new BigDecimal(part2), 2, RoundingMode.HALF_UP)) + "";
            } catch (Exception e) {
                eval = "Undefined.";
            }
        }

        else if(full.contains("*")){
            try {
                eval = (new BigDecimal(part1).multiply(new BigDecimal(part2))) + "";
            } catch (Exception e) {
                eval = "Out of Transistors";
            }
        }

        else if(full.contains("-")){
            try{
                eval = (new BigDecimal(part1).subtract(new BigDecimal(part2))) + "";
            } catch (Exception e){
                eval = "Still working on this :)";
            }

        }

        else if(full.contains("+")){
            try{
                eval = (new BigDecimal(part1).add(new BigDecimal(part2))) + "";
            } catch (Exception e){
                eval = "Still working on this :)";
            }
        }

        return eval;
    }

}
