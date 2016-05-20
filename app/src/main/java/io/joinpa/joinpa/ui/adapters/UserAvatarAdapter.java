package io.joinpa.joinpa.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SingleSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;


public class UserAvatarAdapter extends RecyclerView.Adapter<UserAvatarAdapter.ViewHolder> {

    private static MultiSelector selector = new SingleSelector();
    private static int selectedAvatar = 0;

    public static class ViewHolder extends SwappingHolder implements View.OnClickListener {

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
            selector.setSelectable(true); // enter selection mode
            selector.setSelected(this, true); // set myViewHolder to selected
            Log.e("Click", "Clicked on " + getAdapterPosition());
        }
    }

    public int getSelectedAvatar() {
        return selectedAvatar;
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
