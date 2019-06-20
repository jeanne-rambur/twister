package Tools;

import java.util.Random;

public class Outils {
	
	public static int MAX_USER=10000000;
	public static int NB_USERS=0;
	public static int NB_RESERVE=100;
	
	public static String generateKey() {
		char[] tab = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < 32; i++)
			sb.append( tab[r.nextInt( tab.length )] );
		return sb.toString();
	}
	
	public static int generateUserId() {
		int id;
		Random r=new Random();
		id=r.nextInt(MAX_USER)+NB_RESERVE; //100ers utilisateurs reserves
		return id;
	}
}
