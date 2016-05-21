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
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.models.Friend;

/**
 * Created by TAWEESOFT on 5/21/16 AD.
 */
public class SearchFriendAdapter extends RecyclerView.Adapter<SearchFriendAdapter.ViewHolder>{

    private List<Friend> searchResult;
    private Context context;

    public SearchFriendAdapter(Context context , List<Friend> searchResult) {
        this.context = context;
        this.searchResult = searchResult;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_add)
        ImageView imgAdd;

        @BindView(R.id.tv_username)
        TextView tvUsername;

        @BindView(R.id.img_avatar)
        ImageView imgAvatar;

        @BindView(R.id.tv_friend_count)
        TextView tvFriendCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_friend_item_layout , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Friend friend = searchResult.get(position);
        if(!friend.isFriend()) {
            holder.imgAdd.setImageResource(R.drawable.add_friend_action);
            holder.imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 5/21/16 AD send add friend request
                    holder.imgAdd.setImageResource(R.drawable.already_friend_action); //set icon to already be friend
                }
            });
        }else
            holder.imgAdd.setImageResource(R.drawable.already_friend_action);
        // TODO: 5/21/16 AD set image avatar
        holder.tvUsername.setText(friend.getUsername());
        holder.tvFriendCount.setText(friend.getFriendList().size() + " friends");
    }

    @Override
    public int getItemCount() {
        return searchResult.size();
    }
}
