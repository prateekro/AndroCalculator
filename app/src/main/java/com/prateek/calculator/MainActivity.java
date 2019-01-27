package com.prateek.calculator;

import android.animation.StateListAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.prateek.calculator.utils.Util;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button calc1, calc2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ConstraintLayout parent = this.findViewById(R.id.layout_parent);
//        parent.setBackgroundColor(Color.parseColor("#018786"));
        parent.setBackgroundColor(Color.parseColor("#9DBEAA"));
        parent.setBackground(getResources().getDrawable(R.drawable.bg_repeat));


        calc1 = (Button) findViewById(R.id.btnCal1);
        calc1.setBackgroundResource(R.mipmap.img_cal1);

        calc2 = (Button) findViewById(R.id.btnCal2);
        calc2.setBackgroundResource(R.mipmap.img_cal2);

        calc1.setOnClickListener(this);
        calc2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("TAG - MainAct", String.valueOf(view.getId()));
        if (calc1.getId() == view.getId()){
            Toast.makeText(this, "Clicked 1", Toast.LENGTH_SHORT).show();
            Util.blink(calc1);
            startActivity(new Intent(this, TextCalculator.class));
        }
        if (calc2.getId() == view.getId()){
            Toast.makeText(this, "Clicked 2", Toast.LENGTH_SHORT).show();
            Util.blink(calc2);
            startActivity(new Intent(this, ButtonCalculator.class));
        }

    }
}
