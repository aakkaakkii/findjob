package ge.find.findjob.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private Date creationTime;
    private double salary;
    private VacancyType vacancyType;
    private boolean blocked;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProfessionTag> professionTags;
    @ManyToOne
    @JoinColumn(name = "organisation_id")
    @JsonIgnoreProperties("vacancies")
    private Organisation organisation;
}
