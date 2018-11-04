package com.oaxaca.turismo.mercados.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.oaxaca.turismo.mercados.R;
import com.oaxaca.turismo.mercados.activitys.Activity_Local;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 2/12/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext;
    private boolean typeHolder;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> imageUrls, boolean typeHolder) {
        mNames = names;
        mImageUrls = imageUrls;
        mContext = context;
        this.typeHolder = typeHolder;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view, typeHolder);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Glide.with(mContext).asBitmap().load(mImageUrls.get(position)).into(holder.image);
        holder.name.setText(mNames.get(position));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(typeHolder){
                        startIntent();
                    }
                    else{
                        openLocal(position);
                    }
                }catch (Exception e){
                    Log.d(TAG, e.toString());
                }
                Log.d(TAG, "onClick: clicked oputon an image: " + mNames.get(position));
                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startIntent(){
        mContext.startActivity(new Intent(mContext,Activity_Local.class));

    }
    public void openLocal(int position){
        TextView slogar = (TextView) ((Activity) mContext).findViewById(R.id.textSlogan);
        slogar.setText("Estas dentro de local");

    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    @Override
    public long getItemId(int position) {
        Toast.makeText(mContext,"Position == " + position, Toast.LENGTH_SHORT).show();
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name;
        RelativeLayout layout;

        public ViewHolder(View itemView, boolean i) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);

            if(i){
                name.setTextColor(Color.BLACK);
                image.setBorderColor(Color.rgb(253,113,157));
            }

        }
    }
}
