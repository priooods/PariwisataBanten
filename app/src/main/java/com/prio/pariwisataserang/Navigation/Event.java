package com.prio.pariwisataserang.Navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prio.pariwisataserang.Adapter.Adapter_Event;
import com.prio.pariwisataserang.Model.Model_Event;
import com.prio.pariwisataserang.R;

import java.util.List;

public class Event extends Fragment {

    RecyclerView recyclerView;
    Adapter_Event adapter_event;
    List<Model_Event> model_events;

    TextView descevent,
            text_All,text_pandeglang,text_kotSerang,text_kotaTang, text_tangsel
            , text_kabTang, text_kabSerang, text_cilegon , text_kabLebak;
    LinearLayout
            linearLayout_Allevent,linearLayout_kablebak,linearLayout_tangsel
            ,linearLayout_pandeglang,linearLayout_kotaserang,linearLayout_kabtangg,linearLayout_cilegonevent
            ,linearLayout_kotatangg,linearLayout_kabserang ;

    EditText carievent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event,container,false);

        carievent = view.findViewById(R.id.search_event);
        descevent = view.findViewById(R.id.textdescEvent);

        if (recyclerView != null){
            descevent.setVisibility(View.GONE);
        }

        TabText(view);

        return view;
    }


    private void TabText (View view){

        text_All = view.findViewById(R.id.text_Allevent);
        text_pandeglang = view.findViewById(R.id.text_kotaPandegelangevent);
        text_kabSerang = view.findViewById(R.id.text_KabSerangEvent);
        text_kotSerang = view.findViewById(R.id.text_kotaserang_event);
        text_kabLebak = view.findViewById(R.id.text_KabLebakevent);
        text_cilegon = view.findViewById(R.id.text_kotacilegonEvent);
        text_kabTang = view.findViewById(R.id.text_kabTanggerang);
        text_kotaTang = view.findViewById(R.id.text_kotaTanggerangevent);
        text_tangsel = view.findViewById(R.id.text_tangselevent);

        linearLayout_Allevent = view.findViewById(R.id.allevent);
        linearLayout_pandeglang = view.findViewById(R.id.pandeglangevent);
        linearLayout_cilegonevent = view.findViewById(R.id.KotaCilegonEvent);
        linearLayout_kablebak = view.findViewById(R.id.KabLebakEvent);
        linearLayout_kabserang = view.findViewById(R.id.Kabupaten_Serangevent);
        linearLayout_kotatangg = view.findViewById(R.id.KotaTangrangEvent);
        linearLayout_kotaserang = view.findViewById(R.id.serangkotaevent);
        linearLayout_tangsel = view.findViewById(R.id.tangselevent);
        linearLayout_kabtangg = view.findViewById(R.id.KabTanggerangevent);

        text_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                carievent.getText().clear();
                linearLayout_Allevent.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kablebak.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotaserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegonevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotatangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabtangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_tangsel.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));

                text_All.setTextColor(getResources().getColor(R.color.birumuda));
                text_kabLebak.setTextColor(getResources().getColor(R.color.colorText));
                text_kabSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_kotSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_tangsel.setTextColor(getResources().getColor(R.color.colorText));
                text_kotaTang.setTextColor(getResources().getColor(R.color.colorText));
                text_kabTang.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
        text_pandeglang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String string = text_pandeglang.getText().toString();
                carievent.setText(string);

                linearLayout_Allevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_kablebak.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotaserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegonevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotatangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabtangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_tangsel.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));

                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_kabLebak.setTextColor(getResources().getColor(R.color.colorText));
                text_kabSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.birumuda));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_kotSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_tangsel.setTextColor(getResources().getColor(R.color.colorText));
                text_kotaTang.setTextColor(getResources().getColor(R.color.colorText));
                text_kabTang.setTextColor(getResources().getColor(R.color.colorText));

            }
        });
        text_kotSerang.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;
            @Override
            public void onClick(View v) {
                String string = text_kotSerang.getText().toString();
                carievent.setText(string);

                linearLayout_Allevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kablebak.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotaserang.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_cilegonevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotatangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabtangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_tangsel.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));

                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_kabLebak.setTextColor(getResources().getColor(R.color.colorText));
                text_kabSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_kotSerang.setTextColor(getResources().getColor(R.color.birumuda));
                text_tangsel.setTextColor(getResources().getColor(R.color.colorText));
                text_kotaTang.setTextColor(getResources().getColor(R.color.colorText));
                text_kabTang.setTextColor(getResources().getColor(R.color.colorText));


            }
        });
        text_kabSerang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String string = text_kabSerang.getText().toString();
                carievent.setText(string);

                linearLayout_Allevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegonevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabserang.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_kotaserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kablebak.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotatangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabtangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_tangsel.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));

                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_kabLebak.setTextColor(getResources().getColor(R.color.colorText));
                text_kabSerang.setTextColor(getResources().getColor(R.color.birumuda));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_kotSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_tangsel.setTextColor(getResources().getColor(R.color.colorText));
                text_kotaTang.setTextColor(getResources().getColor(R.color.colorText));
                text_kabTang.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
        text_kabLebak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text_kabLebak.getText().toString();
                carievent.setText(string);

                linearLayout_Allevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegonevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotaserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kablebak.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_kotatangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabtangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_tangsel.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));

                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_kabSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_kabLebak.setTextColor(getResources().getColor(R.color.birumuda));
                text_kotSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_tangsel.setTextColor(getResources().getColor(R.color.colorText));
                text_kotaTang.setTextColor(getResources().getColor(R.color.colorText));
                text_kabTang.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
        text_cilegon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text_cilegon.getText().toString();
                carievent.setText(string);

                linearLayout_Allevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kablebak.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotaserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegonevent.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_kotatangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabtangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_tangsel.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));

                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_kabLebak.setTextColor(getResources().getColor(R.color.colorText));
                text_kabSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.birumuda));
                text_kotSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_tangsel.setTextColor(getResources().getColor(R.color.colorText));
                text_kotaTang.setTextColor(getResources().getColor(R.color.colorText));
                text_kabTang.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
        text_kotaTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text_kotaTang.getText().toString();
                carievent.setText(string);

                linearLayout_Allevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kablebak.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotaserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegonevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotatangg.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_kabtangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_tangsel.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));

                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_kabLebak.setTextColor(getResources().getColor(R.color.colorText));
                text_kabSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_kotSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_tangsel.setTextColor(getResources().getColor(R.color.colorText));
                text_kotaTang.setTextColor(getResources().getColor(R.color.birumuda));
                text_kabTang.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
        text_kabTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text_kabTang.getText().toString();
                carievent.setText(string);

                linearLayout_Allevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kablebak.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotaserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegonevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotatangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabtangg.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));
                linearLayout_tangsel.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));

                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_kabLebak.setTextColor(getResources().getColor(R.color.colorText));
                text_kabSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_kotSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_tangsel.setTextColor(getResources().getColor(R.color.colorText));
                text_kotaTang.setTextColor(getResources().getColor(R.color.colorText));
                text_kabTang.setTextColor(getResources().getColor(R.color.birumuda));
            }
        });
        text_tangsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = text_tangsel.getText().toString();
                carievent.setText(string);

                linearLayout_Allevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_pandeglang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kablebak.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotaserang.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_cilegonevent.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kotatangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_kabtangg.setBackground(getResources().getDrawable(R.drawable.tablayout_unselector));
                linearLayout_tangsel.setBackground(getResources().getDrawable(R.drawable.tablayout_selector));

                text_All.setTextColor(getResources().getColor(R.color.colorText));
                text_kabLebak.setTextColor(getResources().getColor(R.color.colorText));
                text_kabSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_pandeglang.setTextColor(getResources().getColor(R.color.colorText));
                text_cilegon.setTextColor(getResources().getColor(R.color.colorText));
                text_kotSerang.setTextColor(getResources().getColor(R.color.colorText));
                text_tangsel.setTextColor(getResources().getColor(R.color.birumuda));
                text_kotaTang.setTextColor(getResources().getColor(R.color.colorText));
                text_kabTang.setTextColor(getResources().getColor(R.color.colorText));
            }
        });
    }

}
