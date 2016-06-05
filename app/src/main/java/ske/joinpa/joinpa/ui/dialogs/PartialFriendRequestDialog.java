package ske.joinpa.joinpa.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.managers.App;
import ske.joinpa.joinpa.managers.commands.AcceptFriendRequestResponse;
import ske.joinpa.joinpa.managers.commands.UnFriendResponse;
import ske.joinpa.joinpa.models.Friend;

/**
 * Created by TAWEESOFT on 6/4/16 AD.
 */
public class PartialFriendRequestDialog extends Dialog {

    @BindView(R.id.tv_username)
    TextView tvUsername;

    @BindView(R.id.img_avatar)
    ImageView imgAvatar;

    @BindView(R.id.divider)
    View divider;

    private Friend friend;
    private App app;

    public PartialFriendRequestDialog(Context context , Friend friend) {
        super(context,R.style.dialogStyle);
        this.friend = friend;
        app = App.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_partial_friend_request);
        ButterKnife.bind(this);
        initComponents();
    }

    public void initComponents() {
        divider.setVisibility(View.GONE); //hide divider
        tvUsername.setText(friend.getUsername());
        imgAvatar.setImageResource(app.getInternalData().avatarNormal[friend.getAvatar()]);
    }

    @OnClick(R.id.btn_accept)
    public void accept() {
        AcceptFriendRequestResponse response = new AcceptFriendRequestResponse(friend.getId());
        response.execute();
        dismiss();
    }

    @OnClick(R.id.btn_decline)
    public void decline() {
        UnFriendResponse response = new UnFriendResponse(friend.getId());
        response.execute();
        dismiss();
    }
}
