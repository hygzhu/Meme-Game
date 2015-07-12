import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;



public class Projectile extends GameObject{

	private float mouseX;
	private  float mouseY;
	private Handler handler;
	private Player player;

	
	public Projectile(float x, float y, ID id, Handler handler, Player player) {
		super(x, y, id);
		this.handler = handler;
		this.player = player;

		mouseX = MouseInfo.getPointerInfo().getLocation().x;
		mouseY = MouseInfo.getPointerInfo().getLocation().y;

	float deltaX = player.getX()- mouseX;
	float deltaY = player.getY()- mouseY;
	float magnitude = (float) Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	
	velX = deltaX/magnitude;
	velY = deltaY/magnitude;
		
		
		
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,5,5);
	}//Collision Boundary

	public void tick() {
		
		x-= velX*5;
		y-= velY*5;
		
		if(y <= 0 || y >= Game.HEIGHT) this.setID(ID.Removed);
		if(x <= 0 || x >= Game.WIDTH) this.setID(ID.Removed);
		
	
	}
	


	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y,  5, 5);
		
		
	}

}
