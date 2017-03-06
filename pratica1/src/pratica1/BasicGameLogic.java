package pratica1;

import java.util.Scanner;

public class BasicGameLogic {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		if(jogo.mainjogo(s))
			gametwo.mainjogo(s);
		
		s.close();
	}

}
