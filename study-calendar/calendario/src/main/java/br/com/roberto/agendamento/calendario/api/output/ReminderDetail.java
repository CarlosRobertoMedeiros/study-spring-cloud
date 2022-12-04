package br.com.roberto.agendamento.calendario.api.output;

import br.com.roberto.agendamento.calendario.domain.entity.Reminder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReminderDetail {

    private Long id;
    private String title;
    private String description;
    private LocalDate date;

    public static ReminderDetail of(Reminder reminder){
        return ReminderDetail.builder()
                .id(reminder.getId())
                .title(reminder.getTitle())
                .description(reminder.getDescription())
                .date(reminder.getDate())
                .build();
    }




}
