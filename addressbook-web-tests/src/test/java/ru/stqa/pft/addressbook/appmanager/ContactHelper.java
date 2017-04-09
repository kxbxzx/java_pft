package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 22.03.2017.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact, boolean creation) {
        gotoAddNewContact();
        fillContactForm(contact, creation);
        submitContactCreation();
        gotoHome();
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());

        if(creation) new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }

    public void gotoHome() {
        click(By.linkText("home"));
    }

    public void selectEditContact(int index) {
        wd.findElements(By.cssSelector("img[alt='Edit']")).get(index).click();
    }

    public void selectContact(int index) {
        wd.findElements(By.cssSelector("tr[name=entry] input[name='selected[]']")).get(index).click();
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void confirmDeleteContacts() {
        click(By.cssSelector("[value=Delete]"));
        if (isAlertPresent()) {
            wd.switchTo().alert().accept();
        }
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String surname = cells.get(1).getText();
            String name = cells.get(2).getText();
            String email = cells.get(3).getText();
            String address = cells.get(4).getText();
            String group = cells.get(5).getText();
            ContactData contact = new ContactData(id, name, surname, email, address, group);
            contacts.add(contact);
        }
        return contacts;
    }
}

