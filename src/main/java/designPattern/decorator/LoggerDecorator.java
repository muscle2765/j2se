package designPattern.decorator;

public class LoggerDecorator implements Command {
	Command cmd;
	
	public LoggerDecorator(Command cmd){
		this.cmd = cmd;
	}

	@Override
	public void execute() {
		System.out.println("log start");
		this.cmd.execute();
		System.out.println("log end");
	}

}
