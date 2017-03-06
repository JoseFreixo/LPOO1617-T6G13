package pratica1;

import java.util.Scanner;

public class jogo {
	
	public static boolean mainjogo(Scanner s) {
		char tabuleiro[][]=new char [10][10];
		
		int posHero[]=new int[2];
		posHero[0]=1;
		posHero[1]=1;
		
		boolean GameOver=false, wongame=true;
		createTabuleiro(tabuleiro);
		printTabuleiro(tabuleiro);
	
		
		
		int GuardianPositions[][]=CreateGuardianPositions();
		int posGuardian=0;
		int posAntGuardian=23;
	
		while(!GameOver){
			GameOver=MoveHero(tabuleiro,posHero,s);
			
			if(gametwo.HeroCaught(posHero,GuardianPositions[posAntGuardian])){
				System.out.println();
				System.out.println("GAME OVER!Try to avoid the Guardian.");
				wongame=false;
				printTabuleiro(tabuleiro);
				break;
			}
			//Move Guardian
			tabuleiro[GuardianPositions[posGuardian][0]][GuardianPositions[posGuardian][1]]='G';
			tabuleiro[GuardianPositions[posAntGuardian][0]][GuardianPositions[posAntGuardian][1]]='\0';
			posGuardian++;
			posAntGuardian++;
			if(posGuardian==24)
				posGuardian=0;
			if(posAntGuardian==24)
				posAntGuardian=0;
			//
			
			if(gametwo.HeroCaught(posHero,GuardianPositions[posAntGuardian])){
				System.out.println();
				System.out.println("GAME OVER!Try to avoid the Guardian.");
				wongame=false;
				printTabuleiro(tabuleiro);
				break;
			}
			
			printTabuleiro(tabuleiro);
		}
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		return wongame;
	}
	
	public static void printTabuleiro(char[][] tabuleiro){
		for (int i=0;i<tabuleiro.length;i++){
			for(int j=0;j<tabuleiro[i].length;j++)
				System.out.print(tabuleiro[i][j]);
			System.out.println();
		}
	}
	public static void createTabuleiro(char[][] tabuleiro){
		for (int i=0;i<10;i++)
			tabuleiro[0][i]='X';
		for (int i=0;i<10;i++)
			tabuleiro[i][0]='X';
		for(int i=0;i<10;i++)
			tabuleiro[i][9]='X';
		for(int i=0;i<10;i++)
			tabuleiro[9][i]='X';
		tabuleiro[5][0]='I';
		tabuleiro[6][0]='I';
		tabuleiro[1][1]='H';
		tabuleiro[2][1]='X';
		tabuleiro[4][1]='X';
		tabuleiro[7][1]='X';
		tabuleiro[2][2]='X';
		tabuleiro[4][2]='X';
		tabuleiro[7][2]='X';
		tabuleiro[3][2]='I';
		tabuleiro[1][4]='I';
		tabuleiro[3][4]='I';
		tabuleiro[8][4]='I';
		tabuleiro[2][4]='X';
		tabuleiro[4][4]='X';
		tabuleiro[7][4]='X';
		tabuleiro[2][5]='X';
		tabuleiro[4][5]='X';
		tabuleiro[7][5]='X';
		tabuleiro[1][6]='X';
		tabuleiro[2][6]='X';
		tabuleiro[3][6]='X';
		tabuleiro[4][6]='X';
		tabuleiro[7][6]='X';
		tabuleiro[8][6]='X';
		tabuleiro[7][7]='X';
		tabuleiro[8][7]='k';
		tabuleiro[1][8]='G';
	}
	
