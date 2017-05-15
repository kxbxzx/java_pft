package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Alexander on 15.05.2017.
 */
public class RemoveContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        Contacts contacts = app.db().contacts();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("Group1").withHeader("Group1").withFooter("Group1"));
        }
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contacts().create(new ContactData()
                    .withName("Alexander").withSurname("Surname").withEmail("xkbzzx@gmail.com")
                    .inGroup(groups.iterator().next()));
            app.goTo().homePage();
        }
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() == 0) {
                app.goTo().homePage();
                Groups group = app.db().groups();
                ContactData modifiedContact = contacts.iterator().next();
                GroupData addedGroup = group.iterator().next();
                app.contacts().selectContactById(modifiedContact.getId());
                app.contacts().addContactToGroup(modifiedContact.getId());
            }
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        Contacts before = app.db().contacts();
        app.goTo().homePage();
        ContactData modifiedContact = before.iterator().next();
        GroupData removeGroup = modifiedContact.getGroups().iterator().next();
        app.contacts().removeContactFromGroup(removeGroup.getId());
        app.contacts().selectContactById(modifiedContact.getId());
        app.contacts().click(By.cssSelector("input[name='remove']"));
        Contacts after = app.db().contacts();
        after.remove(modifiedContact);
        ContactData modifiedContact2 = new ContactData();
        for (ContactData contact : before) {
            if (contact.equals(modifiedContact)) {
                contact.getGroups().remove(removeGroup);
                after.add(contact);
            }
        }
        modifiedContact2.getGroups().remove(removeGroup);
        System.out.println(modifiedContact2);
        assertThat(after, equalTo(before));
    }
}
