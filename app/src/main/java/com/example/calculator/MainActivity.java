package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.translation.Translator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button convertButton;
    private EditText str;
    private TextView result;
    private Integer[] systems = new Integer[]{10,2,8,16,3,4,5,6,7,9};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        str=findViewById(R.id.editTextFirstNumber);
        convertButton=findViewById(R.id.button);
        result=findViewById(R.id.textSecondNumber);
        //Spinners
        ArrayAdapter<Integer> systemsAdapter= new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item,systems);
        systemsAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        Spinner spinFirstSystem = (Spinner) findViewById(R.id.spinnerFirst);
        Spinner spinSecondSystem = (Spinner) findViewById(R.id.spinnerSecond);
        spinFirstSystem.setAdapter(systemsAdapter);
        spinSecondSystem.setAdapter(systemsAdapter);


        //Button work
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               result.setText(Calculate.Into((Integer) spinFirstSystem.getSelectedItem(), (Integer) spinSecondSystem.getSelectedItem(),str.getText().toString()));
            }
        });
        spinFirstSystem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem() == (Integer)16){
                    str.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                }
                else {
                    str.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (parent.getSelectedItem() == (Integer)16){
                    str.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                }
                else {
                    str.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
                }
            }
        });
    }
}