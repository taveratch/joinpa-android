package ske.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.managers.App;
import ske.joinpa.joinpa.managers.Notifier;
import ske.joinpa.joinpa.models.Friend;

/**
 * Created by TAWEESOFT on 5/30/16 AD.
 */
public class SelectFriendAdapter extends RecyclerView.Adapter<SelectFriendAdapter.ViewHolder> {

    private List<Friend> friendList;
    private Context context;
    private List<Friend> selectedFriends;
    private Notifier notifier;
    private App app;

    public SelectFriendAdapter(Context context,List<Friend> friendList,Notifier notifier) {
        this.friendList = friendList;
        this.context = context;
        this.notifier = notifier;
        selectedFriends = new ArrayList<>();
        app = App.getInstance();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private boolean isChecked = false;

        @BindView(R.id.tv_username)
        TextView tvUsername;

        @BindView(R.id.img_avatar)
        ImageView imgAvatar;

        @BindView(R.id.layout)
        FrameLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            Friend friend = friendList.get(getAdapterPosition());
            notifier.setChanged();
            if (!isChecked) {
                layout.setBackgroundResource(R.drawable.rounded_edittext_blue_gray);
                tvUsername.setTextColor(context.getResources().getColor(R.color.colorWhite));
                selectedFriends.add(friend);
                notifier.notifyObservers(true);
            } else {
                tvUsername.setTextColor(context.getResources().getColor(R.color.colorBlueGray));
                layout.setBackgroundResource(R.drawable.rounded_edittext);
                selectedFriends.remove(friend);
                notifier.notifyObservers(false);
            }
            isChecked = !isChecked;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                        .inflate(R.layout.item_partial_friend,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Friend friend = friendList.get(position);
        holder.imgAvatar.setImageResource(app.getInternalData().avatarNormal[friend.getAvatar()]);
        holder.imgAvatar.setBackgroundResource(R.drawable.blue_circle);
        holder.tvUsername.setText(friend.getUsername());
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public List<Friend> getSelectedFriends() {
        return selectedFriends;
    }
}
