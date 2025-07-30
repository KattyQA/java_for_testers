package manager.hpm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.security.PublicKey;

@Entity
@Table(name = "addressbook")

public class ContactRecord {
    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String address;
    public String home;
    public String work;
    public String mobile;
    public String phone2;
    public String email;
    public String email2;
    public String email3;

    public ContactRecord(int id, String firstname, String lastname, String address){

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public ContactRecord(){

    }

}
