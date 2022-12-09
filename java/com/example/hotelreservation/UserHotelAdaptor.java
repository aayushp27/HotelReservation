package com.example.hotelreservation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserHotelAdaptor extends RecyclerView.Adapter<UserHotelAdaptor.ViewHolder> implements Filterable {



    ArrayList<Hotels> list;
    private Context context;
    ArrayList<Hotels> listfull;


    public UserHotelAdaptor(ArrayList<Hotels> list, Context context) {
        this.list = list;
        this.context = context;
        listfull = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userhotelrec, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Hotels model = list.get(position);
        Picasso.get().load(model.getHotel_image()).placeholder(R.drawable.ic_launcher_background).into(holder.hotel_image);
        holder.hotel_name.setText(list.get(position).getHotel_name());
        holder.hotel_place.setText(list.get(position).getHotel_place());
        holder.hotel_price.setText(list.get(position).getHotel_price());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingleHotel.class);
                intent.putExtra("singlehotel_image", model.getHotel_image());
                intent.putExtra("singlehotel_name", model.getHotel_name());
                intent.putExtra("singlehotel_place", model.getHotel_place());
                intent.putExtra("singlehotel_city", model.getHotel_city());
                intent.putExtra("singlehotel_street", model.getHotel_street());
                intent.putExtra("singlehotel_pincode", model.getHotel_pincode());
                intent.putExtra("singlehotel_desc", model.getHotel_desc());
                intent.putExtra("singlehotel_phone", model.getHotel_phone());
                intent.putExtra("singlehotel_price", model.getHotel_price());
                intent.putExtra("singleowner_email", model.getOwner_email());
                intent.putExtra("singlehotel_email", model.getHotel_email());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return hotelFilter;
    }

    private Filter hotelFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Hotels> filteredhotelList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){

                filteredhotelList.addAll(listfull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Hotels hotel : listfull){
                    if(hotel.getHotel_name().toLowerCase().contains(filterPattern))
                        filteredhotelList.add(hotel);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredhotelList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            list.clear();
            list.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        //change to whatever text/image is required from database
        private TextView hotel_name, hotel_place, hotel_price;//, hotel_desc, hotel_street, hotel_city, hotel_pincode, hotel_phone, hotel_email
        private CardView parent;
        private ImageView hotel_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotel_name = itemView.findViewById(R.id.hotel_name);
            parent = itemView.findViewById(R.id.parent);
            hotel_place = itemView.findViewById(R.id.hotel_place);
            hotel_image = itemView.findViewById(R.id.hotel_image);
            hotel_price = itemView.findViewById(R.id.hotel_price);

        }
    }
}
