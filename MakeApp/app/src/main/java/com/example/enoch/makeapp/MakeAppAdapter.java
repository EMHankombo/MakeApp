package com.example.enoch.makeapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enoch.makeapp.data.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mainza1992 on 18/10/2017.
 */

public class MakeAppAdapter extends RecyclerView.Adapter<MakeAppAdapter.MyViewHolder> {

    private List<ProductModel> productModels;
    private int row;
    private Context applicationContext;
    private onClickListener clickListener;



    public MakeAppAdapter(List<ProductModel> productModels, int row, Context applicationContext,onClickListener clickListener) {
        this.productModels = productModels;
        this.row = row;
        this.applicationContext = applicationContext;
        this.clickListener = clickListener;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(parent.getContext()).inflate(row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.brand.setText(productModels.get(position).getBrand());
        holder.name.setText(productModels.get(position).getName());
        holder.price.setText("$" + productModels.get(position).getPrice());

        Picasso.with(applicationContext)
                .load(productModels.get(position).getImageLink())
                .resize(200, 250)
                .centerCrop()
                .into(holder.image);

        holder.bind(productModels.get(position), clickListener);

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView brand, name, price;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            brand = (TextView) itemView.findViewById(R.id.brandName);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
        }


        public void bind(final ProductModel item, final onClickListener onClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    onClickListener.onItemClick(item);

                }

            });


        }


    }
}
