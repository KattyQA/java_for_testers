package model;

import java.util.Objects;

public record Contact(String id, String firstName, String lastName, String address, String email, String homePhone, String photo) {
    public Contact(){
        this("", "name", "last","Perm", "send@mail.ru", "223332424", "");
    }



    public Contact withFirstName(String firstName) {
        return new Contact(this.id, firstName, this.lastName, this.address, this.email, this.homePhone, this.photo);
    }

    public Contact withLastName(String lastName) {
        return new Contact(this.id, this.firstName, lastName, this.address, this.email, this.homePhone, this.photo);
    }

    public Contact withAddress(String address) {
        return new Contact(this.id, this.firstName, this.lastName, address, this.email, this.homePhone, this.photo);
    }

    public Contact withEmail(String email) {
        return new Contact(this.id, this.firstName, this.lastName, this.address, email, this.homePhone, this.photo);
    }

    public Contact withHomePhone(String homePhone) {
        return new Contact(this.id, this.firstName, this.lastName, this.address, this.email, homePhone, this.photo);
    }

    public Contact withId(String id) {
        return new Contact(id, this.firstName, this.lastName, this.address, this.email, this.homePhone, this.photo);
    }

    public Contact withPhoto(String photo) {
        return new Contact(this.id, this.firstName, this.lastName, this.address, this.email, this.homePhone, photo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
