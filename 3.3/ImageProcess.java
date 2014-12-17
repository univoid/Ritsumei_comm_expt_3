/* sample 3.3 */
import java.awt.*;
import java.awt.image.*;
import java.applet.Applet;


public class ImageProcess extends Applet
{
	int WIDTH, HEIGHT;
	Image imageInput,imageOutput1, imageOutput2;
	int[] pixelInput, pixelOutput1, pixelOutput2;

	public void loadImage(String s)
	{
		MediaTracker mt;

		imageInput = getImage(getDocumentBase(), s);
		mt = new MediaTracker(this);
		mt.addImage(imageInput,0);	
		try 
		{ 
			mt.waitForID(0); // âÊëúÇ™ì«Ç›çûÇ‹ÇÍÇÈÇ‹Ç≈ë“Ç¬
		} 
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
		

		HEIGHT = imageInput.getHeight(this);
		WIDTH  = imageInput.getWidth(this);

		pixelInput  = new int[WIDTH * HEIGHT];
		pixelOutput1 = new int[WIDTH * HEIGHT];
		pixelOutput2 = new int[WIDTH * HEIGHT];

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

	public void threshold(int thres)
	{
		int cnt = 0, x, y, value;
		int white = 0xFFFFFFFF;
		int black = 0xFF000000;

		for (y = 0; y < HEIGHT; y++) 
		{
			for (x = 0; x < WIDTH; x++) 
			{
				value = RGBtoGray(pixelInput[y*WIDTH + x]);
				pixelOutput2[y*WIDTH + x] = 0xFF000000 + value * 0x00010101;
				if (value > thres)
					pixelOutput1[y*WIDTH + x] = white;
				else 
					pixelOutput1[y*WIDTH + x] = black;
			}
		}
		imageOutput1 = createImage(new MemoryImageSource(WIDTH, HEIGHT, pixelOutput1,0,WIDTH));
		imageOutput2 = createImage(new MemoryImageSource(WIDTH, HEIGHT, pixelOutput2,0,WIDTH));
	}
	
	//3.7 doing
	public void contrast(int thres)
	{
		int cnt = 0, x, y, value;
		int white = 0xFFFFFFFF;
		int black = 0xFF000000;

		for (y = 0; y < HEIGHT; y++) 
		{
			for (x = 0; x < WIDTH; x++) 
			{
				value = RGBtoGray(pixelInput[y*WIDTH + x]);
				pixelOutput3[y*WIDTH + x] = 0xFF000000 + value * 0x00010101;
				if (value > thres)
					pixelOutput1[y*WIDTH + x] = white;
				else 
					pixelOutput1[y*WIDTH + x] = black;
			}
		}
		imageOutput3 = createImage(new MemoryImageSource(WIDTH, HEIGHT, pixelOutput1,0,WIDTH));
		
	}

	public void init()
	{
		loadImage("image.GIF");
		threshold(127);
		//threshold(255);
		//threshold(200);
		//threshold(64);
		repaint();
	}

	public void paint(Graphics g)
	{
		g.drawImage(imageInput, 30, 30, this);
		g.drawImage(imageOutput2, 30, 50+HEIGHT, this);
		g.drawImage(imageOutput1, 30, 70+HEIGHT+HEIGHT, this);
		g.drawImage(imageOutput3, 30, 70+HEIGHT * 3, this);
	}
}
