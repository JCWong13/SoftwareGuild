/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author janie
 */
public class FMUserIOImpl implements FMUserIO {

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return Double.parseDouble(sc.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double userDouble = 0;
        do {
            Scanner sc = new Scanner(System.in);
            userDouble = Double.parseDouble(sc.nextLine());
        } while (userDouble < min || userDouble > max);
        return userDouble;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return Float.parseFloat(sc.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float userFloat = 0;
        do {
            Scanner sc = new Scanner(System.in);
            userFloat = Float.parseFloat(sc.nextLine());
        } while (userFloat < min || userFloat > max);
        return userFloat;
    }

    @Override
    public int readInt(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return Integer.parseInt(sc.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int userInt = 0;
        do {
            Scanner sc = new Scanner(System.in);
            userInt = Integer.parseInt(sc.nextLine());
        } while (userInt < min || userInt > max);
        return userInt;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return Long.parseLong(sc.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long userLong = 0;
        do {
            Scanner sc = new Scanner(System.in);
            userLong = Long.parseLong(sc.nextLine());
        } while (userLong < min || userLong > max);
        return userLong;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                Scanner sc = new Scanner(System.in);
                return sc.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);
            } catch (NumberFormatException |InputMismatchException e) {
                System.out.println("Invalid Input.  Please Try Again.");
            }
        }
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal userBigDecimal = new BigDecimal("0");
        do {
            Scanner sc = new Scanner(System.in);
            userBigDecimal = sc.nextBigDecimal();
        } while ((userBigDecimal.compareTo(min) == -1) || (userBigDecimal.compareTo(max) == 1));
        return userBigDecimal.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        while (true) {
            try {
                LocalDate ldprompt = LocalDate.parse(readString(prompt), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                return ldprompt;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Format for Date.  Please Try Again.");
            }
        }
    }
}
