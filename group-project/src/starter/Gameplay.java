package starter;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.Action;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.util.ArrayList;

public class Gameplay extends GraphicsProgram implements ActionListener,KeyListener{
	public static final int PROGRAM_WIDTH = 1600;
	public static final int PROGRAM_HEIGHT = 900;
	
	//Setting levels
	private Level levelOne = new Level(1);
	private Level levelTwo = new Level(2);
	private Level levelThree = new Level(3);
	private Level levelFour = new Level(4);
	
	//List of Images of different screens
	private GImage mainMenuScreen = new GImage("officialMainMenu.png");
	private GImage pauseScreen = new GImage("pauseScreen.png");
	private GImage controlScreen = new GImage("controlScreen.png");
	private GImage winScreen = new GImage("singlePlayerWinScreen.png");
	private GImage loseScreen = new GImage("singlePlayerLoseScreen.png");
	private GImage scoreboardScreen = new GImage("scoreBoardScreen.png");
	private GImage chooseBonusScreen = new GImage("ChooseBonusScreen.png");
	
	//List of Different buttons for different screens
	private GRect singlePlayerButton = new GRect(500, 160, 270, 50);
	private GRect coopButton = new GRect(880, 160, 180, 50);
	private GRect scoreBoardButton = new GRect(500, 235, 270, 50);
	private GRect controlButton = new GRect(880, 235, 200, 50);
	private GRect exitButton = new GRect(500, 310, 120, 50);
	private GRect pauseReturnMainMenuButton = new GRect(480,215,280,50); //pause screen return main menu button
	private GRect resumeButton = new GRect(860, 215, 220, 50);
	private GRect controlReturnMainMenuButton = new GRect(1240,780,280,50); //control screen return main menu button
	private GRect scoreReturnMainMenuButton = new GRect(1240,780,280,50); //scoreboard screen return main menu button
	private GRect winReturnMainMenuButton = new GRect(380, 220, 280, 60);
	private GRect winScoreboardButton = new GRect(1000, 220, 290, 60);
	private GRect winNextLevelButton = new GRect(685, 220, 270, 60);
	private GRect loseReturnMainMenuButton = new GRect(480, 220, 280, 60);
	private GRect loseRetryButton = new GRect(860, 220, 200, 60);
	private GRect bonusAttackSpeedButton = new GRect(250, 220, 310, 60);
	private GRect bonusMovementSpeedButton = new GRect(1000, 220, 370, 60);
	
	
	//List of booleans for buttons
	private boolean singlePlayerButtonPressed = false;
	private boolean coopButtonPressed = false;
	private boolean scoreBoardButtonPressed = false;
	private boolean controlButtonPressed = false;
	private boolean pauseButtonPressed = false;
	private boolean pauseReturnMainMenuPressed = false;
	private boolean resumeButtonPressed = false;
	private boolean exitButtonPressed = false;
	private boolean controlReturnMainMenuPressed = false;
	private boolean scoreReturnMainMenuPressed = false;
	private boolean winReturnMainMenuPressed = false;
	private boolean winScoreboardPressed = false;
	private boolean winNextLevelPressed = false;
	private boolean loseReturnMainMenuPressed = false;
	private boolean loseRetryPressed = false;
	private boolean bonusAttackSpeedPressed = false;
	private boolean bonusMovementSpeedPressed = false;
	
	//List of different timers
	private Timer mainMenuTimer = new Timer(1000, this);
	private Timer pauseTimer = new Timer(1000, this);
	private Timer singlePlayerTimer = new Timer(50, this);
	private Timer controlScreenTimer = new Timer(1000, this);
	private Timer scoreboardScreenTimer = new Timer(1000,this);
	private Timer loseScreenTimer = new Timer(1000,this);
	private Timer winScreenTimer = new Timer(1000,this);
	private Timer bonusScreenTimer = new Timer(1000,this);
	private Timer gameTimer = new Timer(1000, this); //used to time in game, if run out, then you lose.
	private Timer animationTimer = new Timer(1000, this);
	private Timer enemyFireTimer = new Timer(1000, this);

	
	//List of lists of shields and enemies and enemy movement
	private ArrayList<GRect> enemyRectangles = new ArrayList<GRect>();
	private ArrayList<GRect> playerShields = new ArrayList<GRect>();
	private int enemyMovementSpeed = -4;
	
	//List of Single player tank movement variables, score, health, and image
	private GImage singlePlayerTank = new GImage("blue tank.png");
	private int singlePlayerTankStartingXCoord = 800;
	private int singlePlayerTankStartingYCoord = 750;
	private int singlePlayerArrIndex = 0;
	private int singlePlayerSpeedX = 6; 
	private ArrayList<Integer> singlePlayerScores = new ArrayList<Integer>();
	private int gameNumber = -1;
	private GLabel singlePlayerScoreLabel = new GLabel("Score: ", 30, 880);
	private int singlePlayerTankHealth = 10;
	private GLabel singlePlayerHealthLabel = new GLabel("Health: ", 1350, 880);
	private GLabel timerLabel = new GLabel("Time Left: ", 650, 880);
	private ArrayList<Projectile> singlePlayerProjectiles = new ArrayList<Projectile>();
	private ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();

	
	//picture for the shield and enemyTanks
	private GImage Shield1 = new GImage("Rock px.png", 250, 650);
	private GImage Shield2 = new GImage("Rock px.png", 1150, 650);
	private ArrayList<GImage> enemyImages = new ArrayList<GImage>();
	private ArrayList<GImage> enemyMuzzleImages = new ArrayList<GImage>();
	
