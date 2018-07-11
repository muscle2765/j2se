package jvm;

public class MyThread implements Runnable {

    @Override
    public void run() {
        int threadId =  this.hashCode();
        System.out.println("MyThread "+threadId);
    }

    public static void main(String[] args) throws Exception {
    	Runnable r = new MyThread();
    	r.run();
    }
}