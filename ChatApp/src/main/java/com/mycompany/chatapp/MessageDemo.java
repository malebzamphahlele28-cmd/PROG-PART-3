/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;

public class MessageDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("=      MESSAGE DEMO APP         =");
        System.out.println("=================================");

        System.out.println("how many messages would you wish to send");
        int numberOfMessages = input.nextInt();
        input.nextLine();

        for (int i = 0; i < numberOfMessages; i++) {
            System.out.println();
            System.out.println("Message " + (i + 1));
            System.out.println("===========================================");

            String recipient;
            Message messageObject;

            do {
                System.out.print("Enter recipient cell number using +27: ");
                recipient = input.nextLine();
                messageObject = new Message(i, recipient, "Temporary message");
                System.out.println(messageObject.isCheckRecipientCell());
            } while (!messageObject.isCheckRecipientValid());

            String messageText;

            do {
                System.out.print("Enter your message: ");
                messageText = input.nextLine();
                messageObject = new Message(i, recipient, messageText);
                System.out.println(messageObject.checkMessageLength());
            } while (!messageObject.isMessageValid());

            Message finalMessage = new Message(i, recipient, messageText);

            System.out.println();
            System.out.println("Generated information");
            System.out.println("========================");
            System.out.println("Message ID: " + finalMessage.getMessageID());
            System.out.println("Message Hash: " + finalMessage.getMessageHash());

            System.out.println();
            System.out.println("What would you like to do now?");
            System.out.println("1 - send message");
            System.out.println("0 - Disregard message");
            System.out.println("2 - store message");
            System.out.println("select an option");
            int option = input.nextInt();
            input.nextLine();

            System.out.println(finalMessage.sentMessage(option));
            if (option == 1) {
                System.out.println();
                System.out.println("MESSAGE DETAILS");
                System.out.println(finalMessage.printMessageDetails());
            }
        }

        System.out.println();
        System.out.println("=================================");
        System.out.println("=            SUMMARY            =");
        System.out.println("=================================");

        Message summary = new Message("000000", 0, "+27365458695", "summary");

        System.out.println("Total message sent: " + summary.returnTotalMessages());
        System.out.println();
        System.out.println("MESSAGES SENT");
        System.out.println(summary.printMessages());

        input.close();
    }
}