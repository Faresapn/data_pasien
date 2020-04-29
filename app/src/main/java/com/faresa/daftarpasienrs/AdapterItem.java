package com.faresa.daftarpasienrs;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faresa.daftarpasienrs.connection.Client;
import com.faresa.daftarpasienrs.connection.Service;
import com.faresa.daftarpasienrs.pojo.DataItem;
import com.faresa.daftarpasienrs.pojo.delete.Delete;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolder> {
    private List<DataItem> dataGets;


    void setDataGets(List<DataItem> dataGets) {
        this.dataGets = dataGets;
    }

    private MainActivity mainActivity;

    public AdapterItem(MainActivity mainActivity) {

        this.mainActivity = mainActivity;
    }


    @NonNull
    @Override
    public AdapterItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterItem.ViewHolder holder, final int position) {
        holder.nama.setText(dataGets.get(position).getNama());
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popup = new PopupMenu(mainActivity.getApplicationContext(), holder.more);
                popup.inflate(R.menu.crud_divisi);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Intent intent = new Intent(mainActivity, EditActivity.class);
                                intent.putExtra("id", String.valueOf(dataGets.get(position).getPasienId()));
                                intent.putExtra("name", dataGets.get(position).getNama());
                                intent.putExtra("data", dataGets.get(position));
                                mainActivity.startActivity(intent);
                                return true;
                            case R.id.delete:
                                Service service = Client.getClient().create(Service.class);
                                Call<Delete> delete = service.delete( dataGets.get(position).getPasienId());
                                delete.enqueue(new Callback<Delete>() {
                                    @Override
                                    public void onResponse(Call<Delete> call, Response<Delete> response) {
                                        String kode = response.body().getStatusCode();
                                        if (kode.equals("0001")) {
                                            Toast.makeText(mainActivity, view.getContext().getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
                                            dataGets.remove(dataGets.get(position));
                                            notifyDataSetChanged();
                                            if (dataGets.size() == 0) {
                                                mainActivity.null_layout.setVisibility(View.VISIBLE);
                                            }
                                        } else {
                                            Toast.makeText(mainActivity.getApplicationContext(), view.getContext().getString(R.string.msg_error), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<Delete> call, Throwable t) {
                                    }
                                });
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataGets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, more;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.name);
            more = itemView.findViewById(R.id.more);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        DataItem data = dataGets.get(position);
                        Intent intent = new Intent(mainActivity, DetailActivity.class);
                        intent.putExtra("data", data);
                        Objects.requireNonNull(mainActivity).startActivity(intent);
                    }
                }
            });
        }
    }
}
