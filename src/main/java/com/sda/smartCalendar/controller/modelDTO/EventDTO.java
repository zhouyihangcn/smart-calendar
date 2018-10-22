package com.sda.smartCalendar.controller.modelDTO;

import com.sda.smartCalendar.domain.model.Category;
import com.sda.smartCalendar.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private UUID id;
    @Size(max = 100, message = "Pole może zawierać maksymalnie 100 znaków")
    private String name;
    @Size(max = 500, message = "Pole może zawierać maksymalnie 500 znaków")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime event_start;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime event_finish;

    private User user;
    private Category category;
}
