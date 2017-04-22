package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")

public class ContactData {
    @XStreamOmitField
        private int id = Integer.MAX_VALUE;
    @Expose
        private String name;
    @Expose
        private String surname;
    @Expose
        private String email;
    @Expose
        private String email2;
    @Expose
        private String email3;
    @Expose
        private String address;
    @Expose
        private String group;
    @Expose
        private String homePhone;
    @Expose
        private String mobilePhone;
    @Expose
        private String workPhone;
    @Expose
        private String allPhones;
        private String allInfo;
        private String allEmails;
        private File photo;

        public File getPhoto() {
            return photo;
        }

        public ContactData withPhoto(File photo) {
            this.photo = photo;
            return this;
        }

        public String getEmail2() {
            return email2;
        }

        public ContactData withEmail2(String email2) {
            this.email2 = email2;
            return this;
        }

        public String getEmail3() {
            return email3;
        }

        public ContactData withEmail3(String email3) {
            this.email3 = email3;
            return this;
        }

        public String getAllEmails() {
            return allEmails;
        }

        public ContactData withAllEmails(String allEmails) {
            this.allEmails = allEmails;
            return this;
        }

        public String getAllInfo() {
            return allInfo;
        }

        public ContactData setAllInfo(String allInfo) {
            this.allInfo = allInfo;
            return this;
        }

        public String getAllPhones() {
            return allPhones;
        }

        public ContactData withAllPhones(String allPhones) {
            this.allPhones = allPhones;
            return this;
        }

        public ContactData() {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.address = address;
            this.group = group;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getEmail() {
            return email;
        }

        public String getAddress() {
            return address;
        }

        public String getGroup() {
            return group;
        }

        @Override
        public String toString() {
            return "ContactData{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ContactData that = (ContactData) o;
            if (id != that.id) return false;
            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return surname != null ? surname.equals(that.surname) : that.surname == null;
        }

        @Override
        public int hashCode() {
            int result = 31;
            result = 31 * result + (surname != null ? surname.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        public ContactData withId(int id) {
            this.id = id;
            return this;
        }

        public ContactData withName(String name) {
            this.name = name;
            return this;
        }

        public ContactData withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public ContactData withEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactData withAddress(String address) {
            this.address = address;
            return this;
        }

        public ContactData withGroup(String group) {
            this.group = group;
            return this;
        }

        public String getHomePhone() {
            return homePhone;
        }

        public ContactData withHomePhone(String homePhone) {
            this.homePhone = homePhone;
            return this;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public ContactData withMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
            return this;
        }

        public String getWorkPhone() {
            return workPhone;
        }

        public ContactData withWorkPhone(String workPhone) {
            this.workPhone = workPhone;
            return this;
        }


    }


