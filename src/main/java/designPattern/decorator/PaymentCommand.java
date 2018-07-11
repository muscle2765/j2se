package designPattern.decorator;

public class PaymentCommand implements Command {

	@Override
	public void execute() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("do PaymentCommand Business");
	}

}
