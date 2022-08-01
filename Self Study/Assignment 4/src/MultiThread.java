
class MultithreadingDemo extends Thread {
	public void run()
	{
		try {
			System.out.println(
					"Thread Executing: " + Thread.currentThread().getId());
		}
		catch (Exception e) {
			System.out.println("Exception is caught");
		}
	}
}


public class MultiThread {
	public static void main(String[] args)
	{
		int n = 8;
		for (int i = 0; i < n; i++) {
			MultithreadingDemo thread= new MultithreadingDemo();
			thread.start();
		}
	}
}



