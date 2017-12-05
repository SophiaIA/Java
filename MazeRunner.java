import javax.xml.bind.SchemaOutputResolver;
import java.util.*;

public class MazeRunner {
    public static int counter = 0;
    public static void main(String[] args) {
        Maze myMap = new Maze();
        intro(myMap);
        while (!myMap.didIWin()) {
            movesMessage(counter);
            userMove(myMap);
        }
        System.out.println("Congratulations, you made it out alive!");
        System.out.println("and you did it in "+counter+"moves");
    }

    public static void intro(Maze myMap) {

        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position:");
        myMap.printMap();
        System.out.println();
        System.out.println("------------------------------------");
    }

    public static String userMove(Maze myMap) {
        System.out.print("Where would you like to move? (R, L, U, D) ");
        Scanner sc = new Scanner(System.in);
        String move = sc.next();
        while (!("R".equals(move) || "L".equals(move) || "U".equals(move) || "D".equals(move))) {
            System.out.print("Where would you like to move? (R, L, U, D) ");
            move = sc.next();
        }
        counter++;
        if (move.equals("R")) {
            if (myMap.canIMoveRight()) {
                myMap.moveRight();
            } else if (myMap.isThereAPit(move)) {
                navigatePit(myMap, move);
            } else {
                System.out.println("Sorry you hit a wall!");
                myMap.printMap();
            }
        } else if (move.equals("L")) {
            if (myMap.canIMoveLeft()) {
                myMap.moveLeft();

            } else if (myMap.isThereAPit(move)) {
                navigatePit(myMap, move);
            } else {
                System.out.println("Sorry you hit a wall!");
                myMap.printMap();
            }
        } else if (move.equals("U")) {
            if (myMap.canIMoveUp()) {
                myMap.moveUp();

            } else if (myMap.isThereAPit(move)) {
                navigatePit(myMap, move);
            } else {
                System.out.println("Sorry you hit a wall!");
                myMap.printMap();
            }
        } else if (move.equals("D")) {
            if (myMap.canIMoveDown()) {
                myMap.moveDown();

            } else if (myMap.isThereAPit(move)) {
                navigatePit(myMap, move);
            } else {
                System.out.println("Sorry you hit a wall!");
                myMap.printMap();
            }
        }
        return move;
    }

    public static void navigatePit(Maze myMap, String move) {
        System.out.println("Watch out! There's a pit ahead, jump it?");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (answer.substring(0, 1).equalsIgnoreCase("y")) {
            myMap.jumpOverPit(move);
            counter++;
        }
    }

    public static void movesMessage(int counter) {
        if (counter == 50) {
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        }
        if (counter == 75) {
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        }
        if (counter == 90) {
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        }
        if (counter == 100) {
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
            System.out.println("Sorry, but you didn't escape in time- you lose!");
            System.exit(0);
        }
    }
}
