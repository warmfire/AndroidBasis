package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by warmfire_2 on 2016/8/18.
 */
public class GlideActivity extends Activity {

    Button glide_back, glide_btn1, glide_btn2, glide_btn3, glide_btn4, glide_btn5,
            glide_btn6, glide_btn7, glide_btn8, glide_btn9, glide_btn10,
            glide_btn11, glide_btn12, glide_btn13, glide_btn14, glide_btn15,
            glide_btn16, glide_btn17, glide_btn18, glide_btn19;
    ImageView glide_img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setClick();
    }

    public void init(){
        glide_img1 = (ImageView) findViewById(R.id.glide_img1);
        glide_back = (Button) findViewById(R.id.glide_back);
        glide_btn1 = (Button) findViewById(R.id.glide_btn1);
        glide_btn2 = (Button) findViewById(R.id.glide_btn2);
        glide_btn3 = (Button) findViewById(R.id.glide_btn3);
        glide_btn4 = (Button) findViewById(R.id.glide_btn4);
        glide_btn5 = (Button) findViewById(R.id.glide_btn5);
        glide_btn6 = (Button) findViewById(R.id.glide_btn6);
        glide_btn7 = (Button) findViewById(R.id.glide_btn7);
        glide_btn8 = (Button) findViewById(R.id.glide_btn8);
        glide_btn9 = (Button) findViewById(R.id.glide_btn9);
        glide_btn10 = (Button) findViewById(R.id.glide_btn10);
        glide_btn11 = (Button) findViewById(R.id.glide_btn11);
        glide_btn12 = (Button) findViewById(R.id.glide_btn12);
        glide_btn13 = (Button) findViewById(R.id.glide_btn13);
        glide_btn14 = (Button) findViewById(R.id.glide_btn14);
        glide_btn15 = (Button) findViewById(R.id.glide_btn15);
        glide_btn16 = (Button) findViewById(R.id.glide_btn16);
        glide_btn17 = (Button) findViewById(R.id.glide_btn17);
        glide_btn18 = (Button) findViewById(R.id.glide_btn18);
        glide_btn19 = (Button) findViewById(R.id.glide_btn19);
    }

    public void setClick(){
        glide_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        glide_btn1.setOnClickListener(onClickListener);
        glide_btn2.setOnClickListener(onClickListener);
        glide_btn3.setOnClickListener(onClickListener);
        glide_btn4.setOnClickListener(onClickListener);
        glide_btn5.setOnClickListener(onClickListener);
        glide_btn6.setOnClickListener(onClickListener);
        glide_btn7.setOnClickListener(onClickListener);
        glide_btn8.setOnClickListener(onClickListener);
        glide_btn9.setOnClickListener(onClickListener);
        glide_btn10.setOnClickListener(onClickListener);
        glide_btn11.setOnClickListener(onClickListener);
        glide_btn12.setOnClickListener(onClickListener);
        glide_btn13.setOnClickListener(onClickListener);
        glide_btn14.setOnClickListener(onClickListener);
        glide_btn15.setOnClickListener(onClickListener);
        glide_btn16.setOnClickListener(onClickListener);
        glide_btn17.setOnClickListener(onClickListener);
        glide_btn18.setOnClickListener(onClickListener);
        glide_btn19.setOnClickListener(onClickListener);
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.glide_btn1:
                    glide_img1.setImageResource(R.drawable.cover);
                    break;
                case R.id.glide_btn2:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new BlurTransformation(GlideActivity.this, 25))
                            .into((ImageView) findViewById(R.id.glide_img1)); //模糊
                    break;
                case R.id.glide_btn3:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new CropCircleTransformation(GlideActivity.this))
                            .into((ImageView) findViewById(R.id.glide_img1)); //圆形裁剪
                    break;
                case R.id.glide_btn4:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new CropTransformation(GlideActivity.this, 150, 100))
                            .into((ImageView) findViewById(R.id.glide_img1)); //指定大小裁剪
                    break;
                case R.id.glide_btn5:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new CropSquareTransformation(GlideActivity.this))
                            .into((ImageView) findViewById(R.id.glide_img1)); //正方形裁剪
                    break;
                case R.id.glide_btn6:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new RoundedCornersTransformation(GlideActivity.this, 50, 5))
                            .into((ImageView) findViewById(R.id.glide_img1)); //圆角矩形裁剪
                    break;
                case R.id.glide_btn7:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new ColorFilterTransformation(GlideActivity.this, Color.BLUE))
                            .into((ImageView) findViewById(R.id.glide_img1)); //颜色叠加
                    break;
                case R.id.glide_btn8:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new GrayscaleTransformation(GlideActivity.this))
                            .into((ImageView) findViewById(R.id.glide_img1)); //去色 or 黑白
                    break;
                case R.id.glide_btn9:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new MaskTransformation(GlideActivity.this, R.drawable.ic_launcher))
                            .into((ImageView) findViewById(R.id.glide_img1)); //指定图片形状裁剪
                    break;
                case R.id.glide_btn10:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new ToonFilterTransformation(GlideActivity.this, .2f, 10.0f))
                            .into((ImageView) findViewById(R.id.glide_img1)); //油画
                    break;
                case R.id.glide_btn11:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new SepiaFilterTransformation(GlideActivity.this, 1.0f))
                            .into((ImageView) findViewById(R.id.glide_img1)); //褪色
                    break;
                case R.id.glide_btn12:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new ContrastFilterTransformation(GlideActivity.this, 2.0f))
                            .into((ImageView) findViewById(R.id.glide_img1)); //对比度
                    break;
                case R.id.glide_btn13:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new InvertFilterTransformation(GlideActivity.this))
                            .into((ImageView) findViewById(R.id.glide_img1)); //底片
                    break;
                case R.id.glide_btn14:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new PixelationFilterTransformation(GlideActivity.this, 10f))
                            .into((ImageView) findViewById(R.id.glide_img1)); //马赛克
                    break;
                case R.id.glide_btn15:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new SketchFilterTransformation(GlideActivity.this))
                            .into((ImageView) findViewById(R.id.glide_img1)); //素描
                    break;
                case R.id.glide_btn16:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new SwirlFilterTransformation(GlideActivity.this, .5f, 1.0f, new PointF(0.5f, 0.5f)))
                            .into((ImageView) findViewById(R.id.glide_img1)); //漩涡
                    break;
                case R.id.glide_btn17:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new BrightnessFilterTransformation(GlideActivity.this, 0.0f))
                            .into((ImageView) findViewById(R.id.glide_img1)); //高亮
                    break;
                case R.id.glide_btn18:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new KuwaharaFilterTransformation(GlideActivity.this, 25))
                            .into((ImageView) findViewById(R.id.glide_img1)); //滤波
                    break;
                case R.id.glide_btn19:
                    Glide.with(GlideActivity.this).load(R.drawable.cover)
                            .bitmapTransform(new VignetteFilterTransformation(GlideActivity.this, new PointF(0.5f, 0.5f), new float[] { 0.0f, 0.0f, 0.0f }, 0.0f, 0.75f))
                            .into((ImageView) findViewById(R.id.glide_img1)); //暗黑一圈
                    break;
            }
        }
    };
}
