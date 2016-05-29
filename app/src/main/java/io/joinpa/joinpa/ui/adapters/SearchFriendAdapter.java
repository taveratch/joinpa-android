package io.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.managers.Commands.ObjectResponse;
import io.joinpa.joinpa.managers.Commands.SendFriendRequestResponse;
import io.joinpa.joinpa.models.Friend;
import io.joinpa.joinpa.models.Message;
import retrofit2.Response;

/**
 * Created by TAWEESOFT on 5/21/16 AD.
 */
public class SearchFriendAdapter extends RecyclerView.Adapter<SearchFriendAdapter.ViewHolder> implements Observer{

    private List<Friend> searchResult;
    private Context context;
    private App app;

    public SearchFriendAdapter(Context context , List<Friend> searchResult) {
        this.context = context;
        this.searchResult = searchResult;
        app = App.getInstance();
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
        final Friend friend = searchResult.get(position);
        if(!friend.isFriend()) {
            holder.imgAdd.setImageResource(R.drawable.add_friend_action);
            holder.imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SendFriendRequestResponse response = new SendFriendRequestResponse(friend.getId());
                    response.addObserver(SearchFriendAdapter.this);
                    response.execute();
                    holder.imgAdd.setImageResource(R.drawable.already_friend_action); //set icon to already be friend
                }
            });
        }else
            holder.imgAdd.setImageResource(R.drawable.already_friend_action);
        holder.imgAvatar.setImageResource(app.getInternalData().avatarNormal[friend.getAvatar()]);
        holder.imgAvatar.setBackgroundResource(R.drawable.blue_circle);
        holder.tvUsername.setText(friend.getUsername());
        holder.tvFriendCount.setText(friend.getFriendList().size() + " friends");
    }

    @Override
    public int getItemCount() {
        return searchResult.size();
    }

    @Override
    public void update(Observable observable, Object data) {
        if( data == null ) return;
        if (! (data instanceof ObjectResponse)) return;
        ObjectResponse objectResponse = (ObjectResponse)data;
        Response<Message> response = (Response<Message>)objectResponse.getData();
        if(response.isSuccessful())
            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
        else{ // TODO: 5/21/16 AD Maybe change it to show error message from client (detected by status)
            Gson gson = new Gson();
            Message message = gson.fromJson(objectResponse.getMessage(),Message.class);
            Toast.makeText(context, message.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
