package io.joinpa.joinpa.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SingleSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;


public class UserAvatarAdapter extends RecyclerView.Adapter<UserAvatarAdapter.ViewHolder> {

    private static MultiSelector selector = new SingleSelector();
    private static int selectedAvatar = 0;
    private App app;

    public UserAvatarAdapter() {
        app = App.getInstance();
    }

    public class ViewHolder extends SwappingHolder implements View.OnClickListener {

        @BindView(R.id.avatar_image_view)
        ImageView avatar;

        public ViewHolder(View v) {
            super(v, selector);
            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
        }


        @Override
        public void onClick(View v) {
            selectedAvatar = getAdapterPosition();
//            selector.setSelectable(true); // enter selection mode
//            selector.setSelected(this, true); // set myViewHolder to selected
            Log.e("Click", "Clicked on " + getAdapterPosition());
            UserAvatarAdapter.this.notifyDataSetChanged();
        }
    }

    public int getSelectedAvatar() {
        return selectedAvatar;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_avatar, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.avatar.setImageResource(app.getInternalData().avatarNormal[position]);
        if(position == selectedAvatar)
            holder.avatar.setBackgroundResource(R.drawable.red_circle);
        else
            holder.avatar.setBackgroundResource(R.drawable.blue_circle);
    }

    @Override
    public int getItemCount() {
        return app.getInternalData().avatarNormal.length;
    }




}
