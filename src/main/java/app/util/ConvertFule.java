package app.util;

public class ConvertFule{
	
	public static double cvtFuleCspt(String fuletype,double fulecount){
		
		switch (fuletype) {
		case "f1":
			fulecount=fulecount*1.46;
			break;
		case "f2":
			fulecount=fulecount*1.47;
			break;
		case "f4":
			fulecount=fulecount*1.47;
			break;
		case "f6":
			fulecount=fulecount*1.23;
			break;
		case "f8":
			fulecount=fulecount*1.71;
			break;
		case "f9":
			fulecount=fulecount*1.76;
			break;
		default:
			break;
		}
		
		return fulecount;
	}

}
