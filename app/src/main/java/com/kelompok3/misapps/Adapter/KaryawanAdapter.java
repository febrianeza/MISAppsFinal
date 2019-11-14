package com.kelompok3.misapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok3.misapps.Model.KaryawanModel;
import com.kelompok3.misapps.R;
import com.kelompok3.misapps.Util.Font;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class KaryawanAdapter extends RecyclerView.Adapter<KaryawanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<KaryawanModel> list;

    private View.OnClickListener onItemClickListener;

    public KaryawanAdapter(Context context, ArrayList<KaryawanModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_karyawan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KaryawanModel model = list.get(position);

        Picasso.get()
                .load(model.getBase_url())
                .fit()
                .centerCrop()
                .into(holder.imageKaryawan);

        holder.namaKaryawan.setText(model.getEmployee_name());
        holder.nikKaryawan.setText(model.getNomor_induk_pegawai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageKaryawan)
        CircleImageView imageKaryawan;
        @BindView(R.id.namaKaryawan)
        TextView namaKaryawan;
        @BindView(R.id.nikKaryawan)
        TextView nikKaryawan;

        Font font;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
            font = new Font(context);
        }
    }

    public void setOnItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }
}
