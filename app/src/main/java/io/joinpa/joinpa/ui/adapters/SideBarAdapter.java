package io.joinpa.joinpa.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.joinpa.joinpa.R;
import io.joinpa.joinpa.models.SideBarItem;

/**
 * Created by TAWEESOFT on 5/15/16 AD.
 */
public class SideBarAdapter extends ArrayAdapter<SideBarItem> {

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
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;

        @BindView(R.id.tv_itemname)
        TextView tvItemName;

        public ViewHolder(View v) {
            ButterKnife.bind(this,v);
        }
    }
}
