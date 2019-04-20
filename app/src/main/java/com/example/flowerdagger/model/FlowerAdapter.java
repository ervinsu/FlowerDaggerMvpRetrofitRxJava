package com.example.flowerdagger.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flowerdagger.R;

import java.util.ArrayList;
import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.Holder> {

    private final LayoutInflater mInflater;
    private List<FlowerResponse> mItem_flower;
    private FlowerClickListener mListener;

    public FlowerAdapter(FlowerClickListener listener, LayoutInflater inflater){
        mListener = listener;
        mInflater = inflater;
        mItem_flower = new ArrayList<>();
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(mInflater.inflate(R.layout.item_flower,viewGroup,false));
    }

    @Override
    public int getItemCount() {
        return mItem_flower.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        FlowerResponse flowerResponse = mItem_flower.get(i);
        holder.mName.setText(flowerResponse.getName());
        holder.mPrice.setText(String.format("$%.2f", flowerResponse.getPrice()));

        Glide.with(holder.itemView.getContext()).load(Constant.PHOTO_URL + flowerResponse.getPhoto()).into(holder.mPhoto);

    }

    public void addFlower(List<FlowerResponse> flowerResponse) {
        this.mItem_flower.addAll(flowerResponse);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mPhoto;
        private TextView mName, mPrice;
        public Holder(@NonNull View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.flowerPhoto);
            mName = (TextView) itemView.findViewById(R.id.flowerName);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getLayoutPosition(), mItem_flower.get(getAdapterPosition()).getName());
        }
    }

    public interface FlowerClickListener {

        void onClick(int position, String name);
    }
}
