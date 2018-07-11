package designPattern.decorator;

public class Main {

	public static void main(String[] args) {
		Command logAop = new LoggerDecorator(new PlaceOrderCommand());
		logAop.execute();
		
		Command performanceAop = new PerformanceDecorator(new PlaceOrderCommand());
		performanceAop.execute();
		
		Command towAop = new PerformanceDecorator(new LoggerDecorator(new PlaceOrderCommand()));
		towAop.execute();
	}

}
