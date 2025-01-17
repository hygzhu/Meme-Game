import java.util.Random;


public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		
		scoreKeep++;
		if(scoreKeep >= 200){
			scoreKeep =0;
			hud.setLevel(hud.getLevel()+1);
			
			if(hud.getLevel()%2 == 0){
			handler.addObject(new DogeEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.DogeEnemy, handler, hud));//Spawns every second level
			handler.addObject(new YellowEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.YellowEnemy, handler, hud));
			handler.addObject(new YellowEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.YellowEnemy, handler, hud));
			}else{
			handler.addObject(new YellowEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.YellowEnemy, handler, hud));//spawns when no other spawning 
			}
		
			
			
		}
		
	}
}
