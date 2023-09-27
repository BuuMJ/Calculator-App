package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTV, workingTV;
    MaterialButton btnClear, btnLeft, btnRight, btnDivide, btn7, btn8, btn9, btn6, btn5, btn4, btn3, btn2, btn1, btn0, btnMulti, btnMinus, btnPlus, btnDot, btnEqual, btnAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        resultTV.setVisibility(View.INVISIBLE);
        btnClear.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnAC.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalcText = workingTV.getText().toString();
        if(buttonText.equals("AC")){
            workingTV.setText("");
            resultTV.setText("");
            return;
        }
        if(buttonText.equals("=")){
            workingTV.setText(resultTV.getText());
            return;
        }
        if(buttonText.equals("C")){
            if(dataCalcText.isEmpty()){
                Toast.makeText(this, "nothing to delete", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                dataCalcText = dataCalcText.substring(0,dataCalcText.length() - 1).trim();
            }
        }else{
            if(buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/")){
                dataCalcText = dataCalcText + " " + buttonText + " " ;
            }
            else{
                dataCalcText = dataCalcText + buttonText;
            }
        }
        workingTV.setText(dataCalcText);
        String finalResult = getResult(dataCalcText);
        if(!finalResult.equals("Invalid Input")){
            resultTV.setText(finalResult);
        }
    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            Object result = context.evaluateString(scriptable, data, "Javascript", 1, null);
            if (result == Context.getUndefinedValue() || result.toString().equals("undefined")) {
                return "0";
            } else {
                return result.toString();
            }
        } catch (Exception e){
            return "Invalid Input";
        }
    }

    protected void mapping(){
        resultTV = (TextView)findViewById(R.id.resultTV);
        workingTV = (TextView)findViewById(R.id.workingTV);
        btnClear = (MaterialButton)findViewById(R.id.btnClear);
        btnLeft = (MaterialButton)findViewById(R.id.btnLeft);
        btnRight = (MaterialButton)findViewById(R.id.btnRight);
        btnDivide = (MaterialButton)findViewById(R.id.btnDivide);
        btnMulti = (MaterialButton)findViewById(R.id.btnMulti);
        btnEqual = (MaterialButton)findViewById(R.id.btnEqual);
        btnMinus = (MaterialButton)findViewById(R.id.btnMinus);
        btnPlus = (MaterialButton)findViewById(R.id.btnPlus);
        btnDot = (MaterialButton)findViewById(R.id.btnDot);
        btn0 = (MaterialButton)findViewById(R.id.btn0);
        btn1 = (MaterialButton)findViewById(R.id.btn1);
        btn2 = (MaterialButton)findViewById(R.id.btn2);
        btn3 = (MaterialButton)findViewById(R.id.btn3);
        btn4 = (MaterialButton)findViewById(R.id.btn4);
        btn5 = (MaterialButton)findViewById(R.id.btn5);
        btn6 = (MaterialButton)findViewById(R.id.btn6);
        btn7 = (MaterialButton)findViewById(R.id.btn7);
        btn8 = (MaterialButton)findViewById(R.id.btn8);
        btn9 = (MaterialButton)findViewById(R.id.btn9);
        btnAC = (MaterialButton)findViewById(R.id.btnAC);
    }
}