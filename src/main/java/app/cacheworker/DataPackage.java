package app.cacheworker;

public class DataPackage implements Cloneable{
	private String dataType;
	private Object data;

	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	@Override
	protected Object clone()  {
		// TODO Auto-generated method stub
		DataPackage dp = null;
		try{
			dp = (DataPackage)super.clone();
		}catch(CloneNotSupportedException e) {  
		       e.printStackTrace();  
		}  	
		return dp;
	}
	
	
	
	
}
