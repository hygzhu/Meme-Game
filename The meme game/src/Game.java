import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

//Created by Haoyang Zhu
//Summer stuff

public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 1280, HEIGHT = WIDTH/12*9;//16:9 ratio
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;

	
	public enum STATE {
		Menu, Game, Help, End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game(){
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Computational Memes for Bored Teens", this);

		
		
		spawn = new Spawn(handler, hud);
	
		if(gameState == STATE.Menu){
			Audio.initialize();
			Audio.run("MemeCircus");//Plays Audio
			LoadImage.initialize();
		}
		
	}//constructor
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();//No need to click game when opens
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta>=1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				System.out.println("FPS: "+ frames);
				frames = 0;
			}
			
		}
		stop();	
	}//game loop 
	
	private void tick(){
		handler.tick();
		
		if(gameState == STATE.Game){
			hud.tick();
			spawn.tick();
		}else if(gameState == STATE.Menu||gameState == STATE.End){
			menu.tick();
		}
		
		if(HUD.HEALTH <= 0){
			HUD.HEALTH = 100;
			gameState = STATE.End;
			handler.clearEnemies();
		}
		
	
		
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);//creates 3 buffers in the game
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);//Handler renders before HUD
		
		if(gameState == STATE.Game){
			hud.render(g);
		}else if(gameState == STATE.Menu||gameState == STATE.Help||gameState == STATE.End){
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}//Render graphics
	
	public static float clamp(float var, float min, float max){
		
		if (var >= max)
			return var= max;
		else if(var<=min)
			return var = min;
		else
			return var;
	}//Allows restrictions
	
	public static void main (String args[]){
		
		new Game();
	}

}
