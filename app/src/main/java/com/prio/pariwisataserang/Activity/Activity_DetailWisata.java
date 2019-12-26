package com.prio.pariwisataserang.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prio.pariwisataserang.Adapter.Adapter_Gambar;
import com.prio.pariwisataserang.Adapter.Adapter_Ulasan;
import com.prio.pariwisataserang.DialogFragment.Detail_Wisata;
import com.prio.pariwisataserang.DialogFragment.List_Ulasan;
import com.prio.pariwisataserang.Model.Model_Gambar;
import com.prio.pariwisataserang.Model.Model_Ulasan;
import com.prio.pariwisataserang.R;
import com.prio.pariwisataserang.DialogFragment.Tambah_ulasan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_DetailWisata extends AppCompatActivity  {

    TextView nama, alamat, desc, jumlah_like, jumlah_ulasan
            , harga_tiket, tulis_ulasan, totalRating, total_userRating
            ,lihatsemuaUlasan, belumadaulasannya;

    ImageView photo,baca_semua;
    CircleImageView navigasi;
    AppCompatRatingBar ratingBar;
    RecyclerView recyclerView_Ulasan, recyclerView_gambar;

    Adapter_Ulasan adapter_ulasan;
    Adapter_Gambar adapter_gambar;

    List<Model_Ulasan> model_ulasans;
    List<Model_Gambar> model_gambars;

    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    Toolbar toolbar;
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        toolbar = findViewById(R.id.toolbar_detailWisata);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        nama = findViewById(R.id.title_nama);
        alamat = findViewById(R.id.titledetail_alamat_wisata);
        desc = findViewById(R.id.desc_detailwisata);
        photo = findViewById(R.id.photo_detail_wisata);
        baca_semua = findViewById(R.id.checkAll_detailwisata);
        harga_tiket = findViewById(R.id.hargatiket_wisata);
        tulis_ulasan = findViewById(R.id.ulasan_wisatawan);
        totalRating = findViewById(R.id.jumlah_rating);
        ratingBar = findViewById(R.id.rating_total);
        total_userRating = findViewById(R.id.jumlah_userRating);
        lihatsemuaUlasan = findViewById(R.id.lihatsemuaUlasan);
        belumadaulasannya = findViewById(R.id.belumadaulasan);


        jumlah_like = findViewById(R.id.jumlah_suka_detail);
        jumlah_ulasan = findViewById(R.id.jumlah_ulasan_detail);


        String title = getIntent().getExtras().getString("nama");
        String address = getIntent().getExtras().getString("alamat");
        String description = getIntent().getExtras().getString("descripsi");
        String foto = getIntent().getExtras().getString("photo");
        String tiket = getIntent().getExtras().getString("tiket");
        nama.setText(title);
        alamat.setText(address);
        desc.setText(description);
        harga_tiket.setText(tiket);
        Glide.with(this).load(foto).into(photo);

        //TODO: ini untuk menampilkan semua ulasan
        recyclerView_Ulasan = findViewById(R.id.recycler_ulasan);
        recyclerView_Ulasan.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,true));


        //TODO: ini untuk menampilkan semua list gambar dari wisata
        recyclerView_gambar = findViewById(R.id.recycler_gambarwisata);
        LinearLayoutManager managerGambar = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView_gambar.setLayoutManager(managerGambar);
        recyclerView_gambar.setHasFixedSize(true);

        baca_semua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment;
                dialogFragment = new Detail_Wisata();
                dialogFragment.show(getSupportFragmentManager(),"Detail wisata");
            }
        });

        lihatsemuaUlasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment;
                dialogFragment = new List_Ulasan();
                dialogFragment.show(getSupportFragmentManager(),"tambah_komentar");
            }
        });

        tulis_ulasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment;
                dialogFragment = new Tambah_ulasan();
                dialogFragment.show(getSupportFragmentManager(),"tambah_komentar");
            }
        });

        init();

        model_gambars = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("gambar").child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Model_Gambar gambar = snapshot.getValue(Model_Gambar.class);
                    model_gambars.add(gambar);
                }

                adapter_gambar = new Adapter_Gambar(model_gambars,Activity_DetailWisata.this);
                recyclerView_gambar.setAdapter(adapter_gambar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("disukai")
                .child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jumlah_like.setText(dataSnapshot.getChildrenCount()+" "+"Suka");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Ulasan")
                .child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jumlah_ulasan.setText( dataSnapshot.getChildrenCount() +" "+ "Ulasan");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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


                    DecimalFormat decimalFormat = new DecimalFormat("#.#");
                    ratingBar.setRating(Float.parseFloat(String.valueOf(average)));
                    totalRating.setText(String.valueOf(decimalFormat.format(average)));
                    total_userRating.setText(String.valueOf(decimalFormat.format(count)));
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bacaUlasan();

    }

    //TODO: ini untuk memanggil Google Maps
    private void init(){
        navigasi = findViewById(R.id.navigasi_detailwisata);//TODO: Button Navigasi
        navigasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String model = getIntent().getExtras().getString("nama");//TODO: ini adalah nama wisata, saya pnggil ulang
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+model);//TODO: ini keyword untuk automatis melakukan navigasi
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");//TODO: open Google Maps
                try {
                    if (mapIntent.resolveActivity(getPackageManager()) != null){
                        startActivity(mapIntent);
                    }
                } catch (NullPointerException e){
                    Toast.makeText(Activity_DetailWisata.this,"gagal",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void bacaUlasan (){
        lihatsemuaUlasan.setVisibility(View.GONE);
        recyclerView_Ulasan.setVisibility(View.GONE);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ulasan").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model_ulasans = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Model_Ulasan ulasan = snapshot.getValue(Model_Ulasan.class);
                    model_ulasans.add(ulasan);
                    lihatsemuaUlasan.setVisibility(View.VISIBLE);
                    belumadaulasannya.setVisibility(View.INVISIBLE);
                    recyclerView_Ulasan.setVisibility(View.VISIBLE);
                }

                adapter_ulasan = new Adapter_Ulasan(model_ulasans,Activity_DetailWisata.this);
                recyclerView_Ulasan.setAdapter(adapter_ulasan);
                adapter_ulasan.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
