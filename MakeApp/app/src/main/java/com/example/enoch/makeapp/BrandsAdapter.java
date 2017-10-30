package com.example.enoch.makeapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enoch.makeapp.data.model.BrandModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mainza1992 on 19/10/2017.
 */

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandViewHolder> {

    private List<BrandModel> brandModels;

    private int row;

    private Context applicationContext;

    private final BrandFragment.onClickListener clickListener;

    public BrandsAdapter(List<BrandModel> brandModels, int row, Context applicationContext, BrandFragment.onClickListener clickListener) {
        this.brandModels = brandModels;
        this.row = row;
        this.applicationContext = applicationContext;
        this.clickListener = clickListener;
    }

    @Override
    public BrandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(row,parent,false);

        return new BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BrandViewHolder holder, int position) {


        holder.brand.setText(brandModels.get(position).getBrand());
        holder.name.setText(brandModels.get(position).getName());
        holder.price.setText("$" + brandModels.get(position).getPrice());

        //bind the onClick
        holder.bind(brandModels.get(position), clickListener);

        Picasso.with(applicationContext)
                .load(brandModels.get(position).getImageLink())
                .resize(200, 250)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return brandModels.size();
    }

    public class BrandViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView brand, name, price;

        public BrandViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            brand = (TextView) itemView.findViewById(R.id.brandName);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
        }


        public void bind(final BrandModel item, final BrandFragment.onClickListener onClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    onClickListener.onItemClick(item);

                }

            });


        }

    }
}
