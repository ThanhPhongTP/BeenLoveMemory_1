package com.example.beenlovememory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.beenlovememory.Adapter.RecyclerViewAdapter;
import com.example.beenlovememory.Database.Database;
import com.example.beenlovememory.Model.TimeLine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentMemory extends Fragment {

    private ArrayList<TimeLine> timeLines = new ArrayList<>();
    private RecyclerViewAdapter myAdapter;
    private RecyclerView myrv;
    Button btnInsert;
    public static Database database;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memory, container, false);

        setControl(view);
//        createData();
        setEvent();

        return view;
    }

    @Override
    public void onResume() {
        timeLines.clear();
        createData();
        super.onResume();
    }

    private void createData() {
        myAdapter = new RecyclerViewAdapter(getActivity(), timeLines);
        myrv.setAdapter(myAdapter);
        database = new Database(getActivity(), "Memory.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS Memory(Id INTEGER PRIMARY KEY AUTOINCREMENT, Date VARCHAR(50), Content VARCHAR(2000), Imgages BLOB)");

        Cursor cursor = database.GetData("SELECT * FROM Memory");
        while (cursor.moveToNext()) {
            timeLines.add(new TimeLine(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }
        myAdapter.notifyDataSetChanged();
        myrv.setLayoutManager(new LinearLayoutManager(getActivity()));

//        Log.d("abcc", String.valueOf(cursor.getInt(0)));
    }


    private void setEvent() {


//        timeLines.add(new TimeLine("15-10-2020", "similar view in my app.", null));
//        timeLines.add(new TimeLine("20-2-2020", "I have been looking to implement a similar view in my app.",null));
////        myAdapter.notifyDataSetChanged();
//
////        Toast.makeText(getActivity(), "ádasdad", Toast.LENGTH_SHORT).show();
//        myAdapter = new RecyclerViewAdapter(getActivity(), timeLines);
//        myrv.setAdapter(myAdapter);
//        myrv.setLayoutManager(new LinearLayoutManager(getActivity()));


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InsertMemoryActivity.class);
                startActivity(intent);
            }
        });

    }

//    private void getData(String url) {
//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject object = response.getJSONObject(i);
//                                timeLines.add(new TimeLine(
//                                        object.getInt("ID"),
//                                        object.getString("Content"),
//                                        object.getString("Dates "),
//                                        object.getInt("Images")
//                                ));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
////                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
//                        myAdapter = new RecyclerViewAdapter(getActivity(), timeLines);
//                        myrv.setAdapter(myAdapter);
//                        myrv.setLayoutManager(new LinearLayoutManager(getActivity()));
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        );
//        queue.add(jsonArrayRequest);
//    }


    private void setControl(View view) {
        btnInsert = view.findViewById(R.id.btn_insert);
        myrv = view.findViewById(R.id.recycle);

    }

}
