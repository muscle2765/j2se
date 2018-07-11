package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Pot {

	AtomicInteger portion = new AtomicInteger(0);
	int capacity = 10;
	
	public void add(int portionNum,String animalName){
		System.out.println(animalName + ":" + portion.incrementAndGet());
	}
	
	public static void main(String[] args) {
		Pot pot = new Pot();
		Bear bear = new Bear();
		for(int i = 0; i<10; i++){
			new Bee(pot, i).start();
		}
	}
}

class Bear {
	
}


class Bee extends Thread{
	private Pot pot;
	private int num;
	public Bee(Pot pot,int num){
		this.pot = pot;
		this.num = num;
	}
	public void put(){
		pot.add(1,"Bee "+num);
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			put();
		}
	}
}