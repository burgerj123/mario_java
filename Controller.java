import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean editor = false;
	Controller(Model m)
	{
		model = m;
	}

	public void actionPerformed(ActionEvent e)
	{
	}
	
	void setView(View v)
	{
		view = v;
	}
	
	public void mousePressed(MouseEvent e)
	{
		if (editor == true)
			model.setDestination(e.getX()+model.mario.x - model.mario.marioLoc, e.getY());
	}

	public void mouseReleased(MouseEvent e)
	{	
		if (editor == true)
			model.createBrick(e.getX()+model.mario.x - model.mario.marioLoc, e.getY());
		//System.out.print(e.getX() + ", " + e.getY());
	}
	
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {
		if(e.getY() < 100)
		{
			System.out.println("break here");
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		//model.mario.savePrev();
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:{
				keyRight = true; 
				//model.cameraPos += 6;
				//model.mario.x += 6;
				//view.marioAnimation = (view.marioAnimation + 1) % 5;
				break;
			}
			case KeyEvent.VK_LEFT:{
				keyLeft = true;
				//model.cameraPos -= 6;
				//model.mario.x -= 6;
				//view.marioAnimation = (view.marioAnimation + 1) % 5;
				break;
			}
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_SPACE: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: 
				keyRight = false; 
				break;
			case KeyEvent.VK_LEFT: 
				keyLeft = false; 
				break;
			case KeyEvent.VK_UP: 
				keyUp = false; 
				break;
			case KeyEvent.VK_SPACE: 
				keyUp = false; 
				break;
			case KeyEvent.VK_DOWN: 
				keyDown = false; 
				break;
			case KeyEvent.VK_ESCAPE:
				System.out.println("Exiting now...");
				System.exit(0);
				break;
		}
		char c = e.getKeyChar();
		
		if (c == 'e' || c == 'E')
			editor = !editor;
		if (editor == true) {
			if (c == 's' || c == 'S')
				model.marshal().save("map.json");
			if (c == 'l' || c == 'L')
				model.unmarshall().load("map.json");
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.mario.savePrev();
		if (keyRight) {
			model.mario.x += 6;
			view.marioAnimation = (view.marioAnimation + 1) % 5;
		}
		if (keyLeft) {
			model.mario.x -= 6;
			view.marioAnimation = (view.marioAnimation + 1) % 5;
		}
		
		if (keyUp && model.mario.counter < 5)
			model.mario.vert_vel -= 20;
	}
}
