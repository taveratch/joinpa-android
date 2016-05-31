package io.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;

/**
 * Created by TAWEESOFT on 5/30/16 AD.
 */
public class EventIconAdapter extends RecyclerView.Adapter<EventIconAdapter.ViewHolder> {

    private int[] icons;
    private Context context;
    private int selectedItem = 0;

    public EventIconAdapter(Context context ,int[] icons) {
        this.icons = icons;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.avatar_image_view)
        ImageView imgIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
            selectedItem = index;
            EventIconAdapter.this.notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                        .inflate(R.layout.item_avatar , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imgIcon.setImageResource(icons[position]);
        if(position == selectedItem) holder.imgIcon.setBackgroundResource(R.drawable.green_circle);
        else holder.imgIcon.setBackgroundResource(R.drawable.blue_circle);
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    public int getSelectedItem() {
        return selectedItem;
    }
}
