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
        app.contact().gotoHome();
        if (app.contact().all().size() == 0) {
                app.contact().create(new ContactData()
                        .withName("Alexander").withSurname("Stepanov").withAddress("Moscow").withEmail("xkbzzx@gmail.com").withGroup("test1"), true);
            }
        }


    @Test
    public void testContactDeletion(){
        if (! app.contact().isThereAContact()){
            app.contact().create(new ContactData(), true);
        }
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();

        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
