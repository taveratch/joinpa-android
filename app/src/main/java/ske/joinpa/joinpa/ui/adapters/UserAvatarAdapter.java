package ske.joinpa.joinpa.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.managers.App;


public class UserAvatarAdapter extends RecyclerView.Adapter<UserAvatarAdapter.ViewHolder> {

    private static int selectedAvatar = 0;
    private App app;

    public UserAvatarAdapter() {
        app = App.getInstance();
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

        if (position == selectedAvatar)
            holder.avatar.setBackgroundResource(R.drawable.red_circle);
        else
            holder.avatar.setBackgroundResource(R.drawable.blue_circle);
    }

    @Override
    public int getItemCount() {
        return app.getInternalData().avatarNormal.length;
    }

    public class ViewHolder extends ske.joinpa.joinpa.ui.viewholders.ViewHolder implements View.OnClickListener {

        @BindView(R.id.avatar_image_view)
        ImageView avatar;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
            selectedAvatar = getAdapterPosition();
            UserAvatarAdapter.this.notifyDataSetChanged();
        }
    }
}
