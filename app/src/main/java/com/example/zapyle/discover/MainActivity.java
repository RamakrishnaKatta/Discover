package com.example.zapyle.discover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4;
    ImageView imageView;
    RequestQueue requestQueue;
    ArrayList<String> Imagearray=new ArrayList<String>();
    ArrayList<Integer> integerarray=new ArrayList<Integer>();
    ListView listView;
    customAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        getData();
    }

    private void getData() {
        String url="http://dev.zapyle.com/discover/";

        StringRequest strreq=new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);


                try {
                    JSONObject resp = new JSONObject(response);
                    JSONArray data = resp.getJSONArray("data");

                    int i;
                    for (i = 0; i < data.length(); i++) {
                        JSONObject id = data.getJSONObject(i);

                        JSONObject content = id.getJSONObject("content_data");
                        JSONObject discover = content.getJSONObject("discover_data");
                        String title = discover.getString("title");
                        String url = discover.getString("image");
                        System.out.println("discover"+discover);

                        Picasso.with(MainActivity.this).load(url).into(imageView);

                        JSONObject id1 = data.getJSONObject(1);
                        JSONObject content1 = id1.getJSONObject("content_data");
                        JSONObject discover1 = content1.getJSONObject("discover_data");
                        JSONArray product = discover1.getJSONArray("product");

//                        for (int j = 0; j < product.length(); j++) {
//                            JSONObject product1 = product.getJSONObject(j);
//                            String url1 = product1.getString("image");
//                            Picasso.with(MainActivity.this).load(url1).into(imageView);
//                            int ID1 = product1.getInt("id");
//                        }
////                        JSONObject product2=product.getJSONObject(1);
//                        String url2=product2.getString("image");
//                        Picasso.with(MainActivity.this).load(url2).into(imageView);
//                        int ID2=product2.getInt("id");
//
//
//                        JSONObject product3=product.getJSONObject(2);
//                        String url3=product3.getString("image");
//                        Picasso.with(MainActivity.this).load(url3).into(imageView);
//                        int ID3=product3.getInt("id");
//
//                        JSONObject product4=product.getJSONObject(3);
//                        String url4=product4.getString("image");
//                        Picasso.with(MainActivity.this).load(url4).into(imageView);
//                        int ID4=product4.getInt("id");

//
//                        JSONObject id2 = data.getJSONObject(2);
//                        JSONObject content2 = id2.getJSONObject("content_data");
//                        JSONObject discover2 = content2.getJSONObject("discover_data");
//                        JSONArray product5 = discover2.getJSONArray("product");
//
//                        JSONObject product6 = product5.getJSONObject(0);
//                        String url5 = product6.getString("image");
//                        Picasso.with(MainActivity.this).load(url5).into(imageView);
//                        int oprice = product6.getInt("original_price");
//                        int lprice = product6.getInt("listing_price");
//                        int Id5 = product6.getInt("id");
//                        String head = product6.getString("title");

                    }

                    Imagearray.add(data.getJSONObject(i).getString("image"));
                    integerarray.add(data.getJSONObject(i).getInt("id"));

                    adapter = new customAdapter(MainActivity.this, Imagearray, integerarray);
                    listView = (ListView) findViewById(R.id.list_item);
                    listView.setAdapter(adapter);
//                    imageView=(ImageView)findViewById(R.id.image);


//                    LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
//
//                    View view= inflater.inflate(R.layout.discover,null,false);
////                    textView1=(TextView)view.findViewById(R.id.original);
////                    textView2=(TextView)view.findViewById(R.id.listing);
////                    textView3=(TextView)view.findViewById(R.id.id1);
////                    textView4=(TextView)view.findViewById(R.id.title1);
//


                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("error"+e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Getdata inside error__"+error);
            }
        });


        requestQueue.add(strreq);
    }
}
