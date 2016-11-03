package com.tomato.curry;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class SaloonServiceSelection extends AppCompatActivity {
    private Button btnconfirm;
    private ImageView ivclose;
    private EditText etName, etMobile, etemail;
    private AppCompatCheckBox cbHaircut, cbFacial, cbMassaging;
private String Date,Month,Time,Day,DatedayMonth;
    private TextView tvdate,tvtime;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon_service_selection);
        Date=getIntent().getStringExtra("Date");
        Month=getIntent().getStringExtra("Month");
        Time=getIntent().getStringExtra("time");
        Day=getIntent().getStringExtra("Day");
        if(Day.equalsIgnoreCase("sunday")){
            Day="Sun";
        }else if(Day.equalsIgnoreCase("monday")){
            Day="Mon";
        }else if(Day.equalsIgnoreCase("tuesday")){
            Day="Tue";
        }else if(Day.equalsIgnoreCase("wednesday")){
            Day="Wed";
        }else if(Day.equalsIgnoreCase("thursday")){
            Day="Thu";
        }else if(Day.equalsIgnoreCase("friday")){
            Day="Fri";
        }else if(Day.equalsIgnoreCase("Saturday")){
            Day="Sat";
        }
        DatedayMonth=Date+"-"+Day+"-"+Month;
        ivclose = (ImageView) findViewById(R.id.ivclose);
        btnconfirm = (Button) findViewById(R.id.btn_confirm);
        cbHaircut = (AppCompatCheckBox) findViewById(R.id.cbHaircut);
        cbFacial = (AppCompatCheckBox) findViewById(R.id.cbFacial);
        cbMassaging = (AppCompatCheckBox) findViewById(R.id.cbMassaging);
        etName=(EditText)findViewById(R.id.etName);
        etMobile=(EditText)findViewById(R.id.etMoblie);
        etemail=(EditText)findViewById(R.id.etEmail);
        tvtime=(TextView)findViewById(R.id.tvtime);
        tvdate=(TextView)findViewById(R.id.tvdate);
        spinner=(Spinner)findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Saloon Service");
        list.add("Home Service");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        tvtime.setText(Time);
        tvdate.setText(DatedayMonth);
        cbHaircut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbHaircut.isChecked()) {
                    cbFacial.setChecked(false);
                    cbMassaging.setChecked(false);
                }
            }
        });
        cbFacial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbFacial.isChecked()) {
                    cbHaircut.setChecked(false);
                    cbMassaging.setChecked(false);
                }
            }
        });
        cbMassaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbMassaging.isChecked()) {
                    cbHaircut.setChecked(false);
                    cbFacial.setChecked(false);
                }
            }
        });
        ivclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(etName.getText()).length()==0){
                    Toast.makeText(SaloonServiceSelection.this,"Enter your Name",Toast.LENGTH_SHORT).show();
                    etName.setFocusable(true);
                }else if(String.valueOf(etMobile.getText()).length()==0){
                    Toast.makeText(SaloonServiceSelection.this,"Enter your Mobile Number",Toast.LENGTH_SHORT).show();
                    etMobile.setFocusable(true);
                }else if(String.valueOf(etemail.getText()).length()==0){
                    Toast.makeText(SaloonServiceSelection.this,"Enter your Email Address",Toast.LENGTH_SHORT).show();
                    etemail.setFocusable(true);
                }else {
                    startActivity(new Intent(SaloonServiceSelection.this, ServiceSummary.class));
                }

            }
        });
    }
}
