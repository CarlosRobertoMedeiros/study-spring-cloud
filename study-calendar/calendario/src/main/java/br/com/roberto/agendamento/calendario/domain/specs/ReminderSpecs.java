package br.com.roberto.agendamento.calendario.domain.specs;

import br.com.roberto.agendamento.calendario.domain.entity.Reminder;
import br.com.roberto.agendamento.calendario.domain.filter.ReminderFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public class ReminderSpecs {
    public static Specification<Reminder> usingFilter(ReminderFilter filter){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter==null){
                return builder.and(predicates.toArray(new Predicate[0]));
            }

            if (StringUtils.hasText(filter.getTitle())){
                predicates.add(builder.equal(root.get("title"),filter.getTitle()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
