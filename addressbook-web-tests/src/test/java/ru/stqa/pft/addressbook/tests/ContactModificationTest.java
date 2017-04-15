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
        app.contact().gotoHome();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Alexander").withSurname("Stepanov").withAddress("Moscow").withEmail("xkbzzx@gmail.com").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactModification() {
        app.contact().gotoHome();
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData(), true);
        }
        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifyContact.getId()).withName("Alexander").withSurname("Stepanov").withAddress("Moscow").withEmail("xkbzzx@gmail.com").withGroup("test1");;
        app.contact().modify(contact);
        Contacts after = app.contact().all();

        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    }
}
