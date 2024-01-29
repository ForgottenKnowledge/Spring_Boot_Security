package ru.spring.boot_security.demo.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone")
    private Long phone;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;

    public User(String name, String surname, Integer age, Long phone, String password, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.password = password;
        this.roles = roles;
    }

    public User(String name, String surname, Integer age, Long phone, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.password = password;
    }
}
