package me.bayupaoh.screeningtest.view.event;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bayupaoh.screeningtest.R;
import me.bayupaoh.screeningtest.api.dao.EventDao;
import me.bayupaoh.screeningtest.util.AppConstant;
import me.bayupaoh.screeningtest.view.mapsevent.MapsEventFragment;

public class EventActivity extends AppCompatActivity implements EventAdapter.EventListener {
    @BindView(R.id.event_rec_event)
    RecyclerView mRecEvent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.event_container_body)
    FrameLayout frameLayout;

    FragmentManager fragmentManager;
    EventAdapter eventAdapter;
    ArrayList<EventDao> listEvent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

        setRecyclerView();
        settingToolbar();
        settingContainer();
    }

    private void settingContainer() {
        fragmentManager = getSupportFragmentManager();
        frameLayout.setVisibility(View.GONE);
    }

    private void settingToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back_article_normal);
    }

    private void setRecyclerView() {
        //data dummy
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Bandung Culinary Night","17 Mei 2017",-6.8848417,107.6119303));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Indonesia Basketball League","17 Mei 2017",-6.884847, 107.614119));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Unikom Day","17 Mei 2017",-6.8838404,107.6126062));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Developer Day","17 Mei 2017",-6.8863473,107.6142107));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Konser Musik","17 Mei 2017",-6.8870361,107.6153004));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        frameLayout.setVisibility(View.GONE);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_action_media:
                frameLayout.setVisibility(View.VISIBLE);
                fragmentManager.beginTransaction().replace(R.id.event_container_body, new MapsEventFragment()).commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
