package io.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.managers.App;
import io.joinpa.joinpa.models.Event;
import io.joinpa.joinpa.ui.dialogs.NewCustomEventDialog;
import io.joinpa.joinpa.ui.views.SelectFriendActivity;

/**
 * Created by TAWEESOFT on 5/29/16 AD.
 */
public class CreateEventAdapter extends RecyclerView.Adapter<CreateEventAdapter.ViewHolder> {

    private Context context;
    private List<Event> events;
    private App app;
    public CreateEventAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
        app = App.getInstance();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class ItemViewHolder extends ViewHolder implements View.OnClickListener{

        @BindView(R.id.img_event_icon)
        ImageView imgEventIcon;

        @BindView(R.id.tv_event_name)
        TextView tvEventName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
            Event event = events.get(index);
            Intent intent = new Intent(CreateEventAdapter.this.context , SelectFriendActivity.class);
            intent.putExtra("event" , event);
            CreateEventAdapter.this.context.startActivity(intent);
        }
    }

    class CreateMyEventViewHolder extends ViewHolder {
        public CreateMyEventViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == -1) { //Add new custom event
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_add_new_event,parent,false);
            return new CreateMyEventViewHolder(view);
        }else{
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_new_event_list,parent , false);
            return new ItemViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("pppppp" , position + "");
        if(position == events.size()){ // add new custom event
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewCustomEventDialog dialog = new NewCustomEventDialog(context);
                    dialog.addObserver((Observer)context);
                    dialog.show();
                }
            });
        }else{ //Show event in the list
            Event event = events.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder)holder;
            viewHolder.imgEventIcon.setImageResource(app.getInternalData().eventIcon[event.getIcon()]);
            viewHolder.tvEventName.setText(event.getName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == events.size()) return -1;
        return position;
    }

    @Override
    public int getItemCount() {
        return events.size()+1;
    }
}
