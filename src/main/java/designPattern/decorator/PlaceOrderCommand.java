package designPattern.decorator;

public class PlaceOrderCommand implements Command {

	@Override
	public void execute() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("do PlaceOrderCommand Business");
	}

}
