package com.example.hotelreservation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ClientHistoryAdaptor extends RecyclerView.Adapter<ClientHistoryAdaptor.ViewHolder> {

    ArrayList<Transactions> list;
    Context context;

    public ClientHistoryAdaptor(ArrayList<Transactions> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public ClientHistoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clienthistoryrec, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transactions model = list.get(position);

        holder.full_name.setText(list.get(position).getFull_name());
        holder.total_cost.setText(list.get(position).getHotel_price());
        holder.hotel_status.setText(list.get(position).getStatus());
        holder.number_of_rooms.setText(list.get(position).getNum_of_rooms());
        holder.date_1.setText(list.get(position).getDate_1());
        holder.date_2.setText(list.get(position).getDate_2());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView full_name,  total_cost, hotel_status, number_of_rooms, date_1, date_2;
        private CardView parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            full_name = itemView.findViewById(R.id.full_name);
            parent = itemView.findViewById(R.id.parent);
            total_cost = itemView.findViewById(R.id.totalcost);
            hotel_status = itemView.findViewById(R.id.hotel_status);
            number_of_rooms = itemView.findViewById(R.id.number_of_rooms);
            date_1 = itemView.findViewById(R.id.date_1);
            date_2 = itemView.findViewById(R.id.date_2);
        }
    }

}
