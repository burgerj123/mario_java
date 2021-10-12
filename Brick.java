class Brick
{
	int x,y,w,h;
	Brick(int a,int b,int c,int d)
	{
		x = a;
		y = b;
		w = c;
		h = d;		
	}
	
	Json marshal()
	{
        Json ob = Json.newObject();
		 
        ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
        return ob;
	}
	
	Brick(Json ob)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
	}
}