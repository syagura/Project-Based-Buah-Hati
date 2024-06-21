// Generated by view binder compiler. Do not edit!
package com.modul.buahhati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.modul.buahhati.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailArticleBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView ivArticleImage;

  @NonNull
  public final LinearLayout main;

  @NonNull
  public final TextView tvDetailDesc;

  @NonNull
  public final TextView tvDetailTitle;

  private ActivityDetailArticleBinding(@NonNull LinearLayout rootView,
      @NonNull ImageView ivArticleImage, @NonNull LinearLayout main, @NonNull TextView tvDetailDesc,
      @NonNull TextView tvDetailTitle) {
    this.rootView = rootView;
    this.ivArticleImage = ivArticleImage;
    this.main = main;
    this.tvDetailDesc = tvDetailDesc;
    this.tvDetailTitle = tvDetailTitle;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailArticleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailArticleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_detail_article, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailArticleBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iv_article_image;
      ImageView ivArticleImage = ViewBindings.findChildViewById(rootView, id);
      if (ivArticleImage == null) {
        break missingId;
      }

      LinearLayout main = (LinearLayout) rootView;

      id = R.id.tv_detail_desc;
      TextView tvDetailDesc = ViewBindings.findChildViewById(rootView, id);
      if (tvDetailDesc == null) {
        break missingId;
      }

      id = R.id.tv_detail_title;
      TextView tvDetailTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvDetailTitle == null) {
        break missingId;
      }

      return new ActivityDetailArticleBinding((LinearLayout) rootView, ivArticleImage, main,
          tvDetailDesc, tvDetailTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
