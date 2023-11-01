package com.renan.cursojava.exercicios20;

import java.util.Scanner;

public class JogoDaVelha {
	public static String[][] jogoVelhaDisplay = new String[3][3];
	public static Scanner scan = new Scanner(System.in);
	public static boolean currentPlayer = false;
	
	public static void main(String[] args) {
		
		boolean endGame = false;
		
		while(endGame != true) {
			int player = currentPlayer ? 1 : 0;
			displayGame();
			
			int[] playerLastPlay = playerMove(player);
			if(playerLastPlay == null) {
				continue;
			}
			
			String gameMessage = gameVerification(playerLastPlay);
	
			if(gameMessage != null) {
				displayGame();
				 System.out.println("Fim de jogo!");
				 System.out.println(gameMessage);
				 endGame = true;
			};  
			currentPlayer = !currentPlayer;
			
		}
		scan.close(); // BOM SABER -> so pode existir um scan.close na aplicação.
				
	}
	
	public static int[] playerMove(int playerMoment) {
		char playerChar;
		if(playerMoment == 1) {
			playerChar = 'O';
		} else {
			playerChar = 'X';
		}
		
		System.out.println("Jogador " + (playerMoment+1) + " : Defina a linha que quer colocar o " + playerChar + ". 1 a 3");
		int rowSelected = scan.nextInt() -1 ;
		if(rowSelected < 0 || rowSelected > 2) {
			System.out.println("Erro, reinicie o jogo!");
			
			return null;
		}
		System.out.println("Jogador " + (playerMoment+1) + " : Defina a coluna que quer colocar o " + playerChar + ". 1 a 3");
		int columnSelected = scan.nextInt() -1 ;
		if(columnSelected < 0|| columnSelected > 2) {
			System.out.println("Erro, reinicie o jogo!");
			
			return null;
		}
				
		
		if(jogoVelhaDisplay[rowSelected][columnSelected].charAt(0) != ' ') {
			System.out.println("Esse lugar já foi escolhido antes");
			return null;
		}
		
		
		JogoDaVelha.jogoVelhaDisplay[rowSelected][columnSelected] = Character.toString(playerChar);
		int[] playerSelections = {rowSelected, columnSelected};
		
		return playerSelections;
	}

	public static void displayGame() {
		for(int i = 0; i < JogoDaVelha.jogoVelhaDisplay.length; i++) {
			for(int j = 0; j < JogoDaVelha.jogoVelhaDisplay[i].length; j++) {
				if(JogoDaVelha.jogoVelhaDisplay[i][j] == null) JogoDaVelha.jogoVelhaDisplay[i][j] = " ";
				System.out.print(JogoDaVelha.jogoVelhaDisplay[i][j] + " | ");
			}
			System.out.println("\n");
		}
	}
	
	public static String gameVerification(int[] lastPlay) {
		
		char lastPlayChar = jogoVelhaDisplay[lastPlay[0]][lastPlay[1]].charAt(0);
		
		if(rowVerification(lastPlayChar, lastPlay[0])) return "O jogador " + (!currentPlayer ? 1 : 2) + " Ganhou";
		if(columnVerification(lastPlayChar, lastPlay[1])) return "O jogador " + (!currentPlayer ? 1 : 2) + " Ganhou";
		
		if(lastPlay[0] == lastPlay[1] && diagonalLeftVerification(lastPlayChar)) return "O jogador " + (!currentPlayer ? 1 : 2) + " Ganhou";
		
		if(lastPlay[0] == (jogoVelhaDisplay.length-1) - lastPlay[1] && diagonalRightVerification(lastPlayChar)) return "O jogador " + (!currentPlayer ? 1 : 2) + " Ganhou";
	
		int verification = 0;
		for(int i = 0; i < jogoVelhaDisplay.length; i++) {
			for(int j = 0; j < jogoVelhaDisplay[i].length; j++) {
				 if(jogoVelhaDisplay[i][j] != " ") verification++;
			}
		}
		if(verification == 9) return "Empate";
		
		return null;
	}
	
	public static boolean diagonalLeftVerification(char lastCharPlayed) {
		int verification = 0;
		for(int i = 0; i < jogoVelhaDisplay.length; i++) {
			for(int j = 0; j < jogoVelhaDisplay[i].length; j++) {
				if(i == j && jogoVelhaDisplay[i][j].charAt(0) == lastCharPlayed) {
					verification++;
				}
			}
		}
		if(verification == 3) {
			return true;
		}
		return false;
	}
	
	public static boolean diagonalRightVerification(char lastCharPlayed) {
		int verification = 0;
		for(int i = 0; i < jogoVelhaDisplay.length; i++) {
			for(int j = 0; j < jogoVelhaDisplay[i].length; j++) {
				if(i == (jogoVelhaDisplay[i].length-1 - j) && jogoVelhaDisplay[i][j].charAt(0) == lastCharPlayed) {
					verification++;
				}
			}
		}
		if(verification == 3) {
			return true;
		}
		return false;
	}
	
	public static boolean columnVerification(char lastCharPlayed, int lastPlayPosition) {
		int verification = 0;
		for(int i = 0; i < jogoVelhaDisplay.length; i++) {
			for(int j = 0; j < jogoVelhaDisplay[i].length; j++) {
				if(j == lastPlayPosition && jogoVelhaDisplay[i][j].charAt(0) == lastCharPlayed) {
					verification++;
				}
			}
		}
		if(verification == 3) {
			return true;
		}
		return false;
	}
	
	public static boolean rowVerification(char lastCharPlayed, int lastPlayPosition) {
		int verification = 0; 
		for(int i = 0; i < jogoVelhaDisplay.length; i++) {
			for(int j = 0; j < jogoVelhaDisplay[i].length; j++) {
				if(i == lastPlayPosition && jogoVelhaDisplay[i][j].charAt(0) == lastCharPlayed) {
					verification++;
				}
			}
		}
		if(verification == 3) {
			return true;
		}
		return false;
	}
}
