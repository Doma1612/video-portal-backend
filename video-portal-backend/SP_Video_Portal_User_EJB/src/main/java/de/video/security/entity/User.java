/* (C)2023 */
package de.video.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SP_t_user")
@NamedQuery(
        name = "User.findUserByName",
        query = "select u from User u where u.username = :username")
public class User implements Serializable {

    private static final long serialVersionUID = -3990669569183763170L;

    public static final String FIND_BY_NAME = "User.findUserByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String username;

    private String password;

    private int rolle;

    public User(long id, String username, String password, int rolle) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.rolle = rolle;
    }

    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    @Override
    public int hashCode() {
        return (int) getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            return user.username.equals(getUsername());
        }

        return false;
    }

    public int getRolle() {
        return rolle;
    }

    public void setRolle(int rolle) {
        this.rolle = rolle;
    }
}
