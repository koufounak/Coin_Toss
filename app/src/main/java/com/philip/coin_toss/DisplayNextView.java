package com.philip.coin_toss;

        import android.util.FloatProperty;
        import android.view.animation.Animation;
        import android.widget.ImageView;

public final class DisplayNextView implements Animation.AnimationListener {
    private boolean mCurrentView;
    ImageView image1;
    ImageView image2;
    private Flip3d parent;

    public DisplayNextView(Flip3d parent , boolean currentView, ImageView image1, ImageView image2) {
        mCurrentView = currentView;
        this.image1 = image1;
        this.image2 = image2;
        this.parent = parent;
    }

    public void update(boolean currentView, ImageView image1, ImageView image2){
        mCurrentView = currentView;
        this.image1 = image1;
        this.image2 = image2;
    }

    public void onAnimationStart(Animation animation) {


    }

    public void onAnimationEnd(Animation animation) {
        if(!Flip3d.isOnSecondHalf){
            image1.post(new SwapViews(this, mCurrentView, image1, image2));
        }
        if(Flip3d.isOnSecondHalf){
            //System.out.println(Flip3d.loops+"");
            parent.applyRotation(0,90);
            Flip3d.isOnSecondHalf = false;
        }
    }

    public void onAnimationRepeat(Animation animation) {

        //image1.post(new SwapViews(mCurrentView, image1, image2));
    }
}