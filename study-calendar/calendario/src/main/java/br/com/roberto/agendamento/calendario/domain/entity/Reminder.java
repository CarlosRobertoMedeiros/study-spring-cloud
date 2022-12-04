package br.com.roberto.agendamento.calendario.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Builder
@Table(name = "TB_Reminder")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate date;

    protected Reminder(){
        //JPA
    }
    public Reminder(Long id, String title, String description, LocalDate date) {

        Objects.requireNonNull(title);
        Objects.requireNonNull(description);
        Objects.requireNonNull(date);

        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public void update(Reminder reminder){
        this.title = reminder.title;
        this.description = reminder.description;
        this.date = reminder.date;
    }
}
