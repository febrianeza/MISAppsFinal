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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kelompok3.misapps.API.API;
import com.kelompok3.misapps.API.Service;
import com.kelompok3.misapps.Activity.DetailOffice;
import com.kelompok3.misapps.Adapter.OfficeAdapter;
import com.kelompok3.misapps.Model.OfficeModel;
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
public class OfficeFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Service service;

    OfficeAdapter officeAdapter;

    public OfficeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        service = API.getClient().create(Service.class);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_office, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        Call<JsonObject> callOffice = service.getOffice();

        callOffice.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (response.body().get("var_result").getAsString().equals("1")) {
                        JsonArray array = response.body().get("result").getAsJsonArray();

                        ArrayList<OfficeModel> arrayList = new ArrayList<>();
                        for (int i = 0; i < array.size(); i++) {
                            JsonObject object = array.get(i).getAsJsonObject();

                            OfficeModel model = new OfficeModel();
                            model.setOffice_name(object.get("office_name").getAsString());
                            model.setOffice_address(object.get("office_address").getAsString());
                            model.setOffice_description(object.get("office_description").getAsString());
                            model.setCell_phone(object.get("cell_phone").getAsString());
                            model.setEmail(object.get("email").getAsString());
                            model.setLocation_gps(object.get("location_gps").getAsString());
                            model.setBase_url(object.get("base_url").getAsString());

                            arrayList.add(model);
                        }
                        officeAdapter = new OfficeAdapter(getActivity(), arrayList);
                        recyclerView.setAdapter(officeAdapter);

                        officeAdapter.setOnItemClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                OfficeAdapter.ViewHolder viewHolder = (OfficeAdapter.ViewHolder) view.getTag();

                                int position = viewHolder.getAdapterPosition();

                                OfficeModel model = arrayList.get(position);

                                startActivity(new Intent(getActivity(), DetailOffice.class).putExtra("DATA_OFFICE", model));
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
