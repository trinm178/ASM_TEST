package com.example.asm_mob403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    Button btnSignUp;
    EditText edtName, edtEmail, edtPass;
    //  String registerUrl = "http://10.82.175.119/minhtri_ps09376/index.php";
    String registerUrl = "http://192.168.1.224/minhtri_ps09376/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtNameSignUp);
        edtEmail = findViewById(R.id.edtEmailSignUp);
        edtPass = findViewById(R.id.edtPassSignUp);

        btnSignUp = findViewById(R.id.btnSignup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringrequest = new StringRequest(
                        Request.Method.POST, registerUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        xulyRegister(response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(SignUpActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
                        Log.d("loi", error.toString());

                    }
                }
                ) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<String, String>();

                        param.put("name", edtName.getText().toString());
                        param.put("email", edtEmail.getText().toString());
                        param.put("password", edtPass.getText().toString());
                        param.put("tag", "register");

                        return param;
                    }
                };
                Volley.newRequestQueue(SignUpActivity.this).add(stringrequest);
            }
        });


    }

    private void xulyRegister(String response) {
        String thanhcong = "";
        try {
            JSONObject jsonobject = new JSONObject(response);
            thanhcong = jsonobject.getString("thanhcong");

            //doc tat ca du lieu tu json bo vao ArrayList
            if (Integer.parseInt(thanhcong) == 1)//thanh cong
            {
                Toast.makeText(this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else //that bai
            {
                Toast.makeText(this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void login(View view) {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void cancel(View view) {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}