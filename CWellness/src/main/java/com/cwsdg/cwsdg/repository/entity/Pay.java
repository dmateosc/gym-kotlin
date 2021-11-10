package com.cwsdg.cwsdg.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pays")
public class Pay {

    @Id
    private Long payId;

    @JoinColumn(name = "\"userId\"", referencedColumnName = "\"userId\"")
    private String userId;
    private int amount;
    private Date initDate;
    private Date endDate;



}
