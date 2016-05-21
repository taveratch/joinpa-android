package io.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.Commands.AcceptFriendRequestResponse;
import io.joinpa.joinpa.managers.Commands.ObjectResponse;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.models.Message;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 5/21/16 AD.
 */
public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.ViewHolder> implements Observer {

    private Context context;
    private List<Friend> friendRequest;

    public FriendRequestAdapter(Context context, List<Friend> friendRequest) {
        this.context = context;
        this.friendRequest = friendRequest;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_username)
        TextView tvUsername;

        @BindView(R.id.img_avatar)
        ImageView imgAvatar;

        @BindView(R.id.btn_accept)
        Button btnAccept;

        @BindView(R.id.btn_decline)
        Button btnDecline;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friend_request_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Friend friend = friendRequest.get(position);
        holder.tvUsername.setText(friend.getUsername());
        // TODO: 5/21/16 AD set avatar
        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptRequest(friend);
                friendRequest.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declineRequest(friend);
            }
        });
    }

    public void acceptRequest(Friend friend) {
        AcceptFriendRequestResponse response = new AcceptFriendRequestResponse(friend.getId());
        response.addObserver(this);
        response.execute();
    }

    public void declineRequest(Friend friend) {

    }

    @Override
    public int getItemCount() {
        return friendRequest.size();
    }

    @Override
    public void update(Observable observable, Object data) {
        if( data == null) return;
        if( !(data instanceof ObjectResponse)) return;
        ObjectResponse objectResponse = (ObjectResponse)data;
        if(objectResponse.isSuccess()){
            Response<Message> response = (Response<Message>)objectResponse.getData();
            Message message = response.body();
            Toast.makeText(context, message.getMessage(), Toast.LENGTH_SHORT).show();
        }else{
            // TODO: 5/21/16 AD handle error
            Log.e("error" , objectResponse.getMessage());
            Toast.makeText(context, objectResponse.getMessage() , Toast.LENGTH_SHORT).show();
        }
    }
}
