package com.tomato.curry;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.tomato.curry.Font.TcFontIcons;

public class TimeSelection extends AppCompatActivity {
    private String[] mtiming = {"9:00", "9:15", "9:30", "9:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00"};
    private String[] atiming = {"1:00", "1:15", "1:30", "1:45", "2:00", "2:15", "2:30", "2:45", "3:00", "3:15", "3:30", "3:45", "4:00"};
    private String[] etiming = {"4:15", "4:30", "4:45", "5:00", "5:15", "5:30", "5:45", "6:00", "6:15", "6:30", "6:45", "7:00"};
    private GridView gv_time;
    private int Pos, Row;
    private TimeAdapter timeAdapter;
    private String date, day, monthyear, Am,pm,month;
    private TcFontIcons btback, btforward, btdown, btdone;
    private TextView tvdate, tvday, tvmonthyear, tvtime, tvampm;
public String  time="9:00";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selection);
        date = getIntent().getStringExtra("IntDay");
        day = getIntent().getStringExtra("StringDay");
        monthyear = getIntent().getStringExtra("Month&Year");
        month=getIntent().getStringExtra("Month");
        tvdate = (TextView) findViewById(R.id.tvcalendar_date);
        tvmonthyear = (TextView) findViewById(R.id.tvcalendar_montyear);
        tvday = (TextView) findViewById(R.id.tvcalendar_day);
        tvtime = (TextView) findViewById(R.id.tvtime);
        tvampm = (TextView) findViewById(R.id.tv_Am_Pm);
        tvdate.setText(date);
        tvmonthyear.setText(monthyear);
        tvday.setText(day);
        gv_time = (GridView) findViewById(R.id.gv_time);
        btback = (TcFontIcons) findViewById(R.id.btback);
        btforward = (TcFontIcons) findViewById(R.id.btforward);
        btdown = (TcFontIcons) findViewById(R.id.btdown);
        btdone = (TcFontIcons) findViewById(R.id.btdone);
        Row = Pos = 1;
        timeAdapter = new TimeAdapter(TimeSelection.this, mtiming);
        gv_time.setAdapter(timeAdapter);
        tvtime.setText(time);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Pos >= 1) && (Pos <= 3)) {
                    if (Pos == 1) {
                        Pos = 1;
                        UpdateTime(Pos);
                    }else{
                        UpdateTime(--Pos);
                    }

                }

            }
        });
        btforward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Pos >= 1) && (Pos <= 3)) {
                    if (Pos == 3) {
                        Pos=3;
                        UpdateTime(Pos);
                    }else{
                        UpdateTime(++Pos);
                    }


                }
            }

        });
        btdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeSelection.this, SaloonServiceSelection.class);
                intent.putExtra("Date", date);
                intent.putExtra("Day", day);
                intent.putExtra("Month", month);
                intent.putExtra("time", time);
                if (Row == 1) {
                    if (time.equals("12:00")) {
                        intent.putExtra("AM&PM", pm);
                    }
                    intent.putExtra("AM&PM", Am);
                } else if (Row == 2) {
                    intent.putExtra("AM&PM", pm);
                } else if (Row == 3) {
                    intent.putExtra("AM&PM", pm);
                }
                startActivity(intent);
            }
        });
        gv_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int visiblePosition = gv_time.getFirstVisiblePosition(); visiblePosition <= gv_time.getLastVisiblePosition(); visiblePosition++) {
                    View item = gv_time.getChildAt(visiblePosition);
                    TextView textView = (TextView) item;
                    textView.setBackgroundColor(getResources().getColor(R.color.white));
                    textView.setTextColor(getResources().getColor(R.color.black));
                }
                View item = gv_time.getChildAt(position);
                TextView textView = (TextView) item;
                textView.setBackground(getResources().getDrawable(R.drawable.tvtime_background));
                textView.setTextColor(getResources().getColor(R.color.white));
                time = textView.getText().toString();
                pm = "PM";
                Am = "AM";

                if (Row == 1) {
                    if (time.equals("12:00")) {
                        tvampm.setText(pm);
                    } else {
                        tvampm.setText(Am);
                    }
                    tvtime.setText(time);
                } else if (Row == 2) {
                    tvtime.setText(time);
                    tvampm.setText(pm);
                } else if (Row == 3) {
                    tvtime.setText(time);
                    tvampm.setText(pm);
                }
            }
        });
    }

    private void UpdateTime(int i) {
        if (!(i < 1) && !(i > 3)) {
            switch (i) {
                case 1:
                    Row = 1;
                    timeAdapter = new TimeAdapter(TimeSelection.this, mtiming);
                    break;
                case 2:
                    Row = 2;
                    timeAdapter = new TimeAdapter(TimeSelection.this, atiming);
                    break;
                case 3:
                    Row = 3;
                    timeAdapter = new TimeAdapter(TimeSelection.this, etiming);
                    break;
            }
            gv_time.setAdapter(timeAdapter);
        } else {

        }
    }

    public class TimeAdapter extends BaseAdapter {
        private Context mContext;
        private String[] items;


        public TimeAdapter(Context c, String[] items) {
            mContext = c;
            this.items = items;
        }

        public int getCount() {
            return items.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new TextView for each item referenced by the Adapter
        public View getView(final int position, View convertView, ViewGroup parent) {
            final TextView textView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                textView = new TextView(mContext);
                textView.setLayoutParams(new GridView.LayoutParams(130, 56));
                textView.setTextColor(getResources().getColor(R.color.thick_grey));
                textView.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                textView.setTextSize(16);
                textView.setTag(new Integer(position));
            } else {
                textView = (TextView) convertView;
            }

            textView.setText(items[position]);
            if (Row == 1) {
                if (position == 0) {
                    textView.setBackground(getResources().getDrawable(R.drawable.tvtime_background));
                    textView.setTextColor(getResources().getColor(R.color.white));
                }
            }
            return textView;

        }

    }
}
