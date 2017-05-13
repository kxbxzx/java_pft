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

    @Test
    public void ContactPhonesEmailAddressTests() {
        app.contact().gotoHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(cleanAllAddress(contact.getAddress()), equalTo(cleanAllAddress(contactInfoFromEditForm.getAddress())));
        assertThat(contact.getAllEmails(), equalTo(mergeAllEmails(contactInfoFromEditForm)));
        assertThat(contact.getAllPhones(), equalTo(mergeAllPhones(contactInfoFromEditForm)));
    }

    @Test
    public void testContactInfo(){
        app.contact().gotoHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromDetails = app.contact().infoFromInfoPage(contact);
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
