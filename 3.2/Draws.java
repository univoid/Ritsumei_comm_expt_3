import java.applet.Applet;
import java.awt.*;

public class Draws extends Applet
{
	Image body;
    Image arm1;
    Image arm2;
    Image arm3;
    Image arm4;
    Image arm5;
    Image leg1;
    Image leg2;
    Image leg3;
    Image leg4;
    Image leg5;


	public void init()
	{
		body = getImage(getDocumentBase(), "robot/body.gif");
        arm1 = getImage(getDocumentBase(), "robot/arm1.gif");
        arm2 = getImage(getDocumentBase(), "robot/arm2.gif");
        arm3 = getImage(getDocumentBase(), "robot/arm3.gif");
        arm4 = getImage(getDocumentBase(), "robot/arm4.gif");
        arm5 = getImage(getDocumentBase(), "robot/arm5.gif");
        leg1 = getImage(getDocumentBase(), "robot/leg1.gif");
        leg2 = getImage(getDocumentBase(), "robot/leg2.gif");
		leg3 = getImage(getDocumentBase(), "robot/leg3.gif");
        leg4 = getImage(getDocumentBase(), "robot/leg4.gif");
        leg5 = getImage(getDocumentBase(), "robot/leg5.gif");



	}

	public void paint(Graphics g)
	{
		g.drawImage(body, 100, 10, this);
        g.drawImage(arm1, 100, 150, this);
        g.drawImage(leg1, 75, 267, this);
    }
}
