package com.azhar.daftarpesantren.pesantren;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.azhar.daftarpesantren.R;
import com.azhar.daftarpesantren.network.ApiServices;
import com.azhar.daftarpesantren.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PesantrenActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rvDaftarPesantren;
    List<ModelPesantren> modelPesantrenList = new ArrayList<>();
    PesantrenAdapter pesantrenAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesantren);

        toolbar = findViewById(R.id.toolbar);
        rvDaftarPesantren = findViewById(R.id.rvDaftarPesantren);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pesantren di " + Constant.kabupatenName);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        pesantrenAdapter = new PesantrenAdapter(this, modelPesantrenList);
        rvDaftarPesantren.setLayoutManager(new LinearLayoutManager(this));
        rvDaftarPesantren.setHasFixedSize(true);
        rvDaftarPesantren.setAdapter(pesantrenAdapter);

        getListPesantren(Constant.kabupatenId);
    }

    private void getListPesantren(String kabupatenId) {
        progressDialog.show();
        AndroidNetworking.get(ApiServices.BASEURL_PESANTREN)
                .addPathParameter("id_kab_kota", kabupatenId)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                ModelPesantren dataApi = new ModelPesantren();

                                dataApi.setNama(object.getString("nama"));
                                dataApi.setNspp(object.getString("nspp"));
                                dataApi.setAlamat(object.getString("alamat"));
                                dataApi.setKyai(object.getString("kyai"));
                                modelPesantrenList.add(dataApi);
                            }
                            pesantrenAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Toast.makeText(PesantrenActivity.this, "Ups, tidak ada data!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(PesantrenActivity.this,
                                "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}