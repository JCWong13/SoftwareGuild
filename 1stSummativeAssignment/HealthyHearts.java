/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author janie
 */
public class HealthyHearts {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        int age;
        int maxHeart;
        int minZone;
        int maxZone;

        System.out.println("How old are you?");
        age = inputReader.nextInt();

        System.out.println("What is your age? " + age);
        System.out.println("Your maximum heart rate should be " + (220 - age) + " beats per minute");
        maxHeart = (220 - age);
        System.out.println("Your target HR zone is " + maxHeart / 2 + "-" + (maxHeart * 85 / 100) + " beats per minute");

    }
}
