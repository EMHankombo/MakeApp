package com.example.enoch.makeapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enoch.makeapp.data.model.ItemDisplayModel;
import com.squareup.picasso.Picasso;

/**
 * Created by mainza1992 on 20/10/2017.
 */

public class ItemDisplayAdapter extends RecyclerView.Adapter<ItemDisplayAdapter.ItemViewHolder> {


    private ItemDisplayModel itemDisplayModel;
    private  int row;

    private Context applicationContext;

    public ItemDisplayAdapter(ItemDisplayModel itemDisplayModel, int row, Context applicationContext) {
        this.itemDisplayModel = itemDisplayModel;
        this.row = row;
        this.applicationContext = applicationContext;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row,parent,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {



        holder.brand.setText(itemDisplayModel.getBrand());
        holder.name.setText(itemDisplayModel.getName());
        holder.price.setText("$"+ itemDisplayModel.getPrice());
        holder.description.setText(itemDisplayModel.getDescription());

        Picasso.with(applicationContext)
                .load(itemDisplayModel.getImageLink())
                .resize(200, 250)
                .centerCrop()
                .into(holder.image);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = itemDisplayModel.getProductLink();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();

                builder.setToolbarColor(v.getContext().getResources().getColor(R.color.colorAccent));

                builder.setCloseButtonIcon(BitmapFactory.decodeResource(v.getContext().getResources(),R.drawable.ic_arrow_back));

                builder.setStartAnimations(v.getContext(), R.anim.slide_in_right, R.anim.slide_out_left);
                builder.setExitAnimations(v.getContext(), R.anim.slide_in_left, R.anim.slide_out_right);
                builder.build().launchUrl(v.getContext(), Uri.parse(url));
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;


    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView brand,name,price,description;
        Button button;
        public ItemViewHolder(View itemView) {
            super(itemView);

            image=(ImageView)itemView.findViewById(R.id.imageViewItem);

            brand = (TextView)itemView.findViewById(R.id.itemBrand);

            name = (TextView)itemView.findViewById(R.id.itemName);

            price = (TextView)itemView.findViewById(R.id.itemPrice);

            description = (TextView)itemView.findViewById(R.id.itemDesc);

            button = (Button) itemView.findViewById(R.id.buyButton);
        }

    }
}
