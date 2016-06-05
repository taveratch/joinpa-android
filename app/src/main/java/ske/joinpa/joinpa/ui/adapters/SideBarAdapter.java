package ske.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ske.joinpa.joinpa.R;
import ske.joinpa.joinpa.models.SideBarItem;

/**
 * Created by TAWEESOFT on 5/15/16 AD.
 */
public class SideBarAdapter extends ArrayAdapter<SideBarItem> {

    private int selectedItem = 0;

    public SideBarAdapter(Context context, int resource, List<SideBarItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sidebar,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        SideBarItem item = getItem(position);
        holder.imgIcon.setImageResource(item.getIcon());
        holder.tvItemName.setText(item.getTitle());

        if (position == selectedItem) {
            holder.layout.setBackgroundColor(getContext().getResources().getColor(R.color.colorWhite));
            holder.imgIcon.setBackgroundResource(R.drawable.blue_circle);
            holder.tvItemName.setTextColor(getContext().getResources().getColor(R.color.colorBlueGray));
        } else {
            holder.layout.setBackgroundColor(0);
            holder.imgIcon.setBackgroundResource(R.drawable.white_circle);
            holder.tvItemName.setTextColor(getContext().getResources().getColor(R.color.colorWhite));
        }
        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.layout)
        LinearLayout layout;

        @BindView(R.id.img_icon)
        ImageView imgIcon;

        @BindView(R.id.tv_itemname)
        TextView tvItemName;

        public ViewHolder(View v) {
            ButterKnife.bind(this,v);
        }
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
        notifyDataSetChanged();
    }
}
