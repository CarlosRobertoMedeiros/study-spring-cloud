package br.com.roberto.agendamento.calendario.domain.repository;

import br.com.roberto.agendamento.calendario.domain.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReminderRepository
        extends JpaRepository<Reminder, Long>,
        JpaSpecificationExecutor<Reminder> {
}
