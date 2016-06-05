package ske.joinpa.joinpa.ui.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import ske.joinpa.joinpa.R;

/**
 * Created by Peter on 5/31/2016 AD.
 */
public class EventViewHolder extends ViewHolder {

    @BindView(R.id.image_user)
    ImageView userImage;

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.image_event_icon)
    ImageView eventImage;

    @BindView(R.id.text_event_name)
    TextView eventName;

    @BindView(R.id.text_num_joined)
    TextView numJoined;

    @BindView(R.id.text_date)
    TextView dateLabel;

    @BindView(R.id.text_time)
    TextView timeLabel;

    @BindView(R.id.text_location)
    TextView location;

    public EventViewHolder(View v) {
        super(v);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }

}
