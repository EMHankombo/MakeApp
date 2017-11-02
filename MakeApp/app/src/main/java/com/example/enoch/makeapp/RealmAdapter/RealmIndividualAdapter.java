package com.example.enoch.makeapp.RealmAdapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enoch.makeapp.R;
import com.example.enoch.makeapp.data.database.localdb.IndividualItemDatabase;

/**
 * Created by mainza1992 on 01/11/2017.
 */

public class RealmIndividualAdapter extends RecyclerView.Adapter<RealmIndividualAdapter.MyItemViewHolder> {


    private IndividualItemDatabase individualItemDatabase;
    private  int row;

    private Context applicationContext;

    public RealmIndividualAdapter(IndividualItemDatabase individualItemDatabase, int row, Context applicationContext) {
        this.individualItemDatabase = individualItemDatabase;
        this.row = row;
        this.applicationContext = applicationContext;
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(row,parent,false);

        return new MyItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {

        //holder.brand.setText(individualItemDatabase.getBrand());
        holder.name.setText(individualItemDatabase.getName());
        holder.price.setText("£"+ individualItemDatabase.getPrice());
        holder.description.setText(individualItemDatabase.getDescription());

            if(individualItemDatabase.getRating() != 0.0) {
                holder.bar.setRating((float) (individualItemDatabase.getRating()));

            }else{
                holder.bar.setRating((float) (0.0));

            }

           holder.button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast.makeText(applicationContext,"No internet connection",Toast.LENGTH_LONG).show();
               }
           });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView brand,name,price,description;
        Button button;
        RatingBar bar;

        public MyItemViewHolder(View itemView) {
            super(itemView);

            image=(ImageView)itemView.findViewById(R.id.imageViewItem);

           // brand = (TextView)itemView.findViewById(R.id.itemBrand);

            name = (TextView)itemView.findViewById(R.id.itemName);

            price = (TextView)itemView.findViewById(R.id.itemPrice);

            description = (TextView)itemView.findViewById(R.id.itemDesc);

            button = (Button) itemView.findViewById(R.id.buyButton);
            bar = (RatingBar)itemView.findViewById(R.id.ratingBar2);
        }
    }

    public boolean isNetworkConnected() {

        // get Connectivity Manager to get network status
        ConnectivityManager connMgr = (ConnectivityManager)
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Log.i("Connection test", "passed");
            return true; //we have a connection
        } else return false;


    }
}
