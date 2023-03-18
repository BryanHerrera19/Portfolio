package starter;

public class EnemyType {
	
	private int health;
	private int damage;
	private Hitbox enemyHitbox;
	private EnemyTypeCode eTypeCode;
	
	public EnemyType (int health, int damage, Hitbox enemyHitbox, EnemyTypeCode Type) {
		this.health = health;
		this.damage = damage;
		this.enemyHitbox = enemyHitbox;
		seteTypeCode(Type);
	}
	
	public int getHealth() {
		return health;
	}
	public int getDamage() {
		return damage;
	}
	public Hitbox getEnemyHitbox() {
		return enemyHitbox;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void setEnemyHitbox(Hitbox enemyHitbox) {
		this.enemyHitbox = enemyHitbox;
	}

	public EnemyTypeCode geteTypeCode() {
		return eTypeCode;
	}

	public void seteTypeCode(EnemyTypeCode eTypeCode) {
		this.eTypeCode = eTypeCode;
	}
	//ssss
	
	
}
