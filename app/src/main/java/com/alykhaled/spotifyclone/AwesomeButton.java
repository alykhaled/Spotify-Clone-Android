package com.alykhaled.spotifyclone;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.shape.ShapeAppearanceModel;

class AwesomeButton extends MaterialButton {

    @NonNull private ShapeAppearanceModel shapeAppearanceModel;

    private int insetLeft;
    private int insetRight;
    private int insetTop;
    private int insetBottom;
    private int cornerRadius;
    private int strokeWidth;

    @Nullable private PorterDuff.Mode backgroundTintMode;
    @Nullable private ColorStateList backgroundTint;
    @Nullable private ColorStateList strokeColor;
    @Nullable private ColorStateList rippleColor;

    @Nullable private Drawable maskDrawable;
    private boolean shouldDrawSurfaceColorStroke = false;
    private boolean backgroundOverwritten = false;
    private boolean cornerRadiusSet = false;
    private boolean checkable;
    private LayerDrawable rippleDrawable;
    private int elevation;

    public AwesomeButton(@NonNull Context context) {
        super(context);
    }

    public AwesomeButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AwesomeButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCornerRadius(int cornerRadius) {
        // If cornerRadius wasn't set in the style, it would have a default value of -1. Therefore, for
        // setCornerRadius(-1) to take effect, we need this cornerRadiusSet flag.
        if (!cornerRadiusSet || this.cornerRadius != cornerRadius) {
            this.cornerRadius = cornerRadius;
            cornerRadiusSet = true;

            setShapeAppearanceModel(shapeAppearanceModel.withCornerSize(cornerRadius));
        }
    }

    public int getCornerRadius() {
        return cornerRadius;
    }
}
