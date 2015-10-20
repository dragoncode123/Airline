import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import javax.imageio.ImageIO;

/*
 * 
 * import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
 */
public class Login_Panel extends JPanel {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BufferedImage image;
	
	public Login_Panel(){
		init();
	}
	public void init()
	{
		
		try {
			image = ImageIO.read(new File("D:/test Project/Airline/airplane3.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void paintComponent(Graphics g){
		g.drawImage(image, 0,0,getWidth(),getHeight(),null);
//		g.setColor(Color.RED);
//		g.fillRect(10, 10, 100, 50);
	}
}
