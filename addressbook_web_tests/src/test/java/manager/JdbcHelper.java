package manager;

import model.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);

    }

    public List<Group> geGroupList() {
        var groups = new ArrayList<Group>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
        {
            while (result.next()) {
                groups.add(new Group()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT * FROM `address_in_groups` ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL"))
        {
            if (result.next()) {
                throw new IllegalStateException("DB is corrupted");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isInGroup(int contactId, int groupId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             PreparedStatement statement = conn.prepareStatement(
                     "SELECT COUNT(*) FROM address_in_groups WHERE id = ? AND group_id = ?")) {

            statement.setInt(1, contactId);
            statement.setInt(2, groupId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking group membership", e);
        }
    }

}
