package com.nixsolutions.task17.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class for Role Entity, using Hibernate.
 */
@Entity
@Table(name="ROLE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NAME"})})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;

    @Column(name="name")
    private String name;

    private static final Logger logger = LoggerFactory
            .getLogger(Role.class);

    /**
     * Default constructor.
     */
    public Role() {
    }
    /**
     * Constructor for all fields.
     */
    public Role(long id, String name) {
       setId(id);
       setName(name);
    }
    /**
     * Constructor for all fields except id.
     */
    public Role(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        if (id <= 0) {
            logger.error("Role Id <= 0");
            throw new IllegalArgumentException("Role Id must be > 0.");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null) {
            logger.error("name == null");
        throw new NullPointerException();
    } else if (name.equals("")) {
            logger.error("Role name is empty");
        throw new IllegalArgumentException("Role name is empty");
    }

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        return name != null ? name.equals(role.name) : role.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                " id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
