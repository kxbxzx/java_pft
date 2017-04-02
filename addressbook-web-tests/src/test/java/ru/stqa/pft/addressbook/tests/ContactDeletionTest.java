package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Alexander on 22.03.2017.
 */
public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){

        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Alexander", "Stepanov", "xkbzzx@gmail.com", "Moscow", "test1"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();

    }

}
