/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
package com.mycompany.chatapp;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MessageManagerTest {
    MessageManager mm;

    @Before
    public void setUp() {
        mm = new MessageManager();
        mm.addMessage("001", "00:0:DIDCAKE", "Me", "+27834557896", "Did you get the cake?", "Sent");
        mm.addMessage("002", "00:1:WHERETIME", "Me", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        mm.addMessage("003", "00:2:YOHOGATE", "Me", "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        mm.addMessage("0838884567", "00:3:ITTIME", "Me", "0838884567", "It is dinner time!", "Sent");
        mm.addMessage("005", "00:4:OKYOU", "Me", "+27838884567", "Ok, I am leaving without you.", "Stored");
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        assertEquals("Did you get the cake?", mm.sentMessages.get(0));
        assertEquals("It is dinner time!", mm.sentMessages.get(1));
        assertEquals(2, mm.sentMessages.size());
    }

    @Test
    public void testDisplayLongestMessage() {
        assertEquals("Where are you? You are late! I have asked you to be on time.", mm.displayLongestMessage());
    }

    @Test
    public void testSearchByMessageID() {
        String result = mm.searchMessageByIDReturnMessage("0838884567");
        assertEquals("It is dinner time!", result);
    }

    @Test
    public void testSearchByRecipient() {
        String result = mm.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        String result = mm.deleteMessageByHash("00:1:WHERETIME");
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", result);
    }

    @Test
    public void testDisplayReport() {
        String report = mm.displayReport();
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
        assertTrue(report.contains("Did you get the cake?"));
    }
}
