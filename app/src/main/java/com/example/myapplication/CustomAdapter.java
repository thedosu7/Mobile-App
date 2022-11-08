package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList trip_id, trip_name, trip_destination, trip_date, trip_require, trip_description;
    Animation animation;

    CustomAdapter(
                Activity activity,
                Context context,
                ArrayList trip_id,
                ArrayList trip_name,
                ArrayList trip_destination,
                ArrayList trip_date,
                ArrayList trip_require,
                ArrayList trip_description){
        this.activity = activity;
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
         View view = layoutInflater.inflate(R.layout.row_table, parent,false);
         return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView")  int position) {
        holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.trip_name_txt.setText(String.valueOf(trip_name.get(position)));
        holder.trip_destination_txt.setText(String.valueOf(trip_destination.get(position)));
        holder.trip_date_txt.setText(String.valueOf(trip_date.get(position)));
        holder.trip_require_txt.setText(String.valueOf(trip_require.get(position)));
        holder.trip_description_txt.setText(String.valueOf(trip_description.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(trip_id.get(position)));
                intent.putExtra("name",String.valueOf(trip_name.get(position)));
                intent.putExtra("destination",String.valueOf(trip_destination.get(position)));
                intent.putExtra("date",String.valueOf(trip_date.get(position)));
                intent.putExtra("require",String.valueOf(trip_require.get(position)));
                intent.putExtra("description",String.valueOf(trip_description.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }


     class MyViewHolder extends RecyclerView.ViewHolder {
        TextView trip_id_txt, trip_name_txt, trip_destination_txt, trip_date_txt, trip_require_txt, trip_description_txt;
        LinearLayout mainLayout;
         MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            trip_name_txt = itemView.findViewById(R.id.trip_name_txt);
            trip_destination_txt = itemView.findViewById(R.id.trip_destination_txt);
            trip_date_txt = itemView.findViewById(R.id.trip_date_txt);
            trip_require_txt = itemView.findViewById(R.id.trip_require_txt);
            trip_description_txt = itemView.findViewById(R.id.trip_description_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            animation = AnimationUtils.loadAnimation(context, R.anim.trans_anim);
            mainLayout.setAnimation(animation);
        }
    }
}
