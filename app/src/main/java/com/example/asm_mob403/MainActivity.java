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

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtEmail, edtPass;
//    String loginURL = "http://10.82.175.119/minhtri_ps09376/index.php";
    String loginURL = "http://192.168.1.224/minhtri_ps09376/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPassword);

        edtEmail.setText("tri123@gmail.com");
        edtPass.setText("123123");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringrequest = new StringRequest(
                        Request.Method.POST, loginURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        xulyLogin(response);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
                        Log.d("loi",error.toString());
                    }
                }
                ) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<String,String>();


                        param.put("email",edtEmail.getText().toString());
                        param.put("password",edtPass.getText().toString());
                        param.put("tag","login");

                        return param;
                    }
                };
                Volley.newRequestQueue(MainActivity.this).add(stringrequest);
            }
        });
    }

    private void xulyLogin(String response) {
        String thanhcong="";
        String name = "";
        String email = "";
        try {
            JSONObject jsonobject=new JSONObject(response);
            thanhcong=jsonobject.getString("thanhcong");




            //doc tat ca du lieu tu json bo vao ArrayList
            if(Integer.parseInt(thanhcong)==1)//thanh cong
            {
                JSONObject user = jsonobject.getJSONObject("users");
                name = user.getString("name");
                email = user.getString("email");
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);

//                Bundle bundle = new Bundle();
//                bundle.putString("name1",name);
//                HomeFragment homeFragment = new HomeFragment();
//                homeFragment.setArguments(bundle);
                startActivity(intent);
            }
            else //that bai
            {
//                Log.d("login","LoginFail");
                Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
    public void login(View view) {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


}