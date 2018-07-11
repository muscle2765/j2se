package designPattern.templateMethod;

public class PlaceOrderCommand extends BaseCommand{

	@Override
	public void doBusiness() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("do PlaceOrderCommand Business");
	}

}
