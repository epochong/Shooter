package com.tarena.shoot;

import java.awt.image.BufferedImage;


/**
 * Ӣ�ۻ�
 * 
 * @author Administrator
 * 
 */
public class Hero extends FlyingObject {
	
	protected BufferedImage[] images = {};
	protected int index = 0;
 
	//private boolean doubleFire;
	private int doubleFire;
	private int life;

	public Hero() {
		life = 3;
		doubleFire = 0;
		this.image = ShootGame.hero0;
		this.ember = ShootGame.heroEmber;
		images = new BufferedImage[]{ShootGame.hero0, ShootGame.hero1};
		width = image.getWidth();
		height = image.getHeight();
		x = 150;
		y = 400;
	}

	public int isDoubleFire() {
		return doubleFire;
	}

	public void addDoubleFire(){
		doubleFire = 40;
	}
	
	public void setDoubleFire(int doubleFire) {
		this.doubleFire = doubleFire;
	}

	public void addLife() { // ����
		life++;
	}

	public void subtractLife() { // ����
		life--;
	}

	public int getLife() {
		return life;
	}

	/**
	 * ��ǰ�����ƶ���һ�£���Ծ��룬 x,y���λ��
	 */
	public void moveTo(int x, int y) {
		this.x = x - width / 2;
		this.y = y - height / 2;
	}

	@Override
	public boolean outOfBounds() {
		return x < 0 || x > ShootGame.WIDTH - width || y < 0
				|| y > ShootGame.HEIGHT - height;
	}

	public Bullet[] shoot() { // �����ӵ�
		int xStep = width / 4; // �ӵ�����Ϊ�ɻ����4��
		int yStep = 20;
		if (doubleFire>0) {
			Bullet[] bullets = new Bullet[3];
			bullets[0] = new Bullet(x + xStep, y - yStep);
			bullets[1] = new Bullet(x + 2 * xStep, y - yStep);
			bullets[2] = new Bullet(x + 3 * xStep, y - yStep);
			doubleFire -= 2;
			return bullets;
		} else { // ����
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(x + 2 * xStep, y - yStep); // y-yStep(�ӵ���ɻ���λ��)
			return bullets;
		}
	}

	@Override
	public void step() {
		if(images.length>0){
			image = images[index++/10%images.length];
		}
	}

	public boolean hit(FlyingObject other) { // ��ײ�㷨

		int x1 = other.x - this.width / 2;
		int x2 = other.x + other.width + this.width / 2;
		int y1 = other.y - this.height / 2;
		int y2 = other.y + other.height + this.height / 2;
		return this.x + this.width / 2 > x1 && this.x + this.width / 2 < x2
				&& this.y + this.height / 2 > y1
				&& this.y + this.width / 2 < y2;
	}

}
