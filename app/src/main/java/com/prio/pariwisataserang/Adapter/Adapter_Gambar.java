package com.prio.pariwisataserang.Adapter;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.prio.pariwisataserang.Model.Model_Gambar;
import com.prio.pariwisataserang.R;

import java.util.List;

public class Adapter_Gambar extends RecyclerView.Adapter<Adapter_Gambar.Holder> {

    List<Model_Gambar> model_gambar;
    Context context;


    public Adapter_Gambar(List<Model_Gambar> model_gambar, Context context) {
        this.model_gambar = model_gambar;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_gambar,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Glide.with(context).load(model_gambar.get(i).getPhoto()).into(holder.gambar);

    }

    @Override
    public int getItemCount() {
        return model_gambar.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        ImageView gambar;

        public Holder(@NonNull View itemView) {
            super(itemView);

            gambar = itemView.findViewById(R.id.image_modelgambar);
            gambar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    ImagePopup imagePopup = new ImagePopup(context);
                    imagePopup.setBackgroundColor(Color.BLACK);
                    imagePopup.setFullScreen(true);
                    imagePopup.setHideCloseIcon(true);
                    imagePopup.setImageOnClickClose(true);
                    imagePopup.initiatePopupWithGlide(model_gambar.get(position).getPhoto());
                    imagePopup.viewPopup();

                }
            });
        }
    }


}
