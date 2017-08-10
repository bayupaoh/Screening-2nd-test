package me.bayupaoh.screeningtest.view.guest;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bayupaoh.screeningtest.R;
import me.bayupaoh.screeningtest.api.dao.GuestDao;
import me.bayupaoh.screeningtest.util.AppConstant;

public class GuestActivity extends AppCompatActivity implements GuestContract.View,GuestAdapter.GuestListener,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.guest_rec_guest)
    RecyclerView mRecGuest;
    @BindView(R.id.guest_swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    GuestAdapter guestAdapter;
    GuestPresenter guestPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        ButterKnife.bind(this);
        
        initPresenter();
        onAttachView();
        setupRecyclerView();
        setupSwipeRefreshLayout();
        loadData();
    }

    private void loadData() {
        guestPresenter.loadDataGuest();
    }

    private void setupSwipeRefreshLayout() {
        mSwipeRefresh.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        mSwipeRefresh.setOnRefreshListener(this);
    }

    private void initPresenter() {
        guestPresenter = new GuestPresenter();
    }

    private void setupRecyclerView() {
        mRecGuest.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        guestAdapter = new GuestAdapter(getApplicationContext(), new ArrayList<GuestDao>(), this);
        mRecGuest.setAdapter(guestAdapter);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onAttachView() {
        guestPresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        guestPresenter.onDetach();
    }

    @Override
    public void showProgress() {
        mSwipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        mSwipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showGuestData(List<GuestDao> guestDao) {
        guestAdapter.replaceData(guestDao);
    }

    @Override
    public void onClickItem(String param1, String param2) {
        int month = Integer.parseInt(param2.substring(6,7));
        if(isPrime(month)){
            Toast.makeText(getApplicationContext(),"its prime!",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"its not prime",Toast.LENGTH_LONG).show();
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra(AppConstant.PARAM_GUEST_NAME,param1);
        returnIntent.putExtra(AppConstant.PARAM_GUEST_BIRTHDAY,param2);
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }
}
