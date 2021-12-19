package info.behzadian.repository.entity;

import info.behzadian.repository.annotation.Column;
import info.behzadian.repository.annotation.Table;

@Table(tableName = "USER")
public class User {

    @Column(columnName = "ID")
    private Long id;

    @Column(columnName = "FIRST_NAME")
    private String firstName;

    @Column(columnName = "LAST_NAME")
    private String lastName;

    @Column(columnName = "USERNAME")
    private String username;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + ", username=" + username
                + "]";
    }

}
