package ru.stqa.pft.addressbook.model;

    public class ContactData {
        private int id = Integer.MAX_VALUE;
        private String name;
        private String surname;
        private String email;
        private String address;
        private String group;
        private String homePhone;
        private String mobilePhone;
        private String workPhone;

        public String getAllPhones() {
            return allPhones;
        }

        public ContactData withAllPhones(String allPhones) {
            this.allPhones = allPhones;
            return this;
        }

        private String allPhones;

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


