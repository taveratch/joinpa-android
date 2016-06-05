package ske.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.managers.App;
import ske.joinpa.joinpa.models.Friend;

/**
 * Created by TAWEESOFT on 6/1/16 AD.
 */
public class WhoComingAdapter extends RecyclerView.Adapter<WhoComingAdapter.ViewHolder> {

    private List<Friend> friends;
    private Context context;
    private App app;

    public WhoComingAdapter(Context context ,List<Friend> friends) {
        this.friends = friends;
        this.context = context;
        app = App.getInstance();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                        .inflate(R.layout.item_who_coming,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.imgAvatar.setImageResource(app.getInternalData().avatarNormal[friend.getAvatar()]);
        holder.tvUsername.setText(friend.getUsername());
        holder.tvFriendCount.setText(friend.getFriendList().size()+"");
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    class ViewHolder extends ske.joinpa.joinpa.ui.viewholders.ViewHolder {

        @BindView(R.id.img_avatar)
        ImageView imgAvatar;

        @BindView(R.id.tv_username)
        TextView tvUsername;

        @BindView(R.id.tv_friend_count)
        TextView tvFriendCount;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
