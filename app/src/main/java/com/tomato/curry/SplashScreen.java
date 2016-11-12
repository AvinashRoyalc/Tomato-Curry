package com.tomato.curry;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tomato.curry.Adapters.CitySelectionAdapter;
import com.tomato.curry.Data.TcData;
import com.tomato.curry.ItemAnimator.ItemAnimatorFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class SplashScreen extends Activity {
    private final int SPLASH_LENGTH = 200;
    private int mContentViewHeight;
    public TextView tvtitle;
private CitySelectionAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(Build.VERSION.SDK_INT< 21){
//            showLocationDialog();
//
//        }

        if (getStringValue(SplashScreen.this, "id").isEmpty()) {
            apiCalling();
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
//                startActivity(new Intent(SplashScreen.this,CitySelection.class));
//                finish();
                onFakeCreate();
            }
        }, SPLASH_LENGTH);
    }

    private void apiCalling() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
            calendar.setTime(new Date());
            Date time = calendar.getTime();
            SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateAsString = outputFmt.format(time);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("app_id", "90de1f88-6b48-4698-9c9c-dd74c2208c4d");
            //jsonObject.put("identifier", Utils.gcm);
            jsonObject.put("notification_types", 1);
            jsonObject.put("language", Locale.getDefault().getDisplayLanguage());
            jsonObject.put("timezone", dateAsString);
            jsonObject.put("device_os", Build.VERSION.RELEASE);
            jsonObject.put("device_type", "1");
            jsonObject.put("device_model", Build.MODEL);

            Log.e("params", jsonObject.toString());

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    "https://onesignal.com/api/v1/players", jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.has("success")) {
                                    if (response.getBoolean("success")) {
                                        setStringValue(SplashScreen.this, "id", response.getString("id"));
                                        Log.e("id", response.getString("id"));
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            })
            {
                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Accept", "application/json");
                    return headers;
                }
            };

            Volley.newRequestQueue(SplashScreen.this).add(jsonObjReq).setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void setStringValue(Context ctx, String key, String value) {
        SharedPreferences pref = ctx.getSharedPreferences("ABHI", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(key, value);
        edit.commit();
    }

    private static String getStringValue(Context ctx, String key) {
        String value = "";
        SharedPreferences pref = ctx.getSharedPreferences("ABHI", Context.MODE_PRIVATE);
        value = pref.getString(key, value);
        if (value != null)
            return value;
        else return "";
    }

    private void onFakeCreate() {
        setContentView(R.layout.activity_splash_screen);
        tvtitle = (TextView) findViewById(R.id.tvtitle);
        ArrayList myDataset = new ArrayList<>();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.city_rview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(ItemAnimatorFactory.slidein());
        addData(myDataset);
        mAdapter = new CitySelectionAdapter();
        mRecyclerView.setAdapter(mAdapter);
        tvtitle.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        tvtitle.getViewTreeObserver().removeOnPreDrawListener(this);
                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

                        tvtitle.measure(widthSpec, heightSpec);
                        mContentViewHeight = tvtitle.getHeight();
                        collapseToolbar();
                        return true;
                    }
                });
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(SplashScreen.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(SplashScreen.this, CityOverview.class);
               callingActivity(position,intent);
            }
        }));
    }
    private  void callingActivity(int Position,Intent intent){
        switch (Position) {
            case 0:
                intent.putExtra("city", "TIRUPATHI");
                intent.putExtra("city_description", "The city which looks like a foot of god \nand the people are more blessed to have here.");
                intent.putExtra("image", R.drawable.tpty);
                startActivity(intent);
                break;
            case 1:
                intent.putExtra("city", "NELLORE");
                intent.putExtra("city_description", "The city is located on the banks of the \nPenna river and it is blessed with Asia's \nbiggest Krishnapatnam port.");
                intent.putExtra("image", R.drawable.nellore);
                startActivity(intent);
                break;
            case 2:
                intent.putExtra("city", "CHENNAI");
                intent.putExtra("city_description", "The city has the people who has strong \nenough dare to face any Natural \ncalamity. so, hail to Chennaities.");
                intent.putExtra("image", R.drawable.chennai);
                startActivity(intent);
                break;
            case 3:
                intent.putExtra("city", "HYDERABAD");
                intent.putExtra("city_description", "The city which cant describe in a single \nword and can refer to the city of \nnight life, city of Biriyani,\ncity of Mughal heritage bla bla.");
                intent.putExtra("image", R.drawable.hyd);
                startActivity(intent);
                break;
            case 4:
                intent.putExtra("city", "BANGALORE");
                intent.putExtra("city_description", "The proudest city of India which has the \nbiggest I.T hub and as well as known of city of Lamborghini.");
                intent.putExtra("image", R.drawable.bangalore);
                startActivity(intent);
                break;
            case 5:
                intent.putExtra("city", "VIJAYAWADA");
                intent.putExtra("city_description", "One of the biggest bus station is located \nin this city and it is blessed with plenty of water resources here.");
                intent.putExtra("image", R.drawable.vijayawada);
                startActivity(intent);
                break;
            case 6:
                intent.putExtra("city", "VISAKAPATNAM");
                intent.putExtra("city_description", "One of the most beautiful cities of India \nincludes hill stations, sea shores and \nplays a key role in Tollywood industry \nfor locations.");
                intent.putExtra("image", R.drawable.visakapatnam);
                startActivity(intent);
        }
    }
    private void collapseToolbar() {
        int toolBarHeight;
        TypedValue tv = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        toolBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        ValueAnimator valueHeightAnimator = ValueAnimator.ofInt(mContentViewHeight, toolBarHeight);
        valueHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams lp = tvtitle.getLayoutParams();
                lp.height = (Integer) animation.getAnimatedValue();
                tvtitle.setLayoutParams(lp);
            }
        });

        valueHeightAnimator.start();
        valueHeightAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                // Fire item animator
                mAdapter.addAll(ModelItem.getFakeItems());


            }
        });
    }

    private void addData(ArrayList<String> myDataset) {
        TcData objtcdata = new TcData();
        for (int i = 0; i < objtcdata.CitiesArray.length; i++) {
            myDataset.add(objtcdata.CitiesArray[i]);

        }

    }



}
