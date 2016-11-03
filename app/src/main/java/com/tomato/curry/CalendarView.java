package com.tomato.curry;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.PaintDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tomato.curry.Font.TcFontIcons;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class CalendarView extends LinearLayout {
    // for logging
    private static final String LOGTAG = "Calendar View";

    // how many days to show, defaults to six weeks, 42 days
    private static final int DAYS_COUNT = 42;

    // default showingdate format
    private static final String DATE_FORMAT = "MMM yyyy";

    // showingdate format
    private String dateFormat;

    // current displayed month
    private Calendar currentDate = Calendar.getInstance();

    //event handling
    private EventHandler eventHandler = null;

    // internal components
    private LinearLayout header;
    private TcFontIcons btnPrev;
    private TcFontIcons btnNext;
    private TextView txtDate;
    private GridView grid;
    private Date showingdate, currentdate;
    private int showingmonth, currentMonth, showingyear, currentyear;

//    // seasons' rainbow
//    int[] rainbow = new int[] {
//            R.color.summer,
//            R.color.fall,
//            R.color.winter,
//            R.color.spring
//    };

    // month-season association (northern hemisphere, sorry australia :)
    int[] monthSeason = new int[]{2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2};

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    /**
     * Load control xml layout
     */
    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_control, this);

        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();

        updateCalendar();
    }

    private void loadDateFormat(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        try {
            // try to load provided showingdate format, and fallback to default otherwise
//           dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
            if (dateFormat == null)
                dateFormat = DATE_FORMAT;
        } finally {
            ta.recycle();
        }
    }

    private void assignUiElements() {
        // layout is inflated, assign local variables to components
        header = (LinearLayout) findViewById(R.id.calendar_header);
        btnPrev = (TcFontIcons) findViewById(R.id.calendar_prev_button);
        btnNext = (TcFontIcons) findViewById(R.id.calendar_next_button);
        txtDate = (TextView) findViewById(R.id.calendar_date_display);
        grid = (GridView) findViewById(R.id.calendar_grid);
    }

    private void assignClickHandlers() {
        // add one month and refresh UI
        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNext.setTextColor(getResources().getColor(R.color.orange));
                btnPrev.setTextColor(getResources().getColor(R.color.black));
                showingmonth = showingdate.getMonth();
                currentMonth = currentdate.getMonth()+1;
                showingyear = showingdate.getYear();
                currentyear = currentdate.getYear();
                if (!(showingmonth >= currentMonth + 2)) {
                    currentDate.add(Calendar.MONTH, 1);
                    updateCalendar();
                } else {
                    Toast.makeText(getContext(), "Two Months Service Only", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // subtract one month and refresh UI
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPrev.setTextColor(getResources().getColor(R.color.orange));
                btnNext.setTextColor(getResources().getColor(R.color.black));
                showingmonth = showingdate.getMonth();
                currentMonth = currentdate.getMonth()+1;
                showingyear = showingdate.getYear();
                currentyear = currentdate.getYear();
                if (!(showingmonth <= currentMonth)) {
                    currentDate.add(Calendar.MONTH, -1);
                    updateCalendar();
                } else {
                    Toast.makeText(getContext(), "No Service For Previous Month", Toast.LENGTH_SHORT).show();

                }

            }
        });

        // long-pressing a day
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> view, View cell, int position, long id) {
                for (int visiblePosition = grid.getFirstVisiblePosition(); visiblePosition <= grid.getLastVisiblePosition(); visiblePosition++) {
                    View item = grid.getChildAt(visiblePosition);
                    TextView textView = (TextView) item;
                    if (String.format("#%06X", (0xFFFFFF & textView.getCurrentTextColor())).equalsIgnoreCase("#FFFFFF")) {
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView.setBackgroundColor(getResources().getColor(R.color.white));
                    } else if (String.format("#%06X", (0xFFFFFF & textView.getCurrentTextColor())).equalsIgnoreCase("#e9e7e7")) {
                        textView.setTextColor(getResources().getColor(R.color.colorGrey));
                    } else if (String.format("#%06X", (0xFFFFFF & textView.getCurrentTextColor())).equalsIgnoreCase("#000000")) {
                        textView.setTextColor(getResources().getColor(R.color.black));
                    }
                }
                View item = grid.getChildAt(position);
                TextView textView = (TextView) item;
                if (!String.format("#%06X", (0xFFFFFF & textView.getCurrentTextColor())).equalsIgnoreCase("#e9e7e7")) {
                    textView.setBackground(getResources().getDrawable(R.drawable.tvdate_background));
                    textView.setTextColor(getResources().getColor(R.color.white));
                }
                eventHandler.onDayLongPress((Date) view.getItemAtPosition(position));

            }
        });
    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar() {
        updateCalendar(null);
    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar(HashSet<Date> events) {
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar) currentDate.clone();

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        // fill cells
        while (cells.size() < DAYS_COUNT) {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // update grid
        grid.setAdapter(new CalendarAdapter(getContext(), cells, events));

        // update title
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        txtDate.setText(sdf.format(currentDate.getTime()));

//        // set header color according to current season
//        int month = currentDate.get(Calendar.MONTH);
//        int season = monthSeason[month];
//        int color = rainbow[season];

        // header.setBackgroundColor(getResources().getColor(color));
    }


    private class CalendarAdapter extends ArrayAdapter<Date> {
        // days with events
        private HashSet<Date> eventDays;

        // for view inflation
        private LayoutInflater inflater;

        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays) {
            super(context, R.layout.calendar_day, days);
            this.eventDays = eventDays;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            // day in question
            showingdate = getItem(position);
            int day = showingdate.getDate();
            int month = showingdate.getMonth();
            int year = showingdate.getYear();

            // currentdate
            currentdate = new Date();

            // inflate item if it does not exist yet
            if (view == null)
                view = inflater.inflate(R.layout.calendar_day, parent, false);

            // if this day has an event, specify event image
            view.setBackgroundResource(0);
            if (eventDays != null) {
                for (Date eventDate : eventDays) {
                    if (eventDate.getDate() == day &&
                            eventDate.getMonth() == month &&
                            eventDate.getYear() == year) {
                        // mark this day for event
//                        view.setBackgroundResource(R.drawable.reminder);
                        break;
                    }
                }
            }

            // clear styling
            ((TextView) view).setTypeface(null, Typeface.NORMAL);
            ((TextView) view).setTextColor(Color.BLACK);

            if (month < currentdate.getMonth() || month > currentdate.getMonth() + 2) {
                // if this day is outside current month, grey it out
                ((TextView) view).setTextColor(getResources().getColor(R.color.greyed_out));
            } else if (day == currentdate.getDate() && month == currentdate.getMonth()) {
                // if it is currentdate, set it to blue/bold
                ((TextView) view).setTextColor(getResources().getColor(R.color.white));
                ((TextView) view).setBackground(getResources().getDrawable(R.drawable.tvdate_background));
            }

            // set text
            ((TextView) view).setText(String.valueOf(showingdate.getDate()));

            return view;
        }
    }

    /**
     * Assign event handler to be passed needed events
     */
    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    /**
     * This interface defines what events to be reported to
     * the outside world
     */
    public interface EventHandler {
        void onDayLongPress(Date date);
    }
}