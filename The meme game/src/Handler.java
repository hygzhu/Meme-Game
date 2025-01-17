import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i = 0; i<object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
			
			if(tempObject.getID()== ID.Removed){
				removeObject(tempObject);
			}//removed objects needed to be removed
		}
		
	
		
		
	}
	public void render(Graphics g){
		for(int i = 0; i<object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject (GameObject object){
		this.object.add(object);
	}
	public void removeObject (GameObject object){
		this.object.remove(object);
	}
	public void clearEnemies() {
		for(int i = 0; i<object.size(); i++){
			GameObject tempObject = object.get(i);

			if(tempObject.getID()== ID.Player){
				object.clear();
				if(Game.gameState != Game.STATE.End){
				addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));//adds player only if game not over
				}
			}//clears all objects and replaces player
			
		}
		
	}
	
}
