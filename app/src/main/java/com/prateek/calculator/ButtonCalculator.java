package com.prateek.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

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
        tv_result.setText("");
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

    private String performCalculation(String full) {

        String[] parts = full.split("([\\+\\-\\*\\/])");
        String part1 = parts[0]; // 004
        String part2 = parts[1];
//        long temp = 0;
        String eval = "";
        if (full.contains("/")) {
            try  {
                eval = ((new BigDecimal(part1)).divide(new BigDecimal(part2)))  + "";
                
            } catch (Exception e) {
                eval = "Undefined. Stop Testing me :D";
            }
        } else if(full.contains("*")){
            try {
                eval = (BigDecimal.valueOf(Double.parseDouble(part1)).multiply(BigDecimal.valueOf(Double.parseDouble(part2)))) + "";
            } catch (Exception e) {
                eval = "I'm still in process to learn";
            }

        }
            else if(full.contains("-")){
            eval = (parseInt(part1) - parseInt(part2)) + "";

        }
            else if(full.contains("+")){
            eval = (parseInt(part1) + parseInt(part2)) + "";
        }


//        eval = " " + (part1) + (part2);
        return eval;
    }

    private void setClickable(Boolean bool){
        for (int i = 10; i < 14; i++){ // / * - +
            button[i].setClickable(bool);
            if (bool) {
                button[i].setAlpha((float) 1);
            } else {
                button[i].setAlpha((float) 0.1);
            }

        }
    }

    private boolean isSymbol(String btn){
        return  (btn.matches("([\\+\\-\\*\\/])")) ? true : false;
    }
}
