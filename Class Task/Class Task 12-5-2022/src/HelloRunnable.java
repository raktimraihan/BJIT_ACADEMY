import java.lang.constant.Constable;

public class HelloRunnable implements Runnable {
	public static final int limit = 10;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int x=1; x<= limit; x++) {
			System.out.println("Run by");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new HelloRunnable());
		t.start();
		
		Thread t1 = new HelloThread();
		t1.start();
		
		/*
		for(int i=1; i<=limit; i++) {
			t1.join(3000);
			t.join();
		}
		*/

	}

}
