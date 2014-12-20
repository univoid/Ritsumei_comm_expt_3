import java.applet.Applet;
import java.awt.*;
import java.lang.Thread;
public class Draws extends Applet
{
    Image body;
    Image arm[] = new Image[5];
    Image leg[] = new Image[5];
    //Thread th = currentThread(); 

	public void init()
	{
	body = getImage(getDocumentBase(), "robot/body.gif");
        arm[0] = getImage(getDocumentBase(), "robot/arm1.gif");
        arm[1] = getImage(getDocumentBase(), "robot/arm2.gif");
        arm[2] = getImage(getDocumentBase(), "robot/arm3.gif");
        arm[3] = getImage(getDocumentBase(), "robot/arm4.gif");
        arm[4] = getImage(getDocumentBase(), "robot/arm5.gif");
        leg[0] = getImage(getDocumentBase(), "robot/leg1.gif");
        leg[1] = getImage(getDocumentBase(), "robot/leg2.gif");
	leg[2] = getImage(getDocumentBase(), "robot/leg3.gif");
        leg[3] = getImage(getDocumentBase(), "robot/leg4.gif");
        leg[4] = getImage(getDocumentBase(), "robot/leg5.gif");
	}

	public void paint(Graphics g)
	{
	    int b_x = 100, b_y = 0;
	    int a_x = 100, a_y = 150;
	    int l_x = 75, l_y = 267;
	    int now = 0, mark = -1;
	    while(b_x < 800) {
		g.clearRect(0,0,2000,500);
		g.drawImage(body, b_x, b_y, this);
        	g.drawImage(arm[now], a_x, a_y, this);
        	g.drawImage(leg[now], l_x, l_y, this);
		b_x += 30;
		a_x += 35;
		l_x += 35;
		if ((now == 4)||(now == 0)) mark = -mark;
		if (mark > 0) now++;
		else now--;
		try {
		    Thread.currentThread().sleep(500);
		}catch(Exception e) {
		    
		}
	    }
    }
}
