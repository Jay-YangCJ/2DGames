import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 * @author ChangJie Yang
 *
 */

@SuppressWarnings("serial")
public class SnakePanel extends JPanel implements Runnable, KeyListener {
	
	private static final int WIDTH = 800, HEIGHT = 600, SIDE = 10;
	private Thread thread;
	private boolean running = false;
	
	private ArrayList<Point> snake;
	private int direction;
	private Point fruit;
	private Random coords;
	
	private boolean gameStart = false, pause, gameOver;
	private int pCount, score, length, sleepTime;
	private Font font, font1;
	private String gameString1, gameString2;
	
	SnakePanel(){
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		init();
		start();
	}
	
	public void init(){
		
		snake = new ArrayList<Point>();
		direction = Direction.NODIRECTION;
		coords = new Random();
		pCount = 0;
		score = 0;
		length = 3;
		sleepTime = 50;
		pause = false; 
		gameOver = false;
		fruit = new Point(coords.nextInt(WIDTH/SIDE),coords.nextInt(HEIGHT/SIDE));
		snake.add(new Point (4,3));
		snake.add(new Point (3,3));
		snake.add(new Point (2,3));
		font = new Font("Tahoma", Font.BOLD, 30);
		font1 = new Font("Arial", Font.BOLD, 15);
	}
	
	@Override
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(!gameStart){
			gameString1 = "Snake Game";
			gameString2 = "Press Enter to Start Game";
			g.setFont(font);
			g.setColor(Color.GREEN);
			g.drawString(gameString1, (WIDTH-getFontMetrics(font).stringWidth(gameString1))/2, HEIGHT/3);
			g.setColor(Color.WHITE);
			g.drawString(gameString2, (WIDTH-getFontMetrics(font).stringWidth(gameString2))/2, HEIGHT/2);
		}
		else{
			g.setFont(font1);
			g.setColor(Color.WHITE);
			String scoreString = "Scores: " + score + "   Lengths: " +length;
			g.drawString(scoreString, (WIDTH-getFontMetrics(font1).stringWidth(scoreString))/2, 15);
			g.setFont(font);
			if(pause && !gameOver){
				gameString1 = "Paused!";
				gameString2 = "Press Space to Continue Game";
				g.drawString(gameString1, (WIDTH-getFontMetrics(font).stringWidth(gameString1))/2, HEIGHT/3);
				g.drawString(gameString2, (WIDTH-getFontMetrics(font).stringWidth(gameString2))/2, HEIGHT/2);
			}
			if(gameOver){
				gameString1 = "Game Over";
				gameString2 = "Press Enter to Restart Game";
				g.drawString(gameString1, (WIDTH-getFontMetrics(font).stringWidth(gameString1))/2, HEIGHT/3);
				g.drawString(gameString2, (WIDTH-getFontMetrics(font).stringWidth(gameString2))/2, HEIGHT/2);
			}
		
			drawSnake(g);
			drawFruit(g);
		}
	}	
	
	//update snake movement and fruit location
	public void tick(){
		
		if(gameStart && !pause && !gameOver){
			
			Point head = (Point) snake.get(0).clone();
			
			switch(direction){
		
			case Direction.NORTH:
				head.y--;
				break;
			case Direction.SOUTH:
				head.y++;
				break;
			case Direction.WEST:
				head.x--;
				break;
			case Direction.EAST:
				head.x++;
				break;	
			}
			snake.add(0,head);
			snake.remove(snake.size()-1);

			if(head.equals(fruit)){
				snake.add(head);
				fruit.setLocation(new Point(coords.nextInt(WIDTH/SIDE), coords.nextInt(HEIGHT/SIDE)));
				score+=10;
				length++;
			}
			else
				check(head);	
		}
	}
	
	public void drawSnake(Graphics g){
		
		g.setColor(Color.GREEN);
		for(Point s : snake)
			g.fillRect(s.x*SIDE+1, s.y*SIDE+1, SIDE-2, SIDE-2);
	}
	
	public void drawFruit(Graphics g){
		
		g.setColor(Color.RED);
		g.fillOval(fruit.x*SIDE, fruit.y*SIDE, SIDE, SIDE);
	}
	
	//out of bounds or self-collision
	public void check(Point p){
		
		if(p.x < 0 || p.x > WIDTH/SIDE-1 || p.y < 0 || p.y > HEIGHT/SIDE-1)
			gameOver = true;
		
		for(int i = 1; i < snake.size(); i++){
			if(p.equals(snake.get(i)))
				gameOver = true;
		}
	}
	
	public void start(){
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		
		while(running){
			tick();
			repaint();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()){
		
		case KeyEvent.VK_ENTER:
			gameStart = true;
			pause = false;
			pCount = 0;
			if(gameOver)
				init();
			if(direction == 0) 
				direction = Direction.EAST;
			break;
		case KeyEvent.VK_SPACE:
			if(++pCount%2 == 0) pause = false;
			else pause = true ;
			break;
		case KeyEvent.VK_UP:
			if(direction != Direction.SOUTH)
				direction = Direction.NORTH;
			break;
		case KeyEvent.VK_DOWN:
			if(direction != Direction.NORTH)
				direction = Direction.SOUTH;
			break;
		case KeyEvent.VK_LEFT:
			if(direction != Direction.EAST)
				direction = Direction.WEST;
			break;
		case KeyEvent.VK_RIGHT:
			if(direction != Direction.WEST)
				direction = Direction.EAST;
			break; 
		case KeyEvent.VK_W:
			if(sleepTime > 10)
				sleepTime-=10;
			break;
		case KeyEvent.VK_S:
			if(sleepTime < 300)
				sleepTime+=10;
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	private class Direction{ 
		private static final int NODIRECTION = 0, NORTH = 1, SOUTH = 2, WEST = 3, EAST = 4;
	}
}