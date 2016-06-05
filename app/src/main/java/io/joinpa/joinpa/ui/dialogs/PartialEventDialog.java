package io.joinpa.joinpa.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.commands.CancelEventResponse;
import io.joinpa.joinpa.managers.commands.JoinEventResponse;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.util.DateUtil;
import io.joinpa.joinpa.util.ProgressDialogUtil;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class PartialEventDialog extends Dialog {

    @BindView(R.id.image_user)
    ImageView userImage;

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.image_event_icon)
    ImageView eventImage;

    @BindView(R.id.text_event_name)
    TextView eventName;

    @BindView(R.id.text_num_joined)
    TextView tvNumJoined;

    @BindView(R.id.text_date)
    TextView dateLabel;

    @BindView(R.id.text_time)
    TextView timeLabel;

    @BindView(R.id.text_location)
    TextView location;

    private Event event;
    private App app;

    public PartialEventDialog(Context context,Event event) {
        super(context,R.style.dialogStyle);
        this.event = event;
        app = App.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_event_invite);
        ButterKnife.bind(this);
        initComponents();
    }

    public void initComponents() {
        Date date = event.getDate();
        int avatar = event.getHost().getAvatar();
        int eventImg = event.getIcon();
        int numJoined = event.getJoinedList().size();
        String joinText = "Be the first to join this event";
        if (numJoined > 0) joinText = numJoined + " people joined";

        userImage.setImageResource(app.getInternalData().avatarNormal[avatar]);
        eventImage.setImageResource(app.getInternalData().eventIcon[eventImg]);

        username.setText(event.getHost().getUsername());
        eventName.setText(event.getName());
        tvNumJoined.setText(joinText);
        dateLabel.setText(DateUtil.getDate(date));
        timeLabel.setText(DateUtil.getTime(date));
        location.setText(event.getPlace().getName());
    }

    @OnClick(R.id.btn_accept)
    public void acceptEvent() {
        JoinEventResponse response = new JoinEventResponse(event.getId());
        response.execute();
        dismiss();
    }

    @OnClick(R.id.btn_decline)
    public void declineEvent() {
        CancelEventResponse response = new CancelEventResponse(event.getId());
        response.execute();
        dismiss();
    }
}
