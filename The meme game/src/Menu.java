import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu (Game game, Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;

		
	}
	
	public void mousePressed(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
		
		//play button for main menu
		if(game.gameState == game.gameState.Menu){
			if(mouseOver(mx, my, 510, 250, 200, 64)){
			game.gameState = game.gameState.Game;
			handler.addObject(new Player(game.WIDTH/2-32,game.HEIGHT/2-32,ID.Player, handler));//adds player object
			handler.addObject(new YellowEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.YellowEnemy,handler, hud));//adds basic enemy
			}
		}
		//help button for main menu
		if(game.gameState == game.gameState.Menu){
			if(mouseOver(mx, my, 510, 350, 200, 64)){
			game.gameState = game.gameState.Help;
			}
		}
		//back button for help screen
		if(game.gameState == game.gameState.Help){
			if(mouseOver(mx, my, 510, 250, 200, 64)){
				game.gameState = game.gameState.Menu;
			}
		}
	
		
		//quit button for main menu
		if(game.gameState == game.gameState.Menu){
			if(mouseOver(mx, my,510, 450, 200, 64)){
			System.exit(1);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
		
		//back button for end screen
		if(game.gameState == game.gameState.End){
			if(mouseOver(mx, my,510, 350, 200, 64)){
			Audio.run("MemeCircus");	
			hud.setScore(0);
			hud.setLevel(1);	
			game.gameState = game.gameState.Menu;
			}
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx> x && mx < x+ width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		
		if(game.gameState == game.gameState.Menu){
			
			Font fnt = new Font("Comic Sans MS",1,50);
			Font fnt2 = new Font("Comic Sans MS",1,20);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("D4nk maym3ys!!!111!!xd",340,70);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(510, 250, 200, 64);
			g.drawString("JUST DO IT",530, 290);
			
			g.setColor(Color.white);
			g.drawRect(510, 350, 200, 64);
			g.drawString("PLS TO HELP",540, 390);
			
			g.setColor(Color.white);
			g.drawRect(510, 450, 200, 64);
			g.drawString("CYA NERD",570, 490);
			
			
		}else if(game.gameState == game.gameState.Help){
			Font fnt = new Font("Comic Sans MS",1,50);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("I AM NOT GUD WITH COMPTR PLS TO HELP",70,70);
			Font fnt2 = new Font("Comic Sans MS",1,30);
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(510, 250, 200, 64);
			g.drawString("Back",570, 290);
			
		}else if(game.gameState == game.gameState.End){
			
			Audio.run("Sad");
			
			Font fnt = new Font("Comic Sans MS",1,50);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Rip in peppa pig",430,70);
			Font fnt2 = new Font("Comic Sans MS",1,20);
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(510, 350, 200, 64);
			g.drawString("back to da choppa",520, 390);
			Font fnt3 = new Font("Comic Sans MS",1,30);
			g.setFont(fnt3);
			g.drawString("U got "+hud.getScore()+ " k3ks", 500, 200);
		}
		
		
	}
}
