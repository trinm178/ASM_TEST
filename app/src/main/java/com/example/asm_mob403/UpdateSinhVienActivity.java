package com.example.asm_mob403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm_mob403.model.SinhVien;

import java.util.HashMap;
import java.util.Map;

public class UpdateSinhVienActivity extends AppCompatActivity {
    String Url = "http://192.168.1.224/minhtri_ps09376/update.php";
    EditText edtName, edtCode, edtDate, edtPhone, edtAddress;
    Button btnSuaSinhVien;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinh_vien);
        AnhXa();
        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("dataSinhVien");
//        Toast.makeText(this, sinhVien.getName(), Toast.LENGTH_SHORT).show();

        id = sinhVien.getId();
        edtName.setText(sinhVien.getName());
        edtCode.setText(sinhVien.getStudents_code());
        edtDate.setText(sinhVien.getBirthday());
        edtPhone.setText(sinhVien.getPhone());
        edtAddress.setText(sinhVien.getAddress());
        // nút sửa
        btnSuaSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String code = edtCode.getText().toString().trim();
                String date = edtDate.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();

                if (name.isEmpty() || code.isEmpty() ||date.isEmpty() ||phone.isEmpty() ||address.isEmpty() ) {
                    Toast.makeText(UpdateSinhVienActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    UpdateSinhVien(Url);
                }
            }
        });
    }

    private void UpdateSinhVien(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Thanh cong")){
                    Toast.makeText(UpdateSinhVienActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateSinhVienActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(UpdateSinhVienActivity.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateSinhVienActivity.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                params.put("name", edtName.getText().toString().trim());
                params.put("students_code", edtCode.getText().toString().trim());
                params.put("birthday", edtDate.getText().toString().trim());
                params.put("phone", edtPhone.getText().toString().trim());
                params.put("address", edtAddress.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        edtName = findViewById(R.id.edtSuaNameSV);
        edtCode = findViewById(R.id.edtSuaCodeSV);
        edtDate = findViewById(R.id.edtSuaBirthSV);
        edtPhone = findViewById(R.id.edtSuaPhoneSV);
        edtAddress = findViewById(R.id.edtSuaAddressSV);
        btnSuaSinhVien = findViewById(R.id.btnSuaSinhVien);
    }


}