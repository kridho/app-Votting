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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Main3Activity extends AppCompatActivity {
    EditText nama,notlp,email,jurusan,tingkatsemester,pass;
    Button daftar;
    ProgressDialog pd;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefs.edit();

        daftar = findViewById(R.id.daftar);
        nama = findViewById(R.id.nama);
        notlp = findViewById(R.id.tlp);
        email = findViewById(R.id.email);
        jurusan = findViewById(R.id.jurusan);
        tingkatsemester = findViewById(R.id.tingkat);
        pass = findViewById(R.id.pass);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nama.getText().toString().length()==0){
                    //jika form Nama belum di isi / masih kosong
                    nama.setError("Nama diperlukan!");
                }else if(notlp.getText().toString().length()==0){
                    //jika form Notlp belum di isi / masih kosong
                    notlp.setError("No Tlp diperlukan!");
                }else if(email.getText().toString().length()==0){
                    //jika form Email belum di isi / masih kosong
                    email.setError("Email diperlukan!");
                }else if(jurusan.getText().toString().length()==0){
                    //jika form Jurusan belum di isi / masih kosong
                    jurusan.setError("Jurusan diperlukan!");
                }else if(tingkatsemester.getText().toString().length()==0){
                    //jika form Semester belum di isi / masih kosong
                    tingkatsemester.setError("Tingkat Semester diperlukan!");
                }else if(pass.getText().toString().length()==0){
                    //jika form Passwrod belum di isi / masih kosong
                    pass.setError("Password diperlukan!");
                }else {
                    //jika form sudah terisi semua

                    register();
                }
            }
        });
    }
        //     *loginMetod
        public void register(){

            pd = new ProgressDialog(this);
            pd.setMessage("Loading...");
            pd.show();

            final String username = nama.getText().toString();
            final String nomer = notlp.getText().toString();
            final String mail = email.getText().toString();
            final String j = jurusan.getText().toString();
            final String ts = tingkatsemester.getText().toString();
            final String password = pass.getText().toString();

            String url = Constant.BASE_URL + "register.php?email="+username+"&notlp="+nomer+"&email="+mail+"&jurusan="+j+"&tinggkat_semester="+ts+"&password="+password;
            StringRequest req = new StringRequest(Request.Method.GET, url, successListener(), errListener());
            AppController.getInstance().addToRequestQueue(req);

        }

        private Response.ErrorListener errListener() {
            return new Response.ErrorListener() {
                @Override
                public  void onErrorResponse(VolleyError error) {
                    pd.dismiss();
                    Log.e("Error", "Register");
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
                            Toast.makeText(Main3Activity.this," REGIS SUKSES",Toast.LENGTH_LONG).show();
                            intent = new Intent(Main3Activity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(Main3Activity.this,"REGIS GAGAL",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
    }




}
