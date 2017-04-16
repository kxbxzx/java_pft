package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.contact().gotoHome();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withName("Alexander").withSurname("Stepanov").withAddress("Moscow").withEmail("xkbzzx@gmail.com").withGroup("test1");
        app.contact().create(contact, true);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo
                (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}