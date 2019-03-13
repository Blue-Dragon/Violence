package com.example.violence;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class LessonsListAdapter extends ArrayAdapter {

    private List<lessonsList> contacts;

    public LessonsListAdapter(Context context, List<lessonsList> contacts) {
        super(context, R.layout.list_sample, contacts);
        this.contacts = contacts;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        lessonsList contact = contacts.get(position);
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_sample, parent, false);
            holder = new ViewHolder();
            holder.img_profile = (ImageView) convertView.findViewById(R.id.mycontact_pimg);
            holder.tv_name = (TextView) convertView.findViewById(R.id.mycontact_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.fill(contact);
        return convertView;
    }


    private class ViewHolder implements View.OnClickListener {
        public ImageView img_profile;
        public TextView tv_name;

        public void fill(lessonsList contact){
            img_profile.setImageResource(contact.getId());
            tv_name.setText(contact.getName());
        }

        @Override
        public void onClick(View view) {
        // do ur stuff here
        }
    }

}
