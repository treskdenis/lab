import parcs.*;

public class JacobiThread implements AM{
	@Override
    public void run(AMInfo info) {
		MyMatrix a = (MyMatrix) info.parent.readObject();
		MyVector b =  (MyVector)info.parent.readObject();
		MyVector xprev = (MyVector) info.parent.readObject();
		int start = info.parent.readInt();
		int end = info.parent.readInt();
		double[]  res = calculateNextComponent(a, b, xprev, start, end);
		info.parent.write(res);
	}
	
	public double[]  calculateNextComponent(MyMatrix a, MyVector b, MyVector xprev, int start, int end) {
		int height = end - start, n = a.getWidth();
		double [] res = new double[height];
		double temp = 0;
		for(int i = start, k = 0; i < end; ++i, k++){
			temp = b.getItem(i);
			for (int j = 0; j < n; ++j) {
				if (j != i) {
					temp -= a.getItem(i, j) * xprev.getItem(j);
				}
			}
			temp /= a.getItem(i, i);
			res[k] = temp;
		}
		return res;
	}
}