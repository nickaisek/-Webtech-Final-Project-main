package com.akagera.park.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Form {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
@Column
    private String carName;
    @Column

    private int carPrice;
    @Column
    private String payment;
    @Column
    private String status;

}
