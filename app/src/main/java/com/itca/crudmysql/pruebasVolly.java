package com.itca.crudmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class pruebasVolly extends AppCompatActivity {
String URL_PRUEBA;
private TextView textView;
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas_volly);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PruebaVolly();

            }
        });
    }
    private void baseRequest(){
        StringRequest request =new StringRequest(Request.Method.GET, URL_PRUEBA, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map=new HashMap<String, String>();
                map.put("Content-Type","application/json; charset0UTF-8");
                map.put("Accept","application/json");
                map.put("id","1");
                return map;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(request);
    }
    private void PruebaVolly(){
    String url ="http://httpbin.org/html";

        StringRequest request =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println(response.substring(0,16));
                textView.setText(response.substring(0,16));
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("Something went wrong!");
                Toast.makeText(pruebasVolly.this, "Sin Conexion a Internet", Toast.LENGTH_SHORT).show();
                volleyError.printStackTrace();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(request);
    }
    private void peticionJson(){
            String url ="http://192.168.57.1/service2021/json1.php";
            String url1 = URL_PRUEBA;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url1, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                textView.setText("Response: " + jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

        });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
    private void recibirJson(){
        String url2 = URL_PRUEBA;
        StringRequest request =new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject respuestaJSON=new JSONObject(s.toString());
                    String var1 =respuestaJSON.getString("id");
                    String var2 =respuestaJSON.getString("nombre");
                    textView.setText("Response: "+ respuestaJSON.toString());
                    Toast.makeText(pruebasVolly.this, "Id:"+var1+ "\nNombre:"+var2, Toast.LENGTH_SHORT).show();
                }catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }
        );
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

}