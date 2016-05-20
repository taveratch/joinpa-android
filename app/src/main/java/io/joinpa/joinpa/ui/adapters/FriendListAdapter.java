package io.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.SwipeRevealLayout;
import io.joinpa.joinpa.managers.ViewBinderHelper;
import io.joinpa.joinpa.models.Friend;

/**
 * Created by TAWEESOFT on 5/20/16 AD.
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {

    private Context context;
    private List<Friend> friendList;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();
    public FriendListAdapter(Context context , List<Friend> friendList) {
        this.context = context;
        this.friendList = friendList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }

    class ViewHolderList extends ViewHolder {

        @BindView(R.id.tv_username)
        TextView tvUsername;

        @BindView(R.id.img_avatar)
        ImageView imgAvatar;

        @BindView(R.id.swipeLayout)
        SwipeRevealLayout layout;

        @BindView(R.id.removeLayout)
        FrameLayout removeLayout;

        public ViewHolderList(View itemView) {
            super(itemView);
        }
    }

    class ViewHolderAddFriend extends ViewHolder {
        public ViewHolderAddFriend(View itemView) {
            super(itemView);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == 0){
            view = LayoutInflater.from(context).inflate(R.layout.add_new_friend_item_layout , parent,false);
            return new ViewHolderAddFriend(view);
        }
        view = LayoutInflater.from(context).inflate(R.layout.friend_item_layout , parent , false);
        return new ViewHolderList(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position==0) {
            // TODO: 5/20/16 AD start search new friend activity
        }else{
            Friend friend = friendList.get(position);
            ViewHolderList listHolder = (ViewHolderList)holder;
            binderHelper.bind(listHolder.layout,friend.getUsername()); //second parameter is unique string to identify the data.
            listHolder.tvUsername.setText(friend.getUsername());
            // TODO: 5/20/16 AD set avatar
            listHolder.removeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 5/20/16 AD remove friend
                }
            });
        }
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onSaveInstanceState(Bundle)}
     */
    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onRestoreInstanceState(Bundle)}
     */
    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }
}
