package ge.find.findjob.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private Date creationTime;
    private double salaryFrom;
    private double salaryTo;
    private VacancyType vacancyType;

    @ManyToMany
    private List<ProfessionTag> professionTags;
    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;
}
