// Generated by view binder compiler. Do not edit!
package org.gyeongsoton.gyeongsoton_jelly.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.gyeongsoton.gyeongsoton_jelly.R;

public final class ActivityMarketItemBuyBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button buyBtn;

  @NonNull
  public final RadioButton radioButton3;

  @NonNull
  public final RadioButton radioButton4;

  @NonNull
  public final RadioButton radioButton5;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView8;

  private ActivityMarketItemBuyBinding(@NonNull RelativeLayout rootView, @NonNull Button buyBtn,
      @NonNull RadioButton radioButton3, @NonNull RadioButton radioButton4,
      @NonNull RadioButton radioButton5, @NonNull TextView textView10, @NonNull TextView textView2,
      @NonNull TextView textView8) {
    this.rootView = rootView;
    this.buyBtn = buyBtn;
    this.radioButton3 = radioButton3;
    this.radioButton4 = radioButton4;
    this.radioButton5 = radioButton5;
    this.textView10 = textView10;
    this.textView2 = textView2;
    this.textView8 = textView8;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMarketItemBuyBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMarketItemBuyBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_market_item_buy, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMarketItemBuyBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buy_btn;
      Button buyBtn = ViewBindings.findChildViewById(rootView, id);
      if (buyBtn == null) {
        break missingId;
      }

      id = R.id.radioButton3;
      RadioButton radioButton3 = ViewBindings.findChildViewById(rootView, id);
      if (radioButton3 == null) {
        break missingId;
      }

      id = R.id.radioButton4;
      RadioButton radioButton4 = ViewBindings.findChildViewById(rootView, id);
      if (radioButton4 == null) {
        break missingId;
      }

      id = R.id.radioButton5;
      RadioButton radioButton5 = ViewBindings.findChildViewById(rootView, id);
      if (radioButton5 == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      return new ActivityMarketItemBuyBinding((RelativeLayout) rootView, buyBtn, radioButton3,
          radioButton4, radioButton5, textView10, textView2, textView8);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
