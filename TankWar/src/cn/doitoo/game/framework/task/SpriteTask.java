package cn.doitoo.game.framework.task;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * ��Ϸ��������
 * @author Oliver O
 *
 */
public abstract class SpriteTask extends DrawGraphicTask{
	

	private Bitmap spriteBm;
	private SurfaceHolder holder;
	
	public SpriteTask(SurfaceHolder holder,Bitmap spriteBm){
		this.holder = holder;

		this.spriteBm =  spriteBm;
	
	}


	@Override
	public void draw() {
		Canvas c =this.holder.lockCanvas();
		c.drawBitmap(this.spriteBm, 0, 0,null);
		drawTextOrOther();
		this.holder.unlockCanvasAndPost(c);
		
	}


	public abstract void drawTextOrOther();

	
}
