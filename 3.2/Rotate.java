import java.applet.Applet;
import java.awt.*;

public class Rotate extends Applet
{
	Image image;
    float degree;
	public void init()
	{
        degree = 45;
	}
/*
	public void paint(Graphics g)
	{
        Graphics2D g2 = (Graphics2D)g;
        g2.rotate(Math.toRadians(45));
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
    */
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        g.setColor(Color.RED);
		g.drawLine(10, 10, 160, 170);
		g.setColor(Color.GREEN);
		g.drawOval(50, 70, 90, 90);
		g.setColor(Color.BLUE);
		g.drawRect(130, 110, 40, 60);

        g2d.rotate(Math.toRadians(degree), 200, 400);
        g2d.setColor(Color.RED);
		g2d.drawLine(10, 10, 160, 170);
		g2d.setColor(Color.GREEN);
		g2d.drawOval(50, 70, 90, 90);
		g2d.setColor(Color.BLUE);
		g2d.drawRect(130, 110, 40, 60);

    }

}
