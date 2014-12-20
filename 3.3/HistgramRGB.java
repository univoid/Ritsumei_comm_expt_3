/* sample 3.3 */
import java.awt.*;
import java.awt.image.*;
import java.applet.Applet;


public class HistgramRGB extends Applet
{
	int WIDTH, HEIGHT;
	Image imageInput, imageOutputR, imageOutputG, imageOutputB;
	int[] pixelInput;
	private int[] canvasR, canvasG, canvasB;    //histgram
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
		int valueR,
            valueG,
            valueB,
            rgb_value,
            x, y,
            maxR = 0,
            maxG = 0,
            maxB = 0;
		int[] countR = new int[255],
              countG = new int[255],
              countB = new int[255];
		for (int i = 0; i<255; i++)
		{
			countR[i] = 0;
            countG[i] = 0;
            countB[i] = 0;
		}
		for (y = 0; y < HEIGHT; y++)
		{
			for (x = 0; x < WIDTH; x++)
			{
				//value = RGBtoGray(pixelInput[y*WIDTH + x]);
                rgb_value = pixelInput[y*WIDTH + x];
				valueR = (rgb_value & 0x00FF0000) / 0x00010000;
                valueG = (rgb_value & 0x0000FF00) / 0x00000100;
                valueB = (rgb_value & 0x000000FF);

                countR[valueR]++;
                countG[valueG]++;
                countB[valueB]++;

				if (countR[valueR] > maxR) maxR = countR[valueR] + 1;
				if (countG[valueG] > maxG) maxG = countG[valueG] + 1;
				if (countB[valueB] > maxB) maxB = countB[valueB] + 1;
			}
		}
        //set histgram height = HEIGHT * 2;
        //red
		canvasR = new int[255 * HEIGHT *2];
		for (x = 0; x < 255; x ++){
            int cap = (int)Math.round(countR[x] * HEIGHT * 2 / maxR);
			for (y = HEIGHT * 2 - cap; y < HEIGHT * 2; y++)
			{
				canvasR[x + y * 255] = 0xFFFF0000;
			}
		}
        imageOutputR = createImage(new MemoryImageSource(255, HEIGHT * 2, canvasR, 0, 255));
        //green
        canvasG = new int[255 * HEIGHT *2];
		for (x = 0; x < 255; x ++){
            int cap = (int)Math.round(countG[x] * HEIGHT * 2 / maxG);
			for (y = HEIGHT * 2 - cap; y < HEIGHT * 2; y++)
			{
				canvasG[x + y * 255] = 0xFF00FF00;
			}
		}
        imageOutputG = createImage(new MemoryImageSource(255, HEIGHT * 2, canvasG, 0, 255));
        //blue
        canvasB = new int[255 * HEIGHT *2];
		for (x = 0; x < 255; x ++){
            int cap = (int)Math.round(countB[x] * HEIGHT * 2 / maxB);
			for (y = HEIGHT * 2 - cap; y < HEIGHT * 2; y++)
			{
				canvasB[x + y * 255] = 0xFF0000FF;
			}
		}
        imageOutputB = createImage(new MemoryImageSource(255, HEIGHT * 2, canvasB, 0, 255));


	}
    public void init()
    {
        loadImage("rits08.gif");
        hist();
        repaint();
    }
    public void paint(Graphics g)
    {
        g.drawImage(imageInput, 30, 30, this);
        g.drawImage(imageOutputR, 30, 50 + HEIGHT, this);
        g.drawImage(imageOutputG, 130 + WIDTH, 50 + HEIGHT, this);
        g.drawImage(imageOutputB, 230 + WIDTH * 2, 50 + HEIGHT, this);
    }
}


