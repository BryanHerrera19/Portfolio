package starter;

public class CharacterEntity {
	private int health;
	private int damage;
	private boolean isDestroyed;
	private int coordX;
	private int coordY;
	private Projectile userProjectile;
	private Hitbox userHitbox;

	
	public CharacterEntity(int health, int damage, boolean isDestroyed, int coordX, int coordY, Projectile userProjectile, Hitbox userHitbox) {
		this.health = health;
		this.damage = damage;
		this.isDestroyed = isDestroyed;
		this.coordX = coordX;
		this.coordY = coordY;
		this.userProjectile = userProjectile;
		this.userHitbox = userHitbox;
	}
	
	public void userInput() {
		
	}
	public void initCoord() {
		
	}
	public int getHealth() {
		return health;
	}
	public int getDamage() {
		return damage;
	}
	public boolean isDestroyed() {
		if(health == 0) {
			isDestroyed = true;
		}else {
			isDestroyed = false;
		}
		return isDestroyed;
	}
	public int getCoordX() {
		return coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public Projectile getUserProjectile() {
		return userProjectile;
	}
	public Hitbox getUserHitbox() {
		return userHitbox;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	public void setUserProjectile(Projectile userProjectile) {
		this.userProjectile = userProjectile;
	}
	public void setUserHitbox(Hitbox userHitbox) {
		this.userHitbox = userHitbox;
	}

}

