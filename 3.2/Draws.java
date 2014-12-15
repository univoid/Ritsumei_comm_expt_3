import java.applet.Applet;
import java.awt.*;

public class Draws extends Applet
{
	Image image;

	public void init()
	{
		image = getImage(getDocumentBase(), "sample.jpg");
	}

	public void paint(Graphics g)
	{
		g.drawImage(image, 1, 80, this);
		g.setColor(Color.RED);
		g.drawLine(10, 10, 160, 170);
		g.setColor(Color.GREEN);
		g.drawOval(50, 70, 90, 90);
		g.setColor(Color.BLUE);
		g.drawRect(130, 110, 40, 60);
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Impact", Font.BOLD, 25));
		g.drawString("demonstration", 20, 30);
	}
}
