package org.lamisplus.modules.hiv.domain.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.hibernate.annotations.Type;
import org.lamisplus.modules.patient.domain.entity.Person;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "art_pharmacy")
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArtPharmacy extends HivAuditEntity implements Persistable<Long> ,Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @JoinColumn(name = "person_id")
    @ManyToOne
    private Person person;

    @PastOrPresent
    @NotNull
    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    @Column(name = "ard_screened")
    private Boolean adrScreened;

    @Column(name = "prescription_error")
    private Boolean prescriptionError;

    @Column(name = "adherence")
    private Boolean adherence;

    @Column(name = "mmd_type")
    private String mmdType;

    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;

    @Future
    @Column(name = "next_appointment", nullable = false)
    private LocalDate nextAppointment;

    @Type(type = "jsonb-node")
    @Column(columnDefinition = "jsonb")
    private JsonNode extra;


    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode adverseDrugReactions;

    @OneToMany
    @ToString.Exclude
    private Set<Regimen> regimens = new LinkedHashSet<> ();


    @Override
    public boolean isNew() {
        return id == null;
    }
}