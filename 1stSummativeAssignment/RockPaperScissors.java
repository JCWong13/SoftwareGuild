/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.rockpaperscissors;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author janie
 */
public class RockPaperScissors {

    public static void main(String[] args) {
        
        playGame(); 
    } //methods cannot be in other methods
    public static void playGame(){

        
        int userChoiceRounds = 0;
        int userAction = 0;
        int computerAction = 0;
        int numTies = 0;
        int numUserWins = 0;
        int numComputerWins = 0;
        int numOfRounds = 0;

        System.out.println("Welcome to Rock, Paper, Scissors!\nHow many rounds (1-10) would you like to play?");
        Scanner userInput = new Scanner(System.in);
        userChoiceRounds = userInput.nextInt();

        if (userChoiceRounds < 1 || userChoiceRounds > 10) {
            System.out.println("");
            System.out.println("You did not pick within 1-10. Goodbye pathetic human.");
            playGame();
            System.exit(0);
        } else {
            numOfRounds = userChoiceRounds;
        }

        for (int i = 0; i < numOfRounds; i++) {
            Random randomizer = new Random();
            System.out.println("Please pick rock(1), paper(2), or scissors(3): ");
            userAction = userInput.nextInt();

            computerAction = randomizer.nextInt(3)+1;

            if (userAction == computerAction) {
                numTies++;
            } else if (userAction == 1 && computerAction == 2 || userAction == 2 && computerAction == 1 || userAction == 3 && computerAction == 2) {
                numUserWins++;
            } else {
                numComputerWins++;
            }
            System.out.println("The result of round "+(i+1)+":\nTies = " + numTies+"\nYour wins: "+numUserWins+"\nComputer Wins: "+numComputerWins);
            System.out.println("");
        }
        System.out.println("");
        System.out.println("END OF GAME! \nNumber of ties: " + numTies);
        System.out.println("You had this many wins: " + numUserWins);
        System.out.println("The computer had this many wins: " + numComputerWins);
        
        if (numUserWins > numComputerWins) {
            System.out.println("Congratulations! You are the winner!  For now at least until the computers take over...");
        }
        else if (numComputerWins > numUserWins) {
            System.out.println("Whomp whomp! The computer is the winner! Has science gone too far?");
        }
        else {
            System.out.println("You've tied with the computer! You're both winners, huzzah!");
        }
        System.out.println("Do you want to play again? Yes or No?");
        String playAgain;
        playAgain=userInput.next(); 
        
        if (playAgain.equalsIgnoreCase("yes")) {
            System.out.println("");
             playGame();
        }
           
        else if (playAgain.equalsIgnoreCase("no")) {
            System.out.println("");
            System.out.println("Scared Potter? Thanks for playing!");
//            System.exit(0);
        }
        
        else {
            System.out.println("");
            System.out.println("You did not answer yes or no.  Goodbye pathetic human.");
//            System.exit(0);   
        }
        }
}


   


