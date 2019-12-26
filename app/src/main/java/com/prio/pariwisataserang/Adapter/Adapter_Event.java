package com.prio.pariwisataserang.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prio.pariwisataserang.Model.Model_Event;
import com.prio.pariwisataserang.R;

import java.util.List;

public class Adapter_Event extends RecyclerView.Adapter<Adapter_Event.Holder> {

    Context context;
    List<Model_Event> model_events;

    public Adapter_Event(Context context, List<Model_Event> model_events) {
        this.context = context;
        this.model_events = model_events;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_event,viewGroup,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Model_Event event = model_events.get(i);
        holder.title.setText(event.getTitle());
        holder.lokasi.setText(event.getTempat());
        holder.date.setText(event.getDate());
        Glide.with(context).load(event.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return model_events.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,date,lokasi;

        public Holder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_modelevent);
            title = itemView.findViewById(R.id.title_event);
            date = itemView.findViewById(R.id.date_event);
            lokasi = itemView.findViewById(R.id.alamat_event);
        }
    }
}
