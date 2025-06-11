package model;

public record Contact(String firstName, String middleName, String lastName) {
    public Contact(){
        this("new", "new","new");
    }

    public Contact withFirstName(String firstName) {
        return new Contact(firstName, this.middleName, this.lastName);
    }

    public Contact withMiddleName(String middleName) {
        return new Contact(this.firstName, middleName, this.lastName);
    }

    public Contact withLastName(String lastName) {
        return new Contact(this.firstName, this.middleName, lastName);
    }

}
