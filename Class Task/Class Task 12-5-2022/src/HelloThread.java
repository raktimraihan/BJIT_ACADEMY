
public class HelloThread extends Thread{
	public static final int limit = 10;
	
	public void run() {
		for(int x=1; x<= limit; x++) {
			System.out.println("Run by"+Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	
}
