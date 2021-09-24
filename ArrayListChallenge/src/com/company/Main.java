package com.company;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final MobilePhone mobilePhone = new MobilePhone();

    public static void main(String[] args) {

        boolean flag = false;
        int choice;
        startPhone();
        printActions();
        while (!flag) {
            System.out.println("\nEnter action: (6 to show available actions)");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0 -> {
                    System.out.println("\nShutting down...");
                    flag = true;
                }
                case 1 -> mobilePhone.printContacts();
                case 2 -> addNewContact();
                case 3 -> updateContact();
                case 4 -> removeContact();
                case 5 -> queryContact();
                case 6 -> printActions();
            }
        }
    }


    private static void addNewContact() {
        System.out.println("Enter new contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phone);
        if (mobilePhone.addNewContact(newContact)) {
            System.out.println("New contact added: name = " + name + ", phone = " + phone);
        } else {
            System.out.println("Cannot add, " + name + " already on file");
        }
    }

    private static void updateContact() {
        System.out.println("Enter existing contact name");
        String name1 = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name1);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");

        }
        System.out.println("Enter new contact name");
        String newName = scanner.nextLine();
        System.out.println("Enter new contact phone number: ");
        String newNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(newName, newNumber);
        if (mobilePhone.updateContact(existingContactRecord, newContact)) {
            System.out.println("Record updated");

        } else {
            System.out.println("Error occurred");
        }
    }

    private static void removeContact() {
        System.out.println("Enter existing contact name");
        String name1 = scanner.nextLine();
        Contact existingRecord = mobilePhone.queryContact(name1);
        if (existingRecord == null) {
            System.out.println("Contact not found");
        }
        if (mobilePhone.removeContact(existingRecord)) {
            System.out.println("Record deleted");
        } else {
            System.out.println("Error occurred");
        }
    }

    private static void queryContact() {
        System.out.println("Enter existing contact name");
        String name1 = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name1);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
            return;
        }
        System.out.println("Contact found !");
        System.out.println("Name: " + existingContactRecord.getName() + " | Phone number: " + existingContactRecord.getPhoneNumber());
    }


    private static void startPhone() {
        System.out.println("\nStarting phone...");
    }

    private static void printActions() {
        System.out.println("Available actions:\npress");
        System.out.println("""
                0  - to shutdown\s
                1  - to print contacts
                2  - to add a new contact
                3  - to update an existing contact
                4  - to remove an existing contact
                5  - query if an existing contact exists
                6  - to print a list of available actions.""");
        System.out.println("Choose your action: ");
    }
}
