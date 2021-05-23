package com.emart.emartindia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.emart.emartindia.OrderDetails;
import com.emart.emartindia.R;
import com.emart.emartindia.models.Orders;


import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    Context context;

    ArrayList<Orders> list = new ArrayList<>();

    public OrdersAdapter(ArrayList<Orders> list){

        this.list=list;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.orderscard,parent,false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {

        holder.orderid.setText(""+list.get(position).getId().toUpperCase());
        holder.orderdate.setText(""+list.get(position).getCreatedAt());
        holder.orderamount.setText(""+list.get(position).getTotalPrice());

        if(list.get(position).getIsDelivered()){
            holder.orderstatus.setText(""+list.get(position).getDeliveredAt());
        }
        else {
            holder.orderstatus.setText("Yet to be delivered");
        }

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, OrderDetails.class);
                intent.putExtra("orderid",list.get(position).getId());
                System.out.println("Order id 1"+list.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView orderid, orderdate, orderstatus, orderamount;
        CardView cv;

        public OrderViewHolder(View itemView) {

            super(itemView);
            orderid = itemView.findViewById(R.id.orderid);
            orderdate = itemView.findViewById(R.id.orderdate);
            orderstatus = itemView.findViewById(R.id.orderstatus);
            orderamount = itemView.findViewById(R.id.orderamount);

            cv = itemView.findViewById(R.id.orderscardview);

        }
    }
}
