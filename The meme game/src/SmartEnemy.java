import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;

	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
		}
		
		velX = 3;
		velY = 3;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,16,16);
	}//Collision Boundary

	public void tick() {

		x+= velX;
		y+= velY;
		
		float diffX = x - player.getX()-8;
		float diffY = y - player.getY()-8;
		//distance between player sprite
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		velX = (float) (((-1.0/distance))*diffX);
		velY = (float) (((-1.0/distance))*diffY);
		
		//if(y <= 0 || y >= Game.HEIGHT -48) velY *= -1;
	    //if(x <= 0 || x >= Game.WIDTH -16) velX *= -1;
		//prevents from exiting screen
	}


	public void render(Graphics g) {
		g.setColor(Color.green);
		g.drawRect((int)x,(int) y,  16, 16);
		
	}

}
