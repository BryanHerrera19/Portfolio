package starter;

import java.util.ArrayList;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Level {
	private int levelNumber;
	
	private int numberOfEnemiesLight;
	private int numberOfEnemiesMedium;
	private int numberOfEnemiesHeavy;
	private int numberOfPlayers;
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Shield> shields = new ArrayList<Shield>();
	ArrayList<GRect> playerShields = new ArrayList<GRect>();
	ArrayList<CharacterEntity> users = new ArrayList<CharacterEntity>();
	ArrayList<GRect> enemyRectangles = new ArrayList<GRect>();
	ArrayList <GImage> enemyImages = new ArrayList<GImage>();
	
	private Projectile enemyProjectile;

	Hitbox userHitbox = new Hitbox(50, 70);
	Hitbox enemyHitbox = new Hitbox(50, 70);
	//CharacterEntity user = new CharacterEntity(100, 50, false, 800, 800, userProjectile, userHitbox);
	
	EnemyType typeLight = new EnemyType(50, 10, enemyHitbox, EnemyTypeCode.LIGHT);
	 
	EnemyType typeMedium = new EnemyType(100, 20, enemyHitbox, EnemyTypeCode.MEDIUM);
	 
	EnemyType typeHeavy = new EnemyType(200, 50, enemyHitbox, EnemyTypeCode.HEAVY);
	
	Shield tempGameShield1 = new Shield(100, 200, 500, 250, 650, false);
	Shield tempGameShield2 = new Shield(100, 200, 500, 1150, 650, false);
	
	public Level(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	
	public void placeScoreSystem() {
		
	}
	
	public ArrayList<Enemy> getArrayListOfEnemies(){
		return enemies;
	}
	public int getNumberOfEnemiesLight() {
		return numberOfEnemiesLight;
	}
	public void setNumberOfEnemiesLight(int numberOfEnemiesLight) {
		this.numberOfEnemiesLight = numberOfEnemiesLight;
	}
	public int getNumberOfEnemiesMedium() {
		return numberOfEnemiesMedium;
	}
	public void setNumberOfEnemiesMedium(int numberOfEnemiesMedium) {
		this.numberOfEnemiesMedium = numberOfEnemiesMedium;
	}
	public int getNumberOfEnemiesHeavy() {
		return numberOfEnemiesHeavy;
	}
	public void setNumberOfEnemiesHeavy(int numberOfEnemiesHeavy) {
		this.numberOfEnemiesHeavy = numberOfEnemiesHeavy;
	}
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	public int getLevelNumber() {
		return levelNumber;
	}
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	
	
	public Enemy makeEnemy(EnemyType type, boolean isDestroyed, int coordX, int coordY, Projectile enemyProjectile) {
		Enemy temp = new Enemy(type, isDestroyed, coordX, coordY, enemyProjectile);
		return temp;
	}
	public void initLevel() {
		int tempX = 0; //Used in order to keep all X coordinates equal throughout each row
		if(levelNumber == 1) {
			numberOfEnemiesLight = 30;
			for(int i = 0; i < 31; i++) {
				tempX++;
				if(i < 10) { //layer one
					Enemy enemyLight = makeEnemy(typeLight, false, 80 + (80 * tempX), 50 , enemyProjectile);
					enemies.add(enemyLight);
					if(tempX == 10) { //Resets tempX at the end of each row
						tempX = 0;
					}
					
				} if(i > 9 && i < 20) { //layer two
					Enemy light2 = makeEnemy(typeLight, false, 80 + (80 * tempX), 150 , enemyProjectile);
					enemies.add(light2);
					if(tempX == 10) {
						tempX = 0;
					}
					
				} if(i > 20 && i < 31) { //layer three
					Enemy light3 = makeEnemy(typeLight, false, 80 + (80 * tempX), 250 , enemyProjectile);
					enemies.add(light3);
					if(tempX == 10) {
						tempX = 0;
					}
				}
			}
			shields.add(tempGameShield1);
			shields.add(tempGameShield2);
		}
		if(levelNumber == 2) {
			numberOfEnemiesLight = 20;
			numberOfEnemiesMedium = 10;
			for(int i = 0; i < 31; i++) {
				tempX++;
				if(i < 10) { //layer one
					Enemy medium = new Enemy(typeMedium, false, 80 + (80 * tempX), 50 , enemyProjectile);
					enemies.add(medium);
					Enemy medium1 = new Enemy(typeMedium, false, 80 + (80 * tempX), 50 , enemyProjectile); //1 extra health
					enemies.add(medium1);
					if(tempX == 10) {
						tempX = 0;
					}
				} if(i > 9 && i < 20) { //layer two
					Enemy light1 = new Enemy(typeLight, false, 80 + (80 * tempX), 150 , enemyProjectile);
					enemies.add(light1);
					if(tempX == 10) {
						tempX = 0;
					}
				} if(i > 20 && i < 31) { //layer three
					Enemy light2 = new Enemy(typeLight, false, 80 + (80 * tempX), 250 , enemyProjectile);
					enemies.add(light2);
					if(tempX == 10) {
						tempX = 0;
					}
				}
			}
			shields.add(tempGameShield1);
			shields.add(tempGameShield2);
		}
		
		if(levelNumber == 3) {
			numberOfEnemiesLight = 10;
			numberOfEnemiesMedium = 10;
			numberOfEnemiesHeavy = 10;
			for(int i = 0; i < 31; i++) {
				tempX++;
				if(i < 10) { //layer one
					Enemy heavy = new Enemy(typeHeavy, false, 80 + (80 * tempX), 50 , enemyProjectile);
					enemies.add(heavy);
					Enemy heavy1 = new Enemy(typeHeavy, false, 80 + (80 * tempX), 50 , enemyProjectile); //1 extra health
					enemies.add(heavy1);
					Enemy heavy2 = new Enemy(typeHeavy, false, 80 + (80 * tempX), 50 , enemyProjectile); //1 extra health
					enemies.add(heavy2);
					if(tempX == 10) {
						tempX = 0;
					}
				} if(i > 9 && i < 20) { //layer two
					Enemy medium = new Enemy(typeMedium, false, 80 + (80 * tempX), 150 , enemyProjectile); 
					enemies.add(medium);
					Enemy medium1 = new Enemy(typeMedium, false, 80 + (80 * tempX), 150 , enemyProjectile); //1 extra health
					enemies.add(medium1);
					if(tempX == 10) {
						tempX = 0;
					}
				} if(i > 20 && i < 31) { //layer three
					Enemy light = new Enemy(typeLight, false, 80 + (80 * tempX), 250 , enemyProjectile);
					enemies.add(light);
					if(tempX == 10) {
						tempX = 0;
					}
				}
			}
			shields.add(tempGameShield1);
			shields.add(tempGameShield2);
		}
		if(levelNumber == 4) {
			numberOfEnemiesMedium = 20;
			numberOfEnemiesHeavy = 10;
			for(int i = 0; i < 31; i++) {
				tempX++;
				if(i < 10) { //layer one
					Enemy heavy = new Enemy(typeHeavy, false, 80 + (80 * tempX), 50 , enemyProjectile);
					enemies.add(heavy);
					Enemy heavy1 = new Enemy(typeHeavy, false, 80 + (80 * tempX), 50 , enemyProjectile); //1 extra health
					enemies.add(heavy1);
					Enemy heavy2 = new Enemy(typeHeavy, false, 80 + (80 * tempX), 50 , enemyProjectile); //1 extra health
					enemies.add(heavy2);
					if(tempX == 10) {
						tempX = 0;
					}
				} if(i > 9 && i < 20) { //layer two
					Enemy medium = new Enemy(typeMedium, false, 80 + (80 * tempX), 150 , enemyProjectile); 
					enemies.add(medium);
					Enemy medium1 = new Enemy(typeMedium, false, 80 + (80 * tempX), 150 , enemyProjectile); //1 extra health
					enemies.add(medium1);
					if(tempX == 10) {
						tempX = 0;
					}
				} if(i > 20 && i < 31) { //layer three
					Enemy medium2 = new Enemy(typeMedium, false, 80 + (80 * tempX), 250 , enemyProjectile);
					enemies.add(medium2);
					Enemy medium3 = new Enemy(typeMedium, false, 80 + (80 * tempX), 250 , enemyProjectile); // 1 extra health
					enemies.add(medium3);
					if(tempX == 10) {
						tempX = 0;
					}
				}
			}
			shields.add(tempGameShield1);
			shields.add(tempGameShield2);
		}
		
	}
	public void printArrayList() { //Prints the arrayList 
		System.out.println("size: " + enemies.size());
		System.out.println("size of Shields: " + shields.size());
		for(Enemy temp : enemies) {
			System.out.println("CoordX: " + temp.getCordinateX() + " CoordY: " + temp.getCordinateY() + " Enemy Type: " + temp.getTypeOfEnemy().geteTypeCode());
		}
	}
	
	public ArrayList<GRect> createEnemyRect() { //Creates new GRects to be called in Gameplay.java
		GRect tempRec;
		for(Enemy temp : enemies) {
			tempRec = new GRect(temp.getCordinateX(), temp.getCordinateY(), temp.getTypeOfEnemy().getEnemyHitbox().getWidth(), temp.getTypeOfEnemy().getEnemyHitbox().getHeight());
			enemyRectangles.add(tempRec);
		}
		return enemyRectangles;
	}
	public ArrayList<GImage> createEnemyImages(){
		GImage tempImage;
		for(Enemy temp : enemies) {
			if(temp.getTypeOfEnemy().geteTypeCode() == EnemyTypeCode.LIGHT) {
				GImage imageLight = new GImage("red tank.png", temp.getCordinateX(), temp.getCordinateY());
				enemyImages.add(imageLight);
			}if(temp.getTypeOfEnemy().geteTypeCode() == EnemyTypeCode.MEDIUM) {
				GImage imageMedium = new GImage("red tank 2.png", temp.getCordinateX(), temp.getCordinateY());
				enemyImages.add(imageMedium);
			}if(temp.getTypeOfEnemy().geteTypeCode() == EnemyTypeCode.HEAVY) {
				GImage imageHeavy = new GImage("red tank 3.png", temp.getCordinateX(), temp.getCordinateY());
				enemyImages.add(imageHeavy);
			}
		}
		return enemyImages;
	}
	public ArrayList<GRect> placeShield() {
		GRect tempRec;
		for(Shield temp: shields) {
			tempRec = new GRect(temp.getCoordX(), temp.getCoordY(), temp.getWidth(), temp.getHeight());
			playerShields.add(tempRec);
		}
		return playerShields;
	}
	public void destroyShield(int coordX, int coordY) {
		for (Shield temp : shields) {
			if (coordX == temp.getCoordX() && coordY == temp.getCoordY()) {
				Shield tempShield = new Shield(temp.getHeight(), temp.getWidth(), temp.getHealth(), temp.getCoordX(), temp.getCoordY(), true);
				shields.remove(tempShield);
			}
		}
	}
	public void clearEnemyList() {
		if (enemies.size() != 0) {
			enemies.clear();
		}
		else {
			System.out.println("Enemies Arraylist already clear");
		}
	}
	public void destroyEnemy(int coordX, int coordY) {
		for (Enemy temp : enemies) {
			if (coordX == temp.getCordinateX() && coordY == temp.getCordinateY()) {
				Enemy tempEnemy = new Enemy(temp.getTypeOfEnemy(), temp.isDestroyed(), temp.getCordinateX(), temp.getCordinateY(), temp.getEnemyProjectile());
				enemies.remove(tempEnemy);
			}
		}
	}
}