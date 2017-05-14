package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alexander on 22.03.2017.
 */
public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
                app.contacts().create(new ContactData()
                        .withName("Alexander")
                        .withSurname("Stepanov")
                        .withAddress("Moscow")
                        .withEmail("xkbzzx@gmail.com")
                        .withWorkPhone("8600100"));
            }
        }


    @Test
    public void testContactDeletion(){
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.goTo().homePage();
        app.contacts().delete(deletedContact);
        app.goTo().homePage();
        assertEquals(app.contacts().count(), before.size() - 1);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
