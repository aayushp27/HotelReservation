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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserHistoryAdaptor extends RecyclerView.Adapter<UserHistoryAdaptor.ViewHolder> {

    ArrayList<Transactions> list;
    Context context;

    public UserHistoryAdaptor(ArrayList<Transactions> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public UserHistoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userhistroyrec, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transactions model = list.get(position);
        Picasso.get().load(model.getHotel_image()).placeholder(R.drawable.ic_launcher_background).into(holder.hotel_image);
        holder.hotel_name.setText(list.get(position).getHotel_name());
        holder.hotel_place.setText(list.get(position).getHotel_place());
        holder.total_cost.setText(list.get(position).getHotel_price());
        holder.hotel_status.setText(list.get(position).getStatus());
        holder.number_of_rooms.setText(list.get(position).getNum_of_rooms());
        holder.date_1.setText(list.get(position).getDate_1());
        holder.date_2.setText(list.get(position).getDate_2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingleHistory.class);
                intent.putExtra("singlehotel_image", model.getHotel_image());
                intent.putExtra("singlehotel_name", model.getHotel_name());
                intent.putExtra("singlehotel_place", model.getHotel_place());
                intent.putExtra("singleuser_name", model.getFull_name());
                intent.putExtra("singleuser_email", model.getUser_email());
                intent.putExtra("singleclient_email", model.getClient_email());
                intent.putExtra("singledate_1", model.getDate_1());
                intent.putExtra("singledate_2", model.getDate_2());
                intent.putExtra("singlehotel_price", model.getHotel_price());
                intent.putExtra("singlenum_of_rooms", model.getNum_of_rooms());
                intent.putExtra("singlestatus", model.getStatus());
                intent.putExtra("UID", model.getUID());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView hotel_name, hotel_place, total_cost, hotel_status, number_of_rooms, date_1, date_2;
        private CardView parent;
        private ImageView hotel_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotel_name = itemView.findViewById(R.id.hotel_name);
            parent = itemView.findViewById(R.id.parent);
            hotel_place = itemView.findViewById(R.id.hotel_place);
            hotel_image = itemView.findViewById(R.id.hotel_image);
            total_cost = itemView.findViewById(R.id.totalcost);
            hotel_status = itemView.findViewById(R.id.hotel_status);
            number_of_rooms = itemView.findViewById(R.id.number_of_rooms);
            date_1 = itemView.findViewById(R.id.date_1);
            date_2 = itemView.findViewById(R.id.date_2);
        }
    }

}
