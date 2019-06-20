package services;

public class Operation {

	
	
	public static double addition(double a,double b){
		return a+b;
	}
	
	
	public static double multiplication(double a,double b){
		return a*b;
	}
	
	
	public static double division(double a,double b){
		return a/b;
	}
	
	
	public static double calcul(double a,double b,String operation){
		double x=0.0;
		if(operation.equals("multiplication")) x= multiplication( a, b);
		if(operation.equals("division"))x=  division( a, b);
		if(operation.equals("addition")) x= addition( a, b);
		return x;
		
	}
	
	
	public static void main(String[]args) {
	
	}
}
