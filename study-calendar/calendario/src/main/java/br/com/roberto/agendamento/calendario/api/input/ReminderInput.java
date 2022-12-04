package br.com.roberto.agendamento.calendario.api.input;

import br.com.roberto.agendamento.calendario.domain.entity.Reminder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ReminderInput {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private LocalDate date;


    public Reminder toDomain(){
        return toDomain(null);
    }

    public Reminder toDomain(Long reminderId){
        return Reminder.builder()
                .id(reminderId)
                .title(this.title)
                .description(this.description)
                .date(this.date)
                .build();
    }

}
