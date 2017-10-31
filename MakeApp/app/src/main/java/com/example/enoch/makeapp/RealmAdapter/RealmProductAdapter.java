package com.example.enoch.makeapp.RealmAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enoch.makeapp.R;
import com.example.enoch.makeapp.RealmProductOnclick;
import com.example.enoch.makeapp.data.database.localdb.ProductsDatabase;

import java.util.ArrayList;

/**
 * Created by mainza1992 on 31/10/2017.
 */

public class RealmProductAdapter extends RecyclerView.Adapter<RealmProductAdapter.MyViewHolder> {

    private ArrayList<ProductsDatabase> productsDatabase;
    private int row;
    private Context applicationContext;
    private RealmProductOnclick realmClickListener;

    public RealmProductAdapter(ArrayList<ProductsDatabase> productsDatabase, int row, Context applicationContext, RealmProductOnclick realmClickListener) {
        this.productsDatabase = productsDatabase;
        this.row = row;
        this.applicationContext = applicationContext;
        this.realmClickListener = realmClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.brand.setText(productsDatabase.get(position).getBrand());
        holder.name.setText(productsDatabase.get(position).getName());
        holder.price.setText("$" + productsDatabase.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return productsDatabase.size();
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



        public void bind(final ProductsDatabase item, final RealmProductOnclick onClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    onClickListener.onItemClick(item);

                }

            });


        }
    }
}
