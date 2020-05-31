

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public  class MatrixGenerator {
	private static void writeMatrixTofile (double[][] matrix, String fileName) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < matrix.length; i++) {
			   for(int j = 0; j < matrix[0].length; j++) {
			      builder.append(matrix[i][j] + "");
			      if (j < matrix[0].length - 1) {
			         builder.append(" ");
			      }
			   }
			   builder.append("\n");
			}
			bw.write(builder.toString());
			bw.close();
		} catch (IOException e) {}
	}
	
	public static void GenerateInputData(int width, int height, int minValue) {
		boolean isMatrix = height > 1;
		String fileName = (isMatrix ? "matrix" : "vector") + width + ".txt";
		int sum = 0;
		double[][] matrix = new double [height][width];
		Random random = new Random();
		for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
            	matrix[i][j] = random.nextInt(minValue) * 1.0;
            	sum += matrix[i][j];
            }
            if (isMatrix) 
            	matrix[i][i] = sum;
            sum = 0;
        }
		writeMatrixTofile(matrix, fileName);
		System.out.println("Get your data in file " + fileName);
	}
	
}
