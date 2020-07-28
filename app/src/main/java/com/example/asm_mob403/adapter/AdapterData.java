package com.example.asm_mob403.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_mob403.R;
import com.example.asm_mob403.model.Users;

import java.util.List;

public class AdapterData extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Users> usersList;

    public AdapterData(Context context, int layout, List<Users> usersList) {
        this.context = context;
        this.layout = layout;
        this.usersList = usersList;
    }

    @Override
    public int getCount() {
        return usersList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder {
        TextView txtName, txtEmail, txtPass, txtCreate;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder =new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);
            holder.txtName = view.findViewById(R.id.itvName);
            holder.txtEmail = view.findViewById(R.id.itvEmail);
            holder.txtPass = view.findViewById(R.id.itvPass);
            holder.txtCreate = view.findViewById(R.id.itvCreate);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Users users = usersList.get(i);
        holder.txtName.setText("Name: "+users.getName());
        holder.txtEmail.setText("Email: "+users.getEmail());
        holder.txtPass.setText("Pass: "+users.getPassword());
        holder.txtCreate.setText("Create Date: "+users.getCreate_date());
        return view;
    }
}
