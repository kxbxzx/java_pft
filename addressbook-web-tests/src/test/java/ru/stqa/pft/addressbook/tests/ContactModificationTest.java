package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Alexander on 22.03.2017.
 */
public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Alexander", "Stepanov", "xkbzzx@gmail.com", "Moscow", "test1"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Alexander", "Stepanov", "xkbzzx@gmail.com", "Moscow", null), false);
        app.getContactHelper().editContactCreation();

    }
}
