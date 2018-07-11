package designPattern.templateMethod;

public class PaymentCommand extends BaseCommand{

	@Override
	public void doBusiness() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("do PaymentCommand Business");
	}

}
