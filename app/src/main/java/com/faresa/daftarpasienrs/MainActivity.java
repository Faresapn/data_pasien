package com.faresa.daftarpasienrs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.faresa.daftarpasienrs.ViewModel.DataViewModel;
import com.faresa.daftarpasienrs.pojo.DataItem;
import com.faresa.daftarpasienrs.pojo.GetData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataViewModel dataViewModel;
    private AdapterItem adapterItem;
    private LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
    private ProgressBar pb;
    private List<DataItem> data = new ArrayList<>();
    FloatingActionButton fab;
    ConstraintLayout null_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        adapterItem = new AdapterItem(this);
        adapterItem.setDataGets(data);
        recyclerView = findViewById(R.id.rv_);
        null_layout = findViewById(R.id.null_image);

        initRV();
        pb = findViewById(R.id.pb);
        getData();
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });
        pb.setVisibility(View.VISIBLE);

    }

    private void getData() {
        try {
            dataViewModel.liveGet().observe(this, new Observer<GetData>() {
                @Override
                public void onChanged(GetData data1) {

                    try {
                        data.clear();

                        data.addAll(data1.getData());
                        recyclerView.setAdapter(adapterItem);
                        adapterItem.notifyDataSetChanged();
                        pb.setVisibility(View.GONE);
                        null_layout.setVisibility(View.GONE);
                    } catch (Exception e) {
                        null_layout.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception e) {

        }
    }

    private void initRV() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        dataViewModel.loadEvent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.export, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.export:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.export)));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}