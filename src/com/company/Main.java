package com.company;

import java.util.*;

public class Main {
    public static Random rnd = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<String> allPosibilities = new ArrayList<>();
    public static ArrayList<String> moves = new ArrayList<>();
    public static int round = 1;
    public static boolean over = false;

    public static void main(String[] args) {
        System.out.println("Hallo Daniel Runde 1");
        System.out.println("VOm lappy geändert");

        //Anzahl an Verschiedenen Mustern = Strenght! : (Srength-length)!
        //Anzahl der Verschiedenen Mustern ohne Farbreihenfolge = Strenght! : length!

        fillAllPosibilities();
        String solution = allPosibilities.get(rnd.nextInt(1680) + 1);
            do {
                showBoard(solution);
                String move = sc.nextLine();
                moves.add(move);
                round++;
                if(solution.equals(move)) {
                    over = true;
                    break;
                }

            }while(round <13);

            if(!over)
                System.out.println("Leider nicht gewonnen");
            else{
                System.out.println("Gewonnen");
            }
            showBoard(solution);



    }

    private static String checkLine(String solution, int r) {
        int red = 0;
        int yellow = 0;
             // r = 12 11 10 ... 2 1
        if (r >= round) {
            return "oooo";
        } else {
            String move = moves.get(r - 1);

            for (int i = 0; i < 4; i++) {
                if (solution.charAt(i) == move.charAt(i))
                    red++;
            }
            if (move.charAt(0) == solution.charAt(1) || move.charAt(0) == solution.charAt(2) || move.charAt(0) == solution.charAt(3))
                yellow++;
            if (move.charAt(1) == solution.charAt(0) || move.charAt(1) == solution.charAt(2) || move.charAt(1) == solution.charAt(3))
                yellow++;
            if (move.charAt(2) == solution.charAt(1) || move.charAt(2) == solution.charAt(0) || move.charAt(2) == solution.charAt(3))
                yellow++;
            if (move.charAt(3) == solution.charAt(1) || move.charAt(3) == solution.charAt(2) || move.charAt(3) == solution.charAt(0))
                yellow++;

            String ret = "";
            for (int i = 0; i < red; i++) {
                ret += "R";
            }
            for (int i = 0; i < yellow; i++) {
                ret += "Y";
            }
            for (int i = 0; i < (4 - red - yellow); i++) {
                ret += " ";
            }

            return ret;
        }
    }


    private static void showBoard(String solution) {
        System.out.println("Runde " + round);
        System.out.println("----------------------");

        System.out.println("|   X X X X   | " + solution + " |");
        //System.out.println("|   X X X X   |      |");

        System.out.println("|--------------      |");
        for (int i = 12; i > 0; i--) {
            System.out.println("|   " + showMove(i) + "   | " + checkLine(solution, i) + " |");
        }
       // System.out.println("|   O O O O   | oooo |");

        System.out.println("----------------------");
    }

    private static String showMove(int i) {
        if (i >= round) {
            return "O O O O";
        } else {
            String move = moves.get(i-1);
            String dummy = move.charAt(0)+" "+move.charAt(1)+" "+move.charAt(2)+" "+ move.charAt(3);
            return dummy;
        }
    }


    private static void fillAllPosibilities() {
        do {

            String s = getStringCombination();
            boolean alreadyInList = checkIfInList(s, allPosibilities);
            if (!alreadyInList) {
                allPosibilities.add(s);
            }
        } while (allPosibilities.size() < 1680);

        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int i1 = Integer.parseInt(s1);
                int i2 = Integer.parseInt(s2);
                if (i1 < i2) return -1;
                else if (i1 > i2) return 1;
                else return 0;
            }
        };
        Collections.sort(allPosibilities, comp);

        /*

        for (String m : allPosibilities) {
            System.out.println(m);
        }
        System.out.println(allPosibilities.size());

         */


    }

    private static boolean checkIfInList(String s, ArrayList<String> testList) {
        for (String m : testList) {
            if (m.equals(s)) {
                // System.out.println(s + " ist bereits in der Liste");
                return true;
            }
        }
        //  System.out.println(s + " ist noch nicht in der Liste");
        return false;
    }

    public static String getStringCombination() {
        int a, b, c, d;

        a = rnd.nextInt(8) + 1;

        do {
            b = rnd.nextInt(8) + 1;
        } while (b == a);

        do {
            c = rnd.nextInt(8) + 1;
        } while (c == a || c == b);

        do {
            d = rnd.nextInt(8) + 1;
        } while (d == a || d == b || d == c);

        return "" + a + b + c + d;
    }


}


