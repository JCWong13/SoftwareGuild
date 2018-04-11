/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.doggenetics;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author janie
 */
public class DogGenetics {

    public static void main(String[] args) {
        String nameOfDog;
        int percentageDog1;
        int actualDog1;
        int percentageDog2;
        int actualDog2;
        int percentageDog3;
        int actualDog3;
        int percentageDog4;
        int actualDog4;
        int percentageDog5;
        int actualDog5;

        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to Dog Ancestry! We can genetically profile your dog and see if they are related to one of Ghengis Khan's dogs.");
        System.out.println("");
        System.out.println("What is your dog's name?");
        nameOfDog = userInput.nextLine();
        System.out.println("");
        System.out.println("Well then, I have this highly reliable report on " + nameOfDog + "'s prestigious background right here!");
        System.out.println("");
        System.out.println(nameOfDog + "'s ancestry is: ");
        System.out.println("");

        Random randomizer = new Random();
        percentageDog1 = randomizer.nextInt(101); //randomly gives percentage 1-100%
        actualDog1 = percentageDog1; //stores percentage into actualDog   
        percentageDog2 = (100 - actualDog1);
        actualDog2 = randomizer.nextInt(percentageDog2);
        percentageDog3 = 100 - (actualDog1 + actualDog2);
        actualDog3 = randomizer.nextInt(percentageDog3);
        percentageDog4 = 100 - (actualDog1 + actualDog2 + actualDog3);
        actualDog4 = randomizer.nextInt(percentageDog4);
        actualDog5 = 100 - (actualDog1 + actualDog2 + actualDog3 + actualDog4);

        System.out.println(actualDog1 + "%\t Ghengis Khan's dog");
        System.out.println(actualDog2 + "%\t Dramatic RedNosed Asian Pug");
        System.out.println(actualDog3 + "%\t Smiling Sammy");
        System.out.println(actualDog4 + "%\t Wolf/Balto");
        System.out.println(actualDog5 + "%\t Cat (Uh oh!)");
        System.out.println("");

        System.out.println("Woof! That's QUITE the dog!");

    }

}
