import java.util.*;
import java.io.*;

public class MyMatrix implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double matrix[][];
	private int mHeight;
	private int mWidth;
	
	public MyMatrix(int iHeight, int iWidth)
	{
	    mHeight = iHeight;
	    mWidth = iWidth;
	    matrix = new double[mHeight][mWidth];
	}
	
	 public void writeToFile(String fileName)
	 {
	    try
	    {
	      FileOutputStream file = new FileOutputStream(fileName);
	      ObjectOutputStream out = new ObjectOutputStream(file);
	      out.writeObject(this);
	      out.flush();
	      out.close();
	    }
	    catch (IOException e)
	    { 
	      System.out.println("Can not save to file " + fileName + ": " + e);
	    }
	 }
	 
	 public void readFromFile(String fileName) 
	 {
		 try {
			 BufferedReader in = new BufferedReader(new InputStreamReader(
					 new FileInputStream(fileName)));
			 int line = 0;
			String str;
			 while((str = in.readLine()) != null) {
					String[] nums = str.split(" ");
					for(int i = 0; i < nums.length; ++i){
						matrix[line][i] = new Double(nums[i]);
					}
					line++;
				}
				in.close();
		} catch (Exception e) {
			System.out.println("Can not read from file " + fileName + ": " + e);
		}
	 }
	 
	 public void setItem(int y, int x, double value)
	 {
	    matrix[y][x] = value;
	 }
	 
	 public double getItem (int i, int j) {
		 return matrix[i][j];
	 }
	 
	 public int getHeight() {
		 return mHeight;
	 }
	 
	 public int getWidth() {
		 return mWidth;
	 }
	 
	 public void RandomFill(int iMaxValue) {
	    Random rand;
	    int iY, iX;
	    rand = new Random();
	    for (iY = 0; iY < mHeight; iY++) {
	      for (iX = 0; iX < mWidth; iX++)
	        matrix[iY][iX] = rand.nextInt(iMaxValue);
	      if (iY < mWidth && iY < mHeight)
	        matrix[iY][iY] = 1;
	    }
	}
}
