package me.bayupaoh.screeningtest.view.guest;

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
import me.bayupaoh.screeningtest.api.dao.GuestDao;

/**
 * Created by dsantren on 09/08/2017.
 */

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.GuestHolder> {
    private Context context;
    private List<GuestDao> list;
    private GuestAdapter.GuestListener guestListener;

    public GuestAdapter(Context context, List<GuestDao> list, GuestListener guestListener) {
        this.context = context;
        this.list = list;
        this.guestListener = guestListener;
    }

    @Override
    public GuestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_guest_item, null);
        return new GuestAdapter.GuestHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestHolder holder, int position) {
        Glide.with(context).load("http://www.saparch.com/public/widget/1_widget_john-doe.png").into(holder.mImgPhoto);
        holder.mTxtDate.setText(list.get(position).getBirthdate());
        holder.mTxtName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void replaceData(List<GuestDao> guestDao) {
        list = guestDao;
        notifyDataSetChanged();
    }

    public class GuestHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.guest_item_date)
        TextView mTxtDate;
        @BindView(R.id.guest_item_name)
        TextView mTxtName;
        @BindView(R.id.guest_item_img)
        ImageView mImgPhoto;

        public GuestHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.guest_item_rel)
        public void onClick(){
            guestListener.onClickItem(list.get(getAdapterPosition()).getName(),list.get(getAdapterPosition()).getBirthdate());
        }
    }

    public interface GuestListener{
        void onClickItem(String param1,String param2);
    }
}
