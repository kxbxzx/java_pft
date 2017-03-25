package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Alexander on 22.03.2017.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }

    public void gotoHome() {
        click(By.name("home page"));
    }

    public void initContactModification() {
        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
    }

    public void editContactCreation() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }


    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }
}