	//variables for enemy
	private int enemyHitCount = 0;
	private int enemyFireDelay = 0;
	private int enemyFireDelayReach = 100;
	private GImage endOfScreenEnemy = new GImage("singlePlayerLoseScreen.png", 0, 900);
	private GImage endOfScreenUser = new GImage("singlePlayerLoseScreen.png", 0, -1130);
	    
	//Pictures and integers for the animation
	private GImage explode1 = new GImage("Explosion1.png", 100, 200);
	private GImage explode2 = new GImage("Explosion2.png", 100, 200);
	private GImage explode3 = new GImage("Explosion3.png", 100, 200);
	private GImage userFirePic = new GImage("userFirePic.png", 100, 200);
	private GImage levelOneBackground1 = new GImage("snow top view.jpg");
	private GImage levelOneBackground2 = new GImage("snow top view2.jpg");
	private GImage levelFourBackground1 = new GImage("desert top view.jpg");
	private GImage levelFourBackground2 = new GImage("desert top view2.jpg");
	private int explodeNumber = 0;
	private int animateNumber = 0;
	private int singlePlayerFireNumber = 0; //used for user fire delay
	private int singlePlayerFireNumberReach = 2; //used for user fire delay
	private int gameTime = 100; 
	private int gameTimeAmount = 0; //used for game time delay
	
