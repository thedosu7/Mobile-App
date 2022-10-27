package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList trip_id, trip_name, trip_destination, trip_date, trip_require, trip_description;
    CustomAdapter(Context context,
                  ArrayList trip_id,
                  ArrayList trip_name,
                  ArrayList trip_destination,
                  ArrayList trip_date,
                  ArrayList trip_require,
                  ArrayList trip_description){
        this.context = context;
        this.trip_id = trip_id;
        this.trip_name = trip_name;
        this.trip_destination = trip_destination;
        this.trip_date = trip_date;
        this.trip_require = trip_require;
        this.trip_description = trip_description;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         LayoutInflater layoutInflater = LayoutInflater.from(context);
         View view = layoutInflater.inflate(R.layout.row, parent,false);
         return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.trip_name_txt.setText(String.valueOf(trip_name.get(position)));
        holder.trip_destination_txt.setText(String.valueOf(trip_destination.get(position)));
        holder.trip_date_txt.setText(String.valueOf(trip_date.get(position)));
        holder.trip_require_txt.setText(String.valueOf(trip_require.get(position)));
        holder.trip_description_txt.setText(String.valueOf(trip_description.get(position)));


    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView trip_id_txt, trip_name_txt, trip_destination_txt, trip_date_txt, trip_require_txt, trip_description_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            trip_name_txt = itemView.findViewById(R.id.trip_name_txt);
            trip_destination_txt = itemView.findViewById(R.id.trip_destination_txt);
            trip_date_txt = itemView.findViewById(R.id.trip_date_txt);
            trip_require_txt = itemView.findViewById(R.id.trip_require_txt);
            trip_description_txt = itemView.findViewById(R.id.trip_description_txt);
        }
    }
}
