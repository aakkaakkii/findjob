package ge.find.findjob.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private String address;
    private String phone;
    private String mail;
    private String website;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organisation")
    private List<Vacancy> vacancies;

    @ManyToOne
    private User user;

}