	//level tracking variables
	private boolean levelOneRepeat = false;
	private boolean levelTwoRepeat = false;
	private boolean levelThreeRepeat = false;
	private boolean levelFourRepeat = false;
	private int userLevelOneRepeat = 0;
	private int userLevelTwoRepeat = 0;
	private int userLevelThreeRepeat = 0;
	private int userLevelFourRepeat = 0;
	private int userLevel = 1;
	private GLabel levelLabel = new GLabel("Level #", 1400, 30);
	
	
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		addKeyListeners();
		addMouseListeners();
	}
	
	//**** Input Listeners ****
	public void mousePressed(MouseEvent e) {
		System.out.println("X Click: " + e.getX() + " Y Click: " + e.getY());
		if(mainMenuTimer.isRunning()) {
			//Single Player Button
			if(e.getX() < singlePlayerButton.getX() + singlePlayerButton.getWidth() && e.getX() >= singlePlayerButton.getX() 
					&& e.getY() < singlePlayerButton.getY() + singlePlayerButton.getHeight() &&
					e.getY() >= singlePlayerButton.getY()) {
				singlePlayerButtonPressed = true;
			}
			//Coop Player Button
			if(e.getX() < coopButton.getX() + coopButton.getWidth() && e.getX() >=coopButton.getX() 
					&& e.getY() < coopButton.getY() + coopButton.getHeight() &&
					e.getY() >= coopButton.getY()) {
				coopButtonPressed = true;
			}
			//score board button
			if(e.getX() < scoreBoardButton.getX() + scoreBoardButton.getWidth() && e.getX() >= scoreBoardButton.getX() 
					&& e.getY() < scoreBoardButton.getY() + scoreBoardButton.getHeight() &&
					e.getY() >= scoreBoardButton.getY()) {
				scoreBoardButtonPressed = true;
			}
			//Control button
			if(e.getX() < controlButton.getX() + controlButton.getWidth() && e.getX() >= controlButton.getX() 
					&& e.getY() < controlButton.getY() + controlButton.getHeight() &&
					e.getY() >= controlButton.getY()) {
				controlButtonPressed = true;
			}
			//Exit button
			if(e.getX() < exitButton.getX() + exitButton.getWidth() && e.getX() >= exitButton.getX() 
					&& e.getY() < exitButton.getY() + exitButton.getHeight() &&
					e.getY() >= exitButton.getY()) {
				exitButtonPressed = true;
			}
		}

		if(pauseTimer.isRunning()) {
			//pause screen return to main menu button
			if(e.getX() < pauseReturnMainMenuButton.getX() + pauseReturnMainMenuButton.getWidth() && e.getX() >= pauseReturnMainMenuButton.getX() 
					&& e.getY() < pauseReturnMainMenuButton.getY() + pauseReturnMainMenuButton.getHeight() &&
					e.getY() >= pauseReturnMainMenuButton.getY()) {
				pauseReturnMainMenuPressed = true;
			}
			//pause screen resume game button
			if(e.getX() < resumeButton.getX() + resumeButton.getWidth() && e.getX() >= resumeButton.getX() 
					&& e.getY() < resumeButton.getY() + resumeButton.getHeight() &&
					e.getY() >= resumeButton.getY()) {
				resumeButtonPressed = true;
			}
		}
		
		if(controlScreenTimer.isRunning()) {
			//control screen returns to main menu button
			if(e.getX() < controlReturnMainMenuButton.getX() + controlReturnMainMenuButton.getWidth() && e.getX() >= controlReturnMainMenuButton.getX() 
					&& e.getY() < controlReturnMainMenuButton.getY() + controlReturnMainMenuButton.getHeight() &&
					e.getY() >= controlReturnMainMenuButton.getY()) {
				controlReturnMainMenuPressed = true;
			}
		}
		
		if(scoreboardScreenTimer.isRunning()) {
			//scoreboard screen return to main menu button
			if(e.getX() < scoreReturnMainMenuButton.getX() + scoreReturnMainMenuButton.getWidth() && e.getX() >= scoreReturnMainMenuButton.getX() 
					&& e.getY() < scoreReturnMainMenuButton.getY() + scoreReturnMainMenuButton.getHeight() &&
					e.getY() >= scoreReturnMainMenuButton.getY()) {
				scoreReturnMainMenuPressed = true;
			}
		}
		
		if(winScreenTimer.isRunning()) {
			//win screen return to main menu button.
			if(e.getX() < winReturnMainMenuButton.getX() + winReturnMainMenuButton.getWidth() && e.getX() >= winReturnMainMenuButton.getX() 
					&& e.getY() < winReturnMainMenuButton.getY() + winReturnMainMenuButton.getHeight() &&
					e.getY() >= winReturnMainMenuButton.getY()) {
				winReturnMainMenuPressed = true;
			} //win screen next level button.
			if(e.getX() < winNextLevelButton.getX() + winNextLevelButton.getWidth() && e.getX() >= winNextLevelButton.getX() 
					&& e.getY() < winNextLevelButton.getY() + winNextLevelButton.getHeight() &&
					e.getY() >= winNextLevelButton.getY()) {
				winNextLevelPressed = true;
			} //win screen scoreboard button.
			if(e.getX() < winScoreboardButton.getX() + winScoreboardButton.getWidth() && e.getX() >= winScoreboardButton.getX() 
					&& e.getY() < winScoreboardButton.getY() + winScoreboardButton.getHeight() &&
					e.getY() >= winScoreboardButton.getY()) {
				winScoreboardPressed = true;
			}
		}
		
		if(loseScreenTimer.isRunning()) {
			//lose screen return to main menu button
			if(e.getX() < loseReturnMainMenuButton.getX() + loseReturnMainMenuButton.getWidth() && e.getX() >= loseReturnMainMenuButton.getX() 
					&& e.getY() < loseReturnMainMenuButton.getY() + loseReturnMainMenuButton.getHeight() &&
					e.getY() >= loseReturnMainMenuButton.getY()) {
				loseReturnMainMenuPressed = true;
			} //lose screen retry button
			if(e.getX() < loseRetryButton.getX() + loseRetryButton.getWidth() && e.getX() >= loseRetryButton.getX() 
					&& e.getY() < loseRetryButton.getY() + loseRetryButton.getHeight() &&
					e.getY() >= loseRetryButton.getY()) {
				loseRetryPressed = true;
			}
		}
		
		if(bonusScreenTimer.isRunning()) {
			//bonus screen attack speed button
			if(e.getX() < bonusAttackSpeedButton.getX() + bonusAttackSpeedButton.getWidth() && e.getX() >= bonusAttackSpeedButton.getX() 
					&& e.getY() < bonusAttackSpeedButton.getY() + bonusAttackSpeedButton.getHeight() &&
					e.getY() >= bonusAttackSpeedButton.getY()) {
				bonusAttackSpeedPressed = true;
			}
			//bonus screen Movement speed button
			if(e.getX() < bonusMovementSpeedButton.getX() + bonusMovementSpeedButton.getWidth() && e.getX() >= bonusMovementSpeedButton.getX() 
					&& e.getY() < bonusMovementSpeedButton.getY() + bonusMovementSpeedButton.getHeight() &&
					e.getY() >= bonusMovementSpeedButton.getY()) {
				bonusMovementSpeedPressed = true;
			}
			
		}
		
		return;
	}
	
	//Key Input Listeners
	public void keyPressed(KeyEvent e) {
//		if(gameTimer.isRunning()) {
//			if (e.getKeyCode() == 27) {
//				System.out.println("You pressed *esc* ");
//				singlePlayerTimer.start();
//				pauseGame();
//			}
//		}
//		//Single Player Tank Movement and Fire
	     if(singlePlayerTimer.isRunning()) {
			int moveKeyCode = e.getKeyCode();
	        if(moveKeyCode == 68){ //"d" right movement
	        	if(singlePlayerTank.getX() < 1520) {// Line to ensure tank doesn't leave boundaries
		        	singlePlayerTank.move(singlePlayerSpeedX,0);
		        	singlePlayerTank.setImage("Blue tank right.png");
	        	}
	        }
	        if(moveKeyCode == 65) { //"a" left movement
	        	if(singlePlayerTank.getX() > -10) { // Line to ensure tank doesn't leave boundaries
		        	singlePlayerTank.move(-1 * singlePlayerSpeedX,0);
		        	singlePlayerTank.setImage("Blue tank left.png");
	        	}
	        }    
	     }
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 87) { 
			System.out.println("You released key code: " + e.getKeyCode()); 
			System.out.println("you pressed *w* fire projectile");
        	singlePlayerTank.setImage("blue tank.png");
			singlePlayerFireNumber++;
			if(singlePlayerFireNumber >= singlePlayerFireNumberReach) { // Delays the user fire
				singlePlayerUserFire();
				singlePlayerFireNumber = 0;
			}
		}
	}	
	

	public void actionPerformed(ActionEvent e) {
		//Main menu button click recognition
		if(mainMenuTimer.isRunning()) {
			if(singlePlayerButtonPressed) {
				mainMenuTimer.stop();
				singlePlayerButtonPressed = false;
				singlePlayerMode(userLevel);
			}
			if(coopButtonPressed) {
				mainMenuTimer.stop();
				coopButtonPressed = false;
				coopMode();
			}
			if(scoreBoardButtonPressed) {
				gameTimer.stop();
				mainMenuTimer.stop();
				scoreBoardButtonPressed = false;
				displayScoreboard();
			}
			if(controlButtonPressed) {
				gameTimer.stop();
				mainMenuTimer.stop();
				controlButtonPressed = false;
				displayControlScreen();
			}
			if(exitButtonPressed) {
				System.out.println("Exiting game...");
				System.exit(0);
			}
	
		}		

		if(singlePlayerTimer.isRunning()) {
			enemyMovement();
			singlePlayerMoveProjectile();
			singlePlayerObjHit();
			enemyObjHit();
			displayHealthInGame();
			if(enemyHitCount==30) { // Track user win
				gameTimer.stop();
				enemyFireTimer.stop();
				singlePlayerTimer.stop();
				gameTime = 100;
				enemyHitCount=0;
				winScreenTimer.start();
				userWin();
				
			}
			if(gameTime == 0 || singlePlayerTankHealth == 0) { //Track user loss
				singlePlayerTimer.stop();
				enemyFireTimer.stop();
				gameTimer.stop();
				gameTime = 100;
				enemyHitCount = 0;
				loseScreenTimer.start();
				userLose();
			}
		}
		if(enemyFireTimer.isRunning()) {
			enemyFireDelay++;
			if(enemyFireDelay >= enemyFireDelayReach) {
				enemyFire();
				enemyFireDelay = 0;
			}
			enemyProjectileMovement();
		}
		if(animationTimer.isRunning()) {
			animateNumber++;
			if(animateNumber % 7 == 3) {
				deleteUserFirePic();
				deleteEnemyFirePic();
			}
			if(animateNumber == 20) {
				deleteEnemyExplosion();
				animateNumber = 0;
			}
			//Move the background
			levelOneBackground1.move(-2, 0);
			levelOneBackground2.move(-2, 0);
			if(levelOneBackground1.getX() <= -1600) {
				levelOneBackground1.setLocation(1600,0);
			}
			if(levelOneBackground2.getX() <= -1600) {
				levelOneBackground2.setLocation(1600,0);
			}
			if(userLevel == 4) {
				levelFourBackground1.move(-2, 0);
				levelFourBackground2.move(-2, 0);
				if(levelFourBackground1.getX() <= -1600) {
					levelFourBackground1.setLocation(1600,0);
				}
				if(levelFourBackground2.getX() <= -1600) {
					levelFourBackground2.setLocation(1600,0);
				}
			}
		}
		//control screen button recognition
		if(controlScreenTimer.isRunning()) {
			if(controlReturnMainMenuPressed) {
				gameTimer.stop();
				controlScreenTimer.stop();
				controlReturnMainMenuPressed = false;
				displayMenu();
			}
		}
		//scoreboard screen button recognition
		if(scoreboardScreenTimer.isRunning()) {
			if(scoreReturnMainMenuPressed) {
				gameTimer.stop();
				scoreboardScreenTimer.stop();
				scoreReturnMainMenuPressed = false;
				displayMenu();
			}
		}
		//bonus screen timer button recognition
		if(bonusScreenTimer.isRunning()) {
			if(bonusAttackSpeedPressed) {
				singlePlayerFireNumberReach = 1; //Makes the user attack faster
				bonusScreenTimer.stop();
				singlePlayerMode(userLevel);
			}
			if(bonusMovementSpeedPressed) {
				singlePlayerSpeedX = 12; //Makes the user move faster
				bonusScreenTimer.stop();
				singlePlayerMode(userLevel);
			}
		}
		
		//Win screen button recognition
		if(winScreenTimer.isRunning()) {
			if(winReturnMainMenuPressed) {
				winScreenTimer.stop();
				winReturnMainMenuPressed = false;
				displayMenu();
			}
			if(winScoreboardPressed) {
				winScreenTimer.stop();
				winScoreboardPressed = false;
				displayScoreboard();
			}
			if(winNextLevelPressed) {
				winScreenTimer.stop();
				winNextLevelPressed = false;
				if(userLevel != 4) {
					userLevel++;
				}
				if(userLevel == 4 && levelFourRepeat == false) {
					displayBonusScreen();
				}
				else {
					singlePlayerMode(userLevel); //Make sure this becomes the next level
				}
			}
		}
		//Lose screen button recognition
		if(loseScreenTimer.isRunning()) {
			if(loseReturnMainMenuPressed) {
				loseScreenTimer.stop();
				loseReturnMainMenuPressed = false;
				displayMenu();
			}
			if(loseRetryPressed) {
				loseScreenTimer.stop();
				loseRetryPressed = false;
				singlePlayerMode(userLevel);
			}
		}
		//game timer
		if(gameTimer.isRunning()) {
			gameTimeAmount++;
			if(gameTimeAmount == 21) {
				gameTime--;
				gameTimeAmount = 0;
				displayTimerInGame();
			}
		}
	}
	
	
	public void run() { // Main Function
		displayMenu();
	}
	
	public void displayMenu() { //displays menu screen here
		System.out.println("Main menu entered.");
		removeAll();
		mainMenuScreen.setSize(1600, 900);
		add(mainMenuScreen);
		add(singlePlayerButton);
		add(coopButton);
		add(scoreBoardButton);
		add(controlButton);
		add(exitButton);
		mainMenuTimer.start();
		
	}
	
	public void checkIfLevelRepeat() {
		if(userLevel == 1) { 
			userLevelOneRepeat++; //userLevelOneRepeat is initialized at 0. so 2nd game or greater in the same level is repeat.
			if(userLevelOneRepeat >= 2) {
				levelOneRepeat = true;
			}
		}
		if(userLevel == 2) {
			userLevelTwoRepeat++; //userLevelTwoRepeat is initialized at 0. so 2nd game or greater in the same level is repeat.
			if(userLevelTwoRepeat >= 2) {
				levelTwoRepeat = true;
				userLevelTwoRepeat = 0;
			}
		}
		if(userLevel == 3) {
			userLevelThreeRepeat++; //userLevelTwoRepeat is initialized at 0. so 2nd game or greater in the same level is repeat.
			if(userLevelThreeRepeat >= 2) {
				levelThreeRepeat = true;
				userLevelThreeRepeat = 0;
			}
			
		}
		if(userLevel == 4) {
			userLevelFourRepeat++; //userLevelTwoRepeat is initialized at 0. so 2nd game or greater in the same level is repeat.
			if(userLevelFourRepeat >= 2) {
				levelFourRepeat = true;
				userLevelFourRepeat = 0;
			}
		}
	}
	
	public void singlePlayerMode(int levelNumber) {
		singlePlayerTankHealth = 5;
		gameTimer.start();
	    gameNumber++;
		singlePlayerTimer.start();
		animationTimer.start();
		enemyFireTimer.start();
		System.out.println("Single player mode entered.");
		removeAll(); //Removes everything from screen.
		System.out.println(enemyImages.size());
		System.out.println(enemyRectangles.size());
		checkIfLevelRepeat();
		//Level one initialize
		if(levelNumber == 1 && levelOneRepeat == false) {
			levelOneBackground1.setSize(1600,900);
			levelOneBackground2.setSize(1600,900);
			levelOneBackground1.setLocation(0,0);
			levelOneBackground2.setLocation(1600,0);
			levelOne.initLevel();
			levelOne.printArrayList();
			System.out.println("level one was initialized");

		}
		if(levelNumber == 1) {
			add(levelOneBackground1);
			add(levelOneBackground2);
			enemyRectangles = levelOne.createEnemyRect();
			enemyImages = levelOne.createEnemyImages();
			userLevel = 1;
		}
		if(levelNumber == 2 && levelTwoRepeat == false) {
			levelOneBackground1.setSize(1600,900);
			levelOneBackground2.setSize(1600,900);
			levelOneBackground1.setLocation(0,0);
			levelOneBackground2.setLocation(1600,0);
			levelTwo.initLevel();
			levelTwo.printArrayList();
			System.out.println("level two was initialized");
		}
		if(levelNumber == 2) {
			enemyHitCount = -10; // 10 extra hits for the medium enemies in the 3rd row
			add(levelOneBackground1);
			add(levelOneBackground2);
			enemyRectangles = levelTwo.createEnemyRect();
			enemyImages = levelTwo.createEnemyImages();
		}
		if(levelNumber == 3 && levelThreeRepeat == false) {
			levelOneBackground1.setSize(1600,900);
			levelOneBackground2.setSize(1600,900);
			levelOneBackground1.setLocation(0,0);
			levelOneBackground2.setLocation(1600,0);
			levelThree.initLevel();
			levelThree.printArrayList();
			System.out.println("level three was initialized");

		}
		if(levelNumber == 3) {
			enemyHitCount = -30; // 10 extra for 2nd row, 20 extra for 3rd row 
			add(levelOneBackground1);
			add(levelOneBackground2);
			enemyRectangles = levelThree.createEnemyRect();
			enemyImages = levelThree.createEnemyImages();
		}
		if(levelNumber == 4 && levelFourRepeat == false) {
			levelFourBackground1.setSize(1600,900);
			levelFourBackground2.setSize(1600,900);
			levelFourBackground1.setLocation(0,0);
			levelFourBackground2.setLocation(1600,0);
			levelFour.initLevel();
			levelFour.printArrayList();
			enemyFireDelayReach = 60; //Makes the enemies shoot faster
			System.out.println("level four was initialized");
		}
		if(levelNumber == 4) {
			enemyHitCount = -40; // 20 extra for 2nd row, 20 extra for 3rd row
			add(levelFourBackground1);
			add(levelFourBackground2);
			enemyRectangles = levelFour.createEnemyRect();
			enemyImages = levelFour.createEnemyImages();
		}
		pasteEnemyImages();
		pasteEnemies();
		System.out.println("Size of GRect Array: " + enemyRectangles.size());
		playerShields = levelOne.placeShield();
		pasteShields();
	    add(singlePlayerTank,singlePlayerTankStartingXCoord,singlePlayerTankStartingYCoord);
	    singlePlayerScores.add(0);
	    timerLabel.setFont("AgencyFB-Bold-42");
	    add(timerLabel);
		singlePlayerScoreLabel.setFont("AgencyFB-Bold-40");
	    add(singlePlayerScoreLabel);
	    singlePlayerHealthLabel.setFont("AgencyFB-Bold-40");
	    add(singlePlayerHealthLabel);
	    levelLabel.setLabel("Level # " + userLevel);
	    levelLabel.setColor(Color.GREEN);
	    if(userLevel == 4) {
	    	levelLabel.setColor(Color.black);
	    	levelLabel.setLabel("Level # 4 (HARD)");
	    }
	    levelLabel.setFont("AgencyFB-Bold-30");
	    add(levelLabel);
		add(endOfScreenEnemy);
		add(endOfScreenUser);
	   
	}
	
	public void pauseGame() { //Displays a screen when game is paused
		pauseTimer.start();
		System.out.println("Game Paused");
		removeAll();
		enemyRectangles.clear();
		enemyImages.clear();
		singlePlayerProjectiles.clear();
		pauseScreen.setSize(1600, 900);
		add(pauseScreen);
		add(pauseReturnMainMenuButton);
		add(resumeButton);

	}
	
	public void displayScoreboard() { //Displays the scoreboard
		scoreboardScreenTimer.start();
		System.out.println("Scoreboard entered.");
		removeAll(); //Removes everything from screen.
		scoreboardScreen.setSize(1600,900);
		add(scoreboardScreen);
		add(scoreReturnMainMenuButton);
		for(int i = 0; i < singlePlayerScores.size(); i++) {
			GLabel score = new GLabel("Score: " + singlePlayerScores.get(i), 900, 300 + (i * 100)); 
			score.setFont("AgencyFB-Bold-50");
			add(score);
			GLabel gameNumber = new GLabel("Game # " + (i + 1), 500, 300 + (i * 100));
			gameNumber.setFont("AgencyFB-BOLD-50");
			add(gameNumber);
		}
	}
	
	public void displayBonusScreen() { //Displays the bonus screen
		bonusScreenTimer.start();
		System.out.println("Bonus Entered");
		removeAll(); //Removes everything from screen.
		enemyRectangles.clear();
		enemyImages.clear();
		singlePlayerProjectiles.clear();
		chooseBonusScreen.setSize(1600,900);
		add(chooseBonusScreen);
		add(bonusAttackSpeedButton);
		add(bonusMovementSpeedButton);
	}
	
	public void coopMode() {
		System.out.println("Coop player mode entered.");
		removeAll(); //Removes everything from screen.
		return;
	}
	
	public void displayControlScreen() {
		controlScreenTimer.start();
		System.out.println("controlScreen.");
		removeAll(); //Removes everything from screen.
		controlScreen.setSize(1600,900);
		add(controlScreen);
		add(controlReturnMainMenuButton);
		
	}
	
	public void userWin() { //displays the screen when the user wins
		System.out.println("User Win Entered");
		removeAll(); //Removes everything from screen.
		enemyRectangles.clear();
		enemyImages.clear();
		singlePlayerProjectiles.clear();
		winScreen.setSize(1600,900);
		add(winScreen);
		add(winReturnMainMenuButton);
		add(winScoreboardButton);
		add(winNextLevelButton);
		GLabel scoreLabel = new GLabel(" " + singlePlayerScores.get(gameNumber),700,350);
		scoreLabel.setFont("AgencyFB-BOLD-50");
		add(scoreLabel);
	}
	
	public void userLose() { //displays the screen when the user loses
		System.out.println("User Lose Entered");
		removeAll(); //Removes everything from screen.
		enemyRectangles.clear();
		enemyImages.clear();
		singlePlayerProjectiles.clear();
		loseScreen.setSize(1600,900);
		add(loseScreen);
		add(loseReturnMainMenuButton);
		add(loseRetryButton);
		GLabel scoreLabel = new GLabel(" " + singlePlayerScores.get(gameNumber),700,350);
		scoreLabel.setFont("AgencyFB-BOLD-50");
		add(scoreLabel);
	}
	
	public void enemyMovement() { //controls the enemy movement mechanics
		for(GRect temp : enemyRectangles) {
			if(temp.getX() == 0) {
				enemyMovementSpeed = 4;
			}if(temp.getX() == 1548) {
				enemyMovementSpeed = -4;
			}
			temp.move(enemyMovementSpeed, 0);	
		}
		for(GImage temp : enemyImages) {
			if(temp.getX() == 0) {
				enemyMovementSpeed = 4;
			}if(temp.getX() == 1548) {
				enemyMovementSpeed = -4;
			}
			temp.move(enemyMovementSpeed, 0);	
		}
	}
	
	public void singlePlayerUserFire() { //controls the user fire mechanics
		Projectile temp = new Projectile(10,50,40);
		temp.setCoord(singlePlayerTank.getX() + 34, singlePlayerTank.getY());
		add(temp.getProjectilePic());
		singlePlayerProjectiles.add(temp);
		userFirePic.setLocation(singlePlayerTank.getX() + 9, singlePlayerTank.getY() - 45);
		add(userFirePic);
	}
	public void singlePlayerMoveProjectile() {
		for (Projectile temp:singlePlayerProjectiles) {
			temp.getProjectilePic().move(0, -1 * temp.getSpeed());
		}
	}
	
	public void enemyFire() { //controls the enemy fire mechanics
		for(GImage tempImage : enemyImages) {
			if(canEnemyFire(tempImage)) {
				Projectile temp = new Projectile(8, 50, 40);
				GImage enemyFirePic = new GImage("enemy muzzle flash.png", 100, 200);
				temp.setCoord(tempImage.getX(), tempImage.getY());
				add(temp.getEnemyProjectilePic());
				enemyProjectiles.add(temp);
				enemyFirePic.setLocation(tempImage.getX() - 10, tempImage.getY() + 20);
				enemyMuzzleImages.add(enemyFirePic);
				add(enemyFirePic);
			}
		}
	}
	public void enemyProjectileMovement() { //Moves the enemy projectiles
		for(Projectile temp : enemyProjectiles) {
			temp.getEnemyProjectilePic().move(0, temp.getSpeed());
		}
	}
	public boolean canEnemyFire(GImage x) { //Checks if the enemy can fire , whether or not there is an enemy in front
		boolean canFire = false;
		if(x.getY() == 50) {
			for(GImage tempImage : enemyImages) {
				if(x.getX() == tempImage.getX() && tempImage.getY() == 150) {
					canFire = false;
					break;
				}
				if(x.getX() == tempImage.getX() && tempImage.getY() == 250) {
					canFire = false;
					break;
				}else {
					canFire = true;
				}
			}
			return canFire;
		}
		if(x.getY() == 150) {
			for(GImage tempImage : enemyImages) {
				if(x.getX() == tempImage.getX() && tempImage.getY() == 250) {
					canFire = false;
					break;
				}else {
					canFire = true;
				}
			}
			return canFire;
		}
		if(x.getY() == 250) {
			return true;
		}
		return canFire;
	}
	
	public void singlePlayerObjHit() {
		for(int i = 0; i < singlePlayerProjectiles.size(); i++) {
			Projectile temp = singlePlayerProjectiles.get(i);
			double coordX=temp.getProjectilePic().getX()+temp.getProjectilePic().getWidth()+1;
			double coordY=temp.getProjectilePic().getY()+(temp.getProjectilePic().getHeight()/2);
			if(getElementAt(coordX,coordY) instanceof GImage) {
				//for(GImage temp2 : enemyImages) {
					for(int k=0;k<enemyImages.size();k++) {
						
					
					if(enemyImages.get(k)==getElementAt(coordX,coordY)) {
						remove(enemyImages.get(k));
						remove(enemyRectangles.get(k));
						enemyImages.remove(k);
						enemyRectangles.remove(k);
						
						//enemyImages.remove(getElementAt(coordX,coordY));
						//enemyRectangles.remove(getElementAt(coordX,coordY));

						remove(temp.getProjectilePic());
						animateHit(coordX, coordY);
						singlePlayerProjectiles.remove(temp);
						enemyHitCount++;
						calculateScore();
						displayScoreInGame();
					
						System.out.println(enemyImages.size());
					}
					if(endOfScreenUser==getElementAt(coordX,coordY)) {
						remove(temp.getProjectilePic());
						singlePlayerProjectiles.remove(temp);
					}
				}
				if(getElementAt(coordX,coordY)==Shield1) {
					//remove(Shield1);
					remove(temp.getProjectilePic());
					singlePlayerProjectiles.remove(temp);
				}
				else if(getElementAt(coordX,coordY)==Shield2) {
					//remove(Shield2);
					remove(temp.getProjectilePic());
					singlePlayerProjectiles.remove(temp);
				}
				//remove(getElementAt(coordX, coordY));
				//singlePlayerProjectiles.remove(temp);
			}
		}
	}
	public void enemyObjHit() {
		for(int i = 0; i < enemyProjectiles.size(); i++) {
			Projectile temp = enemyProjectiles.get(i);
			double coordX=temp.getEnemyProjectilePic().getX()+temp.getEnemyProjectilePic().getWidth()+1;
			double coordY=temp.getEnemyProjectilePic().getY()+(temp.getEnemyProjectilePic().getHeight()/2);
			if(getElementAt(coordX,coordY) instanceof GImage) {
				//for(GImage temp2 : enemyImages) {
					//for(int k=0;k<enemyImages.size();k++) {
						
					
					if(singlePlayerTank==getElementAt(coordX,coordY)) {
						decreaseHealth();


						remove(temp.getEnemyProjectilePic());
						animateHit(coordX, coordY);
						enemyProjectiles.remove(temp);
					}
					if(endOfScreenEnemy==getElementAt(coordX,coordY)) {
						remove(temp.getEnemyProjectilePic());
						enemyProjectiles.remove(temp);
					}
				}
				if(getElementAt(coordX,coordY)==Shield1) {
					//remove(Shield1);
					remove(temp.getEnemyProjectilePic());
					animateHit(coordX, coordY);
					enemyProjectiles.remove(temp);
				}
				else if(getElementAt(coordX,coordY)==Shield2) {
					//remove(Shield2);
					remove(temp.getEnemyProjectilePic());
					animateHit(coordX, coordY);
					enemyProjectiles.remove(temp);
				}
				//remove(getElementAt(coordX, coordY));
				//singlePlayerProjectiles.remove(temp);
			}
		}
	//}
	
	
	public void animateHit(double coordX, double coordY) {
		if(explodeNumber == 0) {
			explode1.setLocation(coordX - 50, coordY - 70);
			add(explode1);
			explodeNumber++;
			System.out.println("explode 1");
			return;
		}
		if(explodeNumber == 1) {
			explode2.setLocation(coordX - 50, coordY - 70);
			add(explode2);
			explodeNumber++;
			System.out.println("explode 2");
			return;
		}
		if(explodeNumber == 2) {
			explode3.setLocation(coordX - 50, coordY - 70);
			add(explode3);
			explodeNumber = 0;
			System.out.println("explode 3");
			return;
		}

	}
	
	public void deleteEnemyExplosion() {
		remove(explode1);
		remove(explode2);
		remove(explode3);
	}
	
	public void deleteUserFirePic() {
		remove(userFirePic);
	}
	
	public void deleteEnemyFirePic() {
		for (int i = 0; i < enemyMuzzleImages.size(); i++) {
			GImage temp = enemyMuzzleImages.get(i);
			remove(temp);
			enemyMuzzleImages.remove(temp);
		}
	}
	
	public void calculateScore() { //calculates the score of the user
		//we have to put if statemetns showing the increased score if you killed different types of enemies.
		int currentScore = singlePlayerScores.get(gameNumber);
		singlePlayerScores.set(gameNumber, 10 + currentScore);
		System.out.println(singlePlayerScores.get(gameNumber));
	}
	
	public void displayScoreInGame() { //displays the score board in game
		singlePlayerScoreLabel.setLabel("Score: " + singlePlayerScores.get(gameNumber));
		add(singlePlayerScoreLabel);
		
	}
	
	public void displayTimerInGame() { //displays the time left in game
		timerLabel.setLabel("Time Left: " + gameTime);
		add(timerLabel);
	}
	
	public void decreaseHealth() { //Calculates the health of the user
		singlePlayerTankHealth--;
	}
	
	public void displayHealthInGame() { //displays the health in game.
		singlePlayerHealthLabel.setLabel("Health: " + singlePlayerTankHealth);
		add(singlePlayerHealthLabel);
	}
	
	public void pasteEnemies() {
		for(GRect temp : enemyRectangles) {
			//add(temp);
		}
	}
	public void pasteShields() {
		for(GRect temp : playerShields) {
			//add(temp);
			
		}
		add(Shield1);
		add(Shield2);
	}
	public void pasteEnemyImages() {
		for(GImage temp : enemyImages) {
			add(temp);
		}
	}
}