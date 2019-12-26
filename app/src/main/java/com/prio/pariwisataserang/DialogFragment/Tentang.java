package com.prio.pariwisataserang.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prio.pariwisataserang.R;

public class Tentang extends DialogFragment {

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFullscreen);
    }

    ImageView back;
    TextView textView,surfi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tentang,container,false);

        back = view.findViewById(R.id.back_tentang);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        textView = view.findViewById(R.id.gomail);
        SpannableString content = new SpannableString(getString(R.string.email));
        content.setSpan(new UnderlineSpan(),0, getString(R.string.email).length(),0);
        textView.setText(content);

        sendGmail("","");

        surfi = view.findViewById(R.id.w);
        

        return view;
    }

    public void sendGmail ( String pesan, String subject){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String[] kepada ={"priodwisembodo23@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL,kepada);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT,pesan);
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent,"Kirim masukan"));
            }
        });
    }
}
