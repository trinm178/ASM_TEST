package com.example.asm_mob403.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asm_mob403.HomeActivity;
import com.example.asm_mob403.R;
import com.example.asm_mob403.UpdateSinhVienActivity;
import com.example.asm_mob403.model.SinhVien;
import com.example.asm_mob403.model.Users;

import java.util.List;

public class AdapterSV extends BaseAdapter {
    private HomeActivity context;
    private int layout;
    private List<SinhVien> sinhVienList;

    public AdapterSV(HomeActivity context, int layout, List<SinhVien> sinhVienList) {
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
        ImageView btnEdit, btnDelete;
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
            holder.btnEdit = view.findViewById(R.id.btnEditSV);
            holder.btnDelete = view.findViewById(R.id.btnDeleteSV);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final SinhVien sinhVien = sinhVienList.get(i);
        holder.txtName.setText("Tên: "+sinhVien.getName());
        holder.txtEmail.setText("Email: "+sinhVien.getAddress());
        holder.txtCode.setText("MSSV: "+sinhVien.getStudents_code());
        holder.txtPhone.setText("SDT: "+sinhVien.getPhone());
        holder.txtBirth.setText("Ngày sinh: "+sinhVien.getBirthday());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateSinhVienActivity.class);
                intent.putExtra("dataSinhVien", sinhVien);
                context.startActivity(intent);
                context.finish();
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogXoa(sinhVien.getName(), sinhVien.getId());
            }
        });
        return view;

    }
    private void DialogXoa(String name, final int id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage("Chắc chắn muốn xóa?");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.DeleteSV(id);
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }


}
