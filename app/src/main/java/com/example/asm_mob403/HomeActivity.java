package com.example.asm_mob403;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm_mob403.adapter.AdapterSV;
import com.example.asm_mob403.model.SinhVien;
import com.example.asm_mob403.model.Users;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    ImageView imgMenu;
    ListView lvSV;
    ArrayList<SinhVien> sinhVienArrayList;
    AdapterSV adapterSV;
    String Url = "http://192.168.1.224/minhtri_ps09376/getsv.php";
    String UrlDelete = "http://192.168.1.224/minhtri_ps09376/delete.php";
    //String Url = "http://10.82.190.82/minhtri_ps09376/getsv.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        imgMenu = findViewById(R.id.imageViewMenu);
        lvSV = findViewById(R.id.lvSV);
        sinhVienArrayList = new ArrayList<>();
        adapterSV = new AdapterSV(HomeActivity.this, R.layout.item_sinhvien, sinhVienArrayList);
        lvSV.setAdapter(adapterSV);
        getSV(Url);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMenu();
            }
        });
        registerForContextMenu(lvSV);

    }

    private void getSV(String URL) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sinhVienArrayList.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        sinhVienArrayList.add(new SinhVien(object.getInt("id"),
                                object.getString("name"),
                                object.getString("students_code"),
                                object.getString("birthday"),
                                object.getString("phone"),
                                object.getString("address")));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                adapterSV.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void ShowMenu() {

        PopupMenu popupMenu = new PopupMenu(HomeActivity.this, imgMenu);
        popupMenu.getMenuInflater().inflate(R.menu.home, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_addsv:
                        Intent intent = new Intent(HomeActivity.this, ThemSinhVienActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_danhsachuser:
                        Intent intent1 = new Intent(HomeActivity.this, DanhSachUser.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_doimk:
                        break;
                    case R.id.action_logout:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    public void DeleteSV(final int idsv) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlDelete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Thanh cong")) {
                    Toast.makeText(HomeActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    getSV(Url);
                } else {
                    Toast.makeText(HomeActivity.this, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, "Xảy ra lỗi!", Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(idsv));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}