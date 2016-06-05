package ske.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.managers.Notifier;
import ske.joinpa.joinpa.models.Place;
import ske.joinpa.joinpa.ui.dialogs.NewLocationDialog;
import ske.joinpa.joinpa.ui.viewholders.ViewHolder;

/**
 * Created by TAWEESOFT on 5/30/16 AD.
 */
public class LocationAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private List<Place> places;
    private int selectedPlace = -1;
    private Notifier notifier;

    public LocationAdapter(Context context, List<Place> places, Notifier notifier) {
        this.context = context;
        this.places = places;
        this.notifier = notifier;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == -1) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_add_location,parent,false);

            return new CreateLocationViewHolder(view);
        } else {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_location, parent, false);
        }
        return new ItemLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == places.size()) return;
        Place place = places.get(position);
        ItemLocationViewHolder itemHolder = (ItemLocationViewHolder)holder;
        itemHolder.tvLocation.setText(place.getName());
        if (selectedPlace == position) {
            itemHolder.layout.setBackgroundResource(R.drawable.rounded_edittext_blue_gray);
            itemHolder.tvLocation.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }
        else {
            itemHolder.layout.setBackgroundResource(R.drawable.rounded_edittext);
            itemHolder.tvLocation.setTextColor(context.getResources().getColor(R.color.colorBlueGray));
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(position == places.size()) return -1;
        return position;
    }

    @Override
    public int getItemCount() {
        return places.size() + 1;
    }

    public Place getSelectedPlace() {
        if (selectedPlace==-1) return null;
        return places.get(selectedPlace);
    }

    class CreateLocationViewHolder extends ViewHolder implements View.OnClickListener {

        public CreateLocationViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            NewLocationDialog dialog = new NewLocationDialog(context,notifier);
            dialog.show();
        }
    }

    class ItemLocationViewHolder extends ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_location_name)
        TextView tvLocation;

        @BindView(R.id.layout)
        RelativeLayout layout;

        public ItemLocationViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            selectedPlace = getAdapterPosition();
            notifyDataSetChanged();
        }
    }
}
