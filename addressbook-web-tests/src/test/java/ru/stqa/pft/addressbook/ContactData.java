package ru.stqa.pft.addressbook;

public class ContactData {
    private final String name;
    private final String surname;
    private final String email;
    private final String address;

    public ContactData(String name, String surname, String email, String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
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
}
