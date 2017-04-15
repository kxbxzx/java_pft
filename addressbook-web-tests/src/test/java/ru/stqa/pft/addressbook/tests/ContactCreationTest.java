package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;
import java.util.HashSet;

public class ContactCreationTest extends TestBase {

    @Test (enabled = false)
    public void testContactCreation() {
        app.getContactHelper().gotoHome();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().gotoAddNewContact();
        ContactData contact = new ContactData("Alexander", "Stepanov", "xkbzzx@gmail.com", "Moscow", "test1");
        app.getContactHelper().createContact(contact, true);
        app.getContactHelper().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);

        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
