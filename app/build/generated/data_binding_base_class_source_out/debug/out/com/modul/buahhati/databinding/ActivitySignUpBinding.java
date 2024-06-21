// Generated by view binder compiler. Do not edit!
package com.modul.buahhati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.modul.buahhati.R;
import com.modul.buahhati.view.custom_view.MyEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignUpBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton btnRegister;

  @NonNull
  public final MyEditText edRegisterEmail;

  @NonNull
  public final TextInputEditText edRegisterName;

  @NonNull
  public final MyEditText edRegisterPassword;

  @NonNull
  public final TextInputEditText edRegisterUsername;

  @NonNull
  public final TextInputLayout emailEditTextLayout;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final Guideline guidelineHorizontal;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextInputLayout nameEditTextLayout;

  @NonNull
  public final TextInputLayout passwordEditTextLayout;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvPassword;

  @NonNull
  public final TextView tvTitle;

  @NonNull
  public final TextView tvUsername;

  @NonNull
  public final TextInputLayout usernameEditTextLayout;

  private ActivitySignUpBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton btnRegister, @NonNull MyEditText edRegisterEmail,
      @NonNull TextInputEditText edRegisterName, @NonNull MyEditText edRegisterPassword,
      @NonNull TextInputEditText edRegisterUsername, @NonNull TextInputLayout emailEditTextLayout,
      @NonNull Guideline guideline2, @NonNull Guideline guidelineHorizontal,
      @NonNull ConstraintLayout main, @NonNull TextInputLayout nameEditTextLayout,
      @NonNull TextInputLayout passwordEditTextLayout, @NonNull ProgressBar progressBar,
      @NonNull TextView tvEmail, @NonNull TextView tvName, @NonNull TextView tvPassword,
      @NonNull TextView tvTitle, @NonNull TextView tvUsername,
      @NonNull TextInputLayout usernameEditTextLayout) {
    this.rootView = rootView;
    this.btnRegister = btnRegister;
    this.edRegisterEmail = edRegisterEmail;
    this.edRegisterName = edRegisterName;
    this.edRegisterPassword = edRegisterPassword;
    this.edRegisterUsername = edRegisterUsername;
    this.emailEditTextLayout = emailEditTextLayout;
    this.guideline2 = guideline2;
    this.guidelineHorizontal = guidelineHorizontal;
    this.main = main;
    this.nameEditTextLayout = nameEditTextLayout;
    this.passwordEditTextLayout = passwordEditTextLayout;
    this.progressBar = progressBar;
    this.tvEmail = tvEmail;
    this.tvName = tvName;
    this.tvPassword = tvPassword;
    this.tvTitle = tvTitle;
    this.tvUsername = tvUsername;
    this.usernameEditTextLayout = usernameEditTextLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignUpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sign_up, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignUpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_register;
      ImageButton btnRegister = ViewBindings.findChildViewById(rootView, id);
      if (btnRegister == null) {
        break missingId;
      }

      id = R.id.ed_register_email;
      MyEditText edRegisterEmail = ViewBindings.findChildViewById(rootView, id);
      if (edRegisterEmail == null) {
        break missingId;
      }

      id = R.id.ed_register_name;
      TextInputEditText edRegisterName = ViewBindings.findChildViewById(rootView, id);
      if (edRegisterName == null) {
        break missingId;
      }

      id = R.id.ed_register_password;
      MyEditText edRegisterPassword = ViewBindings.findChildViewById(rootView, id);
      if (edRegisterPassword == null) {
        break missingId;
      }

      id = R.id.ed_register_username;
      TextInputEditText edRegisterUsername = ViewBindings.findChildViewById(rootView, id);
      if (edRegisterUsername == null) {
        break missingId;
      }

      id = R.id.emailEditTextLayout;
      TextInputLayout emailEditTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (emailEditTextLayout == null) {
        break missingId;
      }

      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.guidelineHorizontal;
      Guideline guidelineHorizontal = ViewBindings.findChildViewById(rootView, id);
      if (guidelineHorizontal == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.nameEditTextLayout;
      TextInputLayout nameEditTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (nameEditTextLayout == null) {
        break missingId;
      }

      id = R.id.passwordEditTextLayout;
      TextInputLayout passwordEditTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (passwordEditTextLayout == null) {
        break missingId;
      }

      id = R.id.progress_bar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.tv_email;
      TextView tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tv_name;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tv_password;
      TextView tvPassword = ViewBindings.findChildViewById(rootView, id);
      if (tvPassword == null) {
        break missingId;
      }

      id = R.id.tv_title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      id = R.id.tv_username;
      TextView tvUsername = ViewBindings.findChildViewById(rootView, id);
      if (tvUsername == null) {
        break missingId;
      }

      id = R.id.usernameEditTextLayout;
      TextInputLayout usernameEditTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (usernameEditTextLayout == null) {
        break missingId;
      }

      return new ActivitySignUpBinding((ConstraintLayout) rootView, btnRegister, edRegisterEmail,
          edRegisterName, edRegisterPassword, edRegisterUsername, emailEditTextLayout, guideline2,
          guidelineHorizontal, main, nameEditTextLayout, passwordEditTextLayout, progressBar,
          tvEmail, tvName, tvPassword, tvTitle, tvUsername, usernameEditTextLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
