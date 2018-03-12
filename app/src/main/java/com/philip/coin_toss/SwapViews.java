package com.philip.coin_toss;

        import android.view.View;
        import android.view.animation.DecelerateInterpolator;
        import android.view.animation.LinearInterpolator;
        import android.widget.ImageView;

public final class SwapViews implements Runnable {
    private boolean mIsFirstView;
    ImageView image1;
    ImageView image2;
    private DisplayNextView listener;

    public SwapViews(DisplayNextView listener,boolean isFirstView, ImageView image1, ImageView image2) {
        mIsFirstView = isFirstView;
        this.image1 = image1;
        this.image2 = image2;
        this.listener = listener;
    }

    public void run() {
        final float centerX = image1.getWidth() / 2.0f;
        final float centerY = image1.getHeight() / 2.0f;
        Flip3dAnimation rotation;

        if (mIsFirstView) {
            image1.setVisibility(View.GONE);
            image2.setVisibility(View.VISIBLE);
            image2.requestFocus();

            rotation = new Flip3dAnimation(-90, 0, centerX, centerY);
        } else {
            image2.setVisibility(View.GONE);
            image1.setVisibility(View.VISIBLE);
            image1.requestFocus();

            rotation = new Flip3dAnimation(-90, 0, centerX, centerY);
        }


        rotation.setFillAfter(true);
        if(Flip3d.loops==Flip3d.loopRoll){
            rotation.setInterpolator(new DecelerateInterpolator());
            rotation.setDuration(300);
        }else{
            rotation.setInterpolator(new LinearInterpolator());
            rotation.setDuration(150);
        }
        if(Flip3d.loops<Flip3d.loopRoll) {
            rotation.setAnimationListener(listener);
            listener.update(mIsFirstView, image1, image2);
            Flip3d.loops++;
            Flip3d.isOnSecondHalf = true;
        }
        else
            Flip3d.loops=0;
        if (mIsFirstView) {
            image2.startAnimation(rotation);
        } else {
            image1.startAnimation(rotation);
        }
    }
}