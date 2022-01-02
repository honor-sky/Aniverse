package org.gyeongsoton.gyeongsoton_jelly;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Market_bottom_sheet extends BottomSheetDialogFragment {

    int count = 1;
    int price = 2500;
    int total_price=price;
    TextView tvCount, tvPrice;
    String userIdx,userAuth;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.activity_market_bottom_sheet, container, false);

        Bundle bundle = getArguments();
        userIdx = bundle.getString("userIdx");
        userAuth = bundle.getString("userAuth");

        Button add_btn = view.findViewById(R.id.add);
        Button sub_btn = view.findViewById(R.id.sub);
        tvCount = (TextView) view.findViewById(R.id.itemCount);
        tvPrice = (TextView) view.findViewById(R.id.itemPrice);
        tvCount.setText(String.valueOf(count));
        tvPrice.setText(String.valueOf(total_price)+"원");
        add_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count++;
                total_price=price*count;
                tvCount.setText(String.valueOf(count));
                tvPrice.setText(String.valueOf(total_price)+"원");
            }
        });

        sub_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (count>1){
                    count--;
                    total_price=price*count;
                    tvCount.setText(String.valueOf(count));
                    tvPrice.setText(String.valueOf(total_price)+"원");
                }
            }
        });

        Button buy_btn = view.findViewById(R.id.buy_button);
        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Market_item_buy.class);
                intent.putExtra("userIdx",userIdx);
                intent.putExtra("userAuth",userAuth);
                intent.putExtra("num",String.valueOf(count));
                intent.putExtra("price",String.valueOf(total_price));
                startActivity(intent);
                dismiss();
            }
        });
        return view;
    }

}