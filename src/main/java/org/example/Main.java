package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyFileHandler.createTextFile("companyFile");
        MyFileHandler.fillFileWithData("companyFile", 500, 200000);
        MyFileHandler.readDataFromFile("companyFile");

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\nType [number] -> to get : \n" +
                    "[1] list of N customers who talked for the longest time as callers (and this time)\n" +
                    "[2] list of N customers who talked for the longest time as callees (and this time)\n" +
                    "[3] list of N customers who called the largest number of other customers\n" +
                    "[4] list of N customers who received the calls from the largest number of other customers\n" +
                    "[5] list of N customers who made the largest number of calls\n" +
                    "[6] list of N customers who received largest number of calls\n" +
                    "[7] list of N customers who made the smallest number of calls\n" +
                    "[8] list of N customers who received the smallest number of calls\n" +
                    "[9] full information about customer no k: how many calls he/she made and rece-ived, for how many seconds he/she has to pay\n" +
                    "[0] exit\n"


            );
            int choice = input.nextInt();
            if (choice == 1){
                System.out.println("Type N of customers");
                int n = input.nextInt();
                Call.printLongestCallers(n);
            }
            else if (choice == 2){
                System.out.println("Type N of customers");
                int n = input.nextInt();
                Call.printLongestCallees(n);
            }
            else if (choice == 3){
                System.out.println("Type N of customers");
                int n = input.nextInt();
                Call.printLargestCallersByUniqueCallees(n);
            }
            else if (choice == 4){
                System.out.println("Type N of customers");
                int n = input.nextInt();
                Call.printLargestCalleesByUniqueCallers(n);
            }
            else if (choice == 5){
                System.out.println("Type N of customers");
                int n = input.nextInt();
                Call.printLargestCallersByCallCount(n);
            }
            else if (choice == 6){
                System.out.println("Type N of customers");
                int n = input.nextInt();
                Call.printLargestCalleesByCallCount(n);
            }
            else if (choice == 7){
                System.out.println("Type N of customers");
                int n = input.nextInt();
                Call.printSmallestCallersByCallCount(n);
            }
            else if (choice == 8){
                System.out.println("Type N of customers");
                int n = input.nextInt();
                Call.printSmallestCalleesByCallCount(n);
            }
            else if(choice == 9){
                System.out.println("Type K as a customer number");
                int n = input.nextInt();
                Call.printCustomerInformation(n);
            }
            else if (choice == 0) break;
            else System.out.println("Wrong choice, please try again");
        }
    }
}