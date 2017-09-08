package app.cacheworker;

public  abstract class   DataRunnable implements Runnable{
	
	public abstract void setDataPackage(DataPackage dp);
	public abstract void setDataService(DataService ds);	
}
