/*
Name: Jackson Burger
Assignment: Assignment 2
Description: Turtle Graphics
*/
import java.util.ArrayList;
import java.util.Iterator;
class Model
{
	ArrayList<Brick> bricks;
	int dest_x;
	int dest_y;
	int release_x;
	int release_y;
	int cameraPos = 0;
	static Mario mario;

	Model()
	{
		bricks = new ArrayList<Brick>();
		mario = new Mario();
	}
	
	public void update()
	{
		mario.update();
		checkCollisions();
	}

	void checkCollisions()
	{		
		Iterator<Brick> brickIterator = bricks.iterator();
		while (brickIterator.hasNext())
		{
			Brick b = brickIterator.next();		
			if (marioIsColliding(b))
				handleCollision(b);
		}
	}
	
	boolean marioIsColliding(Brick b)
	{
		if (mario.x + mario.w < b.x)
			return false;
		if (mario.x > b.x + b.w)
			return false;
		if (mario.y + mario.h < b.y)
			return false;
		if (mario.y > b.y + b.h)
			return false;
		return true;
	}
	
	void handleCollision(Brick b)
	{
		if (mario.x <= b.x + b.w && mario.px >= b.x + b.w)
			mario.x = mario.px;
		if (mario.x + mario.w >= b.x && mario.px + mario.w <= b.x)
			mario.x = mario.px;
		if (mario.y < b.y + b.h && mario.py >= b.y + b.h) {
			mario.y = b.y + b.h;
			mario.vert_vel += 8;
		}
		if (mario.y + mario.h > b.y && mario.py + mario.h <= b.y) {
			mario.y = b.y - mario.h;
			mario.counter = 0;
			mario.vert_vel = 0;
		}
	}
	
	public void setDestination(int x, int y)
	{
		this.dest_x = x;
		this.dest_y = y;
	}
	
	public void createBrick(int x, int y)
	{
		int w = Math.abs(dest_x - x);
		int h = Math.abs(dest_y - y);
		
		if (x < dest_x && y < dest_y){
			Brick b = new Brick(x,y,w,h);
			bricks.add(b);
		}
		else if (x < dest_x){
			Brick b = new Brick(x,dest_y,w,h);
			bricks.add(b);
		}
		else if (y < dest_y){
			Brick b = new Brick(dest_x,y,w,h);
			bricks.add(b);
		}
		else{
			Brick b = new Brick(dest_x,dest_y,w,h);
			bricks.add(b);
		}
	}
	
	Json marshal()
     {
         Json ob = Json.newObject();
         Json tmpList = Json.newList();
         ob.add("bricks", tmpList);
         for(int i = 0; i < bricks.size(); i++)
             tmpList.add(bricks.get(i).marshal());
         return ob;
     }
	 
	 Json unmarshall()
	 {
		 bricks.clear();
		 
		 Json ob = Json.load("map.json");
		 
		 bricks = new ArrayList<Brick>();
		 Json tmpList = ob.get("bricks");
		 for (int i = 0; i < tmpList.size(); i++)
			 bricks.add(new Brick(tmpList.get(i)));
		 return ob;
	 }
}