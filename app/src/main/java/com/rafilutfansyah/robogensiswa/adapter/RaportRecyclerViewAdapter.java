package com.rafilutfansyah.robogensiswa.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rafilutfansyah.robogensiswa.R;
import com.rafilutfansyah.robogensiswa.model.RaportModel;

import java.util.List;

public class RaportRecyclerViewAdapter extends RecyclerView.Adapter<RaportRecyclerViewAdapter.ViewHolder> {

    private List<RaportModel> raports;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textUsername;
        public TextView textMateri;

        public ViewHolder(View view) {
            super(view);
            textUsername = (TextView) view.findViewById(R.id.text_username);
            textMateri = (TextView) view.findViewById(R.id.text_materi);
        }
    }

    public RaportRecyclerViewAdapter(List<RaportModel> raports) {
        this.raports = raports;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RaportModel raport = raports.get(position);
        holder.textUsername.setText(position + ". "+raport.getUsername());
        holder.textMateri.setText(raport.getMateri());
    }

    @Override
    public int getItemCount() {
        return raports.size();
    }
}