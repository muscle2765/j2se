package designPattern.decorator;

public class PerformanceDecorator implements Command {
	Command cmd;
	
	public PerformanceDecorator(Command cmd){
		this.cmd = cmd;
	}

	@Override
	public void execute() {
		System.out.println("timer start");
		long startTime = System.currentTimeMillis();

		this.cmd.execute();

		long endTime = System.currentTimeMillis();

		long executeTime = endTime-startTime;
		System.out.println(this.getClass().getName()+" execute time : " + executeTime + "ms");
		
	}

}
