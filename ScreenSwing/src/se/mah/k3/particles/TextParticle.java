package se.mah.k3.particles;

import java.awt.Color;
import java.awt.Graphics2D;

import se.mah.k3.Constants;
import se.mah.k3.Word;

public class TextParticle extends Particle {
	private String text;
	private Word owner;
	private float w,h,opacity=40;
	
	public TextParticle(int _x, int _y,float _w,float _h,int _vx,int _vy,String _text) {
		super(_x, _y);
		vx=_vx;
		vy=_vy;
		w=_w;
		h=_h;
		text=_text;
	}

	public void display(Graphics2D g2) {
		g2.setFont(Constants.font);
		g2.setColor(new Color(255,255,255,(int)opacity));
		g2.drawString(text, (int)(x-w*0.5), (int)(y+ h * 0.25));
	}

	public void update(){
		x+=vx;
		y+=vy;
		vx*=0.85;
		vy*=0.94;
		opacity*=0.95;
		if(opacity<10)kill();
	}

}
