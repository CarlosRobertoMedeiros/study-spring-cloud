package br.com.roberto.agendamento.calendario.api.output;

import br.com.roberto.agendamento.calendario.domain.entity.Reminder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReminderSummary {

    private Long id;
    private String title;
    private LocalDate date;

    public static ReminderSummary of(Reminder reminder){
        return ReminderSummary.builder()
                .id(reminder.getId())
                .title(reminder.getTitle())
                .date(reminder.getDate())
                .build();
    }




}
