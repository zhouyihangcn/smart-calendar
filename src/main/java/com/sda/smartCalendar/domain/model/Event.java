
package com.sda.smartCalendar.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;


    @Column
    private String name;

    @Column
    private String description;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime event_start;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") // i tak sie tego nie slucha
    private LocalDateTime event_finish;

    @ManyToOne
    @JoinColumn (name="user_email")
    private User user;

    @Column
    private Category category;
}
