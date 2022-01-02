// Generated by view binder compiler. Do not edit!
package org.gyeongsoton.gyeongsoton_jelly.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.gyeongsoton.gyeongsoton_jelly.R;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageButton adoptBtn;

  @NonNull
  public final ImageButton fundingBtn;

  @NonNull
  public final ImageButton homeBtn;

  @NonNull
  public final ImageView imageView1;

  @NonNull
  public final ImageView imageView10;

  @NonNull
  public final ImageView imageView11;

  @NonNull
  public final ImageView imageView12;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final ImageView imageView6;

  @NonNull
  public final ImageView imageView7;

  @NonNull
  public final ImageView imageView8;

  @NonNull
  public final ImageView imageView9;

  @NonNull
  public final BottomNavigationView mainBottom;

  @NonNull
  public final ImageButton marketBtn;

  @NonNull
  public final ImageButton mypageBtn;

  @NonNull
  public final ImageView scroll1;

  @NonNull
  public final ImageView scroll2;

  @NonNull
  public final ImageView scroll3;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView6;

  private ActivityMainBinding(@NonNull RelativeLayout rootView, @NonNull ImageButton adoptBtn,
      @NonNull ImageButton fundingBtn, @NonNull ImageButton homeBtn, @NonNull ImageView imageView1,
      @NonNull ImageView imageView10, @NonNull ImageView imageView11,
      @NonNull ImageView imageView12, @NonNull ImageView imageView2, @NonNull ImageView imageView3,
      @NonNull ImageView imageView4, @NonNull ImageView imageView5, @NonNull ImageView imageView6,
      @NonNull ImageView imageView7, @NonNull ImageView imageView8, @NonNull ImageView imageView9,
      @NonNull BottomNavigationView mainBottom, @NonNull ImageButton marketBtn,
      @NonNull ImageButton mypageBtn, @NonNull ImageView scroll1, @NonNull ImageView scroll2,
      @NonNull ImageView scroll3, @NonNull TextView textView2, @NonNull TextView textView3,
      @NonNull TextView textView4, @NonNull TextView textView6) {
    this.rootView = rootView;
    this.adoptBtn = adoptBtn;
    this.fundingBtn = fundingBtn;
    this.homeBtn = homeBtn;
    this.imageView1 = imageView1;
    this.imageView10 = imageView10;
    this.imageView11 = imageView11;
    this.imageView12 = imageView12;
    this.imageView2 = imageView2;
    this.imageView3 = imageView3;
    this.imageView4 = imageView4;
    this.imageView5 = imageView5;
    this.imageView6 = imageView6;
    this.imageView7 = imageView7;
    this.imageView8 = imageView8;
    this.imageView9 = imageView9;
    this.mainBottom = mainBottom;
    this.marketBtn = marketBtn;
    this.mypageBtn = mypageBtn;
    this.scroll1 = scroll1;
    this.scroll2 = scroll2;
    this.scroll3 = scroll3;
    this.textView2 = textView2;
    this.textView3 = textView3;
    this.textView4 = textView4;
    this.textView6 = textView6;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.adopt_btn;
      ImageButton adoptBtn = ViewBindings.findChildViewById(rootView, id);
      if (adoptBtn == null) {
        break missingId;
      }

      id = R.id.funding_btn;
      ImageButton fundingBtn = ViewBindings.findChildViewById(rootView, id);
      if (fundingBtn == null) {
        break missingId;
      }

      id = R.id.home_btn;
      ImageButton homeBtn = ViewBindings.findChildViewById(rootView, id);
      if (homeBtn == null) {
        break missingId;
      }

      id = R.id.imageView1;
      ImageView imageView1 = ViewBindings.findChildViewById(rootView, id);
      if (imageView1 == null) {
        break missingId;
      }

      id = R.id.imageView10;
      ImageView imageView10 = ViewBindings.findChildViewById(rootView, id);
      if (imageView10 == null) {
        break missingId;
      }

      id = R.id.imageView11;
      ImageView imageView11 = ViewBindings.findChildViewById(rootView, id);
      if (imageView11 == null) {
        break missingId;
      }

      id = R.id.imageView12;
      ImageView imageView12 = ViewBindings.findChildViewById(rootView, id);
      if (imageView12 == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.imageView6;
      ImageView imageView6 = ViewBindings.findChildViewById(rootView, id);
      if (imageView6 == null) {
        break missingId;
      }

      id = R.id.imageView7;
      ImageView imageView7 = ViewBindings.findChildViewById(rootView, id);
      if (imageView7 == null) {
        break missingId;
      }

      id = R.id.imageView8;
      ImageView imageView8 = ViewBindings.findChildViewById(rootView, id);
      if (imageView8 == null) {
        break missingId;
      }

      id = R.id.imageView9;
      ImageView imageView9 = ViewBindings.findChildViewById(rootView, id);
      if (imageView9 == null) {
        break missingId;
      }

      id = R.id.main_bottom;
      BottomNavigationView mainBottom = ViewBindings.findChildViewById(rootView, id);
      if (mainBottom == null) {
        break missingId;
      }

      id = R.id.market_btn;
      ImageButton marketBtn = ViewBindings.findChildViewById(rootView, id);
      if (marketBtn == null) {
        break missingId;
      }

      id = R.id.mypage_btn;
      ImageButton mypageBtn = ViewBindings.findChildViewById(rootView, id);
      if (mypageBtn == null) {
        break missingId;
      }

      id = R.id.scroll1;
      ImageView scroll1 = ViewBindings.findChildViewById(rootView, id);
      if (scroll1 == null) {
        break missingId;
      }

      id = R.id.scroll2;
      ImageView scroll2 = ViewBindings.findChildViewById(rootView, id);
      if (scroll2 == null) {
        break missingId;
      }

      id = R.id.scroll3;
      ImageView scroll3 = ViewBindings.findChildViewById(rootView, id);
      if (scroll3 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      return new ActivityMainBinding((RelativeLayout) rootView, adoptBtn, fundingBtn, homeBtn,
          imageView1, imageView10, imageView11, imageView12, imageView2, imageView3, imageView4,
          imageView5, imageView6, imageView7, imageView8, imageView9, mainBottom, marketBtn,
          mypageBtn, scroll1, scroll2, scroll3, textView2, textView3, textView4, textView6);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
