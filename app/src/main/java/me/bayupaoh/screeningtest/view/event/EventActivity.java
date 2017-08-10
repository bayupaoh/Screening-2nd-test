package me.bayupaoh.screeningtest.view.event;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bayupaoh.screeningtest.R;
import me.bayupaoh.screeningtest.api.dao.EventDao;
import me.bayupaoh.screeningtest.util.AppConstant;

public class EventActivity extends AppCompatActivity implements EventAdapter.EventListener {
    @BindView(R.id.event_rec_event)
    RecyclerView mRecEvent;
    EventAdapter eventAdapter;
    ArrayList<EventDao> listEvent = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        setRecyclerView();
    }

    private void setRecyclerView() {
        //data dummy
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Bandung Culinary Night","17 Mei 2017"));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Indonesia Basketball League","17 Mei 2017"));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Unikom Day","17 Mei 2017"));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Developer Day","17 Mei 2017"));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Konser Musik","17 Mei 2017"));

        eventAdapter = new EventAdapter(getApplicationContext(),listEvent,this);
        mRecEvent.setLayoutManager(new LinearLayoutManager(getApplicationContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        mRecEvent.setAdapter(eventAdapter);
    }

    @Override
    public void onClickItem(String param) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(AppConstant.PARAM_EVENT,param);
        setResult(RESULT_OK,returnIntent);
        finish();
    }
}
