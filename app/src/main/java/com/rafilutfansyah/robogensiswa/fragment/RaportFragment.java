package com.rafilutfansyah.robogensiswa.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rafilutfansyah.robogensiswa.activity.DetailRaportActivity;
import com.rafilutfansyah.robogensiswa.R;
import com.rafilutfansyah.robogensiswa.adapter.RaportRecyclerViewAdapter;
import com.rafilutfansyah.robogensiswa.model.RaportModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaportFragment extends Fragment {

    List<RaportModel> raports;
    String username;

    private RecyclerView recyclerView;
    private RaportRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Context context;

    SwipeRefreshLayout swipeRefresh;

    public RaportFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_raport, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        raports = new ArrayList<>();
        adapter = new RaportRecyclerViewAdapter(context, raports);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        RaportModel raport = raports.get(position);
                        Intent intent = new Intent(context, DetailRaportActivity.class);
                        intent.putExtra("id_raport", raport.getIdRaport());
                        intent.putExtra("username", raport.getUsername());
                        intent.putExtra("hari", raport.getHari());
                        intent.putExtra("tanggal", raport.getTanggal());
                        intent.putExtra("jam_masuk", raport.getJamMasuk());
                        intent.putExtra("materi", raport.getMateri());
                        intent.putExtra("foto", raport.getFoto());
                        intent.putExtra("nilai_merakit", raport.getNilaiMerakit());
                        intent.putExtra("nilai_mandiri", raport.getNilaiMandiri());
                        intent.putExtra("nilai_kreativitas", raport.getNilaiKreativitas());
                        intent.putExtra("nilai_total", raport.getNilaiTotal());
                        intent.putExtra("grade", raport.getGrade());
                        intent.putExtra("keterangan", raport.getKeterangan());
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        //
                    }
                })
        );

        SharedPreferences pref = context.getSharedPreferences("session", 0);
        SharedPreferences.Editor editor = pref.edit();
        username = pref.getString("username", null);

        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://robogen.000webhostapp.com/API/Raport?username="+username;
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                RaportModel raport = new RaportModel();
                                raport.setIdRaport(obj.getString("id_raport"));
                                raport.setUsername(obj.getString("username"));
                                raport.setHari(obj.getString("hari"));
                                raport.setTanggal(obj.getString("tanggal"));
                                raport.setJamMasuk(obj.getString("jam_masuk"));
                                raport.setMateri(obj.getString("materi"));
                                raport.setFoto(obj.getString("foto"));
                                raport.setNilaiMerakit(obj.getInt("nilai_merakit"));
                                raport.setNilaiMandiri(obj.getInt("nilai_mandiri"));
                                raport.setNilaiKreativitas(obj.getInt("nilai_kreativitas"));
                                raport.setNilaiTotal(obj.getDouble("nilai_total"));
                                raport.setGrade(obj.getString("grade"));
                                raport.setKeterangan(obj.getString("keterangan"));
                                raports.add(raport);
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);

        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RequestQueue queue = Volley.newRequestQueue(context);
                String url ="https://robogen.000webhostapp.com/API/Raport?username="+username;
                JsonArrayRequest request = new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                raports.clear();
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject obj = response.getJSONObject(i);
                                        RaportModel raport = new RaportModel();
                                        raport.setIdRaport(obj.getString("id_raport"));
                                        raport.setUsername(obj.getString("username"));
                                        raport.setHari(obj.getString("hari"));
                                        raport.setTanggal(obj.getString("tanggal"));
                                        raport.setJamMasuk(obj.getString("jam_masuk"));
                                        raport.setMateri(obj.getString("materi"));
                                        raport.setFoto(obj.getString("foto"));
                                        raport.setNilaiMerakit(obj.getInt("nilai_merakit"));
                                        raport.setNilaiMandiri(obj.getInt("nilai_mandiri"));
                                        raport.setNilaiKreativitas(obj.getInt("nilai_kreativitas"));
                                        raport.setNilaiTotal(obj.getDouble("nilai_total"));
                                        raport.setGrade(obj.getString("grade"));
                                        raport.setKeterangan(obj.getString("keterangan"));
                                        raports.add(raport);
                                        adapter.notifyDataSetChanged();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                                swipeRefresh.setRefreshing(false);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });

        return view;
    }

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }
}
