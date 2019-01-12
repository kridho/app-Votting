package com.example.kridho.aplikasivoting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
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


    public final static String TAG_USERNAME = "name";
    public final static String TAG_ID = "id";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;
    Boolean session = false;

    public static final String session_status ="status failed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(Constant.KEY_SHAREDPREFS, Context.MODE_PRIVATE);

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
        String url = Constant.BASE_URL + "login.php?&email="+username+"&password="+password;
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
                    String message = json.getString("status");

                    Log.d("message", message);
                    if (message.equals("OK")) {
                        Intent intent;
//                        */regis

                        JSONArray datas = json.getJSONArray("data");
                        JSONObject data = datas.getJSONObject(0);

                        String name = data.getString("nama");
                        String id = data.getString("id");

                        editor = sharedpreferences.edit();
                        editor.putBoolean(Constant.KEY_SHAREDPREFS_LOGIN_STATUS, true);
                        editor.putString(Constant.KEY_ID, id);
                        editor.putString(Constant.KEY_NAME, name);

                        editor.apply();
//                        */regis
                        Toast.makeText(MainActivity.this,"SUKSES",Toast.LENGTH_LONG).show();
                        intent = new Intent(MainActivity.this, MenuUtama.class);
                        intent.putExtra("first_login","true");
                        finish();
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
