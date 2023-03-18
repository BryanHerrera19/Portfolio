package starter;

public class Enemy {
 private EnemyType typeOfEnemy;
 private boolean isDestroyed;
 private int cordinateX;
 private int cordinateY;
 private Projectile enemyProjectile;
 
 public Enemy (EnemyType typeOfEnemy, boolean isDestroyed , int cordinateX, int cordinateY, Projectile enemyProjectile) {
		this.typeOfEnemy = typeOfEnemy;
		this.isDestroyed = isDestroyed;
		this.cordinateX = cordinateX;
		this.cordinateY = cordinateY;
		this.enemyProjectile=enemyProjectile;
	}

public EnemyType getTypeOfEnemy() {
	return typeOfEnemy;
}

public void setTypeOfEnemy(EnemyType typeOfEnemy) {
	this.typeOfEnemy = typeOfEnemy;
}

public boolean isDestroyed() { 
	if(typeOfEnemy.getHealth() == 0) {
		isDestroyed = true;
	}else {
		isDestroyed = false;
	}
	return isDestroyed;
}

public void setDestroyed(boolean isDestroyed) {
	this.isDestroyed = isDestroyed;
}

public int getCordinateX() {
	return cordinateX;
}

public void setCordinateX(int cordinateX) {
	this.cordinateX = cordinateX;
}

public int getCordinateY() {
	return cordinateY;
} 

public void setCordinateY(int cordinateY) {
	this.cordinateY = cordinateY;
}

public Projectile getEnemyProjectile() {
	return enemyProjectile;
}

public void setEnemyProjectile(Projectile enemyProjectile) {
	this.enemyProjectile = enemyProjectile;
}
}
