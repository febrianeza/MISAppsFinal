package com.kelompok3.misapps.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kelompok3.misapps.API.API;
import com.kelompok3.misapps.API.Service;
import com.kelompok3.misapps.Activity.DetailKaryawan;
import com.kelompok3.misapps.Adapter.KaryawanAdapter;
import com.kelompok3.misapps.Model.KaryawanModel;
import com.kelompok3.misapps.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KaryawanFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    KaryawanAdapter karyawanAdapter;

    Service service;
    KaryawanAdapter adapter;
    ArrayList<KaryawanModel> arrayList = new ArrayList<>();

    public KaryawanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        service = API.getClient().create(Service.class);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_karyawan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        Call<JsonObject> callEmployee = service.getEmployee();
        callEmployee.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (response.body().get("var_result").getAsString().equals("1")) {
                        JsonArray array = response.body().get("result").getAsJsonArray();


                        for (int i = 0; i < array.size(); i++) {
                            JsonObject object = array.get(i).getAsJsonObject();

                            KaryawanModel model = new KaryawanModel();
                            model.setEmployee_name(object.get("employee_name").getAsString());
                            model.setNomor_induk_pegawai(object.get("nomor_induk_pegawai").getAsString());
                            model.setAddress(object.get("address").getAsString());
                            model.setGender(object.get("gender").getAsString());
                            model.setBase_url(object.get("base_url").getAsString());
                            model.setTempat_lahir(object.get("tempat_lahir").getAsString());
                            model.setTanggal_lahir(object.get("tanggal_lahir").getAsString());
                            model.setGol_darah(object.get("gol_darah").getAsString());
                            model.setAgama(object.get("agama").getAsString());
                            model.setStatus_perkawinan(object.get("status_perkawinan").getAsString());
                            model.setKewarganegaraan(object.get("kewarganegaraan").getAsString());
                            model.setBerlaku_hingga(object.get("berlaku_hingga").getAsString());
                            model.setTempat_buat(object.get("tempat_buat").getAsString());
                            model.setTanggal_buat(object.get("tanggal_buat").getAsString());

                            arrayList.add(model);
                        }

                        adapter = new KaryawanAdapter(getActivity(), arrayList);
                        recyclerView.setAdapter(adapter);

                        adapter.setOnItemClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                KaryawanAdapter.ViewHolder viewHolder = (KaryawanAdapter.ViewHolder) view.getTag();

                                int position = viewHolder.getAdapterPosition();

                                KaryawanModel model = arrayList.get(position);

                                startActivity(new Intent(getActivity(), DetailKaryawan.class)
                                        .putExtra("DATA_KARYAWAN", model));
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getActivity(), "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
