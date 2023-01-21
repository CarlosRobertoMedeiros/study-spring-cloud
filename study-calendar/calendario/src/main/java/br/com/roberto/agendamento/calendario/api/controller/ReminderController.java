package br.com.roberto.agendamento.calendario.api.controller;

import br.com.roberto.agendamento.calendario.api.output.ReminderDetail;
import br.com.roberto.agendamento.calendario.api.input.ReminderInput;
import br.com.roberto.agendamento.calendario.api.output.ReminderSummary;
import br.com.roberto.agendamento.calendario.domain.entity.Reminder;
import br.com.roberto.agendamento.calendario.domain.filter.ReminderFilter;
import br.com.roberto.agendamento.calendario.domain.repository.ReminderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    private final ReminderRepository reminderRepository;

    public ReminderController(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @GetMapping
    public List<ReminderSummary> getAll(ReminderFilter filter) {
        List<Reminder> reminderList = this.reminderRepository.findAll();
        return reminderList.stream().map(ReminderSummary::of).collect(Collectors.toUnmodifiableList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReminderDetail create(@RequestBody @Valid ReminderInput input) {
        Reminder reminder = input.toDomain();
        this.reminderRepository.save(reminder);
        return ReminderDetail.of(reminder);
    }

    @GetMapping("{reminderId}")
    @RolesAllowed("app_admin_role")
    public ReminderDetail getOne(@PathVariable Long reminderId) {
        Reminder reminder = findOrFail(reminderId);
        return ReminderDetail.of(reminder);
    }

    @PutMapping("{reminderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid ReminderInput input,
                       @PathVariable Long reminderId) {
        Reminder actualReminder = findOrFail(reminderId);
        actualReminder.update(input.toDomain(reminderId));
        this.reminderRepository.save(actualReminder);
    }

    @DeleteMapping("{reminderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long reminderId) {
        Reminder reminder = findOrFail(reminderId);
        this.reminderRepository.delete(reminder);
    }

    private Reminder findOrFail(Long reminderId) {
        return this.reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lembrete não Encontrado"));
    }
}
