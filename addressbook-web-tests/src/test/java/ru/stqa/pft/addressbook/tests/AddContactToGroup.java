package ru.stqa.pft.addressbook.tests;

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
   public class AddContactToGroup extends TestBase{

   @BeforeMethod
   public void ensurePreconditions(){
      app.goTo().groupPage();
      if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("Group1").withHeader("Group1").withFooter("Group1"));
   }
      Groups groups = app.db().groups();
      if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.contacts().create(new ContactData()
                    .withName("Alexander").withSurname("Stepanov").withEmail("xkbzzx@gmail.com")
                    .inGroup(groups.iterator().next()));
      app.goTo().homePage();
      }
   }

   @Test
   public void testAddContactToGroup(){
      Contacts before = app.db().contacts();
      app.goTo().homePage();
      Groups group = app.db().groups();
      ContactData modifiedContact = before.iterator().next();
      GroupData addedGroup = group.iterator().next();
      app.contacts().selectContactById(modifiedContact.getId());
      app.contacts().addContactToGroup(modifiedContact.getId());
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact.inGroup(addedGroup))));
   }
}

