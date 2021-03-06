package edu.vub.portalpong.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

public class Portal extends DoubleBall {

	protected Paint ringPaint;
	private int ringRadius;
	private int radiusDiff = -1;

	public Portal(int x, int y) {
		this(x, y, 0.6);
	}
	
	public Portal(int x, int y, double ratio) {
		super(x, y, ratio);
		super.setSize(40);
		this.ringRadius = this.size;
		this.ringPaint = new Paint();
		this.ringPaint.setStyle(Style.STROKE);
		this.ringPaint.setStrokeWidth(5);
		this.setColor(Color.GREEN);
	}

	public boolean collidesWith(Ball ball) {
		int dist2 = (int) Math.pow(ball.x - this.x, 2) + (int) Math.pow(ball.y - this.y, 2);
		return dist2 < this.size * this.size;
	}

	@Override
	public void draw(Canvas c) {
		super.draw(c);
		c.drawCircle(x, y, this.size, ringPaint);
		if (ringRadius <= this.size * super.ratio * super.ratio) {
			this.radiusDiff = 1;
		}
		if (ringRadius >= this.size) {
			this.radiusDiff = -1;
		}
		ringRadius = ringRadius + radiusDiff;
		c.drawCircle(x, y, ringRadius, ringPaint);
	}

	public void enter(Ball ball) {
		Log.d("portal-pong","Portal entered!");
	}
	
	public void setColor(int color) {
		super.setColor(color);
		this.ringPaint.setColor(color);
	}

	
}
