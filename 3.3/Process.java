/* sample 3.3 */
import java.awt.*;
import java.awt.image.*;
import java.applet.Applet;
import java.util.Arrays;


public class Process extends Applet
{
	int WIDTH, HEIGHT;
	Image imageInput,imageOutput1, imageOutput2, imageOutput3, imageOutput4, imageOutput5;
	int[] pixelInput, pixelOutput1, pixelOutput2, pixelOutput3, pixelOutput4, pixelOutput5;
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
		pixelOutput1 = new int[WIDTH * HEIGHT];
		pixelOutput2 = new int[WIDTH * HEIGHT];
		pixelOutput3 = new int[WIDTH * HEIGHT];
        pixelOutput4 = new int[WIDTH * HEIGHT];
        pixelOutput5 = new int[WIDTH * HEIGHT];

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

	//3.8 done
	public void contrast(int a, int b)
	{
		int cnt = 0, x, y, value;
		int white = 0xFFFFFFFF;
		int black = 0xFF000000;

		for (y = 0; y < HEIGHT; y++)
		{
			for (x = 0; x < WIDTH; x++)
			{
				value = RGBtoGray(pixelInput[y*WIDTH + x]);
				if (value > b)
					pixelOutput3[y*WIDTH + x] = white;
				else if (value < a)
					pixelOutput3[y*WIDTH + x] = black;
				else
					pixelOutput3[y*WIDTH + x] = (value * 255/150 * 0x00010101) + 0xFF000000;

			}
		}
		imageOutput3 = createImage(new MemoryImageSource(WIDTH, HEIGHT, pixelOutput3,0,WIDTH));
	}

    //3.9 done
    public void weight()
	{
		int cnt = 0, x, y, value;
		int white = 0xFFFFFFFF;
		int black = 0xFF000000;
        int[][] t = new int[WIDTH][HEIGHT];
        for (y = 0; y < HEIGHT; y++)
        {
            for (x = 0; x < WIDTH; x++)
            {
                t[x][y] = RGBtoGray(pixelInput[y*WIDTH + x]);

            }
        }
		for (y = 0; y < HEIGHT; y++)
		{
			for (x = 0; x < WIDTH; x++)
			{
                if ((x > 0)&&(y > 0)&&(x < WIDTH-1)&&(y < HEIGHT-1))
                {
                    pixelOutput4[y*WIDTH + x] = ((t[x][y] * 2 + t[x-1][y-1]
                                                + t[x-1][y] + t[x][y-1]
                                                + t[x+1][y+1] + t[x+1][y]
                                                + t[x][y+1] + t[x-1][y+1]
                                                + t[x+1][y-1]) / 10 * 0x00010101) + 0xFF000000;
                }else
                {
                    pixelOutput4[y*WIDTH + x] = t[x][y]* 0x00010101 + 0xFF000000;

                }

            }
        }
		imageOutput4 = createImage(new MemoryImageSource(WIDTH, HEIGHT, pixelOutput4,0,WIDTH));
	}

    //tyousen6 done
    public int middle(int[][] t, int x, int y)
    {
        int[] temp = new int[9];
        temp[0] = t[x-1][y-1]; temp[1] =t[x][y-1]; temp[2] = t[x+1][y-1];
        temp[3] = t[x-1][y]; temp[4] =t[x][y]; temp[5] = t[x+1][y];
        temp[6] = t[x-1][y+1]; temp[7] =t[x][y+1]; temp[8] = t[x+1][y+1];
        Arrays.sort(temp);
        return temp[4];
    }
    public void median()
	{
		int cnt = 0, x, y, value;
		int white = 0xFFFFFFFF;
		int black = 0xFF000000;
        int[][] t = new int[WIDTH][HEIGHT];
        for (y = 0; y < HEIGHT; y++)
        {
            for (x = 0; x < WIDTH; x++)
            {
                t[x][y] = RGBtoGray(pixelInput[y*WIDTH + x]);

            }
        }
		for (y = 0; y < HEIGHT; y++)
		{
			for (x = 0; x < WIDTH; x++)
			{
                if ((x > 0)&&(y > 0)&&(x < WIDTH-1)&&(y < HEIGHT-1))
                {
                    pixelOutput5[y*WIDTH + x] = (middle(t, x, y) * 0x00010101) + 0xFF000000;
                }else
                {
                    pixelOutput5[y*WIDTH + x] = t[x][y]* 0x00010101 + 0xFF000000;

                }

            }
        }
		imageOutput5 = createImage(new MemoryImageSource(WIDTH, HEIGHT, pixelOutput5,0,WIDTH));
	}

	public void init()
	{
		//loadImage("image.GIF");
        loadImage("boat.gif");
		threshold(127);
		//3.6 done
		//threshold(255);
		//threshold(200);
		//threshold(64);
		//3.8 done
		contrast(50, 200);
        //3.9 done
        weight();
        //tyousen6 done
        median();
		repaint();
	}

	public void paint(Graphics g)
	{
		g.drawImage(imageInput, 30, 30, this);
		g.drawImage(imageOutput4, 30, 50+HEIGHT, this);
        g.drawImage(imageOutput5, 50 + WIDTH, 50+HEIGHT, this);
		//g.drawImage(imageOutput1, 30, 70+HEIGHT+HEIGHT, this);
		//g.drawImage(imageOutput3, 30, 90+HEIGHT+HEIGHT+HEIGHT, this);
        //g.drawImage(imageOutput2, 30, 110+HEIGHT+HEIGHT+HEIGHT+HEIGHT, this);
	}
}
