// Generated by view binder compiler. Do not edit!
package com.modul.buahhati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.modul.buahhati.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegistrasiAnakBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton btnRegisterAnak;

  @NonNull
  public final EditText etDateOfBirth;

  @NonNull
  public final EditText etFullName;

  @NonNull
  public final EditText etHeadCircumference;

  @NonNull
  public final EditText etHeight;

  @NonNull
  public final EditText etWeight;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RadioButton rbFemale;

  @NonNull
  public final RadioButton rbMale;

  @NonNull
  public final RadioGroup rgGender;

  @NonNull
  public final Spinner spAdditionalProfile;

  private ActivityRegistrasiAnakBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton btnRegisterAnak, @NonNull EditText etDateOfBirth,
      @NonNull EditText etFullName, @NonNull EditText etHeadCircumference,
      @NonNull EditText etHeight, @NonNull EditText etWeight, @NonNull ProgressBar progressBar,
      @NonNull RadioButton rbFemale, @NonNull RadioButton rbMale, @NonNull RadioGroup rgGender,
      @NonNull Spinner spAdditionalProfile) {
    this.rootView = rootView;
    this.btnRegisterAnak = btnRegisterAnak;
    this.etDateOfBirth = etDateOfBirth;
    this.etFullName = etFullName;
    this.etHeadCircumference = etHeadCircumference;
    this.etHeight = etHeight;
    this.etWeight = etWeight;
    this.progressBar = progressBar;
    this.rbFemale = rbFemale;
    this.rbMale = rbMale;
    this.rgGender = rgGender;
    this.spAdditionalProfile = spAdditionalProfile;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegistrasiAnakBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegistrasiAnakBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_registrasi_anak, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegistrasiAnakBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_register_anak;
      ImageButton btnRegisterAnak = ViewBindings.findChildViewById(rootView, id);
      if (btnRegisterAnak == null) {
        break missingId;
      }

      id = R.id.etDateOfBirth;
      EditText etDateOfBirth = ViewBindings.findChildViewById(rootView, id);
      if (etDateOfBirth == null) {
        break missingId;
      }

      id = R.id.etFullName;
      EditText etFullName = ViewBindings.findChildViewById(rootView, id);
      if (etFullName == null) {
        break missingId;
      }

      id = R.id.etHeadCircumference;
      EditText etHeadCircumference = ViewBindings.findChildViewById(rootView, id);
      if (etHeadCircumference == null) {
        break missingId;
      }

      id = R.id.etHeight;
      EditText etHeight = ViewBindings.findChildViewById(rootView, id);
      if (etHeight == null) {
        break missingId;
      }

      id = R.id.etWeight;
      EditText etWeight = ViewBindings.findChildViewById(rootView, id);
      if (etWeight == null) {
        break missingId;
      }

      id = R.id.progress_bar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.rbFemale;
      RadioButton rbFemale = ViewBindings.findChildViewById(rootView, id);
      if (rbFemale == null) {
        break missingId;
      }

      id = R.id.rbMale;
      RadioButton rbMale = ViewBindings.findChildViewById(rootView, id);
      if (rbMale == null) {
        break missingId;
      }

      id = R.id.rgGender;
      RadioGroup rgGender = ViewBindings.findChildViewById(rootView, id);
      if (rgGender == null) {
        break missingId;
      }

      id = R.id.spAdditionalProfile;
      Spinner spAdditionalProfile = ViewBindings.findChildViewById(rootView, id);
      if (spAdditionalProfile == null) {
        break missingId;
      }

      return new ActivityRegistrasiAnakBinding((ConstraintLayout) rootView, btnRegisterAnak,
          etDateOfBirth, etFullName, etHeadCircumference, etHeight, etWeight, progressBar, rbFemale,
          rbMale, rgGender, spAdditionalProfile);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
