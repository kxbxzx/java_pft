package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Alexander on 16.04.2017.
 */
public class ContactPhonesEmailAddressTests extends TestBase{

    @Test
    public void testContactPhone(){
        app.contact().gotoHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress()).stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesEmailAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail()).stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesEmailAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesEmailAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }


    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]", "");
    }
}
