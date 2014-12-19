/* sample 3.3 */
import java.awt.*;
import java.awt.image.*;
import java.applet.Applet;


public class Histgram extends Applet
{
	int WIDTH, HEIGHT;
	Image imageInput, imageOutput;
	int[] pixelInput;
	private int[] canvas;	//histgram
	public void loadImage(String s)
	{
		MediaTracker mt;

		imageInput = getImage(getDocumentBase(), s);
		mt = new MediaTracker(this);
		mt.addImage(imageInput,0);
		try
		{
			mt.waitForID(0); // �摜���ǂݍ��܂��܂ő҂�
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}


		HEIGHT = imageInput.getHeight(this);
		WIDTH  = imageInput.getWidth(this);

		pixelInput  = new int[WIDTH * HEIGHT];

		PixelGrabber pg = new PixelGrabber(imageInput, 0, 0, WIDTH, HEIGHT, pixelInput, 0, WIDTH);
		try
		{
			pg.grabPixels();
		}
			catch (InterruptedException e)
		{
			System.out.println(e);
		}
	}

	public int RGBtoGray(int rgb_value)
	{
		int r, g, b;

		r = (rgb_value & 0x00FF0000) / 0x00010000;
		g = (rgb_value & 0x0000FF00) / 0x00000100;
		b = (rgb_value & 0x000000FF);

		return (int)(r * 0.299 + g * 0.587 + b * 0.114);
	}

	//3.7 doing
	public void hist ()
	{
		int value, x, y, max = 0;
		int[] count = new int[255];
		for (int i = 0; i<255; i++)
		{
			count[i] = 0;
		}
		for (y = 0; y < HEIGHT; y++)
		{
			for (x = 0; x < WIDTH; x++)
			{
				value = RGBtoGray(pixelInput[y*WIDTH + x]);
				count[value]++;
				if (count[value] > max) max = count[value] + 1;
			}
		}
		canvas = new int[255 * max];
		for (x = 0; x < 255; x ++){
			for (y = count[x]; y>max-count[x]; y--)
			{
                try{
				canvas[x + y * 255] = 0xFF000000;
                }catch(Exception e) {
                    System.out.println(max);
                    System.out.println(count[x]);
                }
			}
		}
        imageOutput = createImage(new MemoryImageSource(255, max, canvas, 0, 255));
	}
    public void init()
    {
        loadImage("image.GIF");
        hist();
        repaint();
    }
    public void paint(Graphics g)
    {
        g.drawImage(imageInput, 30, 30, this);
        g.drawImage(imageOutput, 30, 50+HEIGHT, this);
    }
}


