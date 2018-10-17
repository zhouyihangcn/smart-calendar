package com.sda.smartCalendar.controller.modelDTO;

import com.sda.smartCalendar.domain.model.Category;
import com.sda.smartCalendar.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private UUID id;
    private String name;
    private String description;
    private LocalDateTime event_start;
    private LocalDateTime event_finish;
    private User user;
    private Category category;

}
