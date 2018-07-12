package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Pot {

	AtomicInteger portion = new AtomicInteger(0);
	int capacity = 10;
	
	public void add(int id){
		System.out.println("bee "+id+" add capacity : " + portion.incrementAndGet());
	}
	
	public void get(){
		System.out.println("bear get capacity : " + portion.decrementAndGet());
	}
	
	public static void main(String[] args) {
		Pot pot = new Pot();
		Bear bear = new Bear(pot);
		for(int i = 0; i<10; i++){
			new Bee(pot, i).start();
		}
		bear.start();
	}
}

class Bear extends Thread{
	private Pot pot;
	public Bear(Pot pot){
		this.pot = pot;
	}
	
	@Override
	public void run() {		
		while(true){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(pot.portion.intValue()>=10){
				while(true){
					pot.get();
					if(pot.portion.intValue()==0){
						break;
					}
				}
			}
			
		}
	}
}


class Bee extends Thread{
	private Pot pot;
	private int id;
	public Bee(Pot pot,int id){
		this.pot = pot;
		this.id = id;
	}
	public void put(){
		pot.add(this.id);
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			put();
		}
	}
}