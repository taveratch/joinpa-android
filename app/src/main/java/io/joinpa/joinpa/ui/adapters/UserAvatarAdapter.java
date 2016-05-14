package io.joinpa.joinpa.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;


public class UserAvatarAdapter extends RecyclerView.Adapter<UserAvatarAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar_image_view)
        ImageView avatar;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.avatar_item, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.avatar.setImageResource();
        holder.avatar.setBackgroundColor(Color.BLUE);
    }

    @Override
    public int getItemCount() {
        //TODO

        return 8;
    }




}
