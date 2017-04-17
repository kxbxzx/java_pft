package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Created by Alexander on 16.04.2017.
 */
public class ContactInfo extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().gotoHome();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Alexander").withSurname("Stepanov").withAddress("Moscow").withEmail("xkbzzx@gmail.com").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactInfo(){
        app.contact().gotoHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        String contactInfoFromInfoPage = app.contact().infoFromInfoPage(contact);
    }

    private String mergeContactInfoFromEditForm(ContactData contact){

        String mergeName = Arrays.asList(contact.getName(), contact.getSurname())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining(" "));

        String mergeAddress = Arrays.asList(contact.getAddress())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));

        String mergePhones = Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesEmailAddressTests::cleaned)
                .collect(Collectors.joining("\n"));

        return Arrays.asList(mergeName, mergeAddress, mergePhones, contact.getEmail())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String clearEmptyLines(String s){
        return s.replaceAll("\n\n", "\n");
    }
}
