package com.prio.pariwisataserang.Navigation;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prio.pariwisataserang.Activity.Activity_DetailWisata;
import com.prio.pariwisataserang.Model.Model_Pariwisata;
import com.prio.pariwisataserang.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
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

import de.hdodenhof.circleimageview.CircleImageView;


public class Pariwisata extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    List<Model_Pariwisata> list;
    Adapter_Alam adapter;

    ProgressBar progressBar;
    FirebaseUser firebaseUser;
    CircleImageView photo;

    TextView
            text_All,text_pandeglang,text_pulau,text_pantai, text_serang
            , text_taman, text_gunung, text_cilegon ;
    LinearLayout linearLayout_searchClick,
            linearLayout_All,linearLayout_pantai,linearLayout_pulau
            ,linearLayout_pandeglang,linearLayout_serang,linearLayout_taman,linearLayout_cilegon
            ,linearLayout_gunung ;


    EditText cari_wisata;

    private InterstitialAd mInterstitialAd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pariwisata,container,false);

        MobileAds.initialize(getActivity(),"ca-app-pub-3494659501934540~8750229544");
        setAds();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("pariwisata");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        progressBar = view.findViewById(R.id.progresBar_pariwisata);

        cari_wisata = view.findViewById(R.id.search_wisata);
        recyclerView = view.findViewById(R.id.recyclerView_pariwisata);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();
        adapter = new Adapter_Alam(getContext(),list);
        recyclerView.setAdapter(adapter);

        bacaWisata();

        cari_wisata.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TabText(view);


        return view;
    }

    private void TabText (View view){

        text_All = view.findViewById(R.id.text_All);
        text_pandeglang = view.findViewById(R.id.text_kotaPandegelang);
        text_pantai = view.findViewById(R.id.text_Pantai);
        text_pulau = view.findViewById(R.id.text_Pulau);
        text_serang = view.findViewById(R.id.text_kotaSerang);
        text_cilegon = view.findViewById(R.id.text_kotaCilegon);
        text_gunung = view.findViewById(R.id.text_gunung);
        text_taman = view.findViewById(R.id.text_Taman);

        linearLayout_All = view.findViewById(R.id.lAll);
        linearLayout_pandeglang = view.findViewById(R.id.lpandeglang);
        linearLayout_pulau = view.findViewById(R.id.lpulau);
        linearLayout_pantai = view.findViewById(R.id.lpantai);
        linearLayout_serang = view.findViewById(R.id.lserang);
        linearLayout_taman = view.findViewById(R.id.ltaman);
        linearLayout_cilegon = view.findViewById(R.id.lcilegon);
        linearLayout_gunung = view.findViewById(R.id.lgunung);

        text_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cari_wisata.getText().clear();
                linearLayout_All.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pulau.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pantai.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_serang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegon.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_gunung.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_taman.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));

                text_All.setTextColor(getResources().getColor(R.color.birumuda));
                text_pulau.setTextColor(getResources().getColor(R.color.colorText));
                text_pantai.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_serang.setTextColor(getResources().getColor(R.color.colorText));
                text_taman.setTextColor(getResources().getColor(R.color.colorText));
                text_gunung.setTextColor(getResources().getColor(R.color.colorText));

            }
        });
        text_pandeglang.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;
            @Override
            public void onClick(View v) {
                String string = text_pandeglang.getText().toString();
                cari_wisata.setText(string);


                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_All.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pulau.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pantai.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_serang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegon.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_gunung.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_taman.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));


                text_pandeglang.setTextColor(getResources().getColor(R.color.birumuda));
                text_pulau.setTextColor(getResources().getColor(R.color.colorText));
                text_pantai.setTextColor(getResources().getColor(R.color.colorText));
                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_serang.setTextColor(getResources().getColor(R.color.colorText));
                text_taman.setTextColor(getResources().getColor(R.color.colorText));
                text_gunung.setTextColor(getResources().getColor(R.color.colorText));

            }
        });
        text_pantai.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;
            @Override
            public void onClick(View v) {
                String string = text_pantai.getText().toString();
                cari_wisata.setText(string);

                linearLayout_pantai.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pulau.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_All.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_serang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegon.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_gunung.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_taman.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));


                text_pantai.setTextColor(getResources().getColor(R.color.birumuda));
                text_pulau.setTextColor(getResources().getColor(R.color.colorText));
                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_serang.setTextColor(getResources().getColor(R.color.colorText));
                text_taman.setTextColor(getResources().getColor(R.color.colorText));
                text_gunung.setTextColor(getResources().getColor(R.color.colorText));

            }
        });
        text_pulau.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;
            @Override
            public void onClick(View v) {

                String string = text_pulau.getText().toString();
                cari_wisata.setText(string);

                linearLayout_pulau.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_All.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pantai.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_serang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegon.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_gunung.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_taman.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));


                text_pulau.setTextColor(getResources().getColor(R.color.birumuda));
                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_pantai.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_serang.setTextColor(getResources().getColor(R.color.colorText));
                text_taman.setTextColor(getResources().getColor(R.color.colorText));
                text_gunung.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
        text_serang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text_serang.getText().toString();
                cari_wisata.setText(string);

                linearLayout_serang.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_All.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pantai.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pulau.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegon.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_gunung.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_taman.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));


                text_serang.setTextColor(getResources().getColor(R.color.birumuda));
                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_pantai.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_pulau.setTextColor(getResources().getColor(R.color.colorText));
                text_taman.setTextColor(getResources().getColor(R.color.colorText));
                text_gunung.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
        text_cilegon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text_cilegon.getText().toString();
                cari_wisata.setText(string);

                linearLayout_cilegon.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_All.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pantai.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_serang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pulau.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_gunung.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_taman.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));


                text_cilegon.setTextColor(getResources().getColor(R.color.birumuda));
                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_pantai.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_pulau.setTextColor(getResources().getColor(R.color.colorText));
                text_serang.setTextColor(getResources().getColor(R.color.colorText));
                text_taman.setTextColor(getResources().getColor(R.color.colorText));
                text_gunung.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
        text_gunung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text_gunung.getText().toString();
                cari_wisata.setText(string);

                linearLayout_gunung.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_All.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pantai.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_serang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegon.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pulau.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_taman.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));


                text_gunung.setTextColor(getResources().getColor(R.color.birumuda));
                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_pantai.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_serang.setTextColor(getResources().getColor(R.color.colorText));
                text_taman.setTextColor(getResources().getColor(R.color.colorText));
                text_pulau.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
        text_taman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text_taman.getText().toString();
                cari_wisata.setText(string);

                linearLayout_taman.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_All.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pantai.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_serang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegon.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_gunung.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pulau.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));


                text_taman.setTextColor(getResources().getColor(R.color.birumuda));
                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_pantai.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_serang.setTextColor(getResources().getColor(R.color.colorText));
                text_pulau.setTextColor(getResources().getColor(R.color.colorText));
                text_gunung.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
    }

    private void bacaWisata(){
        progressBar.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Model_Pariwisata pantai = snapshot.getValue(Model_Pariwisata.class);
                    list.add(pantai);
                }

                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public class Adapter_Alam extends RecyclerView.Adapter<Adapter_Alam.Holder> implements Filterable {

        Context context;
        List<Model_Pariwisata> listpantais;
        List<Model_Pariwisata> listwisata;

        public Adapter_Alam(Context context, List<Model_Pariwisata> listpantais) {
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
            addlistLove(listwisata.get(i).getId(),holder.love);

            jumlahUlasan(listwisata.get(i).getId(),holder.jumlahUlasan);
            jumlahsuka(holder.disukai,listwisata.get(i).getId());
            tambahFavorite(listwisata.get(i).getId(),holder.favorite);
            jumlahRating(listwisata.get(i).getId(),holder.appCompatRatingBar);

            holder.love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.love.getTag().equals("like")){
                        FirebaseDatabase.getInstance().getReference().child("disukai")
                                .child(listwisata.get(i).getId()).child(firebaseUser.getUid()).setValue(true);

                        FirebaseDatabase.getInstance().getReference().child("menyukai")
                                .child(firebaseUser.getUid())
                                .child(listwisata.get(i).getId())
                                .setValue(true);

                        Toast.makeText(getContext(),"Makasih yahh love nya",Toast.LENGTH_LONG).show();
                    } else {
                        FirebaseDatabase.getInstance().getReference().child("disukai")
                                .child(listwisata.get(i).getId()).child(firebaseUser.getUid()).removeValue();

                        FirebaseDatabase.getInstance().getReference().child("menyukai")
                                .child(firebaseUser.getUid())
                                .child(listwisata.get(i).getId())
                                .removeValue();
                        Toast.makeText(getContext(),"Nanti kasih aku love lagi ya",Toast.LENGTH_LONG).show();
                    }
                }
            });

            holder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.favorite.getTag().equals("favorites")){
                        FirebaseDatabase.getInstance().getReference().child("favorites").child(firebaseUser.getUid())
                                .child(listwisata.get(i).getId())
                                .setValue(true);
                        Snackbar.make(v,"Yuhuu list favorite kamu bertambah",Snackbar.LENGTH_LONG).show();
                    } else {
                        FirebaseDatabase.getInstance().getReference().child("favorites").child(firebaseUser.getUid())
                                .child(listwisata.get(i).getId())
                                .removeValue();
                        Snackbar.make(v,"Jangan dihapus dari favorite dong",Snackbar.LENGTH_LONG).show();
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
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                            mInterstitialAd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                    Intent intent = new Intent(getActivity(), Activity_DetailWisata.class);
                                    intent.putExtra("nama",listpantais.get(position).getNama());
                                    intent.putExtra("alamat",listpantais.get(position).getAlamat());
                                    intent.putExtra("descripsi",listpantais.get(position).getDesc());
                                    intent.putExtra("tiket",listpantais.get(position).getTiket());
                                    intent.putExtra("photo",listpantais.get(position).getPhoto());
                                    intent.putExtra("fasi",listpantais.get(position).getFasilitas());
                                    intent.putExtra("id",listpantais.get(position).getId());
                                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),title,"sharedElement");
                                    startActivity(intent,options.toBundle());

                                }
                            });
                        } else {

                        }
                    }
                });
            }
        }
    }

    //TODO: ini untuk mengatur cara kerja like
    private void menyukai ( String id ,final ImageView imageView){
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("disukai").child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(firebaseUser.getUid()).exists()){
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

    //TODO: kirim data list Menyukai
    private void addlistLove ( String id ,final ImageView imageView){
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

    private void setAds(){
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3494659501934540/9916789797");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

}
