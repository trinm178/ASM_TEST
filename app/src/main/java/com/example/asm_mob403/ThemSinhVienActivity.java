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

import java.util.HashMap;
import java.util.Map;

public class ThemSinhVienActivity extends AppCompatActivity {
    EditText edtName, edtCode, edtBirth, edtPhone, edtEmail;
    Button btnThemSinhVien;
     String Url = "http://192.168.1.224/minhtri_ps09376/insert.php";

    //String Url = "http://10.82.190.82/minhtri_ps09376/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sinh_vien);

        edtName = findViewById(R.id.edtNameSV);
        edtCode = findViewById(R.id.edtCodeSV);
        edtBirth = findViewById(R.id.edtBirthSV);
        edtPhone = findViewById(R.id.edtPhoneSV);
        edtEmail = findViewById(R.id.edtEmailSV);
        btnThemSinhVien = findViewById(R.id.btnThemSinhVien);

        btnThemSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String code = edtName.getText().toString().trim();
                String birth = edtName.getText().toString().trim();
                String phone = edtName.getText().toString().trim();
                String email = edtName.getText().toString().trim();
                if (name.isEmpty() || code.isEmpty() || birth.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    Toast.makeText(ThemSinhVienActivity.this, "Vui lòng nhập đủ dữ liệu!", Toast.LENGTH_SHORT).show();
                } else {
                    ThemSinhVien(Url);
                }
            }
        });
    }

    private void ThemSinhVien(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Thanh cong")) {
                    Toast.makeText(ThemSinhVienActivity.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ThemSinhVienActivity.this, HomeActivity.class));
                } else {
                    Toast.makeText(ThemSinhVienActivity.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemSinhVienActivity.this, "Lỗi !!", Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", edtName.getText().toString());
                params.put("students_code", edtCode.getText().toString());
                params.put("birthday", edtBirth.getText().toString());
                params.put("phone", edtPhone.getText().toString());
                params.put("address", edtEmail.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}