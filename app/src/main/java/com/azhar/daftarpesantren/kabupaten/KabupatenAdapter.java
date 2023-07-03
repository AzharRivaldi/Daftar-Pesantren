package com.azhar.daftarpesantren.kabupaten;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.daftarpesantren.R;
import com.azhar.daftarpesantren.pesantren.PesantrenActivity;
import com.azhar.daftarpesantren.utils.Constant;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 07-03-2023
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

public class KabupatenAdapter extends RecyclerView.Adapter<KabupatenAdapter.ViewHolder> {

    private List<ModelKabupaten> modelKabupatenList;
    private Context context;

    public KabupatenAdapter(Context mContext, List<ModelKabupaten> modelKabupaten) {
        this.context = mContext;
        this.modelKabupatenList = modelKabupaten;
    }

    @Override
    public KabupatenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_data_kabupaten, parent, false);
        return new KabupatenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KabupatenAdapter.ViewHolder holder, int position) {
        final ModelKabupaten data = modelKabupatenList.get(position);

        holder.tvKabupaten.setText(data.getNama());
        holder.cvDaftarKabupaten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.kabupatenId = modelKabupatenList.get(holder.getAdapterPosition()).getId();
                Constant.kabupatenName = modelKabupatenList.get(holder.getAdapterPosition()).getNama();
                Intent intent = new Intent(context, PesantrenActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelKabupatenList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvDaftarKabupaten;
        public TextView tvKabupaten;

        public ViewHolder(View itemView) {
            super(itemView);
            cvDaftarKabupaten = itemView.findViewById(R.id.cvDaftarKabupaten);
            tvKabupaten = itemView.findViewById(R.id.tvKabupaten);
        }
    }

}