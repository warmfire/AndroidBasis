package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by warmfire_2 on 2016/7/29.
 */
public class AnimationActivity extends Activity {

    Button animation_rotate, animation_scale, animation_alpha, animation_translate, animation_back;
    ImageView animation_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        init();

        animation_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animationSet = new AnimationSet(true);
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                        Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimation.setDuration(1000);
                animationSet.addAnimation(rotateAnimation);
                animation_image.startAnimation(animationSet);
            }
        });
        animation_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animationSet = new AnimationSet(true);
                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1, 0, 1, 0,
                        Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f);
                scaleAnimation.setDuration(1000);
                animationSet.addAnimation(scaleAnimation);
                animation_image.startAnimation(animationSet);
            }
        });
        animation_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                alphaAnimation.setDuration(500);
                animationSet.addAnimation(alphaAnimation);
                animation_image.startAnimation(animationSet);
            }
        });
        animation_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animationSet = new AnimationSet(true);
                TranslateAnimation translateAnimation =
                        new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF,0f,
                                Animation.RELATIVE_TO_SELF,0.5f,
                                Animation.RELATIVE_TO_SELF,0f,
                                Animation.RELATIVE_TO_SELF,0.5f);
                translateAnimation.setDuration(1000);
                animationSet.addAnimation(translateAnimation);
                animation_image.startAnimation(animationSet);
            }
        });
        animation_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init(){
        animation_rotate = (Button) findViewById(R.id.animation_rotate);
        animation_scale = (Button) findViewById(R.id.animation_scale);
        animation_alpha = (Button) findViewById(R.id.animation_alpha);
        animation_translate = (Button) findViewById(R.id.animation_translate);
        animation_back = (Button) findViewById(R.id.animation_back);
        animation_image = (ImageView) findViewById(R.id.animation_image);
    }
}
