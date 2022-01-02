package org.gyeongsoton.gyeongsoton_jelly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class Adopt_monitor_diagno_fragment extends Fragment {

    ImageView imageView1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.fragment_adopt_monitor_diagno, container, false);

        imageView1=view.findViewById(R.id.imageView1);

        Bundle bundle = getArguments();
        String adoptReviewFile1 = bundle.getString("adoptReviewFile1");
        Glide.with(Adopt_monitor_diagno_fragment.this).load(adoptReviewFile1).into(imageView1);
        imageView1.setClipToOutline(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}