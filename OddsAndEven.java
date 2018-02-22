package OddsAndEven;

import java.util.Random;
import java.util.Scanner;

public class OddsAndEven {
    public static void main(String[] args){
    //introduction
        System.out.println("Let’s play a game called “Odds and Evens”");
        System.out.print("What is your name? ");
        Scanner scan = new Scanner(System.in);
        String name = scan.next();
        String answer ="";

        while(!(answer.equals("O")||answer.equals("E"))){
            System.out.print("Hi "+name+", which do you choose? (O)dds or (E)vens? ");
            answer = scan.next();
        }

        if(answer.equals("O")){
            System.out.println(name+" has picked odds! The computer will be evens.");
        }
        else {System.out.println(name+" has picked evens! The computer will be odds.");}

        System.out.println("-----------------------------------------------");
        System.out.println();


        int fingers=-1;
        while(!(fingers>(-1)&&fingers<6)) {
            System.out.print("How many \"fingers\" do you put out? ");
            fingers = scan.nextInt();
        }

        //randomizer
        Random rand = new Random();
        int computer = rand.nextInt(6);

        System.out.println("The computer plays "+computer+"\"fingers\".");
        System.out.println("-----------------------------------------------");
        System.out.println();

        //sum
        int sum = fingers + computer;
        System.out.println(fingers+" + "+computer+" = "+sum);
        System.out.print(sum+" is ...");

       //who won
        if (sum%2==0) {
            System.out.println("even!");
            if (answer.equals("E")){
                System.out.println("That means "+name+" wins! :)");
            }
            else {
                System.out.println("That means computer wins!");
            }
        }
        else {
            System.out.println("odd!");
            if (answer.equals("O")){
                System.out.println("That means "+name+" wins! :)");
            }
            else {
                System.out.println("That means computer wins!");
            }
        }

       System.out.println("-----------------------------------------------");

    }
}
