package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alexander on 22.03.2017.
 */
public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contacts().create(new ContactData()
                    .withName("Alexander")
                    .withSurname("Stepanov")
                    .withAddress("Moscow")
                    .withEmail("xkbzzx@gmail.com")
                    .withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        if (!app.contacts().isThereAContact()) {
            app.contacts().create(new ContactData());
        }
        Contacts before = app.db().contacts();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifyContact.getId()).withName("Alexander").withSurname("Stepanov").withAddress("Moscow").withEmail("xkbzzx@gmail.com").withGroup("test1");;
        app.contacts().modify(contact);
        assertThat(app.group().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    }
}
