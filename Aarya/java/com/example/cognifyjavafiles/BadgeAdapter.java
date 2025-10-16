package com.example.cognifyjavafiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class BadgeAdapter extends BaseAdapter {
    private Context context;
    private List<Badge> badges;
    private LayoutInflater inflater;

    public BadgeAdapter(Context context, List<Badge> badges) {
        this.context = context;
        this.badges = badges;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return badges.size();
    }

    @Override
    public Object getItem(int position) {
        return badges.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_badge, parent, false);
            holder = new ViewHolder();
            holder.badgeIcon = convertView.findViewById(R.id.iv_badge_icon);
            holder.badgeName = convertView.findViewById(R.id.tv_badge_name);
            holder.badgeDescription = convertView.findViewById(R.id.tv_badge_description);
            holder.earnedIndicator = convertView.findViewById(R.id.iv_earned_indicator);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Badge badge = badges.get(position);
        holder.badgeIcon.setImageResource(badge.getIconResource());
        holder.badgeName.setText(badge.getName());
        holder.badgeDescription.setText(badge.getDescription());

        if (badge.isEarned()) {
            holder.earnedIndicator.setVisibility(View.VISIBLE);
            convertView.setAlpha(1.0f);
        } else {
            holder.earnedIndicator.setVisibility(View.GONE);
            convertView.setAlpha(0.5f);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView badgeIcon;
        TextView badgeName;
        TextView badgeDescription;
        ImageView earnedIndicator;
    }
}
