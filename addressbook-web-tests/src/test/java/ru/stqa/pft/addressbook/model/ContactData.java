package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String surname;
    private final String email;
    private final String address;
    private String group;

    public ContactData(String name, String surname, String email, String address, String group) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGroup() {
        return group;
    }
}
