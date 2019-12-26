package com.prio.pariwisataserang.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prio.pariwisataserang.Model.Model_Ulasan;
import com.prio.pariwisataserang.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_Ulasan extends RecyclerView.Adapter<Adapter_Ulasan.Holder> {

    private List<Model_Ulasan> ulasanList;
    private Context mcontext;

    public Adapter_Ulasan(List<Model_Ulasan> ulasanList, Context mcontext) {
        this.ulasanList = ulasanList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.model_ulasan,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Model_Ulasan ulasan = ulasanList.get(i);
        holder.textUlasan.setText(ulasan.getUlasan());
        Glide.with(mcontext).load(ulasan.getPhoto()).into(holder.profileUser);
        holder.namaUser.setText(ulasan.getUsername());
        holder.waktu.setText(timestampString((Long)ulasan.getTimestamp()));
        holder.ratingBar.setRating(Float.parseFloat(String.valueOf(ulasan.getBanyakrating())));

        holder.btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mcontext, holder.btnmenu);
                popupMenu.inflate(R.menu.menu_ulasan);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.spam:
                                Snackbar.make(v,"Ulasan ditandai sebagai spam",Snackbar.LENGTH_LONG).show();
                                break;
                            case R.id.tidakpantas:
                                Snackbar.make(v,"Ulasan ditandai sebagai tidak pantas",Snackbar.LENGTH_LONG).show();
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (ulasanList == null) {
            ulasanList.size();
        }
        return 1;
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView namaUser, textUlasan, waktu, btnmenu;
        CircleImageView profileUser;
        AppCompatRatingBar ratingBar;

        public Holder(@NonNull View itemView) {
            super(itemView);


            waktu = itemView.findViewById(R.id.timestamp);
            namaUser = itemView.findViewById(R.id.username_listUlasan);
            textUlasan = itemView.findViewById(R.id.text_ulasan);
            profileUser = itemView.findViewById(R.id.photoprofile_listulasan);
            ratingBar = itemView.findViewById(R.id.rating_model);
            btnmenu = itemView.findViewById(R.id.btn_menuModelUlasan);
        }
    }

    private String timestampString (long time){
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd/MM/yyyy" +
                "",calendar).toString();
        return date;
    }


}
