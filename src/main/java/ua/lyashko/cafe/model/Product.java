package ua.lyashko.cafe.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column
    private String name;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    List<Bill> bills;

    @Column
    private BigDecimal price;
}
