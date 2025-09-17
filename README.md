# 🚗 Car Rental System (Java Project)
The Car Rental System is a user-friendly Java application that simplifies renting cars. Users can create accounts, log in, and browse available cars with details like brand, model, year, color, and price.



A **user-friendly Java application** that simplifies renting cars.  
Users can **create accounts, log in, and browse available cars** with details such as brand, model, year, color, and price.  

---

## 📸 Screenshots

### 🔑 Login Page
![Screenshot 2025-09-17 101323](https://github.com/user-attachments/assets/1ff0ff98-616b-4444-8768-d527619bf009)


### 📝 Register Page
![create account](https://github.com/user-attachments/assets/20268068-b169-4467-9ef3-8b06f43b216f)
![account created](https://github.com/user-attachments/assets/7aad7bb8-2d26-4075-bfca-70bdde72f185)



### 🚘 Available Cars
![view cars](https://github.com/user-attachments/assets/bc2d1a05-ab1a-4bde-938b-f41416aa78f6)


### 📦 Booking Confirmation
![rented](https://github.com/user-attachments/assets/c7218e3b-a4b8-4e6f-a562-adc6c6249a3f)

---
 ### Display rented car
![show rent](https://github.com/user-attachments/assets/d3703b66-f6b1-49dd-a010-ae28ee9884e1)

## ✨ Features

- 👤 **User Management** – Register, login, and manage accounts.
- 🚗 **Car Listings** – Browse available cars with details like brand, model, year, color, and price.
- 📅 **Booking System** – Rent cars and get booking confirmation.
- 🔒 **Secure Login** – Password-protected user accounts.
- 🎨 **Simple UI** – Easy-to-use interface built with Java Swing/JavaFX (depending on your implementation).

---

## 🛠️ Tech Stack

- **Language:** Java  
- **Database:** MySQL / SQLite (depending on your setup)  
- **IDE:** IntelliJ IDEA  
- **Version Control:** Git & GitHub  

---

## ⚙️ Setup & Installation

1. **Clone the repo:**
   ```bash
   git clone https://github.com/saumyata818/Java-Project.git


Project Structure:
CarRentalSystem/
## 📂 Project Structure

CarRentalSystem/
├── .idea/ # IDE configuration files
├── .settings/ # Project settings
├── bin/ # Compiled bytecode files
├── classes/ # Additional class files (if any)
├── src/ # Source code
│ ├── Controller/ # Handles business logic & operations
│ │ ├── AddNewAccount.java
│ │ ├── AddNewCar.java
│ │ ├── ChangePassword.java
│ │ ├── DeleteCar.java
│ │ ├── EditUserData.java
│ │ ├── Main.java
│ │ ├── Quit.java
│ │ ├── RentCar.java
│ │ ├── ReturnCar.java
│ │ ├── ShowAllRents.java
│ │ ├── ShowSpecUserRents.java
│ │ ├── ShowUserRents.java
│ │ ├── UpdateCar.java
│ │ └── ViewCars.java
│ │
│ └── Model/ # Data models and core entities
│ ├── Admin.java
│ ├── Car.java
│ ├── Client.java
│ ├── Database.java
│ ├── JButton.java
│ ├── JComboBox.java
│ ├── JLabel.java
│ ├── JPasswordField.java
│ ├── JTable.java
│ ├── JTextField.java
│ ├── Operation.java
│ ├── Rent.java
│ └── User.java
│
├── .classpath # Classpath configuration
├── .project # Project metadata
├── CarRentalSystem.iml # IntelliJ project file
├── README.md # Project documentation
└── External Libraries/ # Dependencies and libraries
