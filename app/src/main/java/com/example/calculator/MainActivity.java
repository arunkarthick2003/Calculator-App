package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }
    private void updateTxt(String strToAdd){
        String oldstr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String leftstr=oldstr.substring(0,cursorPos);
        String rightstr=oldstr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos+1);
        }else{
            display.setText(String.format("%s%s%s",leftstr,strToAdd,rightstr));
            display.setSelection(cursorPos+1);
        }
    }
    public void zerobtn(View view){
        updateTxt("0");
    }
    public void onebtn(View view){
        updateTxt("1");
    }
    public void twobtn(View view){
        updateTxt("2");
    }
    public void threebtn(View view){
        updateTxt("3");
    }
    public void fourbtn(View view){
        updateTxt("4");
    }
    public void fivebtn(View view){
        updateTxt("5");
    }
    public void sixbtn(View view){
        updateTxt("6");
    }
    public void sevenbtn(View view){
        updateTxt("7");
    }
    public void eightbtn(View view){
        updateTxt("8");
    }
    public void ninebtn(View view){
        updateTxt("9");
    }
    public void addbtn(View view){
        updateTxt("+");
    }
    public void minusbtn(View view){
        updateTxt("-");
    }
    public void multiplybtn(View view){
        updateTxt("x");
    }
    public void dividebtn(View view){
        updateTxt("÷");
    }
    public void equalsbtn(View view){
        String userExp=display.getText().toString();
        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("x","*");
        Expression exp=new Expression(userExp);
        String res=String.valueOf(exp.calculate());
        display.setText(res);
        display.setSelection(res.length());
    }
    public void pointbtn(View view){
        updateTxt(".");
    }
    public void plusminusbtn(View view){
        updateTxt("-");
    }
    public void exponentbtn(View view){
        updateTxt("^");
    }
    public void parenthesesbtn(View view){
        int cursorPos=display.getSelectionStart();
        int openPar=0,closePar=0;
        int textLen=display.getText().length();
        for(int i=0;i<cursorPos;i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar++;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closePar++;
            }
        }
        if(openPar==closePar || display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateTxt("(");
        }else if(closePar<openPar && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateTxt(")");
        }
        display.setSelection(cursorPos+1);
    }
    public void clearbtn(View view){
        display.setText("");
    }
    public void backspacebtn(View view){
        int cursorPos=display.getSelectionStart();
        int textLen=display.getText().length();
        if(cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection =(SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }
}