
class Draw
{
	private int sz_x, sz_y;
	private int canvas[][];

	public Draw(int x, int y)
	{
		canvas = new int[x][y];

		sz_x = x;
		sz_y = y;
	}

	public void init()
	{
		for (int i = 0; i < sz_y; i++)
			for (int j = 0; j < sz_x; j++)
				canvas[i][j] = 0;
	}

	public void oval(int x, int y, int lng, int shrt, double angle)
    {
        double  a = (double) lng / 2,
                b = (double) shrt / 2;
        double tmp_x, tmp_y;
        int dot_x, dot_y;
        tmp_x = a;
        while (tmp_x >= -a )
        {
            tmp_y = Math.sqrt(1-((tmp_x - x)*(tmp_x - x)/(a*a))) / b + y;
            System.out.println(tmp_y);
            canvas[(int) Math.round(tmp_x)][(int) Math.round(tmp_y)] = 255;
            //rotate
            dot_x = (int) Math.round((tmp_x * Math.cos(angle) + tmp_y * Math.sin(angle)));
            dot_y = (int) Math.round((-tmp_x * Math.sin(angle) + tmp_y * Math.cos(angle)));
            canvas[dot_x][dot_y] = 255;
            //symmetry
            dot_x = (int) Math.round((tmp_x * Math.cos(angle) - tmp_y * Math.sin(angle)));
            dot_y = (int) Math.round((-tmp_x * Math.sin(angle) - tmp_y * Math.cos(angle)));
            canvas[dot_x][dot_y] = 255;
            tmp_x -= 0.01;
        }
    }

	public void out()
	{
			System.out.println("P2 "+sz_x+" "+sz_y+" 255");
			for(int i=0; i < sz_y; i++)
				for(int j=0; j < sz_x; j++)
					System.out.println(canvas[j][i]);
	}

    public static void main(String arg[])
    {

    }
}

class test
{
	public static void main(String arg[])
	{
		int x_size=200, y_size=200;
		Draw cvs = new Draw(x_size, y_size);

		cvs.init();
		//cvs.line(100, 10, 10, 100);
		//for (int i = 0; i < 100; i++)
		//	cvs.dot((int) Math.round(Math.random()*(x_size-1)),
		//		(int) Math.round(Math.random()*(y_size-1)));

		//1.2done
		//cvs.x(100, 100, 10, 190);
		//1.3done
		//cvs.circle(100, 100, 25);
        //1.x1done
        cvs.oval(100, 100, 20, 10, 0.3926);
		cvs.out();
	}
}
