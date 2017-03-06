package pratica1;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class gametwo {

		
		public static void mainjogo(Scanner s) {
			char tabuleiro[][]=createTabuleiro();
			
			int posHero[]=new int[2];
			posHero[0]=7;
			posHero[1]=1;
			
			int posOgre[]={1,4};
			
			char[] Representations={'H','O'};
			
			boolean GameOver=false;
			jogo.printTabuleiro(tabuleiro);
		
			
			while(!GameOver){
				GameOver=MoveHero(tabuleiro,posHero,s,Representations);
				if(GameOver){
					jogo.printTabuleiro(tabuleiro);
					break;
					}
				GameOver=HeroCaught(posHero,posOgre);
				
				if(GameOver){
					jogo.printTabuleiro(tabuleiro);
					break;
					}
				
				MoveOgre(tabuleiro,posOgre,Representations);
				GameOver=HeroCaught(posHero,posOgre);
				jogo.printTabuleiro(tabuleiro);
			}
			
		
			return;
		}
		
		public static char[][] createTabuleiro(){
			char[][] tabuleiro = new char[9][9];
			for (int i=0;i<9;i++)
				tabuleiro[0][i]='X';
			for (int i=0;i<9;i++)
				tabuleiro[i][0]='X';
			for(int i=0;i<9;i++)
				tabuleiro[i][8]='X';
			for(int i=0;i<9;i++)
				tabuleiro[8][i]='X';
			tabuleiro[1][7]='k';
			tabuleiro[1][4]='O';
			tabuleiro[7][1]='H';
			tabuleiro[1][0]='I';
			
			return tabuleiro;
		}
		
		public static boolean MoveHero(char[][] tabuleiro,int[] posHero, Scanner s,char[] Representations){
			System.out.println();
			System.out.println("Input a moviment for the hero (0- Up, 1-Down, 2-Right, 3-Left):"); //up and down move[X][] Left and right move[][X]
			
			
			int moviment = s.nextInt();

			
			int[] newpos=new int[2];

			
			if(moviment==0){
				newpos[0]=posHero[0]-1;
				newpos[1]=posHero[1];
				return canMove(tabuleiro, newpos,posHero,Representations);
			}
			else if(moviment==1){
				newpos[0]=posHero[0]+1;
				newpos[1]=posHero[1];
				return canMove(tabuleiro, newpos,posHero,Representations);
			}
			else if(moviment==2){
				newpos[0]=posHero[0];
				newpos[1]=posHero[1]+1;
				return canMove(tabuleiro, newpos,posHero,Representations);
			}
			else if(moviment==3){
				newpos[0]=posHero[0];
				newpos[1]=posHero[1]-1;
				return canMove(tabuleiro, newpos,posHero,Representations);
			}
			return false;
		}
		
		public static boolean canMove(char[][] tabuleiro,int[] newpos, int[] posHero, char[] Representations){
			if(tabuleiro[newpos[0]][newpos[1]]=='\0'){
				tabuleiro[newpos[0]][newpos[1]]=Representations[0];
				tabuleiro[posHero[0]][posHero[1]]='\0';
				posHero[0]=newpos[0];
				posHero[1]=newpos[1];}
			
			else if(tabuleiro[newpos[0]][newpos[1]]=='k'){
				Representations[0]='K';
				tabuleiro[newpos[0]][newpos[1]]=Representations[0];
				tabuleiro[posHero[0]][posHero[1]]='\0';
				posHero[0]=newpos[0];
				posHero[1]=newpos[1];
				
			} else if(tabuleiro[newpos[0]][newpos[1]]=='I'&&Representations[0]=='K'){
				tabuleiro[newpos[0]][newpos[1]]='S';
			} else if(tabuleiro[newpos[0]][newpos[1]]=='S'){
				tabuleiro[newpos[0]][newpos[1]]=Representations[0];
				tabuleiro[posHero[0]][posHero[1]]='\0';
				posHero[0]=newpos[0];
				posHero[1]=newpos[1];
				System.out.println();
				System.out.println("You won the game! Congratulations!!!");
				return true;
			}
				
			return false;
		}
		
		public static void MoveOgre(char[][] tabuleiro,int[] posOgre,char[] Representations){
			boolean moved=false;

			while(!moved){

				int moviment= ThreadLocalRandom.current().nextInt(0, 4);
				int[] newpos=new int[2];


				if(moviment==0){
					newpos[0]=posOgre[0]-1;
					newpos[1]=posOgre[1];
					moved= OgreCanMove(tabuleiro, newpos,posOgre,Representations);
				}
				else if(moviment==1){
					newpos[0]=posOgre[0]+1;
					newpos[1]=posOgre[1];
					moved= OgreCanMove(tabuleiro, newpos,posOgre,Representations);
				}
				else if(moviment==2){
					newpos[0]=posOgre[0];
					newpos[1]=posOgre[1]+1;
					moved= OgreCanMove(tabuleiro, newpos,posOgre,Representations);
				}
				else if(moviment==3){
					newpos[0]=posOgre[0];
					newpos[1]=posOgre[1]-1;
					moved= OgreCanMove(tabuleiro, newpos,posOgre,Representations);
				}
			}
			return;
		}
		
		public static boolean OgreCanMove(char[][] tabuleiro,int[] newpos, int[] posOgre, char[] Representations){
			boolean OgreMoved=false, MovedOutOfKeyPos=false;
			
			if(Representations[1]=='$'){
				MovedOutOfKeyPos=true;
				Representations[1]='O';
			}
			if(tabuleiro[newpos[0]][newpos[1]]=='\0'){
				tabuleiro[newpos[0]][newpos[1]]=Representations[1];
				tabuleiro[posOgre[0]][posOgre[1]]='\0';
				posOgre[0]=newpos[0];
				posOgre[1]=newpos[1];
				OgreMoved=true;
			}
			else if(tabuleiro[newpos[0]][newpos[1]]=='k'){
				Representations[1]='$';
				tabuleiro[newpos[0]][newpos[1]]=Representations[1];
				tabuleiro[posOgre[0]][posOgre[1]]='\0';
				posOgre[0]=newpos[0];
				posOgre[1]=newpos[1];
				OgreMoved=true;
			}

			if(MovedOutOfKeyPos&&OgreMoved)
				tabuleiro[1][7]='k';
			else if(MovedOutOfKeyPos)
				Representations[1]='$';
			
			return OgreMoved;
		}
		
		public static boolean HeroCaught(int [] posHero, int[] posGuard){
			if(posGuard[0]==posHero[0]-1&&posGuard[1]==posHero[1]){
				System.out.println();
				System.out.println("You got caught by the Guardian!");
				System.out.println("GAME OVER!!");
				return true;
			}
			if(posGuard[0]==posHero[0]+1&&posGuard[1]==posHero[1]){
				System.out.println();
				System.out.println("You got caught by the Guardian!");
				System.out.println("GAME OVER!!");
				return true;
			}
			if(posGuard[0]==posHero[0]&&posGuard[1]==posHero[1]+1){
				System.out.println();
				System.out.println("You got caught by the Guardian!");
				System.out.println("GAME OVER!!");
				return true;
			}
			if(posGuard[0]==posHero[0]&&posGuard[1]==posHero[1]-1){
				System.out.println();
				System.out.println("You got caught by the Guardian!");
				System.out.println("GAME OVER!!");
				return true;
			}		
			return false;
		}
	}

