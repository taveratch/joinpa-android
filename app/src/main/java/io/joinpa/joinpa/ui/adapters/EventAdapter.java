package io.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.util.DateUtil;

/**
 * Created by Peter on 5/20/2016 AD.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private App app;

    private Context context;
    private List<Event> events;

    public EventAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
        app = App.getInstance();
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

        Event event = events.get(position);
        int avatar = event.getHost().getAvatar();
        Date date = event.getDate();

        holder.userImage.setImageResource(app.getInternalData().avatarNormal[avatar]);
        holder.username.setText(event.getHost().getUsername());
        holder.eventName.setText(event.getName());
        holder.dateLabel.setText(DateUtil.getDay(date));
        holder.timeLabel.setText(DateUtil.getTime(date));
        holder.location.setText(event.getPlace().getName());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_user)
        ImageView userImage;

        @BindView(R.id.username)
        TextView username;

        @BindView(R.id.text_event_name)
        TextView eventName;

        @BindView(R.id.text_date)
        TextView dateLabel;

        @BindView(R.id.text_time)
        TextView timeLabel;

        @BindView(R.id.text_location)
        TextView location;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }

}
