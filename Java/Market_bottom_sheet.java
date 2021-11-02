/*package org.tecttown.aniverse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class Market_bottom_sheet extends BottomSheetDialogFragment {

    public View onCreate(@NonNull LayoutInflater inflater,
                         @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_market_bottom_sheet, container, false);

        Button buy_button = (Button)view.findViewById(R.id.buy_button);
        buy_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Market_item_buy.class);
                startActivity(intent);
                dismiss();
            }
        });

        return view;
    }
}*/