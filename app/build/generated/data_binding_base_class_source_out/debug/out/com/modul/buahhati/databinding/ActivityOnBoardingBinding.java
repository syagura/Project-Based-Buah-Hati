// Generated by view binder compiler. Do not edit!
package com.modul.buahhati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.modul.buahhati.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOnBoardingBinding implements ViewBinding {
  @NonNull
  private final MotionLayout rootView;

  @NonNull
  public final Button button;

  @NonNull
  public final TextView descMenu1;

  @NonNull
  public final TextView descMenu2;

  @NonNull
  public final TextView descMenu3;

  @NonNull
  public final TextView descMenu4;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ImageView logoMenu1;

  @NonNull
  public final ImageView logoMenu2;

  @NonNull
  public final ImageView logoMenu3;

  @NonNull
  public final ImageView logoMenu4;

  @NonNull
  public final MotionLayout main;

  @NonNull
  public final ImageFilterView menu1;

  @NonNull
  public final ImageFilterView menu2;

  @NonNull
  public final ImageFilterView menu3;

  @NonNull
  public final ImageFilterView menu4;

  @NonNull
  public final TextView titleMenu1;

  @NonNull
  public final TextView titleMenu2;

  @NonNull
  public final TextView titleMenu3;

  @NonNull
  public final TextView titleMenu4;

  private ActivityOnBoardingBinding(@NonNull MotionLayout rootView, @NonNull Button button,
      @NonNull TextView descMenu1, @NonNull TextView descMenu2, @NonNull TextView descMenu3,
      @NonNull TextView descMenu4, @NonNull ImageView imageView, @NonNull ImageView logoMenu1,
      @NonNull ImageView logoMenu2, @NonNull ImageView logoMenu3, @NonNull ImageView logoMenu4,
      @NonNull MotionLayout main, @NonNull ImageFilterView menu1, @NonNull ImageFilterView menu2,
      @NonNull ImageFilterView menu3, @NonNull ImageFilterView menu4, @NonNull TextView titleMenu1,
      @NonNull TextView titleMenu2, @NonNull TextView titleMenu3, @NonNull TextView titleMenu4) {
    this.rootView = rootView;
    this.button = button;
    this.descMenu1 = descMenu1;
    this.descMenu2 = descMenu2;
    this.descMenu3 = descMenu3;
    this.descMenu4 = descMenu4;
    this.imageView = imageView;
    this.logoMenu1 = logoMenu1;
    this.logoMenu2 = logoMenu2;
    this.logoMenu3 = logoMenu3;
    this.logoMenu4 = logoMenu4;
    this.main = main;
    this.menu1 = menu1;
    this.menu2 = menu2;
    this.menu3 = menu3;
    this.menu4 = menu4;
    this.titleMenu1 = titleMenu1;
    this.titleMenu2 = titleMenu2;
    this.titleMenu3 = titleMenu3;
    this.titleMenu4 = titleMenu4;
  }

  @Override
  @NonNull
  public MotionLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOnBoardingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOnBoardingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_on_boarding, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOnBoardingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      Button button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.descMenu1;
      TextView descMenu1 = ViewBindings.findChildViewById(rootView, id);
      if (descMenu1 == null) {
        break missingId;
      }

      id = R.id.descMenu2;
      TextView descMenu2 = ViewBindings.findChildViewById(rootView, id);
      if (descMenu2 == null) {
        break missingId;
      }

      id = R.id.descMenu3;
      TextView descMenu3 = ViewBindings.findChildViewById(rootView, id);
      if (descMenu3 == null) {
        break missingId;
      }

      id = R.id.descMenu4;
      TextView descMenu4 = ViewBindings.findChildViewById(rootView, id);
      if (descMenu4 == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.logoMenu1;
      ImageView logoMenu1 = ViewBindings.findChildViewById(rootView, id);
      if (logoMenu1 == null) {
        break missingId;
      }

      id = R.id.logoMenu2;
      ImageView logoMenu2 = ViewBindings.findChildViewById(rootView, id);
      if (logoMenu2 == null) {
        break missingId;
      }

      id = R.id.logoMenu3;
      ImageView logoMenu3 = ViewBindings.findChildViewById(rootView, id);
      if (logoMenu3 == null) {
        break missingId;
      }

      id = R.id.logoMenu4;
      ImageView logoMenu4 = ViewBindings.findChildViewById(rootView, id);
      if (logoMenu4 == null) {
        break missingId;
      }

      MotionLayout main = (MotionLayout) rootView;

      id = R.id.menu1;
      ImageFilterView menu1 = ViewBindings.findChildViewById(rootView, id);
      if (menu1 == null) {
        break missingId;
      }

      id = R.id.menu2;
      ImageFilterView menu2 = ViewBindings.findChildViewById(rootView, id);
      if (menu2 == null) {
        break missingId;
      }

      id = R.id.menu3;
      ImageFilterView menu3 = ViewBindings.findChildViewById(rootView, id);
      if (menu3 == null) {
        break missingId;
      }

      id = R.id.menu4;
      ImageFilterView menu4 = ViewBindings.findChildViewById(rootView, id);
      if (menu4 == null) {
        break missingId;
      }

      id = R.id.titleMenu1;
      TextView titleMenu1 = ViewBindings.findChildViewById(rootView, id);
      if (titleMenu1 == null) {
        break missingId;
      }

      id = R.id.titleMenu2;
      TextView titleMenu2 = ViewBindings.findChildViewById(rootView, id);
      if (titleMenu2 == null) {
        break missingId;
      }

      id = R.id.titleMenu3;
      TextView titleMenu3 = ViewBindings.findChildViewById(rootView, id);
      if (titleMenu3 == null) {
        break missingId;
      }

      id = R.id.titleMenu4;
      TextView titleMenu4 = ViewBindings.findChildViewById(rootView, id);
      if (titleMenu4 == null) {
        break missingId;
      }

      return new ActivityOnBoardingBinding((MotionLayout) rootView, button, descMenu1, descMenu2,
          descMenu3, descMenu4, imageView, logoMenu1, logoMenu2, logoMenu3, logoMenu4, main, menu1,
          menu2, menu3, menu4, titleMenu1, titleMenu2, titleMenu3, titleMenu4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
