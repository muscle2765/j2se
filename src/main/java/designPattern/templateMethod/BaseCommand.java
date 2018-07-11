package designPattern.templateMethod;

public abstract class BaseCommand {
	
	//实现切面
	public void execute(){
		long startTime = System.currentTimeMillis();
		
		doBusiness();
		
		long endTime = System.currentTimeMillis();

		long executeTime = endTime-startTime;
		System.out.println(this.getClass().getName()+" execute time : " + executeTime + "ms");
	}
	
	public abstract void doBusiness();
}
