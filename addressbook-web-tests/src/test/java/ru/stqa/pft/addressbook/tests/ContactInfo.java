package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Alexander on 16.04.2017.
 */
public class ContactInfo extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().gotoHome();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Alexander")
                    .withSurname("Stepanov")
                    .withAddress("Moscow")
                    .withHomePhone("666 666")
                    .withMobilePhone("+999777")
                    .withWorkPhone("123")
                    .withEmail("xkbzzx@gmail.com")
                    .withEmail2("ololol@protonmail.ch")
                    .withEmail3("omgash@hotmail.com"));
        }
    }

    @Test
    public void testContactInfo(){
        app.contact().gotoHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromInfoPage = app.contact().infoFromInfoPage(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeAllEmails(contactInfoFromEditForm)));
    }

    private String mergeAllEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s) -> !s.equals(""))
              .collect(Collectors.joining("\n"));
        }

    private String mergePhones(ContactData contact){
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
              .stream().filter((s) -> ! s.equals(""))
              .map(ContactInfo::clearEmptyLines)
              .collect(Collectors.joining("\n"));
    }

    public static String clearEmptyLines(String s){
        return s.replaceAll("\n\n", "\n");
    }
}
