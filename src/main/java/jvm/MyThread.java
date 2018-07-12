package jvm;

public class MyThread{


    public static void main(String[] args) throws Exception {
    	new Thread(new T()).start();    	
    	for(int i = 0 ;i<100 ;i++){
    		System.out.println(i);
    	}
    }
}

class T implements Runnable {

    @Override
    public void run() {
//        int threadId =  this.hashCode();
        for(int i = 0 ;i<100 ;i++){
        	System.out.println("MyThread "+i);
    	}
    }
}