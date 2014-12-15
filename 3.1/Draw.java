
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
	//1.2done
	public void x(int st_x, int st_y, int ed_x, int ed_y)
	{
		line(st_x, st_y, ed_x, ed_y);
		line(st_x, ed_y, ed_x, st_y);
	}
	//1.3done
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
		//cvs.line(100, 10, 10, 100);
		//for (int i = 0; i < 100; i++)
		//	cvs.dot((int) Math.round(Math.random()*(x_size-1)), 
		//		(int) Math.round(Math.random()*(y_size-1)));

		//1.2done
		//cvs.x(100, 100, 10, 190); 
		//1.3done
		cvs.circle(100, 100, 25);
		cvs.out();
	}
}
