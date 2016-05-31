package io.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.models.Place;

/**
 * Created by TAWEESOFT on 5/30/16 AD.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private Context context;
    private List<Place> places;
    private int selectedPlace = 0;

    public LocationAdapter(Context context, List<Place> places) {
        this.context = context;
        this.places = places;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class CreateLocationViewHolder extends ViewHolder implements View.OnClickListener{
        public CreateLocationViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO: 5/31/16 AD Add new location activity
        }
    }

    class ItemLocationViewHolder extends ViewHolder implements View.OnClickListener{
        @BindView(R.id.tv_location_name)
        TextView tvLocation;

        @BindView(R.id.layout)
        RelativeLayout layout;

        public ItemLocationViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            selectedPlace = getAdapterPosition();
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == -1) {
            view = LayoutInflater.from(context)
                        .inflate(R.layout.item_add_new_location,parent,false);
        }else {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_location, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position == places.size()) return;
        Place place = places.get(position);
        ItemLocationViewHolder itemHolder = (ItemLocationViewHolder)holder;
        itemHolder.tvLocation.setText(place.getName());
        if(selectedPlace == position) itemHolder.layout.setBackgroundResource(R.drawable.rounded_edittext_blue_gray);
    }


    @Override
    public int getItemViewType(int position) {
        if(position == places.size()) return -1;
        return position;
    }

    @Override
    public int getItemCount() {
        return places.size()+1;
    }
}