import java.io.*;
import java.util.*;

public class MyVector implements Serializable{
	private static final long serialVersionUID = 2L;//TODO
	private double array[];
	
	public MyVector(int length) {
		array = new double[length];
	}

	public MyVector(double[] arr) {
		array = new double[arr.length];
		for(int i = 0; i < arr.length; ++i) {
			array[i] = arr[i];
		}
	}

	public MyVector (List<Double> list) {
		array = new double[list.size()];
		for(int i = 0; i < list.size(); ++i) {
			array[i] = list.get(i);
		}
	}
	public MyVector(MyVector v) {
		array = new double[v.getLength()];
		for(int i = 0; i < v.getLength(); ++i) {
			array[i] = v.getItem(i);
		}
	}

	public void writeToFile(String fileName){
	    try {
	    	BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
	    	StringBuilder builder = new StringBuilder();
	    	for(int i = 0; i < array.length; i++) {
	    		builder.append(array[i] + " ");
	    	}
	    	builder.append("\n");
	    	bw.write(builder.toString());
	    	bw.close();
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
			String str = in.readLine();
			String[] nums = str.split(" ");
			for(int i = 0; i < nums.length; ++i){
				array[i] = new Double(nums[i]);
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Can not read from file " + fileName + ": " + e);
		}
	 }
	 
	 public void setItem(int index, double value)
	 {
		 array[index] = value;
	 }
	 
	 public double getItem (int index)
	 {
		 return array[index];
	 }
	 
	 public int getLength()
	 {
		 return array.length;
	 }

	 public MyVector SubVector(int start, int  len) {
	 	double[] res = new double[len];
	 	for(int i = start, j = 0; j < len; ++i, j++) {
	 		res[j] = array[i];
	 	}

	 	return new MyVector(res);
	 }

	 public void CopyItems(double[] temp, int start, int len) {
	 	for (int i = start, k = 0; i < start + len; ++i, ++k) {
			array[i] = temp[k];
		}
	 }
	 
	 public void CopyVector(MyVector v) {
		if (v.getLength() != array.length) {
			return;
		}
		for (int i = 0; i < array.length; ++i) {
			array[i] = v.getItem(i);
		}
	 }
}