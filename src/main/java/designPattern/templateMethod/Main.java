package designPattern.templateMethod;

public class Main {

	public static void main(String[] args) {
		new PlaceOrderCommand().execute();
		new PaymentCommand().execute();
	}

}
