
class Draw
{
	private int sz_x, sz_y;
	private int canvas[][];
    private int ppm_canvas[][];

	public Draw(int x, int y)
	{
		canvas = new int[x][y];
        ppm_canvas = new int [x*y][3];
		sz_x = x;
		sz_y = y;
	}
	public void init()
	{
		for (int i = 0; i < sz_y; i++)
			for (int j = 0; j < sz_x; j++)
				canvas[i][j] = 0;

        for (int i = 0; i< sz_y * sz_x; i++)
            for (int j = 0; j < 3; j++)
                ppm_canvas[i][j] = 0;
	}

	public void dot(int x, int y)
	{
		//1.1done
		canvas[x][y] = (int) Math.round(Math.random()*255);
	}

	public void line(int x_start, int y_start, int x_end, int y_end)
	{
		int big, tmp_x, tmp_y;

		if (Math.abs(x_end-x_start) > Math.abs(y_end-y_start))
			big = Math.abs(x_end-x_start);
		else
			big = Math.abs(y_end-y_start);

		for (int i = 0; i <= big; i++)
		{
			tmp_x=x_start+i*(x_end-x_start)/big;
			tmp_y=y_start+i*(y_end-y_start)/big;
			//1.1done
			//dot(tmp_x, tmp_y);
			canvas[tmp_x][tmp_y] = 255;
		}
	}
	//1.2 done
	public void x(int st_x, int st_y, int ed_x, int ed_y)
	{
		line(st_x, st_y, ed_x, ed_y);
		line(st_x, ed_y, ed_x, st_y);
	}
	//1.3 done
	public void circle(int x,int y, int radius)
	{
		int tmp_x, tmp_y;
		double tmp_c = 0;
		tmp_x = x + radius;
		tmp_y = y;
		canvas[tmp_x][tmp_y] = 255;
		//Mradius = pi/50;
		//up
		for (int i = 0; i <= 50; i++)
		{
			tmp_x = tmp_x - ((int) Math.round(((Math.cos(tmp_c) - Math.cos(tmp_c + 0.031415*4)) * radius) ) );
			tmp_y = tmp_y - ((int) Math.round(((Math.sin(tmp_c + 0.031415*4) - Math.sin(tmp_c)) * radius) ) );
			tmp_c = tmp_c +0.031415*4;
			canvas[tmp_x][tmp_y] = 255;
		}
	}
    //1.x1 done
    public void oval(int x, int y, int lng, int shrt, double angle)
    {
        double  a = (double) lng / 2,
                b = (double) shrt / 2;
        double tmp_x, tmp_y1, tmp_y2;
        int dot_x = 0, dot_y = 0;
        tmp_x = x + a;
        while (tmp_x >= x - a )
        {
            tmp_y1 = Math.sqrt(1 - ((tmp_x - x)*(tmp_x - x)/(a*a))) * b + y;
            tmp_y2 = -Math.sqrt(1 - ((tmp_x - x)*(tmp_x - x)/(a*a))) * b + y;
            //rotate
            dot_x = (int) Math.round(((tmp_x - x) * Math.cos(angle) + (tmp_y1 - y) * Math.sin(angle)) + x);
            dot_y = (int) Math.round((-(tmp_x - x) * Math.sin(angle) + (tmp_y1 - y) * Math.cos(angle)) + y);
            canvas[dot_x][dot_y] = 255;
            //symmetry
            dot_x = (int) Math.round(((tmp_x - x) * Math.cos(angle) + (tmp_y2 - y)* Math.sin(angle)) + x);
            dot_y = (int) Math.round((-(tmp_x - x)  * Math.sin(angle) + (tmp_y2 - y)* Math.cos(angle)) + y);
            canvas[dot_x][dot_y] = 255;
            tmp_x -= 1;
        }
    }
    //1.x2 done
    public void paint(int x, int y)
    {
        int tmp_x, tmp_y;
        tmp_x = (x / 4) * 4;
        tmp_y = (y / 4) * 4;
        for (int i = tmp_x; i < tmp_x + 50; i++)
            for (int j = tmp_y; j < tmp_y + 50; j++)
            {
                canvas[i][j] = 255;
            }
    }
    //1.x3 doing
    public void ppm_dot(int x, int y, int r, int g, int b)
	{
        ppm_canvas[x*200+y][0] = r;
        ppm_canvas[x*200+y][1] = b;
        ppm_canvas[x*200+y][2] = g;
	}

	public void ppm_line(int x_start, int y_start, int x_end, int y_end, int r, int g, int b)
	{
		int big, tmp_x, tmp_y;

		if (Math.abs(x_end-x_start) > Math.abs(y_end-y_start))
			big = Math.abs(x_end-x_start);
		else
			big = Math.abs(y_end-y_start);

		for (int i = 0; i <= big; i++)
		{
			tmp_x=x_start+i*(x_end-x_start)/big;
			tmp_y=y_start+i*(y_end-y_start)/big;
			//1.1done
			//dot(tmp_x, tmp_y);
            ppm_dot(tmp_x, tmp_y, r, g, b);
		}
	}

	public void ppm_out()
	{
			System.out.println("P3 "+sz_x+" "+sz_y+" 255");
			for(int i=0; i < sz_y * sz_x; i++)
				for(int j=0; j < 3; j++)
					System.out.println(ppm_canvas[i][j]);
	}


    public void out()
	{
			System.out.println("P2 "+sz_x+" "+sz_y+" 255");
			for(int i=0; i < sz_y; i++)
				for(int j=0; j < sz_x; j++)
					System.out.println(canvas[j][i]);
	}

}

class test
{
	public static void main(String arg[])
	{
		int x_size=200, y_size=200;
		Draw cvs = new Draw(x_size, y_size);

		cvs.init();
        //1.1 done
		//cvs.line(100, 10, 10, 100);
		//for (int i = 0; i < 100; i++)
		//	cvs.dot((int) Math.round(Math.random()*(x_size-1)),
		//		(int) Math.round(Math.random()*(y_size-1)));
		//1.2 done
		//cvs.x(100, 100, 10, 190);
		//1.3 done
		//cvs.circle(100, 100, 25);
        //1.x1 done
        //cvs.oval(100, 100, 100, 50, 0.398);
        //1.x2 done
        //cvs.paint(100, 100);
	    //cvs.out();
        //1.x3 doing
        cvs.ppm_dot(100, 100, 255, 255, 0);
        cvs.ppm_line(100, 10, 10, 100, 0, 255, 255);
        cvs.ppm_out();
	}
}
