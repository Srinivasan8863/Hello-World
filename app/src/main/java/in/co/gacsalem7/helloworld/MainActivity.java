package in.co.gacsalem7.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    TextView celcious,description;
    EditText city;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celcious = findViewById(R.id.celcious);
        description  = findViewById(R.id.description);
        city = findViewById(R.id.city);
        search = findViewById(R.id.search);
        final String appid = "ab92bdf41ee39bef42a619638ae10cb9";

        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //celcious.setText(city.getText());
                String url = "https://api.openweathermap.org/data/2.5/weather?q="+city.getText()+"&appid="+appid;

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("weather");
                            JSONObject data = array.getJSONObject(0);
                            celcious.setText(data.get("main").toString());
                            description.setText(data.get("description").toString());
                        }
                        catch (Exception e){
                            e.printStackTrace();

                        }
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        celcious.setText("no response");
                    }
                });

                queue.add(request);
            }
        });






    }
    public int addition(int a,int b){
        return a+b;
    }
}
