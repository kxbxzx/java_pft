package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Alexander on 22.03.2017.
 */
public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion(){

        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
    }

}
