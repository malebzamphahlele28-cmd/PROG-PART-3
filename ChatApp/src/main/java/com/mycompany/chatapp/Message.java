/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static int totalMessages = 0;
    private static ArrayList<Message> allSent = new ArrayList<>();

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    public Message(String messageID, int messageNumber, String recipient, String messageText) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    private String generateID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) sb.append(rand.nextInt(10));
        return sb.toString();
    }

    public void setMessageID(String id) {
        this.messageID = id;
        this.messageHash = createMessageHash();
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (isCheckRecipientValid()) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public boolean isCheckRecipientValid() {
        if (recipient == null) return false;
        return recipient.matches("^\\+27\\d{9}$");
    }

    public String isCheckRecipientCell() { return checkRecipientCell(); }

    public String checkMessageLength() {
        if (isMessageValid()) return "Message ready to send.";
        else return "Message exceeds 250 characters by " + (messageText.length() - 250) + ", please reduce number of characters.";
    }

    public boolean isMessageValid() {
        return messageText != null && messageText.length() <= 250;
    }

    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) {
            return messageID.substring(0, 2) + ":" + messageNumber + ":EMPTY";
        }
        String[] words = messageText.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z]", "");
        String last = words[words.length - 1].replaceAll("[^A-Za-z]", "");
        return (messageID.substring(0, 2) + ":" + messageNumber + ":" + first + last).toUpperCase();
    }

    public String sentMessage(int option) {
        if (option == 1) {
            totalMessages++;
            allSent.add(this);
            return "Message successfully sent.";
        } else if (option == 0) {
            return "Press 0 to delete the message.";
        } else if (option == 2) {
            allSent.add(this);
            storeMessage();
            return "Message successfully stored.";
        }
        return "Invalid option.";
    }

    public String SentMessage(int option) { return sentMessage(option); }

    public String storeMessage() {
        try {
            // Manual JSON without library - NO IMPORT NEEDED
            String json = "{\"messageID\":\"" + messageID + "\",\"messageHash\":\"" + messageHash + "\",\"recipient\":\"" + recipient + "\",\"message\":\"" + messageText.replace("\"", "'") + "\"}";
            FileWriter fw = new FileWriter("storedMessages.json", true);
            fw.write(json + "\n");
            fw.close();
            return "Message stored.";
        } catch (Exception e) {
            return "Error";
        }
    }

    public int returnTotalMessages() { return totalMessages; }

    public String printMessages() {
        if (allSent.isEmpty()) return "No messages sent yet.";
        StringBuilder sb = new StringBuilder();
        for (Message m : allSent) sb.append(m.printMessageDetails()).append("\n---\n");
        return sb.toString();
    }

    public String printMessageDetails() {
        return "MessageID: " + messageID + "\nMessageHash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }
}
