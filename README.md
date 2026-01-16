# Bunny Bistro Accounting Ledger 2.0 🍵₊˚⊹
> **Capstone 1: Accounting Ledger App Revised**
> 
> Year Up United (Bay Bytes) - _Technical Academy Fall 2025_
---

## 🐰 Description

A revised version of the first accounting ledger app. Interactable through the console, designed for a fictional cafe named "Bunny Bistro."
This tool helps the cafe efficiently track their financial activity.

Built entirely in Java to practice application development, specifically to gain familiarity with streams, enums, file structure, and robust error-handling.

## 🛠️ Updates
- Transferred CSV File transaction data into an SQL Database
- Integrated SQL DataBase with the application
- Improved Project Structure


### 🗝️ Key Features

- **Transaction Management**: Easily input new deposits and outgoing payments.

- **Filtering**: View past transactions using pre-set common filters or custom parameters.

- **Transaction History**: Provides a clear history of all financial activity, stored inside a CSV file.


### 💭 Interesting Piece of Code
``` java
//the following is taken from line 322 of TransactionList.java

//collect matching results into list to check if empty
List<Transaction> matchingTransactions = tempStream.toList();

if (matchingTransactions.isEmpty()) {
    System.out.println("No matching transactions found.");
}
else { //print results only if there are matching transactions
    matchingTransactions.stream()
        .sorted(SORT_BY_MOST_RECENT)
        .forEach(t -> System.out.println("> " + t));
}
```

🌟 This code snippet shows how I handled the edge case in which there are no matching transactions.

In my first attempt, I checked whether the `tempStream` variable was empty or not inside the `if` condition. 
However, doing so closed the stream, causing an error to be thrown in the case where the stream was NOT empty.
This is because the `else` statement would try to read from a stream that was already closed due to me checking its elements.

To fix this, I collected the filtered stream into a list using `toList()`, then checked if the list was empty.
Even though `toList()` closed my stream, storing it inside a list allowed me to restore it by creating a new stream from the list.

I thought this was interesting because it was something I struggled with during the programming process, since I was unfamiliar with streams.
It was both challenging and fun to find a workaround to checking whether my stream is empty or not.

---

## 📁 First File Structure
```
src/
├── main/
│   ├── java/
│   │   ├── com.accounting/
│   │   │   ├── Main.java               //entry
│   │   │   │   
│   │   │   ├── HomeScreen.java         //menu
│   │   │   ├── LedgerScreen.java       //menu
│   │   │   ├── ReportScreen.java       //menu
│   │   │   │   
│   │   │   ├── Utility.java            //helper class
│   │   │   ├── TransactionList.java    //manager class
│   │   │   ├── Transaction.java        //model class
│   │   │   │   
│   │   │   ├── HomeOption.java         //enum
│   │   │   ├── LegderOption.java       //enum
│   │   │   └── ReportOption.java       //enum
│   └── resources/
│       └── transactions.csv
```
## 📁 New File Structure
```
src/
├── main/
│   ├── java/
│   │   ├── com.accounting/
│   │   │   ├── BunnyBistroAccountingLedgerApplication.java   // Spring Boot entry point
│   │   │
│   │   │   ├── configurations/
│   │   │   │   └── DataBaseConfig.java                        // database configuration
│   │   │
│   │   │   ├── controllers/
│   │   │   │   ├── ProfileController.java                    // profile REST endpoints
│   │   │   │   ├── TransactionController.java                // transaction REST endpoints
│   │   │   │   └── UserController.java                       // user/auth REST endpoints
│   │   │
│   │   │   ├── data/
│   │   │   │   ├── mysql/
│   │   │   │   │   ├── ProfileDao.java                       // profile DAO
│   │   │   │   │   ├── TransactionDao.java                   // transaction DAO
│   │   │   │   │   └── UserDao.java                          // user DAO
│   │   │
│   │   │   ├── models/
│   │   │   │   ├── authentication/
│   │   │   │   ├── Profile.java                              // profile model
│   │   │   │   ├── Transaction.java                          // transaction model
│   │   │   │   └── User.java                                 // user model
│   │   │
│   │   │   ├── security/
│   │   │   │   ├── jwt/
│   │   │   │   │   ├── JwtAccessDeniedHandler.java           // JWT access handling
│   │   │   │   │   ├── JwtAuthenticationEntryPoint.java     // unauthorized handler
│   │   │   │   │   ├── SecurityUtils.java                   // security helpers
│   │   │   │   │   └── UserModelDetailsService.java         // user details service
│   │   │   │   │
│   │   │   │   ├── UserNotActivatedException.java           // custom auth exception
│   │   │   │   └── WebSecurityConfig.java                   // Spring Security config
│   └── resources/
│       └── application.properties

```
---

## 📸 Screenshots

### .☘︎ ݁˖ Home Screen
<img width="329" height="420" alt="home screen screenshot" src="https://github.com/user-attachments/assets/bd0f2f18-ab2f-4b82-8810-e70c703f0320" />

### .☘︎ ݁˖ Ledger Screen
<img width="329" height="358" alt="ledger screen screenshot" src="https://github.com/user-attachments/assets/754ecbb6-9f14-441b-883f-8920d62dec66" />

### .☘︎ ݁˖ Report Screen
<img width="329" height="409" alt="report screen screenshot" src="https://github.com/user-attachments/assets/bbdb24e4-3457-430e-9f10-66058c4f861c" />

### .☘︎ ݁˖ Display Example (Payments)
<img width="839" height="397" alt="display all payments screenshot" src="https://github.com/user-attachments/assets/353efa6a-eab9-4ccb-a00f-9a0b5bdae7ec" />

### .☘︎ ݁˖ Report Example (Custom Search)
<img width="833" height="602" alt="image" src="https://github.com/user-attachments/assets/9c46c028-bf8d-48ed-bead-d308ff6c13d5" />

### .☘︎ ݁˖ CSV File Example
<img width="793" height="562" alt="starter csv file screenshot" src="https://github.com/user-attachments/assets/284a6721-019c-4603-9ecc-82c2a86e08d1" />


