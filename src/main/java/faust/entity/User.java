package faust.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    @NotBlank
    @Size(min = 3, max = 12)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    @NotBlank
    @Size(min = 1, max = 20)
    private String firstName;
    @Column(name = "last_name")
    @NotBlank
    @Size(min = 1, max = 20)
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToMany(
            cascade = {DETACH, MERGE, PERSIST, REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups;


    @Transient
    private List<String> groupNames;
    @Transient
    private String passwordConfirm;
    @Transient
    private boolean editingPassword;
    @Transient
    private String currentPasswordHash; //existing in database
    @Transient
    private String currentPassword; //user guess


    public User() {
    }

    public User(String username, String password, String firstName, String lastName, Date birthDate) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isEditingPassword() {
        return editingPassword;
    }

    public void setEditingPassword(boolean editingPassword) {
        this.editingPassword = editingPassword;
    }

    public String getCurrentPasswordHash() {
        return currentPasswordHash;
    }

    public void setCurrentPasswordHash(String currentPasswordHash) {
        this.currentPasswordHash = currentPasswordHash;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);
    }

    public User fillGroupNames() {
        groupNames = new ArrayList<>();
        for (Group group : groups) {
            groupNames.add(group.getGroupName());
        }
        return this;
    }

    public List<String> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(List<String> groupNames) {
        this.groupNames = groupNames;
    }
}
