/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {
    @Test public void testRecipientSuccess() {
        Message m = new Message(0, "+27718693002", "Hi");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }
    @Test public void testRecipientFailure() {
        Message m = new Message(0, "08575975", "Hi");
        assertTrue(m.checkRecipientCell().contains("incorrectly formatted"));
    }
    @Test public void testLengthSuccess() {
        Message m = new Message(0, "+27718693002", "Hi Keegan, did you receive the payment?");
        assertEquals("Message ready to send.", m.checkMessageLength());
    }
    @Test public void testLengthFailure() {
        Message m = new Message(0, "+27718693002", "a".repeat(300));
        assertFalse(m.isMessageValid());
    }
    @Test public void testHash() {
        Message m = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        m.setMessageID("0012345678");
        assertEquals("00:0:HITONIGHT", m.createMessageHash());
    }
    @Test public void testIDLength() {
        Message m = new Message(0, "+27718693002", "Test");
        assertEquals(10, m.getMessageID().length());
    }
    @Test public void testSentMessage() {
        Message m = new Message(0, "+27718693002", "Test");
        assertEquals("Message successfully sent.", m.sentMessage(1));
    }
    @Test public void testStoredMessage() {
        Message m = new Message(0, "+27718693002", "Test");
        assertEquals("Message successfully stored.", m.sentMessage(2));
    }
}