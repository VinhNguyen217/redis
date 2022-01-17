package com.java.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@With
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(200)")
    private String name;

    @Column(columnDefinition = "INTEGER")
    private int qty;

    @Column(columnDefinition = "INTEGER")
    private int price;

    private Date timeCreate;

}
