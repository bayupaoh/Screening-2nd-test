package me.bayupaoh.screeningtest.view.mapsevent;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bayupaoh.screeningtest.R;
import me.bayupaoh.screeningtest.api.dao.EventDao;

import static android.support.v7.widget.LinearLayoutManager.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsEventFragment extends Fragment implements OnMapReadyCallback,MapsEventAdapter.EventListener {

    @BindView(R.id.mapsevent_rec)
    RecyclerView recEvent;

    private GoogleMap googleMap;
    MapsEventAdapter mapsEventAdapter;
    ArrayList<EventDao> listEvent = new ArrayList<>();
    Marker now;

    public MapsEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps_event, container, false);
        ButterKnife.bind(this,view);

        SupportMapFragment supportMapFragment;
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapevent_map);

        setDataDummy();
        settingRecyclerView();
        supportMapFragment.getMapAsync(this);

        return view;
    }

    private void settingRecyclerView() {
        mapsEventAdapter = new MapsEventAdapter(getActivity(),listEvent,this);
        recEvent.setLayoutManager(new LinearLayoutManager(getActivity(),HORIZONTAL,false));
        recEvent.setAdapter(mapsEventAdapter);
    }

    private void setDataDummy() {
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Bandung Culinary Night","17 Mei 2017",-6.8848417,107.6119303));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Indonesia Basketball League","17 Mei 2017",-6.884847, 107.614119));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Unikom Day","17 Mei 2017",-6.8838404,107.6126062));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Developer Day","17 Mei 2017",-6.8863473,107.6142107));
        listEvent.add(new EventDao("http://jadwalevent.web.id/wp-content/uploads/2014/01/Kunjungi-Braga-Culinary-Night.jpg","Konser Musik","17 Mei 2017",-6.8870361,107.6153004));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;


        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-6.8838404,107.6126062)));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        googleMap.animateCamera(zoom);

        for (EventDao eventDao:listEvent) {
            this.googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(eventDao.getLatitude(),eventDao.getLongitude()))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }
        now = googleMap.addMarker(new MarkerOptions().position(new LatLng(listEvent.get(0).getLatitude(),listEvent.get(0).getLongitude())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
    }

    @Override
    public void onClick(EventDao eventDao) {
        if(now != null){
            now.remove();

        }

        LatLng latLng = new LatLng(eventDao.getLatitude(), eventDao.getLongitude());
        now = googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
    }
}
