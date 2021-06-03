package testDD;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class rateLimitter {
	
	static int counter = 0;
	static long startingTime = System.currentTimeMillis();
	static long timeInterval = 10000;
	
	@Test
	public void testSingular() {
		System.out.println("comoulsory wait starting");
		try {
			Thread.sleep(timeInterval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("comoulsory wait finish");
		assertEquals(rateLimit(System.currentTimeMillis()),true);
	}
	
	@Test
	public void testSequenceOfRequest() {
		System.out.println("comoulsory wait starting");
		try {
			Thread.sleep(timeInterval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("comoulsory wait ending");
		
		for(int i = 1;i<=21;i++)
		{
			System.out.println("i = " + i);
			if(i==21)
				assertEquals(rateLimit(System.currentTimeMillis()),false);
			else
				assertEquals(rateLimit(System.currentTimeMillis()),true);
		}
	}
	
	@Test
	public void testSequenceOfRequestWithSleep() {
		System.out.println("comoulsory wait starting");
		try {
			Thread.sleep(timeInterval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("comoulsory wait ending");
		
		for(int i = 1;i<=10;i++)
		{
			assertEquals(rateLimit(System.currentTimeMillis()),true);
		}
		
		System.out.println("start of sleep");
		try {
			Thread.sleep(timeInterval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end of sleep");
		
		for(int i = 1;i<=11;i++)
		{
			assertEquals(rateLimit(System.currentTimeMillis()),true);
		}
	}
	
	public static boolean rateLimit(long timeStamp)
    {
		if(timeStamp - startingTime >= timeInterval )
		{
			System.out.println("RESETTING STATIC VALUES");
			counter=1;
			startingTime = timeStamp-(timeStamp%timeInterval);  
		}
		else
		{
			counter++;
		}
		System.out.println("counter: " + counter);
		if(counter < 21)
			return true;
		else 
			return false;
    }
}

