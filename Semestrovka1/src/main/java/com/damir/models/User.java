package com.damir.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String passwordHash;

    @OneToOne
    private FileInfo avatar;

    @ManyToMany(mappedBy = "subscribers", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Course> courses;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column
    private String confirmCode;

    public enum Status{
        ACTIVE,
        UNCONFIRMED
    }


}
