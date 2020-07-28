package com.example.asm_mob403.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_mob403.R;
import com.example.asm_mob403.model.SinhVien;
import com.example.asm_mob403.model.Users;

import java.util.List;

public class AdapterSV extends BaseAdapter {
    private Context context;
    private int layout;
    private List<SinhVien> sinhVienList;

    public AdapterSV(Context context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
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
        TextView txtName, txtEmail, txtCode, txtPhone, txtBirth;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder =new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);
            holder.txtName = view.findViewById(R.id.tvNameSV);
            holder.txtEmail = view.findViewById(R.id.tvEmailSV);
            holder.txtCode = view.findViewById(R.id.tvCodeSV);
            holder.txtPhone = view.findViewById(R.id.tvPhoneSV);
            holder.txtBirth = view.findViewById(R.id.tvBirthSV);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        SinhVien sinhVien = sinhVienList.get(i);
        holder.txtName.setText("Tên: "+sinhVien.getName());
        holder.txtEmail.setText("Email: "+sinhVien.getAddress());
        holder.txtCode.setText("MSSV: "+sinhVien.getStudents_code());
        holder.txtPhone.setText("SDT: 0"+sinhVien.getPhone());
        holder.txtBirth.setText("Ngày sinh: "+sinhVien.getBirthday());
        return view;
    }
}
