package com.example.kridho.aplikasivoting;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class total extends AppCompatActivity {
    private ProgressDialog pd;

    ArrayList<BarDataSet> yAxis;
    ArrayList<BarEntry> yValues;
    ArrayList<String> xAxis1;
    BarEntry values ;
    BarChart chart;


    BarData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        pd = new ProgressDialog(total.this);
        pd.setMessage("loading");


        // Log.d("array",Arrays.toString(fullData));
        chart = (BarChart) findViewById(R.id.chart);
        load_data_from_server();

    }

    public void load_data_from_server() {
        pd.show();
        String url = "https://bankku.yippytech.com/pemira/vote.php";
        xAxis1 = new ArrayList<>();
        yAxis = null;
        yValues = new ArrayList<>();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("string",response);

                        try {

                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);


                                String score = jsonobject.getString("jml").trim();
                                String name = jsonobject.getString("nama_paslon").trim();

                                xAxis1.add(name);

                                values = new BarEntry(Float.valueOf(score),i);
                                yValues.add(values);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();


                        }





                        BarDataSet barDataSet1 = new BarDataSet(yValues, "HASIL VOTE MPM ");
                        barDataSet1.setColor(Color.rgb(0, 82, 159));



                        yAxis = new ArrayList<>();
                        yAxis.add(barDataSet1);
                        String names[]= xAxis1.toArray(new String[xAxis1.size()]);
                        data = new BarData(names,yAxis);
                        chart.setData(data);
                        chart.setDescription("");
                        chart.animateXY(2000, 2000);
                        chart.invalidate();
                        pd.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){

                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                            pd.hide();
                        }
                    }
                }

        );

        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }


}
