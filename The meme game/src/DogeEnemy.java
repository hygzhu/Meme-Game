import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class DogeEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	private HUD hud;

	public DogeEnemy(float x, float y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;

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
		
		collision();
	}
	private void collision(){
		for (int i = 0; i< handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Projectile){ //tempObject is now projectile
				if(getBounds().intersects(tempObject.getBounds())){

					hud.setScore(hud.getScore()+100);
					this.setID(ID.Removed);

				}
				//collision code for porjectiles
			}
			
		}
		
	}


	public void render(Graphics g) {
		g.setColor(Color.green);
		g.drawRect((int)x,(int) y,  16, 16);
		g.drawImage(LoadImage.doge, (int)x -10 , (int)y-10, null);
		
	}

}
