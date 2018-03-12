package com.philip.coin_toss;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.animation.AccelerateInterpolator;
        import android.view.animation.LinearInterpolator;
        import android.widget.ImageView;

public class Flip3d extends Activity {

    public static int loops = 0;
    public static int loopRoll;
    public static boolean isOnSecondHalf = false;
    private ImageView image1;
    private ImageView image2;

    private boolean isFirstImage = true;


    public Flip3d(ImageView img1, ImageView img2, boolean isFirstImage){
        image1 = img1;
        image2 = img2;
        this.isFirstImage = isFirstImage;
        if(isFirstImage){
            image2.setVisibility(View.GONE);
        }else{
            image1.setVisibility(View.GONE);
        }
    }

    public void applyRotation(float start, float end) {
// Find the center of image
        final float centerX = image1.getWidth() / 2.0f;
        final float centerY = image1.getHeight() / 2.0f;

// Create a new 3D rotation with the supplied parameter
// The animation listener is used to trigger the next animation
        final Flip3dAnimation rotation =
                new Flip3dAnimation(start, end, centerX, centerY);
        rotation.setFillAfter(true);
        if(Flip3d.loops == 0){
            rotation.setDuration(300);
            rotation.setInterpolator(new AccelerateInterpolator());
        }else{
            rotation.setDuration(150);
            rotation.setInterpolator(new LinearInterpolator());
        }
        rotation.setAnimationListener(new DisplayNextView(this,isFirstImage, image1, image2));
        if (isFirstImage)
        {
            image1.startAnimation(rotation);
        } else {
            image2.startAnimation(rotation);
        }

        isFirstImage = !isFirstImage;
    }
}