	public static boolean MoveHero(char[][] tabuleiro,int[] posHero, Scanner s){
		System.out.println();
		System.out.println("Input a moviment for the hero (0- Up, 1-Down, 2-Right, 3-Left):"); //up and down move[X][] Left and right move[][X]
		
		
		int moviment = s.nextInt();

		
		int[] newpos=new int[2];

		
		if(moviment==0){
			newpos[0]=posHero[0]-1;
			newpos[1]=posHero[1];
			return canMove(tabuleiro, newpos,posHero);
		}
		else if(moviment==1){
			newpos[0]=posHero[0]+1;
			newpos[1]=posHero[1];
			return canMove(tabuleiro, newpos,posHero);
		}
		else if(moviment==2){
			newpos[0]=posHero[0];
			newpos[1]=posHero[1]+1;
			return canMove(tabuleiro, newpos,posHero);
		}
		else if(moviment==3){
			newpos[0]=posHero[0];
			newpos[1]=posHero[1]-1;
			return canMove(tabuleiro, newpos,posHero);
		}
		return false;
	}
	
	public static boolean canMove(char[][] tabuleiro,int[] newpos, int[] posHero){
		if(tabuleiro[newpos[0]][newpos[1]]=='\0'){
			tabuleiro[newpos[0]][newpos[1]]='H';
			tabuleiro[posHero[0]][posHero[1]]='\0';
			posHero[0]=newpos[0];
			posHero[1]=newpos[1];}
		
		else if(tabuleiro[newpos[0]][newpos[1]]=='k'){
			tabuleiro[5][0]='S';
			tabuleiro[6][0]='S';
		} else if(tabuleiro[newpos[0]][newpos[1]]=='S'){
			tabuleiro[newpos[0]][newpos[1]]='H';
			tabuleiro[posHero[0]][posHero[1]]='\0';
			System.out.println();
			System.out.println("You won this level! Good luck on the next one!");
			return true;
		}
			
		return false;
	}
	
	public static int [][] CreateGuardianPositions(){
		int GuardianPositions[][]=new int [24][2];
		GuardianPositions[0][0]=1;
		GuardianPositions[0][1]=7;
		
		GuardianPositions[1][0]=2;
		GuardianPositions[1][1]=7;
		
		GuardianPositions[2][0]=3;
		GuardianPositions[2][1]=7;
		
		GuardianPositions[3][0]=4;
		GuardianPositions[3][1]=7;
		
		GuardianPositions[4][0]=5;
		GuardianPositions[4][1]=7;
		
		GuardianPositions[5][0]=5;
		GuardianPositions[5][1]=6;
		
		GuardianPositions[6][0]=5;
		GuardianPositions[6][1]=5;
		
		GuardianPositions[7][0]=5;
		GuardianPositions[7][1]=4;
		
		GuardianPositions[8][0]=5;
		GuardianPositions[8][1]=3;
		
		GuardianPositions[9][0]=5;
		GuardianPositions[9][1]=2;
		
		GuardianPositions[10][0]=5;
		GuardianPositions[10][1]=1;
		
		GuardianPositions[11][0]=6;
		GuardianPositions[11][1]=1;
		
		GuardianPositions[12][0]=6;
		GuardianPositions[12][1]=2;

		GuardianPositions[13][0]=6;
		GuardianPositions[13][1]=3;

		GuardianPositions[14][0]=6;
		GuardianPositions[14][1]=4;

		GuardianPositions[15][0]=6;
		GuardianPositions[15][1]=5;

		GuardianPositions[16][0]=6;
		GuardianPositions[16][1]=6;

		GuardianPositions[17][0]=6;
		GuardianPositions[17][1]=7;

		GuardianPositions[18][0]=6;
		GuardianPositions[18][1]=8;

		GuardianPositions[19][0]=5;
		GuardianPositions[19][1]=8;
		
		GuardianPositions[20][0]=4;
		GuardianPositions[20][1]=8;

		GuardianPositions[21][0]=3;
		GuardianPositions[21][1]=8;

		GuardianPositions[22][0]=2;
		GuardianPositions[22][1]=8;

		GuardianPositions[23][0]=1;
		GuardianPositions[23][1]=8;
		
		return GuardianPositions;
	} 	
}
