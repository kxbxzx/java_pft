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
        app.goTo().homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData()
                    .withName("Alexander").withSurname("Stepanov").withAddress("Moscow")
                    .withHomePhone("777774564").withMobilePhone("+4564745").withWorkPhone("66 54")
                    .withEmail("xkbzx3@gmail.com").withEmail2("xkbzx2@gmail.com").withEmail3("xkbzx@gmail.com"));

            app.goTo().homePage();
        }
    }

    @Test
    public void ContactPhonesEmailAddressTests() {
        app.goTo().homePage();
        ContactData contact = app.db().contacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);

        assertThat(cleanAllAddress(contact.getAddress()), equalTo(cleanAllAddress(contactInfoFromEditForm.getAddress())));
        assertThat(contact.getAllEmails(), equalTo(mergeAllEmails(contactInfoFromEditForm)));
        assertThat(contact.getAllPhones(), equalTo(mergeAllPhones(contactInfoFromEditForm)));
    }

    @Test
    public void testContactInfo(){
        app.goTo().homePage();
        ContactData contact = app.db().contacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        ContactData contactInfoFromDetails = app.contacts().infoFromInfoPage(contact);
        assertThat(mergeAllInfo(contactInfoFromEditForm), equalTo(cleanAllInfo(contactInfoFromDetails.getAllInfo())));

    }

    private String mergeAllEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s) -> !s.equals(""))
              .collect(Collectors.joining("\n"));
        }

    private String mergeAllPhones(ContactData contact){
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
              .stream().filter((s) -> ! s.equals(""))
              .map(ContactInfo::cleanAllPhones)
              .collect(Collectors.joining("\n"));
    }

    private String mergeAllInfo (ContactData contact){
        return Arrays.asList(contact.getName(), contact.getSurname(), contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter(s -> ! s.equals(""))
                .map(ContactInfo::cleanAllInfo)
                .collect(Collectors.joining(""));
    }

    public static String cleanAllPhones(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public static String cleanAllAddress(String address) {
        return address.replaceAll("\\s", "");
    }

    public static String cleanAllInfo(String details) {
        return details.replaceAll("\\s", "").replaceAll("[-()]", "").replaceAll("H:", "").replaceAll("M:", "").replaceAll("W:", "");
    }
}
