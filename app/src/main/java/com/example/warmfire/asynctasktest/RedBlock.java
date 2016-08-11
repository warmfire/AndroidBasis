package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RedBlock extends View {

	Paint rblock = null;
	private int lastX,lastY;
	public int x,y,rawx,rawy;

	public RedBlock(Context context) {
		super(context);
	}

	public RedBlock(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RedBlock(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		rblock = new Paint();
		rblock.setColor(Color.RED);
		canvas.drawRect(0, 0, 150, 150, rblock);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		x = (int) event.getX();
		y = (int) event.getY();
		rawx = (int) event.getRawX();
		rawy = (int) event.getRawY()-300;
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				lastX = x;
				lastY = y;
				break;
			case MotionEvent.ACTION_MOVE:
				int offX = x - lastX;
				int offY = y - lastY;
				layout(getLeft() + offX, getTop() + offY, getRight() + offX,
						getBottom() + offY);
				break;
		}
		return true;
	}

}
