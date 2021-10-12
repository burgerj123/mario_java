import java.io.File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

class Mario 
{
	int x, y, px, py;
	final int w = 60;
	final int h = 95;
	int marioLoc = 200;
	double vert_vel;
	int counter = 0;
	//static BufferedImage[] mario_images = null;
	int imageNum;
	//String[] mario_string = new String[] {"mario1.png", "mario2.png", "mario3.png", "mario4.png", "mario5.png"};
	
	public Mario()
	{
		x = 200;
		y = 0;
		vert_vel = 0;
		imageNum = 4;
		px = x;
		py = y;
		
//		if (mario_images == null) {
//			for (int i = 0; i < 5; i++)
//			{
//				try
//				{
//					mario_images[i] = ImageIO.read(new File(mario_string[i]));
//				} catch(Exception e) {
//					e.printStackTrace(System.err);
//					System.exit(1);
//				}
//			}
//		}
	} 
	
	void savePrev()
	{
		px = x;
		py = y;
	}
	
	void update()
	{
		if (vert_vel == 0)
			counter = 0;
		else
			counter++;
		
		vert_vel += 10.2;
		y += vert_vel;
		
		if(y > 500)
		{
			vert_vel = 0.0;
			y = 500; // snap back to the ground
		}
	}
}
