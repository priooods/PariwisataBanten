package com.prio.pariwisataserang.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prio.pariwisataserang.Adapter.Adapter_Ulasan_Full;
import com.prio.pariwisataserang.Model.Model_Ulasan;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class List_Ulasan extends DialogFragment {

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFullscreen);
    }

    CircleImageView gambarprofile;
    ImageView back;
    RecyclerView listUlasan;


    List<Model_Ulasan> model_ulasans;
    Adapter_Ulasan_Full adapter_ulasan;

    String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kumpulan_ulasan,container,false);

        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("id");


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //Glide.with(getContext()).load(firebaseUser.getPhotoUrl()).into(gambarprofile);

        gambarprofile = view.findViewById(R.id.photoprofile_list_ulasan);
        back = view.findViewById(R.id.icon_back_listulasan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        listUlasan = view.findViewById(R.id.recycler_listUlasan);
        listUlasan.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        bacaUlasan();
        return view;
    }


    private void bacaUlasan (){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ulasan").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model_ulasans = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Model_Ulasan ulasan = snapshot.getValue(Model_Ulasan.class);
                    model_ulasans.add(ulasan);
                }

                adapter_ulasan = new Adapter_Ulasan_Full(model_ulasans, getContext());
                listUlasan.setAdapter(adapter_ulasan);
                adapter_ulasan.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
