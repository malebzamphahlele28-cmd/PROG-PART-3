/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    public List<String> sentMessages = new ArrayList<>();
    public List<String> disregardedMessages = new ArrayList<>();
    public List<String> storedMessages = new ArrayList<>();
    public List<String> messageHashes = new ArrayList<>();
    public List<String> messageIDs = new ArrayList<>();
    public List<String> recipients = new ArrayList<>();
    public List<String> senders = new ArrayList<>();
    public List<String> allMessages = new ArrayList<>();
    public List<String> allRecipients = new ArrayList<>();
    public List<String> allHashes = new ArrayList<>();
    public List<String> allIDs = new ArrayList<>();
    public List<String> allFlags = new ArrayList<>();

    public void addMessage(String id, String hash, String sender, String recipient, String message, String flag) {
        messageIDs.add(id);
        messageHashes.add(hash);
        recipients.add(recipient);
        senders.add(sender);
        allMessages.add(message);
        allRecipients.add(recipient);
        allHashes.add(hash);
        allIDs.add(id);
        allFlags.add(flag);
        if (flag.equalsIgnoreCase("Sent")) {
            sentMessages.add(message);
        } else if (flag.equalsIgnoreCase("Stored")) {
            storedMessages.add(message);
        } else if (flag.equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(message);
        }
    }

    public static String createHash(String id, int num, String message) {
        String[] words = message.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z]", "").toUpperCase();
        String last = words[words.length - 1].replaceAll("[^A-Za-z!]", "").toUpperCase();
        return id.substring(0, 2) + ":" + num + ":" + first + last;
    }

    public String displaySenderAndRecipient() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allMessages.size(); i++) {
            if (allFlags.get(i).equalsIgnoreCase("Stored")) {
                sb.append("Sender: ").append(senders.get(i))
                  .append(", Recipient: ").append(allRecipients.get(i)).append("\n");
            }
        }
        return sb.toString().trim();
    }

    public String displayLongestMessage() {
        String longest = "";
        for (String m : allMessages) {
            if (m.length() > longest.length()) longest = m;
        }
        return longest;
    }

    public String searchByMessageID(String id) {
        for (int i = 0; i < allIDs.size(); i++) {
            if (allIDs.get(i).equals(id)) {
                return "Recipient: " + allRecipients.get(i) + "\nMessage: " + allMessages.get(i);
            }
        }
        return "Message ID not found.";
    }

    public String searchMessageByIDReturnMessage(String id) {
        for (int i = 0; i < allIDs.size(); i++) {
            if (allIDs.get(i).equals(id)) return allMessages.get(i);
        }
        return "Not found";
    }

    public String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allMessages.size(); i++) {
            if (allRecipients.get(i).equals(recipient)) {
                if (sb.length() > 0) sb.append("\n");
                sb.append(allMessages.get(i));
            }
        }
        return sb.toString();
    }

    public String deleteMessageByHash(String hash) {
        for (int i = 0; i < allHashes.size(); i++) {
            if (allHashes.get(i).equals(hash)) {
                String msg = allMessages.get(i);
                allMessages.remove(i);
                allRecipients.remove(i);
                allHashes.remove(i);
                allIDs.remove(i);
                allFlags.remove(i);
                storedMessages.remove(msg);
                sentMessages.remove(msg);
                messageHashes.remove(hash);
                return "Message: \"" + msg + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    public String displayReport() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allMessages.size(); i++) {
            sb.append("Message Hash: ").append(allHashes.get(i)).append("\n");
            sb.append("Recipient: ").append(allRecipients.get(i)).append("\n");
            sb.append("Message: ").append(allMessages.get(i)).append("\n");
            sb.append("--------------------\n");
        }
        return sb.toString();
    }

    public List<String> loadStoredMessagesFromFile() {
        return storedMessages;
    }
}
