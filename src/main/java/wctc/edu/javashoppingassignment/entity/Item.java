package wctc.edu.javashoppingassignment.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name="Item")
public class Item {
    @Id
    @Column(name="item_id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private double price;
    @Column(name="inventory")
    private int inventory;
}
