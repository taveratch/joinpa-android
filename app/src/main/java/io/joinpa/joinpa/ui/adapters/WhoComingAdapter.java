package io.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.ui.viewholders.ViewHolder;

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
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    class ViewHolder extends io.joinpa.joinpa.ui.viewholders.ViewHolder {

        @BindView(R.id.img_avatar)
        ImageView imgAvatar;

        @BindView(R.id.tv_username)
        TextView tvUsername;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
