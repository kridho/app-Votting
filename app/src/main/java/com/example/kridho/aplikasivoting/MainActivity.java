package com.example.kridho.aplikasivoting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView lupaPassword;
    EditText user,pass;
    Button masuk;
    Button masuk2;
    ProgressDialog pd;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefs.edit();

//        lupaPassword = findViewById(R.id.lupa);
//        lupaPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent pindah = new Intent(MainActivity.this, MenuUtama.class);
//                startActivity(pindah);
//            }
//        });

        masuk2 = findViewById(R.id.regis);
        masuk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(pindah);
            }
        });

        masuk = findViewById(R.id.masuk);
        user =findViewById(R.id.user);
        pass =findViewById(R.id.pass);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProses();
            }
        });

    }
    //     *loginMetod
    public void loginProses(){

        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        final String username = user.getText().toString();
        final String password = pass.getText().toString();
        String url = Constant.BASE_URL + "login.php?email="+username+"&password="+password;
        StringRequest req = new StringRequest(Request.Method.GET, url, successListener(), errListener());
        AppController.getInstance().addToRequestQueue(req);

    }

    private Response.ErrorListener errListener() {
        return new Response.ErrorListener() {
            @Override
            public  void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Log.e("Error", "Login");
                Log.e("Error", String.valueOf(error));
            }
        };
    }

    private Response.Listener<String> successListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try{
                    Log.d("response", response);
                    JSONObject json = new JSONObject(response);

//                    JSONArray jsonOutput = new JSONArray(jsonopt.getString("object"));
                    String message = json.getString("status");

                    Log.d("message", message);
                    if (message.equals("OK")) {
//                        JSONObject data = new JSONObject(json.getString("data"));
                        Intent intent;
//                        */regis
                        editor.putString(Constant.KEY_SHAREDPREFS_USER_DATA, json.getString("data"));
                        editor.putString(Constant.KEY_SHAREDPREFS_LOGIN_STATUS, "1");
//                        editor.putString(Constant.KEY_SHAREDPREFS_TOKEN, json.getString("_token")); //buat ngambil json token
                        editor.commit();
//                        */regis
                        Toast.makeText(MainActivity.this,"SUKSES",Toast.LENGTH_LONG).show();
                        intent = new Intent(MainActivity.this, MenuUtama.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this,"GAGAL",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
