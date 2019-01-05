package com.example.kridho.aplikasivoting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Form_voting extends AppCompatActivity {


    RadioButton satu;
    RadioButton dua;
    int id_paslon = 0;
    Button vote;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_voting);

        satu = findViewById(R.id.checkBox);
        dua = findViewById(R.id.checkBox2);
        vote = findViewById(R.id.submit);

        satu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                satu.setChecked(true);
                dua.setChecked(false);
                id_paslon = 1;
            }
        });

        dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dua.setChecked(true);
                satu.setChecked(false);
                id_paslon = 2;
            }
        });

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesVote();
            }
        });
    }

    private void prosesVote() {
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        String url = Constant.BASE_URL + "paslon.php?idvote="+id_paslon;
        StringRequest req = new StringRequest(Request.Method.GET, url, successListener(), errListener());
        AppController.getInstance().addToRequestQueue(req);

    }

    private Response.ErrorListener errListener() {
        return new Response.ErrorListener() {
            @Override
            public  void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Log.e("Error", "Voting");
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
//                            editor.putString(Constant.KEY_SHAREDPREFS_USER_DATA, json.getString("data"));
//                            editor.putString(Constant.KEY_SHAREDPREFS_LOGIN_STATUS, "1");
////                        editor.putString(Constant.KEY_SHAREDPREFS_TOKEN, json.getString("_token")); //buat ngambil json token
//                            editor.commit();
//                        */regis
                        Toast.makeText(Form_voting.this," VOTING SUKSES",Toast.LENGTH_LONG).show();
                        intent = new Intent(Form_voting.this, MenuUtama.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Form_voting.this,"VOTING GAGAL",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
