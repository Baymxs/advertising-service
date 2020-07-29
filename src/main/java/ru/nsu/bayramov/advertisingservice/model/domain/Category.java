package ru.nsu.bayramov.advertisingservice.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String requestName;

    @Column(columnDefinition = "BIT(1)")
    private Boolean deleted;

    @OneToMany(mappedBy = "category")
    private List<Banner> banners;
}
