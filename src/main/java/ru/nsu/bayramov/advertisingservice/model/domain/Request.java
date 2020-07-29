package ru.nsu.bayramov.advertisingservice.model.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
@Setter
@Getter
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Banner banner;

    @Type(type = "text")
    private String userAgent;

    @Type(type = "text")
    private String ipAddress;

    private Date date;
}
