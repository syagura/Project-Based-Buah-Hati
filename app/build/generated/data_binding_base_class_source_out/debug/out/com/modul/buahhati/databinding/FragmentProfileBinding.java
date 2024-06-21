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
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.modul.buahhati.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final ImageButton btAddAnak;

  @NonNull
  public final ImageButton ibLogout;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RecyclerView rvProfile;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView tvNameUser;

  private FragmentProfileBinding(@NonNull NestedScrollView rootView, @NonNull ImageButton btAddAnak,
      @NonNull ImageButton ibLogout, @NonNull ProgressBar progressBar,
      @NonNull RecyclerView rvProfile, @NonNull TextView textView, @NonNull TextView tvNameUser) {
    this.rootView = rootView;
    this.btAddAnak = btAddAnak;
    this.ibLogout = ibLogout;
    this.progressBar = progressBar;
    this.rvProfile = rvProfile;
    this.textView = textView;
    this.tvNameUser = tvNameUser;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bt_add_anak;
      ImageButton btAddAnak = ViewBindings.findChildViewById(rootView, id);
      if (btAddAnak == null) {
        break missingId;
      }

      id = R.id.ib_logout;
      ImageButton ibLogout = ViewBindings.findChildViewById(rootView, id);
      if (ibLogout == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.rv_profile;
      RecyclerView rvProfile = ViewBindings.findChildViewById(rootView, id);
      if (rvProfile == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.tv_name_user;
      TextView tvNameUser = ViewBindings.findChildViewById(rootView, id);
      if (tvNameUser == null) {
        break missingId;
      }

      return new FragmentProfileBinding((NestedScrollView) rootView, btAddAnak, ibLogout,
          progressBar, rvProfile, textView, tvNameUser);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}