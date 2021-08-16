package com.quote.generator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ufhopla
 * on 10/08/2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<QuoteDao> quoteDaos = new HashSet<>();

}


