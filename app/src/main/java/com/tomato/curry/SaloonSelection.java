package com.tomato.curry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tomato.curry.Adapters.SameSaloonListAdapter;
import com.tomato.curry.Data.TcData;
import com.tomato.curry.Font.TcFontIcons;

import java.util.ArrayList;

public class SaloonSelection extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SameSaloonListAdapter sameSaloonListAdapter;
    private TcFontIcons ivback;
    private String saloonname;
    public TcData objTcData;
    private ImageView iv_saloon_poster;
    private ArrayList<String> saloondata1 = new ArrayList<>();
    private ArrayList<String> saloondata2 = new ArrayList<>();
    private LinearLayout root_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon_selection);
        saloonname = getIntent().getExtras().getString("SaloonName");
        objTcData = new TcData();
        iv_saloon_poster = (ImageView) findViewById(R.id.saloon_poster);
        ivback = (TcFontIcons) findViewById(R.id.ivback);
        mRecyclerView = (RecyclerView) findViewById(R.id.saloon_selection_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if (objTcData.IsMen) {
            for (int i = 0; i <= objTcData.SaloonsMen.length; i++) {
                if (saloonname.equalsIgnoreCase(objTcData.SaloonsMen[i])) {
                    iv_saloon_poster.setImageResource(objTcData.bannerpics_men[i]);
                    if (objTcData.getSaloonmenHashmap(i).size() == 1) {
                        for (String s : objTcData.getSaloonmenHashmap(i).get(objTcData.SaloonsMen[i])) {
                            saloondata1.add(s);
                        }
                        sameSaloonListAdapter = new SameSaloonListAdapter(SaloonSelection.this, saloondata1, 1);

                    } else if (objTcData.getSaloonmenHashmap(i).size() == 2) {
                        for (String s : objTcData.getSaloonmenHashmap(i).get(objTcData.SaloonsMen[i] + "1")) {
                            saloondata1.add(s);
                        }
                        for (String s : objTcData.getSaloonmenHashmap(i).get(objTcData.SaloonsMen[i] + "2")) {
                            saloondata2.add(s);
                        }
                        sameSaloonListAdapter = new SameSaloonListAdapter(SaloonSelection.this, saloondata1, saloondata2, 2);

                    }
                    break;
                }
            }
        } else {
            for (int i = 0; i <= objTcData.SaloonsWoMen.length; i++) {
                if (saloonname.equalsIgnoreCase(objTcData.SaloonsWoMen[i])) {
                    iv_saloon_poster.setImageResource(objTcData.bannerpics_women[i]);
                    if (objTcData.getSaloonwomenHashmap(i).size() == 1) {
                        for (String s : objTcData.getSaloonwomenHashmap(i).get(objTcData.SaloonsWoMen[i])) {
                            saloondata1.add(s);
                        }
                        sameSaloonListAdapter = new SameSaloonListAdapter(SaloonSelection.this, saloondata1, 1);

                    } else if (objTcData.getSaloonwomenHashmap(i).size() == 2) {
                        for (String s : objTcData.getSaloonwomenHashmap(i).get(objTcData.SaloonsWoMen[i] + "1")) {
                            saloondata1.add(s);
                        }
                        for (String s : objTcData.getSaloonwomenHashmap(i).get(objTcData.SaloonsWoMen[i] + "2")) {
                            saloondata2.add(s);
                        }
                        sameSaloonListAdapter = new SameSaloonListAdapter(SaloonSelection.this, saloondata1, saloondata2, 2);

                    }

                    break;
                }
            }
        }
        mRecyclerView.setAdapter(sameSaloonListAdapter);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(SaloonSelection.this, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                final Intent intent = new Intent(SaloonSelection.this, SaloonOverview.class);
//
//startActivity();
//
//            }
//        }));
    }
}
