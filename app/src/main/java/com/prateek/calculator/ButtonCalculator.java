package com.prateek.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ButtonCalculator extends Activity implements View.OnClickListener {

    Button button[] = new Button[16];
    TextView tv_view;
    String regex = "^(\\d+[\\+\\-\\*\\/]{1})+\\d+$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_calculator);
        tv_view = (TextView) findViewById(R.id.tv_show);
        tv_view.setText("");


    }

    @Override
    protected void onStart() {
        super.onStart();
        for (int i = 0; i < 16; i++) {

            int id = getResources().getIdentifier("button_" + i, "id", getPackageName());
//            Log.d("TAG - ButtonCalc", "onCreate ID: "+id);
//            Log.d("TAG - ButtonCalc 2", String.valueOf(findViewById(R.id.button_0)));
//            Log.d("TAG - ButtonCalc 3", String.valueOf(R.id.button_0));
            button[i] = (Button) findViewById(id);
            button[i].setOnClickListener(this);
        }
        setClickable(false);

//        ^(\d+[\+\-\*\/]{1})+\d+$

    }

    @Override
    public void onClick(View view) {
        for (Button btn: button){
            if(btn.getId() == view.getId()){


//                setClickable(true);

                Log.d("TAG - ButtonCalc", "onClick-Text: " + tv_view.getText().toString() + " :TYPE: " + btn.getText().toString() );
//                if ((tv_view.getText().toString() + btn.getText().toString()).matches(regex)){
//                }


                if ((tv_view.getText().toString() + btn.getText().toString()).matches("(\\d)*([\\+\\-\\*\\/]{1})\\d*")){
                    setClickable(false);
                }

                if ((tv_view.getText().toString() + btn.getText().toString()).matches("(\\d)*")){
                    setClickable(true);
                }

//                equal in first run close. equal click asses ans and all hide except c

                if(!(btn.getText().toString().contentEquals("="))){
                    tv_view.setText(tv_view.getText().toString() + btn.getText().toString());
                }else if(tv_view.getText().toString().matches("(\\d)*([\\+\\-\\*\\/]{1})\\d*")){
                    //split on symbol & perform symbol
                    Log.d("TAG - ButtonCalc", "onClick: Equals final regex with equal: " + (tv_view.getText().toString() + btn.getText().toString()));
                    tv_view.setText(performCalculation(tv_view.getText().toString()));
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

    private String performCalculation(String full) {

        String[] parts = full.split("([\\+\\-\\*\\/])");
        String part1 = parts[0]; // 004
        String part2 = parts[1];
        String eval = "";
        return eval;
    }

    private void setClickable(Boolean bool){
        for (int i = 10; i < 14; i++){ // / * - +
            button[i].setClickable(bool);
//            button[i].;

        }
    }

    private boolean isSymbol(String btn){
        return  (btn.matches("([\\+\\-\\*\\/])")) ? true : false;
    }
}
