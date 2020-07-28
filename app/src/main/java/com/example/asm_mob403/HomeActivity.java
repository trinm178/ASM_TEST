package com.example.asm_mob403;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.asm_mob403.adapter.AdapterData;
import com.example.asm_mob403.model.Users;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    String URL = "http://192.168.1.224/minhtri_ps09376/getdata.php";
    ListView lvdata;
    ArrayList<Users> usersArrayList;
    AdapterData adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lvdata = findViewById(R.id.lvItem);
        usersArrayList = new ArrayList<>();

        adapter = new AdapterData(this, R.layout.item_data, usersArrayList);
        lvdata.setAdapter(adapter);
        getData(URL);

    }

    private void getData(String URL) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               for (int i = 0; i < response.length(); i++){
                   try {
                       JSONObject object = response.getJSONObject(i);
                       usersArrayList.add(new Users(object.getInt("id"),
                               object.getString("name"),
                               object.getString("email"),
                               object.getString("password"),
                               object.getString("create_date"),
                               object.getString("update_date")));

                   } catch (Exception e) {
                        e.printStackTrace();
                   }
               }
               adapter.notifyDataSetChanged();
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
}