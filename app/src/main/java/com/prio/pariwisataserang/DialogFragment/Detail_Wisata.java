package com.prio.pariwisataserang.DialogFragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prio.pariwisataserang.R;

public class Detail_Wisata extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFullscreen);
    }

    TextView namaWisata, isiDescription, fasilitasWisata;
    ImageView backDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desc_detailwisata,container,false);

        namaWisata = view.findViewById(R.id.namawisataText);
        isiDescription = view.findViewById(R.id.description_wisata);
        backDialog = view.findViewById(R.id.close_tentangwisata);
        fasilitasWisata = view.findViewById(R.id.full_wahanaDanSarana);

        String title = getActivity().getIntent().getExtras().getString("nama");
        String description = getActivity().getIntent().getExtras().getString("descripsi");
        String fasilitas = getActivity().getIntent().getExtras().getString("fasi");

        isiDescription.setText(description);
        namaWisata.setText(title);
        fasilitasWisata.setText(fasilitas);

        backDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return view;
    }
}
