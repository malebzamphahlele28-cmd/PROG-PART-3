# PROG-PART-3

A Java console application for sending and managing chat messages.
Built with NetBeans / Maven for PROG POE Part 3.

 Features

- User Registration & Login with username and password validation
- Send messages (max 250 characters)
- Messages stored with:
    - Message ID (10-digit random number)
    - Message Hash (e.g. 00:1:WHERETIME)
    - Recipient (+27 cell number)
    - Message text
    - Flag: Sent / Stored / Disregard
- View all sent messages
- Display longest message sent
- Search message by Message ID
- Search messages by Recipient
- Delete message by Message Hash
- Display full report of all messages (Hash, Recipient, Message)

 How to Run

1. Open project in NetBeans
2. Right-click `ChatApp.java` or `MessageDemo.java` > Run File
3. Register:
      - Username must contain `_` and be max 5 characters (e.g. `kyl_1`)
      - Password must be 8+ characters with capital letter, number, special character (e.g. `Ch&&sec@ke99!`)
4. Login and use the menu to send messages

 How to Run Tests

Right-click on each test file > Test File

- `LoginTest.java` - Tests username, password and login
- `MessageTest.java` - Tests message validation
- `MessageManagerTest.java` - Tests:
    - Sent messages array populated
    - Display longest message
    - Search by Message ID
    - Search by Recipient
    - Delete by Hash
    - Display report

Expected result:
