/*
Name: Jackson Burger
Assignment: Assignment 2
Description: Turtle Graphics
*/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	BufferedImage mario_images[];
	String[] mario_string = new String[] {"mario1.png", "mario2.png", "mario3.png", "mario4.png", "mario5.png"};
	BufferedImage brick_image;
	BufferedImage background_image;
	int initial = 0;
	int marioAnimation;
	
	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		mario_images = new BufferedImage[5];
		for (int i = 0; i < 5; i++)
		{
			try
			{
				this.mario_images[i] = ImageIO.read(new File(mario_string[i]));
			} catch(Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
		
		try
		{
			this.brick_image = ImageIO.read(new File("brick.png"));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		
		try
		{
			this.background_image = ImageIO.read(new File("background.png"));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		
		
		
	}
	
	
	public void paintComponent(Graphics g)
	{
		//Draws background
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
		g.drawImage(background_image, initial - (model.mario.x) /3, initial, 1800, 625, null);
		
		g.setColor(Color.gray);
		g.drawLine(0, 596, 2000, 596);
		
		//Draws bricks
		for(int i = 0; i < model.bricks.size(); i++)
		{
			g.setColor(new Color(102,51,0));
			Brick b = model.bricks.get(i);
			g.drawImage(brick_image,b.x - model.mario.x + model.mario.marioLoc, b.y, b.w, b.h, null);
		}
		
		//Draws mario
		g.drawImage(mario_images[marioAnimation], 200, model.mario.y, null);
		
	}
}
