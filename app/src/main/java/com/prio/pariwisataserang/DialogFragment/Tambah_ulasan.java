package com.prio.pariwisataserang.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.widget.AppCompatRatingBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prio.pariwisataserang.Model.Model_Ulasan;
import com.prio.pariwisataserang.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class Tambah_ulasan extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFullscreen);
    }

    TextView posting, namawisata;
    CircleImageView profile_photo;
    EditText tambahulasan;
    ImageView close;
    AppCompatRatingBar ratingBar;

    String id;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tambah_komentar,container,false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String title = getActivity().getIntent().getExtras().getString("nama");

        namawisata = view.findViewById(R.id.namawisata_ulasan);
        namawisata.setText(title);
        posting = view.findViewById(R.id.posting_ulasan);
        profile_photo = view.findViewById(R.id.photoprofile_add_ulasan);
        tambahulasan = view.findViewById(R.id.tambah_ulasan);
        close = view.findViewById(R.id.icon_back_ulasan);

        ratingBar = view.findViewById(R.id.total_star_rating);

        Glide.with(getContext()).load(firebaseUser.getPhotoUrl()).into(profile_photo);

        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("id");

        posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tambahulasan.getText().toString().equals("")){
                    Toast.makeText(getContext(),"Tidak bisa mengirim ulasan kosong",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(),"Ulasan berhasil dikirim",Toast.LENGTH_SHORT).show();
                    setTambahulasan();
                    dismiss();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

    private void setTambahulasan() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Ulasan")
                .child(id).push();
        String ulasan = tambahulasan.getText().toString();
        String username = firebaseUser.getDisplayName();
        String uid = firebaseUser.getUid();
        String photo = firebaseUser.getPhotoUrl().toString();
        float banyakrating = ratingBar.getRating();
        Model_Ulasan model_ulasan = new Model_Ulasan(ulasan,username,uid,photo,banyakrating);
        databaseReference.setValue(model_ulasan);
        tambahulasan.setText("");
    }


}
