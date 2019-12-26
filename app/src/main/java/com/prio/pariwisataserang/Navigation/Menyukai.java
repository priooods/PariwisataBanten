package com.prio.pariwisataserang.Navigation;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prio.pariwisataserang.Activity.Activity_DetailWisata;
import com.prio.pariwisataserang.Model.Model_Pariwisata;
import com.prio.pariwisataserang.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Menyukai extends Fragment {

    RecyclerView recyclerView;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    List<Model_Pariwisata> listwisata;
    Adapter_Menyukai adapter;
    List<String> checkUserList;

    String id;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_love,container,false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("pariwisata");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("id");

        textView = view.findViewById(R.id.disukai_kosong);
        recyclerView = view.findViewById(R.id.recycler_love);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));


        CheckUser();

        return view;
    }

    private void CheckUser (){
        checkUserList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("menyukai")
                .child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                checkUserList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    checkUserList.add(snapshot.getKey());
                }


                BacaMenyukai();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void BacaMenyukai (){
        textView.setVisibility(View.VISIBLE);
        listwisata = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("pariwisata");
        reference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                listwisata.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Model_Pariwisata post = snapshot.getValue(Model_Pariwisata.class);
                    for (String id : checkUserList){
                        if (post.getId().equals(id)){
                            listwisata.add(post);
                            textView.setVisibility(View.GONE);
                        }
                    }

                }

                adapter = new Adapter_Menyukai(getContext(),listwisata);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public class Adapter_Menyukai extends RecyclerView.Adapter<Adapter_Menyukai.Holder> implements Filterable {

        Context context;
        List<Model_Pariwisata> listpantais;
        List<Model_Pariwisata> listwisata;

        public Adapter_Menyukai(Context context, List<Model_Pariwisata> listpantais) {
            this.context = context;
            this.listpantais = listpantais;
            this.listwisata = listpantais;

        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.model_pariwisata, viewGroup, false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int i) {

            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            holder.title.setText(listwisata.get(i).getNama());
            holder.alamat.setText(listwisata.get(i).getAlamat());
            Glide.with(context).load(listwisata.get(i).getPhoto()).into(holder.photo);
            menyukai(listwisata.get(i).getId(),holder.love);

            jumlahUlasan(listwisata.get(i).getId(),holder.jumlahUlasan);
            jumlahsuka(holder.disukai,listwisata.get(i).getId());
            tambahFavorite(listwisata.get(i).getId(),holder.favorite);
            jumlahRating(listwisata.get(i).getId(),holder.appCompatRatingBar);

            holder.love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.love.getTag().equals("liked")){
                        FirebaseDatabase.getInstance().getReference().child("disukai")
                                .child(listwisata.get(i).getId())
                                .child(firebaseUser.getUid())
                                .removeValue();
                        FirebaseDatabase.getInstance().getReference().child("menyukai")
                                .child(firebaseUser.getUid())
                                .child(listwisata.get(i).getId())
                                .removeValue();
                    } else {
                        FirebaseDatabase.getInstance().getReference().child("disukai")
                                .child(listwisata.get(i).getId())
                                .child(firebaseUser.getUid())
                                .setValue(true);
                        FirebaseDatabase.getInstance().getReference().child("menyukai")
                                .child(firebaseUser.getUid())
                                .child(listwisata.get(i).getId())
                                .setValue(true);
                    }
                }
            });

            holder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.favorite.getTag().equals("addfavorites")){
                        FirebaseDatabase.getInstance().getReference().child("favorites").child(firebaseUser.getUid())
                                .child(listwisata.get(i).getId()).removeValue();
                    } else {
                        FirebaseDatabase.getInstance().getReference().child("favorites").child(firebaseUser.getUid())
                                .child(listwisata.get(i).getId()).setValue(true);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return listwisata.size();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String key = charSequence.toString();
                    if (key.isEmpty()){
                        listwisata = listpantais;
                    } else {
                        List<Model_Pariwisata> modelwisata = new ArrayList<>();
                        for (Model_Pariwisata model : listpantais){
                            if (model.getAlamat().toLowerCase().contains(key.toLowerCase()) ||
                                    model.getNama().toLowerCase().contains(key.toLowerCase())){
                                modelwisata.add(model);
                            }
                        }

                        listwisata = modelwisata;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = listwisata;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    listwisata = (List<Model_Pariwisata>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

        public class Holder extends RecyclerView.ViewHolder {

            ImageView photo, love, favorite;
            TextView title, alamat, disukai, jumlahUlasan;
            AppCompatRatingBar appCompatRatingBar;

            public Holder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                love = itemView.findViewById(R.id.image_addLove_list);
                favorite = itemView.findViewById(R.id.image_addfavorite_list);
                alamat = itemView.findViewById(R.id.alamat);
                disukai = itemView.findViewById(R.id.jumlah_suka);
                photo = itemView.findViewById(R.id.image_modelrekomendasi);
                jumlahUlasan = itemView.findViewById(R.id.jumlah_ulasan);
                appCompatRatingBar = itemView.findViewById(R.id.rating);


                photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();

                        Intent intent = new Intent(getActivity(), Activity_DetailWisata.class);
                        intent.putExtra("nama",listpantais.get(position).getNama());
                        intent.putExtra("alamat",listpantais.get(position).getAlamat());
                        intent.putExtra("descripsi",listpantais.get(position).getDesc());
                        intent.putExtra("photo",listpantais.get(position).getPhoto());
                        intent.putExtra("id",listpantais.get(position).getId());
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),title,"sharedElement");
                        startActivity(intent,options.toBundle());
                    }
                });
            }
        }
    }

    //TODO: ini untuk mengatur cara kerja like
    private void menyukai ( String id ,final ImageView imageView){
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("menyukai").child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(id).exists()){
                    imageView.setImageResource(R.drawable.icon_lovered);
                    imageView.setTag("liked");
                } else {
                    imageView.setImageResource(R.drawable.icon_love);
                    imageView.setTag("like");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void jumlahsuka (final TextView likes , String id){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("disukai")
                .child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                likes.setText(dataSnapshot.getChildrenCount()+" "+"Suka");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //TODO: ini untuk mengatur cara kerja favorite
    private void tambahFavorite ( String id ,final ImageView imageView){
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("favorites")
                .child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(id).exists()){
                    imageView.setImageResource(R.drawable.icon_favorite);
                    imageView.setTag("addfavorites");
                } else {
                    imageView.setImageResource(R.drawable.icon_favorite_check);
                    imageView.setTag("favorites");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //TODO: ini untuk menampilkan jumlah ulasan
    private void jumlahUlasan (String id, final TextView ulasan){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Ulasan").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ulasan.setText( dataSnapshot.getChildrenCount() +" "+ "Ulasan");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void jumlahRating (String id, AppCompatRatingBar ratingBar){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Ulasan").child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                double total = 0.0,
                        count = 0.0,
                        average = 0.0 ;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object rate = map.get("banyakrating");
                    double rating = Double.parseDouble(String.valueOf(rate));
                    total += rating;
                    count = count + 1 ;
                    average = total / count;

                    ratingBar.setRating(Float.parseFloat(String.valueOf(average)));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
