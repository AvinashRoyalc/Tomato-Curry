package com.tomato.curry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tomato.curry.Font.TcFontIcons;
import java.util.Date;
import java.util.HashSet;


public class DateSelection extends AppCompatActivity {
    private String dayOfTheWeek, stringMonth, intMonth, year, day;
    private TextView tvcalendar_day, tvcalendar_montyear, tvcalendar_date;
    private TcFontIcons btdown, btdone;
    private Date currentdate;
    private int Currentday, CurrentMonth, CurrentYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);
        currentdate = new Date();
        Currentday = currentdate.getDate();
        CurrentMonth = currentdate.getMonth() + 1;
        CurrentYear = currentdate.getYear();
        dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", currentdate);
        stringMonth = (String) android.text.format.DateFormat.format("MMM", currentdate);
        day = (String) android.text.format.DateFormat.format("dd", currentdate);
        year = (String) android.text.format.DateFormat.format("yyyy", currentdate);
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());
        CalendarView cv = ((CalendarView) findViewById(R.id.calendar_view));
        cv.updateCalendar(events);
        tvcalendar_day = (TextView) findViewById(R.id.tvcalendar_day);
        tvcalendar_montyear = (TextView) findViewById(R.id.tvcalendar_montyear);
        tvcalendar_date = (TextView) findViewById(R.id.tvcalendar_date);
        tvcalendar_day.setText(dayOfTheWeek);
        tvcalendar_montyear.setText(stringMonth + year);
        tvcalendar_date.setText(day);
        btdown = (TcFontIcons) findViewById(R.id.btdown);
        btdone = (TcFontIcons) findViewById(R.id.btdone);
        btdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DateSelection.this, TimeSelection.class);
                intent.putExtra("IntDay", day);
                intent.putExtra("Month&Year", stringMonth + year);
                intent.putExtra("StringDay", dayOfTheWeek);
                intent.putExtra("Month", stringMonth);
                startActivity(intent);
            }
        });
        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date);//Thursday
                stringMonth = (String) android.text.format.DateFormat.format("MMM", date); //Jun
                intMonth = (String) android.text.format.DateFormat.format("MM", date); //06
                year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
                day = (String) android.text.format.DateFormat.format("dd", date); //20
                if (Integer.parseInt(intMonth) == CurrentMonth) {
                    if (!(Integer.parseInt(day) < Currentday)) {
                        tvcalendar_day.setText(dayOfTheWeek);
                        tvcalendar_montyear.setText(stringMonth + year);
                        tvcalendar_date.setText(day);
                    } else {
                        Toast.makeText(DateSelection.this, "No service for this day", Toast.LENGTH_SHORT).show();
                    }
                } else if ((Integer.parseInt(intMonth) <= CurrentMonth + 2)) {
                    if ((Integer.parseInt(intMonth) != CurrentMonth)) {
                        tvcalendar_day.setText(dayOfTheWeek);
                        tvcalendar_montyear.setText(stringMonth + year);
                        tvcalendar_date.setText(day);
                    } else {
                        Toast.makeText(DateSelection.this, "No service for this day", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DateSelection.this, "No service for this day", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

}
