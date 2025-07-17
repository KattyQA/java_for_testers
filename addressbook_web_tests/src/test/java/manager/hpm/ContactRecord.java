package manager.hpm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")

public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String address;

    public ContactRecord(int id, String firstname, String lastname, String address){

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public ContactRecord(){

    }

}
