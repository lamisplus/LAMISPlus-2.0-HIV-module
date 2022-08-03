package org.lamisplus.modules.hiv.service.activity;

import lombok.RequiredArgsConstructor;
import org.lamisplus.modules.hiv.domain.dto.PatientActivity;
import org.lamisplus.modules.hiv.domain.entity.ArtPharmacy;
import org.lamisplus.modules.hiv.repositories.ArtPharmacyRepository;
import org.lamisplus.modules.hiv.service.PatientActivityProvider;
import org.lamisplus.modules.patient.domain.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyActivityProvider  implements PatientActivityProvider {

private  final ArtPharmacyRepository pharmacyRepository;
    @Override
    public List<PatientActivity> getActivitiesFor(Person person) {
        List<ArtPharmacy> pharmacies = pharmacyRepository.getArtPharmaciesByPerson (person);
        String name = "Pharmacy Refill";
       return pharmacies.stream ()
                .map (artPharmacy ->  new PatientActivity (artPharmacy.getId (), name, artPharmacy.getVisitDate (), null, "Pharmcy"))
                .collect(Collectors.toList());
    }
}
