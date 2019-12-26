package com.prio.pariwisataserang.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prio.pariwisataserang.DialogFragment.Tentang;
import com.prio.pariwisataserang.R;
import com.prio.pariwisataserang.Navigation.Aktivitas;
import com.prio.pariwisataserang.Navigation.Event;
import com.prio.pariwisataserang.Navigation.Pariwisata;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_Utama extends AppCompatActivity{

    boolean doubleclick = false;

    FirebaseUser user;
    FrameLayout layout;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView username,titileapp;
    CircleImageView photo,image;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }

        toolbar = findViewById(R.id.toolbar_Home);
        setSupportActionBar(toolbar);
        if(toolbar != null){
            getSupportActionBar().setTitle(R.string.namaapp);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Activity_Utama.this,drawerLayout,
                toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        user = FirebaseAuth.getInstance().getCurrentUser();
        layout = findViewById(R.id.layout);
        titileapp = toolbar.findViewById(R.id.titleApp);
        image = toolbar.findViewById(R.id.photoprofile_kanan);
        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(image);

        settUser();

        changeFragment(new Pariwisata());


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        titileapp.setText("Destinasi");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.layout, new Pariwisata(), "fragmentPariwisata")
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();
                        break;
                    case R.id.nav_favorite:
                        titileapp.setText("Aktivitas");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.layout, new Aktivitas(), "fragmentAktivitas")
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();
                        break;
                    case R.id.nav_Event:
                        titileapp.setText("Event");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.layout, new Event(), "fragmentEvent")
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();
                        break;
                    case R.id.nav_tentang:
                        DialogFragment dialogFragment;
                        dialogFragment = new Tentang();
                        dialogFragment.show(getSupportFragmentManager(),"Dialogtentang");
                        break;
                    case R.id.nav_bagikan:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        String shareBody = "Pariwisata Banten." + "  " +
                                "Ayoo bergabung denganku sekarang dan eksplor semua tempat menarik di Banten." + "  "+
                                "Download Applikasi nya sekarang di PlayStore";
                        String shareSubject = "Pariwisata Banten";
                        intent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        intent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
                        startActivity(Intent.createChooser(intent,"Bagikan dengan"));
                        break;
                    case R.id.nav_logout:
                        logOut();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void settUser() {
        navigationView = findViewById(R.id.navigation_view);
        View v = navigationView.getHeaderView(0);
        photo = v.findViewById(R.id.photo_profle);
        username = v.findViewById(R.id.user_name);
        username.setText(user.getDisplayName());
        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(photo);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (doubleclick){
            super.onBackPressed();
            return;
        }
        this.doubleclick = true;
        Toast.makeText(Activity_Utama.this,"Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleclick = false;
            }
        },3000);
    }


    private void changeFragment(Fragment fragmentTujuan){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout,fragmentTujuan,"fragmentTujuan")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


    private void logOut(){
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signOut();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
        mGoogleSignInClient.revokeAccess().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                auth.signOut();
                Toast.makeText(Activity_Utama.this,"Anda behasil keluar",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Activity_Utama.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getApplicationContext(),"Gagal keluar",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });


    }


}
