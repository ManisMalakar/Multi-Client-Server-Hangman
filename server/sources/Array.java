package server;
import java.util.*;

public class Array
{
	final int ROWS;
	final int COLS;
	String [][]myArray;
	String res = "";

	public Array(int rows, int cols)
	{
		ROWS=rows;
		COLS=cols;
		myArray=new String[ROWS][COLS];
	}

	public void Initialize(String [][]elements)
	{
		for (int i=0; i<ROWS; i++)
		{
			for (int j=0; j<COLS; j++)
				myArray[i][j]=elements[i][j];
		}
	}

	public void Initialize(String []lines, String separator)
	{
		for (int i=0; i<ROWS; i++)
		{
			String []elements=lines[i].split(separator);
			for (int j=0; j<COLS; j++)
				myArray[i][j]=elements[j];
		}
	}

	public void Update(int row, int col, String element)
	{
		myArray[row][col]=element;
	}

	public String []Row(int r)
	{
		String []row=new String[COLS];
		for (int j=0; j<COLS; j++)
			row[j]=myArray[r][j];
	return row;
	}

	public String []Col(int c)
	{
		String []col=new String[ROWS];
		for (int i=0; i<ROWS; i++)
			col[i]=myArray[i][c];
	return col;
	}

	public String Print()
	{
		res = "";
		for (int i=0; i<ROWS; i++)
		{
			for (int j=0; j<COLS; j++)
				//System.out.printf("%s", myArray[i][j]);
				res += myArray[i][j];
			//System.out.printf("\n");
			res += "\n";
		}
		return res;
	}

	public static void main(String []args)
	{
		String even=" %-% %-% %-% ";
		String odd="|% %|% %|% %|";
		String []lines={even, odd, even, odd, even, odd, even};

		Array a=new Array(7, 7);
		System.out.printf("Rows=%d, cols=%d\n", a.ROWS, a.COLS);
		a.Initialize(lines, "%");
		a.Print();
		a.Update(1, 1, "X");
		a.Print();
	}
}
