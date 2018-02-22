import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BattleShips {

    public static void main(String []args) throws InterruptedException{
        welcome();
        int[][] ocean = new int[10][10];
        fillOcean(ocean);
        deployUserShips(ocean);
        deployCompShips(ocean);
        while(!endGame(ocean)) {
            userMove(ocean);
            if(endGame(ocean)){
                break;
            }
            compMove(ocean);
        }
    }

    public static void welcome() throws InterruptedException{
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println();
        Thread.sleep(1000);
    }

    public static int[][] fillOcean(int[][] ocean){
        for(int[] row:ocean) {
            Arrays.fill(row, 0);
        }
        System.out.println("Right now, the sea is empty");
        printOcean(ocean);
        return ocean;
    }

    public static void printOcean(int[][] ocean){
        // 0 = empty sea spot - print " "
        // 1 = user placed ship - print "@"
        // 2 = comp placed ship - print " "
        // 3 = sunk your own ship - print "x"
        // 4 = user move, missed - print "-"
        // 5 = sunk computer ship - print "!"

        System.out.println("    0123456789");
        for(int row=0; row<10; row++){
            System.out.print(row+" | ");
            for(int col=0; col<10; col++){
                if(ocean[row][col] == 1){
                    System.out.print("@");
                }
                else if(ocean[row][col]==3){
                    System.out.print("x");
                }
                else if(ocean[row][col]==4){
                    System.out.print("-");
                }
                else if(ocean[row][col]==5){
                    System.out.print("!");
                }
                else System.out.print(" ");
            }
            System.out.println(" | "+row);
        }
        System.out.println("    0123456789");
        System.out.println();
        System.out.println("Your ships: "+numOfUserShips(ocean)+" | Computer ships: "+numOfCompShips(ocean));
        System.out.println("---------------------------------");

    }

    public static void deployUserShips(int[][] ocean){
        System.out.println("Deploy your ships:");
        Scanner input = new Scanner(System.in);
        int shipsCount = 0;
        while(shipsCount<5){
            try{
                System.out.print("Enter X coordinate for your ship: ");
                int x = input.nextInt();
                System.out.print("Enter Y coordinate for your ship: ");
                int y = input.nextInt();


                if(x>=10 || x<0 || y>=10 || y<0){
                    System.out.println("You can’t place ships outside the 10 by 10 grid !");
                }
                else if (ocean[x][y]==1){
                    System.out.println("You can NOT place two or more ships on the same location");
                }
                else {
                    ocean[x][y] = 1;
                    shipsCount++;
                    System.out.println();
                }
            }
            catch (InputMismatchException e){
                System.out.println("Coordinate must be integer from 0 to 9");
            }
            input.nextLine();
        }
        printOcean(ocean);
    }

    public static void deployCompShips(int[][] ocean) throws InterruptedException{
        System.out.println("Computer is deploying ships:");
        Thread.sleep(1000);
        int shipsCount = 0;
        while(shipsCount<5){
            int randomX = ThreadLocalRandom.current().nextInt(0, 10);
            int randomY = ThreadLocalRandom.current().nextInt(0, 10);
            if(ocean[randomX][randomY]!= 1 & ocean[randomX][randomY]!= 2){
                ocean[randomX][randomY] = 2;
                System.out.println(shipsCount+1+". ship DEPLOYED");
                Thread.sleep(500);
                shipsCount++;
            }
        }
        System.out.println("----------------------------------");
        printOcean(ocean);
    }

    public static void userMove(int[][]ocean) {
        System.out.println();
        System.out.println("YOUR TURN");
        Scanner input = new Scanner(System.in);
        int x;
        int y;
            do {
                try {
                System.out.print("Enter X coordinate: ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate: ");
                y = input.nextInt();
                if (x >= 10 || x < 0 || y >= 10 || y < 0) {
                    System.out.println("No ships outside the ocean (10 by 10 grid) !");
                } else if (ocean[x][y] == 3 || ocean[x][y] == 4 || ocean[x][y] == 5) {
                    System.out.println("You've already tried this location! Try different.");
                } else break;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid coordinate!");
            }
                input.nextLine();
            } while (true);

            //possible outcomes
            if (ocean[x][y] == 0) {
                System.out.println("Sorry, you missed");
                ocean[x][y] = 4;
            } else if (ocean[x][y] == 1) {
                System.out.println("Oh no, you sunk your own ship :(");
                ocean[x][y] = 3;
            } else if (ocean[x][y] == 2) {
                System.out.println("Boom! You sunk the ship!");
                ocean[x][y] = 5;
            }
    }

    public static void compMove(int[][] ocean)throws InterruptedException{
        // 0 = empty sea spot - print " "
        // 1 = user ship - print "@"
        // 2 = comp ship - print " "
        // 3 = action -sunk user ship - print "x"
        // 4 = action -user/comp move, missed - print "-"
        // 5 = action -sunk computer ship - print "!"

        System.out.println();
        System.out.println("COMPUTER TURN");
        Thread.sleep(1000);
        int randomX = ThreadLocalRandom.current().nextInt(0, 10);
        int randomY = ThreadLocalRandom.current().nextInt(0, 10);

        //check if move is valid
        while(true){
            if(randomX >= 10 || randomX < 0 || randomY >= 10 || randomY < 0){
                randomX = ThreadLocalRandom.current().nextInt(0, 10);
                randomY = ThreadLocalRandom.current().nextInt(0, 10);
            }
            else if (ocean[randomX][randomY] == 3 || ocean[randomX][randomY] == 4 || ocean[randomX][randomY] ==5){
                randomX = ThreadLocalRandom.current().nextInt(0, 10);
                randomY = ThreadLocalRandom.current().nextInt(0, 10);
            }
            else break;
        }

        //possible outcomes
        if(ocean[randomX][randomY] == 0) {
            System.out.println("Computer missed");
            ocean[randomX][randomY] = 4;
        }
        else if(ocean[randomX][randomY]==1){
            System.out.println("The Computer sunk one of your ships!");
            ocean[randomX][randomY]=3;
        }
        else if(ocean[randomX][randomY] == 2) {
            System.out.println("The Computer sunk one of its own ships");
            ocean[randomX][randomY] = 5;
        }
        printOcean(ocean);
    }

    public static int numOfUserShips(int[][] ocean){
        int numUserShips = 0;
        for (int[] spot:ocean){
            for(int value:spot){
                if(value == 1){
                    numUserShips++;
                }
            }
        }
         return numUserShips;
    }
    public static int numOfCompShips(int[][] ocean){
        int numCompShips = 0;
        for (int[] spot:ocean){
            for(int value:spot){
                if(value == 2){
                    numCompShips++;
                }
            }
        }
        return numCompShips;
    }

    public static boolean endGame(int[][]ocean){
        if(numOfUserShips(ocean)==0){
            System.out.println("Sorry, Computer won!");
            return true;
        }
        else if(numOfCompShips(ocean) == 0){
            System.out.println("You won the battle!");
            return true;
        }
        else return false;
    }
}



