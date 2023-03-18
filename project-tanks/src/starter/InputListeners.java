package starter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class InputListeners extends GraphicsProgram implements ActionListener,KeyListener{
	
	 private GImage tank;
	    private  int tankXcord = 20;
	    private  int tankYcord = 800 ;
	    private final int SCREEN_WIDTH = 1600;
	    private final int SCREEN_HEIGHT = 900;
	    private int arrIndex = 0;
	    private int speedX = 0; 
	    private int speedY = 0;
	    private int speedVal = 5;
	    private int x = 0;
	    private int y = 0;
	    // below line is in case if we want to add different images to make it look animated
	    private String[] pics = {"blue tank.png"}; 
	    private Timer t = new Timer(25, this);
	    
    public void run(){
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        tank = new GImage("blue tank.png");
        x=tankXcord;
        y=tankYcord;
        add(tank,x,y);
        t.start();
        addKeyListeners();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addMouseListeners();
    }
    public void actionPerformed(ActionEvent e) {
    	if(x<0) {
    		speedX=0;
    		x=0;
    	}
    	if(x>SCREEN_WIDTH-90) {
    		speedX=0;
    		x=SCREEN_WIDTH-90;
    	}
    	if(y<0) {
    		speedY=0;
    		y=0;
    	}
    	if(y>SCREEN_HEIGHT-90) {
    		speedY=0;
    		y=SCREEN_HEIGHT-90;
    	}
    	x+=speedX;
    	y+=speedY;
    	add(tank,x,y);
    }


    public void keyPressed(KeyEvent e){ 
        char moveKey = e.getKeyChar();
        int moveKeyCode = e.getKeyCode();
        if(moveKey == 'd' || moveKeyCode==KeyEvent.VK_RIGHT){
        	speedX=speedVal;
        	speedY=0;
        		tank.move(speedX,0);
                tank.setImage(pics[arrIndex]);
                arrIndex++;
                if(arrIndex>=pics.length){
                    arrIndex = 0;
                }
            }
        else if(moveKey == 'a'|| moveKeyCode==KeyEvent.VK_LEFT) {
        	speedX=-speedVal;
        	speedY=0;
        	tank.move(-speedX,0);
               tank.setImage(pics[arrIndex]);
               arrIndex++;
               if(arrIndex>=pics.length){
                   arrIndex = 0;
               }
        }
        else if(moveKey == 'w'|| moveKeyCode==KeyEvent.VK_UP) {
        	speedX=0;
        	speedY=-speedVal;
        	tank.move(0,-speedY);
            tank.setImage(pics[arrIndex]);
            arrIndex++;
            if(arrIndex>=pics.length){
                arrIndex = 0;
            }
     }
        else if(moveKey == 's'|| moveKeyCode==KeyEvent.VK_DOWN) {
        	speedX=0;
        	speedY=speedVal;
        	tank.move(0,speedY);
             tank.setImage(pics[arrIndex]);
             arrIndex++;
             if(arrIndex>=pics.length){
                 arrIndex = 0;
             }
      }
        
    }
    public void keyReleased(KeyEvent e) {
    	speedX=0;
    	speedY=0;
    }
}