package ru.nsu.bayramov.advertisingservice.model.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "banners")
@Setter
@Getter
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne
    private Category category;

    @Type(type = "text")
    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "BIT(1)")
    private Boolean deleted;

    @OneToMany(mappedBy = "banner")
    private List<Request> requests;
}
