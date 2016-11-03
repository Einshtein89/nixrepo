package com.nixsolutions.task17.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class for User entity, using Hibernate.
 */
@Entity
@Table(name = "USER", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "LOGIN" }),
        @UniqueConstraint(columnNames = { "EMAIL" }) })
public class User implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = javax.persistence.CascadeType.MERGE)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @Column(name = "LOGIN")
    @NotEmpty(message = "{error.login.required}")
    @Size(min = 5, max = 15, message = "{error.login.size}")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{error.login.regex}")
    private String login;

    @Column(name = "PASSWORD")
    @NotEmpty(message = "{error.password.required}")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{error.password.regex}")
    @Size(min = 5, max = 15, message = "{error.password.size}")
    private String password;

    @Column(name = "PASSWORDAGAIN")
    @NotEmpty(message = "{error.passwordagain.required}")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{error.passwordagain.regex}")
    @Size(min = 5, max = 15, message = "{error.passwordagain.size}")
    private String passwordagain;

    @Column(name = "EMAIL")
    @NotEmpty(message = "{error.email.required}")
    @Email(message = "{error.email.pattern}")
    private String email;

    @Column(name = "FIRSTNAME")
    @NotEmpty(message = "{error.firstName.required}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{error.firstName.regex}")
    private String firstName;

    @Column(name = "LASTNAME")
    @NotEmpty(message = "{error.lastName.required}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{error.lastName.regex}")
    private String lastName;

    @Column(name = "BIRTHDAY")
    @NotNull(message = "{error.birthday.required}")
    @Past(message = "{error.birthday.past}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructor for all fields except passwordagain.
     */
    public User(long id, Role role, String login, String password, String email,
            String firstName, String lastName, Date birthday,
            String passwordagain) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.passwordagain = passwordagain;
    }

    /**
     * Constructor for all fields except id.
     *
     */
    public User(Role role, String login, String password, String email,
            String firstName, String lastName, Date birthday) {
        setRole(role);
        setLogin(login);
        setPassword(password);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
    }

    /**
     * Constructor for all fields.
     */
    public User(Role role, String login, String password, String email,
            String firstName, String lastName, Date birthday,
            String passwordagain) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.passwordagain = passwordagain;
    }

    public long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordagain() {
        return passwordagain;
    }

    public void setPasswordagain(String passwordagain) {
        this.passwordagain = passwordagain;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        if (role == null) {
            logger.error("role == null");
            throw new NullPointerException();
        } else if (role.equals("")) {
            logger.error("role is empty");
            throw new IllegalArgumentException("role is empty");
        }
        this.role = role;
    }

    public void setLogin(String login) {
        if (login == null) {
            logger.error("login == null");
            throw new NullPointerException();
        } else if (login.equals("")) {
            logger.error("login is empty");
            throw new IllegalArgumentException("login is empty");
        }
        this.login = login;
    }

    public void setPassword(String password) {
        if (password == null) {
            logger.error("password == null");
            throw new NullPointerException();
        } else if (password.equals("")) {
            logger.error("password is empty");
            throw new IllegalArgumentException("password is empty");
        }
        this.password = password;
    }

    public void setEmail(String email) {
        if (email == null) {
            logger.error("email == null");
            throw new NullPointerException();
        } else if (email.equals("")) {
            logger.error("email is empty");
            throw new IllegalArgumentException("email is empty");
        }
        this.email = email;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            logger.error("firstName == null");
            throw new NullPointerException();
        } else if (firstName.equals("")) {
            logger.error("firstName is empty");
            throw new IllegalArgumentException("firstName is empty");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            logger.error("lastName == null");
            throw new NullPointerException();
        } else if (lastName.equals("")) {
            logger.error("lastName is empty");
            throw new IllegalArgumentException("lastName is empty");
        }
        this.lastName = lastName;
    }

    public void setBirthday(Date birthday) {
        if (birthday == null) {
            logger.error("birthday == null");
            throw new NullPointerException();
        } else if (birthday.equals("")) {
            logger.error("birthday is empty");
            throw new IllegalArgumentException("birthday is empty");
        }
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (id != user.id)
            return false;
        if (role != null ? !role.equals(user.role) : user.role != null)
            return false;
        if (login != null ? !login.equals(user.login) : user.login != null)
            return false;
        if (password != null ? !password.equals(user.password)
                : user.password != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null)
            return false;
        if (firstName != null ? !firstName.equals(user.firstName)
                : user.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(user.lastName)
                : user.lastName != null)
            return false;
        return birthday != null ? birthday.equals(user.birthday)
                : user.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{ id = " + id + ", ROLE = " + role.getName() + ", LOGIN = "
                + login + ", PASSWORD = " + password + ", PASSWORD_AGAIN = "
                + passwordagain + ", EMAIL = " + email + ", FIRSTNAME = "
                + firstName + ", LASTNAME = " + lastName + ", BIRTHDAY = "
                + birthday + "}";
    }

}