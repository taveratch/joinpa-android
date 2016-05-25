package io.joinpa.joinpa.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;

/**
 * Created by Peter on 5/20/2016 AD.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_user)
        ImageView userImage;

        @BindView(R.id.username)
        TextView username;

        @BindView(R.id.text_event_name)
        TextView eventName;

        @BindView(R.id.text_time)
        TextView timeLabel;

        @BindView(R.id.text_location)
        TextView location;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_explore, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
