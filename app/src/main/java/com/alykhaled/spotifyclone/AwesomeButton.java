package com.alykhaled.spotifyclone;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.ShapeAppearanceModel;

class AwesomeButton extends androidx.appcompat.widget.AppCompatImageButton {


    public AwesomeButton(@NonNull Context context) {
        super(context);
    }

    public AwesomeButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AwesomeButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(isPressed())
        {
//            setImageTintList();
        }
    }
}
