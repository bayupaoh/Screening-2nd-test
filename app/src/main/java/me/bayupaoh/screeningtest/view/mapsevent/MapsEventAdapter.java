package me.bayupaoh.screeningtest.view.mapsevent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bayupaoh.screeningtest.R;
import me.bayupaoh.screeningtest.api.dao.EventDao;
import me.bayupaoh.screeningtest.view.event.EventAdapter;

/**
 * Created by codelabsunikom on 8/10/17.
 */

public class MapsEventAdapter extends RecyclerView.Adapter<MapsEventAdapter.MapsEventHolder>{
    private Context context;
    private List<EventDao> list;
    private MapsEventAdapter.EventListener eventListener;

    public MapsEventAdapter(Context context, List<EventDao> list, EventListener eventListener) {
        this.context = context;
        this.list = list;
        this.eventListener = eventListener;
    }

    @Override
    public MapsEventAdapter.MapsEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_maps_event_item, null);
        return new MapsEventAdapter.MapsEventHolder(view);
    }

    @Override
    public void onBindViewHolder(MapsEventAdapter.MapsEventHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getImage()).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface EventListener {
        void onClick(EventDao eventDao);
    }

    public class MapsEventHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mapsevent_item_name)
        TextView txtName;
        @BindView(R.id.mapsevent_item_photo)
        ImageView imgPhoto;

        public MapsEventHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.mapsevent_item_rel)
        public void onClick(){
            eventListener.onClick(list.get(getAdapterPosition()));
        }
    }
}
