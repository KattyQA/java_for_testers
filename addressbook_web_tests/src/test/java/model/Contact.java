package model;

public record Contact(String id, String firstName, String lastName, String address, String email, String homePhone) {
    public Contact(){
        this("", "name", "last","Perm", "send@mail.ru", "223332424");
    }

    public Contact withFirstName(String firstName) {
        return new Contact(this.id, firstName, this.lastName, this.address, this.email, this.homePhone);
    }

    public Contact withLastName(String lastName) {
        return new Contact(this.id, this.firstName, lastName, this.address, this.email, this.homePhone);
    }

    public Contact withAddress(String address) {
        return new Contact(this.id, this.firstName, this.lastName, address, this.email, this.homePhone);
    }

    public Contact withEmail(String email) {
        return new Contact(this.id, this.firstName, this.lastName, this.address, email, this.homePhone);
    }

    public Contact withHomePhone(String homePhone) {
        return new Contact(this.id, this.firstName, this.lastName, this.address, this.email, homePhone);
    }

    public Contact withId(String id) {
        return new Contact(id, this.firstName, this.lastName, this.address, this.email, homePhone);
    }

}
