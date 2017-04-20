package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.lang.String;
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

    public void create(ContactData contact, boolean creation) {
        gotoAddNewContact();
        fillContactForm(contact, creation);
        submitContactCreation();
        contactCache = null;
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

    public void selectEditContactById(int id) {
        wd.findElement(By.cssSelector("img[alt='Edit']")).click();
    }

    public void selectContact(int index) {
        wd.findElements(By.cssSelector("tr[name=entry] input[name='selected[]']")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s'", id))).click();
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void delete(ContactData contact){
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
    }

    private void deleteSelectedContact() {
        click(By.cssSelector("input[value=Delete]"));
        if (isAlertPresent()) {
            wd.switchTo().alert().accept();
        }
    }

    private void initContactInfoById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }

    public int count(){
        return wd.findElements(By.name("selectedp[]")).size();
    }

    public void modify(ContactData contact){
        selectContactById(contact.getId());
        fillContactForm(contact, false );
        submitContactModification();
        contactCache = null;
        gotoHome();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String surname = cells.get(1).getText();
            String name = cells.get(2).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withName(name).withSurname(surname).withAddress(address)
                    .withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstname).withSurname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public String infoFromInfoPage(ContactData contact) {
        initContactInfoById(contact.getId());
        String contactContent = wd.findElement(By.id("content")).getText();
        wd.navigate().back();
        return contactContent;
    }
}

