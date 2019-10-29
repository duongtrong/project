package com.spring.projectsem4.model;

import com.spring.projectsem4.model.enumm.RoleName;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role{

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleName roleName;
}
