package me.bayupaoh.screeningtest.view.event;

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

/**
 * Created by dsantren on 09/08/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private Context context;
    private List<EventDao> list;
    private EventAdapter.EventListener eventListener;

    public EventAdapter(Context context, List<EventDao> list, EventListener eventListener) {
        this.context = context;
        this.list = list;
        this.eventListener = eventListener;
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_event_item, null);
        return new EventAdapter.EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.mImgPhoto);
        holder.mTxtDate.setText(list.get(position).getDate());
        holder.mTxtName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.event_item_date)
        TextView mTxtDate;
        @BindView(R.id.event_item_img)
        ImageView mImgPhoto;
        @BindView(R.id.event_item_name)
        TextView mTxtName;

        public EventHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.event_item_rel)
        public void onClick(){
            eventListener.onClickItem(list.get(getAdapterPosition()).getName());
        }
    }

    public interface EventListener {
        void onClickItem(String param);
    }
}
