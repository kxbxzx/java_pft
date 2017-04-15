package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Alexander on 22.03.2017.
 */
public class ContactModificationTest extends TestBase {

    @Test (enabled = false)
    public void testContactModification() {
        app.getContactHelper().gotoHome();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Alexander", "Stepanov", "xkbzzx@gmail.com", "Moscow", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectEditContact(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Alxndr", "Stpnv", "12345@gmail.com", "Saint-P", "test1");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super  ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
