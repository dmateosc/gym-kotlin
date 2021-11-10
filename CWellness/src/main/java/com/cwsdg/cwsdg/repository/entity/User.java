package com.cwsdg.cwsdg.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"userId\"", nullable = false)
    private Long userId;

    @Column(name = "name")
    private String name;


    private String dni;
    private String years;
    private Date initDate;

}
