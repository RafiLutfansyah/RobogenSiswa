package com.rafilutfansyah.robogensiswa.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rafilutfansyah.robogensiswa.R;
import com.rafilutfansyah.robogensiswa.activity.MainActivity;
import com.rafilutfansyah.robogensiswa.model.RaportModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RaportRecyclerViewAdapter extends RecyclerView.Adapter<RaportRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<RaportModel> raports;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textMateri, textHariTanggal, textGrade;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image_view);
            textMateri= (TextView) view.findViewById(R.id.text_materi);
            textHariTanggal = (TextView) view.findViewById(R.id.text_hari_tanggal);
            textGrade = (TextView) view.findViewById(R.id.text_grade);
        }
    }

    public RaportRecyclerViewAdapter(Context context,List<RaportModel> raports) {
        this.context = context;
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
        Picasso.with(context).load("https://robogen.000webhostapp.com/codeigniter/uploads/"+raport.getFoto())
                .resize(640, 480)
                .centerCrop()
                .into(holder.imageView);
        holder.textMateri.setText(raport.getMateri());
        holder.textHariTanggal.setText(raport.getHari()+", "+raport.getTanggal());
    }

    @Override
    public int getItemCount() {
        return raports.size();
    }
}