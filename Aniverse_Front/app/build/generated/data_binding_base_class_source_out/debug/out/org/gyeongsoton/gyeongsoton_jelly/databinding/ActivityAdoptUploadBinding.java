// Generated by view binder compiler. Do not edit!
package org.gyeongsoton.gyeongsoton_jelly.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
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

public final class ActivityAdoptUploadBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextInputEditText age;

  @NonNull
  public final ImageButton animalImg;

  @NonNull
  public final ImageButton backBtn;

  @NonNull
  public final TextInputEditText centerName;

  @NonNull
  public final TextInputEditText condition;

  @NonNull
  public final TextInputEditText deadline;

  @NonNull
  public final TextInputEditText disease;

  @NonNull
  public final TextView ess1;

  @NonNull
  public final TextView essMessage;

  @NonNull
  public final TextInputEditText findingSpot;

  @NonNull
  public final TextInputEditText inoculation;

  @NonNull
  public final TextInputEditText neutering;

  @NonNull
  public final TextInputEditText personality;

  @NonNull
  public final TextView picture;

  @NonNull
  public final TextInputEditText sex;

  @NonNull
  public final TextInputEditText species;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final Button uploadButton;

  private ActivityAdoptUploadBinding(@NonNull RelativeLayout rootView,
      @NonNull TextInputEditText age, @NonNull ImageButton animalImg, @NonNull ImageButton backBtn,
      @NonNull TextInputEditText centerName, @NonNull TextInputEditText condition,
      @NonNull TextInputEditText deadline, @NonNull TextInputEditText disease,
      @NonNull TextView ess1, @NonNull TextView essMessage, @NonNull TextInputEditText findingSpot,
      @NonNull TextInputEditText inoculation, @NonNull TextInputEditText neutering,
      @NonNull TextInputEditText personality, @NonNull TextView picture,
      @NonNull TextInputEditText sex, @NonNull TextInputEditText species,
      @NonNull TextView textView10, @NonNull TextView textView8, @NonNull Button uploadButton) {
    this.rootView = rootView;
    this.age = age;
    this.animalImg = animalImg;
    this.backBtn = backBtn;
    this.centerName = centerName;
    this.condition = condition;
    this.deadline = deadline;
    this.disease = disease;
    this.ess1 = ess1;
    this.essMessage = essMessage;
    this.findingSpot = findingSpot;
    this.inoculation = inoculation;
    this.neutering = neutering;
    this.personality = personality;
    this.picture = picture;
    this.sex = sex;
    this.species = species;
    this.textView10 = textView10;
    this.textView8 = textView8;
    this.uploadButton = uploadButton;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAdoptUploadBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAdoptUploadBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_adopt_upload, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAdoptUploadBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.age;
      TextInputEditText age = ViewBindings.findChildViewById(rootView, id);
      if (age == null) {
        break missingId;
      }

      id = R.id.animal_img;
      ImageButton animalImg = ViewBindings.findChildViewById(rootView, id);
      if (animalImg == null) {
        break missingId;
      }

      id = R.id.back_btn;
      ImageButton backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.center_name;
      TextInputEditText centerName = ViewBindings.findChildViewById(rootView, id);
      if (centerName == null) {
        break missingId;
      }

      id = R.id.condition;
      TextInputEditText condition = ViewBindings.findChildViewById(rootView, id);
      if (condition == null) {
        break missingId;
      }

      id = R.id.deadline;
      TextInputEditText deadline = ViewBindings.findChildViewById(rootView, id);
      if (deadline == null) {
        break missingId;
      }

      id = R.id.disease;
      TextInputEditText disease = ViewBindings.findChildViewById(rootView, id);
      if (disease == null) {
        break missingId;
      }

      id = R.id.ess1;
      TextView ess1 = ViewBindings.findChildViewById(rootView, id);
      if (ess1 == null) {
        break missingId;
      }

      id = R.id.ess_message;
      TextView essMessage = ViewBindings.findChildViewById(rootView, id);
      if (essMessage == null) {
        break missingId;
      }

      id = R.id.finding_spot;
      TextInputEditText findingSpot = ViewBindings.findChildViewById(rootView, id);
      if (findingSpot == null) {
        break missingId;
      }

      id = R.id.inoculation;
      TextInputEditText inoculation = ViewBindings.findChildViewById(rootView, id);
      if (inoculation == null) {
        break missingId;
      }

      id = R.id.neutering;
      TextInputEditText neutering = ViewBindings.findChildViewById(rootView, id);
      if (neutering == null) {
        break missingId;
      }

      id = R.id.personality;
      TextInputEditText personality = ViewBindings.findChildViewById(rootView, id);
      if (personality == null) {
        break missingId;
      }

      id = R.id.picture;
      TextView picture = ViewBindings.findChildViewById(rootView, id);
      if (picture == null) {
        break missingId;
      }

      id = R.id.sex;
      TextInputEditText sex = ViewBindings.findChildViewById(rootView, id);
      if (sex == null) {
        break missingId;
      }

      id = R.id.species;
      TextInputEditText species = ViewBindings.findChildViewById(rootView, id);
      if (species == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      id = R.id.upload_button;
      Button uploadButton = ViewBindings.findChildViewById(rootView, id);
      if (uploadButton == null) {
        break missingId;
      }

      return new ActivityAdoptUploadBinding((RelativeLayout) rootView, age, animalImg, backBtn,
          centerName, condition, deadline, disease, ess1, essMessage, findingSpot, inoculation,
          neutering, personality, picture, sex, species, textView10, textView8, uploadButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
