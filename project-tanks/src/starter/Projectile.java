package starter;

import acm.graphics.*;

public class Projectile {
	private int speed;
	private int height;
	private int width;
	private GImage projectilePic = new GImage("projectile px.png");
	private GImage enemyFirePic = new GImage("enemy projectile.png");
	
	public Projectile(int speed,int height,int width) {
		this.speed=speed;
		this.height=height;
		this.width=width;
	}

	
	public static Boolean isProjectileDestroyed(Boolean x) {
		return x;
	}
	
	public GImage getProjectilePic() {
		return projectilePic;
	}
	public GImage getEnemyProjectilePic() {
		return enemyFirePic;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setSize(int height,int width) {
		projectilePic.setSize(height,width);
		enemyFirePic.setSize(height, width);
	}
	public void setCoord(double xCoord, double yCoord) {
		projectilePic.setLocation(xCoord, yCoord);
		enemyFirePic.setLocation(xCoord, yCoord);
	}

	
}