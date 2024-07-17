package org.snhu.alexanderbaires;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ContactService contactService = new ContactService();//Contact newCustomer = new Contact("1", "Ivan", "Baires", "8008675309", "123 Sesame Street");

        contactService.addContact("Ivan", "Baires", "8008675309", "123 Sesame Street");
        contactService.addContact("Luke", "Skywalker", "8009992345", "Imperial City, Coruscant");
        contactService.addContact("Ivan", "Baires", "8008675309", "123 Sesame Street");
        contactService.printContactList();
    }
}