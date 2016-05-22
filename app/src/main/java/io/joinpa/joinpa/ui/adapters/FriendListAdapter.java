package io.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import io.joinpa.joinpa.R;
import butterknife.ButterKnife;
import io.joinpa.joinpa.managers.Commands.GetFriendListResponse;
import io.joinpa.joinpa.managers.Commands.ObjectResponse;
import io.joinpa.joinpa.managers.Commands.UnFriendResponse;
import io.joinpa.joinpa.managers.LoadService;
import io.joinpa.joinpa.managers.SwipeRevealLayout;
import io.joinpa.joinpa.managers.ViewBinderHelper;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.models.Message;
import io.joinpa.joinpa.ui.views.SearchNewFriendActivity;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 5/20/16 AD.
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> implements Observer{

    private Context context;
    private List<Friend> friendList;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();
    private Observer observer;

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

        @BindView(R.id.add_friend_layout)
        LinearLayout addFriendLayout;

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
            ViewHolderAddFriend addFriendHolder = (ViewHolderAddFriend)holder;
            addFriendHolder.addFriendLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context , SearchNewFriendActivity.class);
                    context.startActivity(intent);
                }
            });
        }else{
            final Friend friend = friendList.get(position-1);
            ViewHolderList listHolder = (ViewHolderList)holder;
            binderHelper.bind(listHolder.layout,friend.getUsername()); //second parameter is unique string to identify the data.
            listHolder.tvUsername.setText(friend.getUsername());
            // TODO: 5/20/16 AD set avatar
            listHolder.removeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String friendId = friend.getId();
                    Log.e("yyyy" , friendId);
                    UnFriendResponse response = new UnFriendResponse(friendId);
                    response.addObserver(FriendListAdapter.this);
                    response.execute();
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
        return friendList.size()+1;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }


    @Override
    public void update(Observable observable, Object data) {
        if( data == null ) return;
        if( !(data instanceof ObjectResponse)) return;
        ObjectResponse objectResponse = (ObjectResponse)data;
        if(objectResponse.isSuccess()){
            Response<Message> response = (Response<Message>)objectResponse.getData();
            Message message = response.body();
            Toast.makeText(context, message.getMessage(), Toast.LENGTH_SHORT).show();
            loadNewFriendList();
        }else{
            // TODO: 5/22/16 AD handle error
            Log.e("error" , objectResponse.getMessage());
        }
    }

    public void loadNewFriendList() {
        GetFriendListResponse response = new GetFriendListResponse();
        response.addObserver(observer);
        response.execute();
    }
}
