package com.emart.emartindia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.emart.emartindia.ProductDetail;
import com.emart.emartindia.R;
import com.emart.emartindia.models.Products;

import java.util.ArrayList;

/*
 * @author Bipin Singh
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.BrowseViewHolder> {

    Context context;

    ArrayList<Products> list = new ArrayList<>();


    public ProductsAdapter(ArrayList<Products> list) {

        this.list = list;
    }

    @Override
    public BrowseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.browsecard, parent, false);

        return new BrowseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.BrowseViewHolder holder, int position) {

        if (context == null) {
            return;
        }
        Glide.with(context).load(list.get(position).getImage()).into(holder.imgview);
        holder.titleTV.setText(list.get(position).getName());
        holder.priceTV.setText("₹ " + list.get(position).getPrice());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("productid", list.get(position).getId());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BrowseViewHolder extends RecyclerView.ViewHolder {

        ImageView imgview;
        TextView titleTV, subsTV, priceTV;
        CardView cardView;

        public BrowseViewHolder(View itemView) {
            super(itemView);
            imgview = itemView.findViewById(R.id.browseimage);
            titleTV = itemView.findViewById(R.id.browsename);
            subsTV = itemView.findViewById(R.id.browsesub);
            priceTV = itemView.findViewById(R.id.browseprice);
            cardView = itemView.findViewById(R.id.browsecardview);

        }
    }

}
