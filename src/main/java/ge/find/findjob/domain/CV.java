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
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private Date creationTime;
    private double expectedSalary;
    @ManyToMany
    private List<ProfessionTag> professionTags;
    private boolean blocked;
    //TODO remove?
    private String cvDoc;

    @OneToMany
    private List<Experience> experiences;
    @OneToMany
    private List<Education> educations;
    @ManyToOne
    private User user;
}
