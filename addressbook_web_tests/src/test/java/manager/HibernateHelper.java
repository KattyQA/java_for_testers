package manager;

import manager.hpm.ContactRecord;
import manager.hpm.GroupRecord;
import model.Contact;
import model.Group;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory =
                new Configuration()
                        .addAnnotatedClass(ContactRecord.class)
                        .addAnnotatedClass(GroupRecord.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    static List<Group> converList(List<GroupRecord> records){
        List<Group> result = new ArrayList<>();
        for (var record : records){
            result.add(convert(record));
        }
        return result;
    }

    private static Group convert(GroupRecord record) {
        return new Group("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(Group group) {
        var id = group.id();
        if ("".equals(id)){
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), group.name(), group.header(), group.footer());
    }

    static List<Contact> convertContactList(List<ContactRecord> records){
        List<Contact> result = new ArrayList<>();
        for (var record : records){
            result.add(convert(record));
        }
        return result;
    }

    private static Contact convert(ContactRecord record){
        return new Contact().withId("" + record.id)
                .withFirstName(record.firstname)
                .withLastName(record.lastname)
                .withAddress(record.address);
    }

    private static ContactRecord convert(Contact contact) {
        var id = contact.id();
        if ("".equals(id)){
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), contact.firstName(), contact.lastName(), contact.address());
    }

    public List<Group> getGroupList(){
        return converList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public List<Contact> getContactList(){
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(Group group) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(group));
            session.getTransaction().commit();
        });
    }

    public List<Contact> getContactsInGroup(Group group) {
        return sessionFactory.fromSession(session -> {
           return convertContactList(session.find(GroupRecord.class, group.id()).contacts);
        });
    }

    public List<Integer> getContactIds() {
        return sessionFactory.fromSession(session -> {
            try {
                return session.createQuery("SELECT c.id FROM ContactRecord c", Integer.class)
                        .list();
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch contact IDs", e);
            }
        });
    }

    public boolean isInGroup(int contactId, int groupId) {
        return sessionFactory.fromSession(session -> {
            Long count = session.createQuery(
                            "SELECT COUNT(ag) FROM `address_in_groups` " +
                                    "WHERE ag.id = :contactId " +
                                    "AND ag.group.id = :groupId",
                            Long.class)
                    .setParameter("contactId", contactId)
                    .setParameter("groupId", groupId)
                    .uniqueResult();

            return count != null && count > 0;
        });
    }
}
