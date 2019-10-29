package com.spring.projectsem4.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.projectsem4.model.audit.DateAudit;
import com.spring.projectsem4.util.annotation.EmailConstraint;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = "email")})
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Size(max = 40)
    @JsonProperty("fullName")
    @Column(name = "fullName")
    private String fullName;

    @NotNull
    @Size(max = 15)
    @JsonProperty("username")
    @Column(name = "username")
    private String username;

    @NotNull
    @NaturalId
    @EmailConstraint
    @Size(min = 6, max = 100, message = "{email.size}")
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(max = 100)
    @JsonProperty("password")
    @Column(name = "password")
    @NotEmpty(message = "{password.notempty}")
    private String password;

    @NotNull
    @JsonProperty("birthday")
    @Past
    private Date birthday;

    @NotNull
    @JsonProperty("phone")
    private String phone;

    @NotNull
    @JsonProperty("birthday")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String fullName, String password, String email, String phone, Date birthday, String address) {
        super();
    }
}
