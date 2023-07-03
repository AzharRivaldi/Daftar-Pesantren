package com.azhar.daftarpesantren.provinsi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.azhar.daftarpesantren.R;
import com.azhar.daftarpesantren.network.ApiServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvDaftarProvinsi;
    LinearLayout linearNoData;
    List<ModelMain> modelMainList = new ArrayList<>();
    MainAdapter mainAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvDaftarProvinsi = findViewById(R.id.rvDaftarProvinsi);
        linearNoData = findViewById(R.id.linearNoData);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        linearNoData.setVisibility(View.GONE);

        mainAdapter = new MainAdapter(this, modelMainList);
        rvDaftarProvinsi.setLayoutManager(new LinearLayoutManager(this));
        rvDaftarProvinsi.setHasFixedSize(true);
        rvDaftarProvinsi.setAdapter(mainAdapter);

        getListProvinsi();
    }

    private void getListProvinsi() {
        progressDialog.show();
        AndroidNetworking.get(ApiServices.BASEURL_PROV)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                ModelMain dataApi = new ModelMain();

                                dataApi.setId(object.getString("id"));
                                dataApi.setNama(object.getString("nama"));
                                modelMainList.add(dataApi);
                            }
                            mainAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Ups, tidak ada data!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this,
                                "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}