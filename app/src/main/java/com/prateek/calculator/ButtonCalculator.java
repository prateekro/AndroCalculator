package com.prateek.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

import static com.prateek.calculator.utils.Util.performCalculation;
import static java.lang.Integer.parseInt;

public class ButtonCalculator extends Activity implements View.OnClickListener {

    Button button[] = new Button[16];
    TextView tv_view, tv_result;
    String regex = "^(\\d+[\\+\\-\\*\\/]{1})+\\d+$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_calculator);
        tv_view = (TextView) findViewById(R.id.tv_show);
        tv_result = (TextView) findViewById(R.id.tv_result);
        tv_view.setText("");
        tv_result.setText("");


    }

    @Override
    protected void onStart() {
        super.onStart();
        for (int i = 0; i < 16; i++) {

            int id = getResources().getIdentifier("button_" + i, "id", getPackageName());
            button[i] = (Button) findViewById(id);
            button[i].setOnClickListener(this);
        }
        setClickable(false);

//        Regex = ^(\d+[\+\-\*\/]{1})+\d+$

    }

    @Override
    public void onClick(View view) {
        tv_result.setText("");
        for (Button btn: button){
            if(btn.getId() == view.getId()){
                if ((tv_view.getText().toString() + btn.getText().toString()).matches("(\\d)*([\\+\\-\\*\\/]{1})\\d*")){
                    setClickable(false);
                }

                if ((tv_view.getText().toString() + btn.getText().toString()).matches("(\\d)*")){
                    setClickable(true);
                }

                if(!(btn.getText().toString().contentEquals("="))){
                    tv_view.setText(tv_view.getText().toString() + btn.getText().toString());
                }else if(tv_view.getText().toString().matches("(\\d)*([\\+\\-\\*\\/]{1})\\d*")){
                    //split on symbol & perform symbol
//                    Log.d("TAG - ButtonCalc", "onClick: Equals final regex with equal: " + (tv_view.getText().toString() + btn.getText().toString()));
                    tv_result.setText(performCalculation(tv_view.getText().toString()));
                    tv_view.setText("");
                }

                if(btn.getText().toString().contentEquals("C")){
                    tv_view.setText("");
                    setClickable(false);
                }

                if(isSymbol(btn.getText().toString())){
                    setClickable(false);
                }

            }
        }
    }



    private void setClickable(Boolean bool){
        for (int i = 10; i < 14; i++){ // / * - +
            button[i].setClickable(bool);
            if (bool) {
                button[i].animate().setDuration(250).setInterpolator(new FastOutSlowInInterpolator()).alpha((float) 1);//setAlpha((float) 1);
            } else {
                button[i].animate().setDuration(250).setInterpolator(new LinearOutSlowInInterpolator()).alpha((float) 0.1);//setAlpha((float) 1);
            }

        }
    }

    private boolean isSymbol(String btn){
        return  (btn.matches("([\\+\\-\\*\\/])")) ? true : false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_to_right, R.anim.slide_from_left);
    }
}
