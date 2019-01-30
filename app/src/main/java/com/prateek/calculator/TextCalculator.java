package com.prateek.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.prateek.calculator.utils.Util;

public class TextCalculator extends Activity implements View.OnClickListener {

    Button button[] = new Button[4];
    EditText et1, et2;
    TextView tv_result;
    String eval = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_calculator);
        et1 = (EditText) findViewById(R.id.et_1);
        et2 = (EditText) findViewById(R.id.et_2);
        tv_result = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    protected void onStart() {
        super.onStart();

        for (int i = 0; i < 4; i++) {

            int id = getResources().getIdentifier("button_t_" + i, "id", getPackageName());
            button[i] = (Button) findViewById(id);
            button[i].setOnClickListener(this);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_to_right, R.anim.slide_from_left);
    }

    @Override
    public void onClick(View view) {
        for(Button btn: button){
            if(btn.getId() == view.getId()){
                eval = et1.getText().toString() + btn.getText().toString() + et2.getText().toString();
                if(eval.matches("(\\d)*([\\+\\-\\*\\/]{1})(\\d)*") && !(et1.getText().toString().isEmpty()) && !(et2.getText().toString().isEmpty())){
                    tv_result.setText(Util.performCalculation(eval).toString());
                }else{
                    tv_result.setText(" ");
                }
            }
        }

    }
}