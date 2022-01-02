// Generated by view binder compiler. Do not edit!
package org.gyeongsoton.gyeongsoton_jelly.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.gyeongsoton.gyeongsoton_jelly.R;

public final class ActivityLoginNormalBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final ImageButton backBtn;

  @NonNull
  public final Button centerbtn;

  @NonNull
  public final Button loginButton;

  @NonNull
  public final TextInputEditText loginID;

  @NonNull
  public final TextInputEditText loginPass;

  @NonNull
  public final Button normalbtn;

  @NonNull
  public final Button sellerbtn;

  @NonNull
  public final Button signupBtn;

  @NonNull
  public final TextView textView2;

  private ActivityLoginNormalBinding(@NonNull FrameLayout rootView, @NonNull ImageButton backBtn,
      @NonNull Button centerbtn, @NonNull Button loginButton, @NonNull TextInputEditText loginID,
      @NonNull TextInputEditText loginPass, @NonNull Button normalbtn, @NonNull Button sellerbtn,
      @NonNull Button signupBtn, @NonNull TextView textView2) {
    this.rootView = rootView;
    this.backBtn = backBtn;
    this.centerbtn = centerbtn;
    this.loginButton = loginButton;
    this.loginID = loginID;
    this.loginPass = loginPass;
    this.normalbtn = normalbtn;
    this.sellerbtn = sellerbtn;
    this.signupBtn = signupBtn;
    this.textView2 = textView2;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginNormalBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginNormalBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login_normal, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginNormalBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_btn;
      ImageButton backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.centerbtn;
      Button centerbtn = ViewBindings.findChildViewById(rootView, id);
      if (centerbtn == null) {
        break missingId;
      }

      id = R.id.login_button;
      Button loginButton = ViewBindings.findChildViewById(rootView, id);
      if (loginButton == null) {
        break missingId;
      }

      id = R.id.login_ID;
      TextInputEditText loginID = ViewBindings.findChildViewById(rootView, id);
      if (loginID == null) {
        break missingId;
      }

      id = R.id.login_pass;
      TextInputEditText loginPass = ViewBindings.findChildViewById(rootView, id);
      if (loginPass == null) {
        break missingId;
      }

      id = R.id.normalbtn;
      Button normalbtn = ViewBindings.findChildViewById(rootView, id);
      if (normalbtn == null) {
        break missingId;
      }

      id = R.id.sellerbtn;
      Button sellerbtn = ViewBindings.findChildViewById(rootView, id);
      if (sellerbtn == null) {
        break missingId;
      }

      id = R.id.signup_btn;
      Button signupBtn = ViewBindings.findChildViewById(rootView, id);
      if (signupBtn == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      return new ActivityLoginNormalBinding((FrameLayout) rootView, backBtn, centerbtn, loginButton,
          loginID, loginPass, normalbtn, sellerbtn, signupBtn, textView2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
