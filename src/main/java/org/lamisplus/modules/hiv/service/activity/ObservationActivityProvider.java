package org.lamisplus.modules.hiv.service.activity;

import lombok.RequiredArgsConstructor;
import org.lamisplus.modules.hiv.domain.dto.PatientActivity;
import org.lamisplus.modules.hiv.domain.entity.Observation;
import org.lamisplus.modules.hiv.repositories.ObservationRepository;
import org.lamisplus.modules.hiv.service.PatientActivityProvider;
import org.lamisplus.modules.patient.domain.entity.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ObservationActivityProvider implements PatientActivityProvider {
    private final ObservationRepository observationRepository;

    @Override
    public List<PatientActivity> getActivitiesFor(Person person) {
        List<Observation> observations = observationRepository.getAllByPerson (person);
        return observations.stream ()
                .map (observation -> {
                    String type = observation.getType ();
                    String path = type.replaceAll (" ", "-");
                    return new PatientActivity (observation.getId (), type, observation.getDateOfObservation (), "", path);
                })
                .collect (Collectors.toList ());

    }
}
