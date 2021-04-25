package ge.find.findjob.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organisation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private String address;
    private String phone;
    private String mail;
    private String website;
    private boolean blocked;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "organisation")
    @JsonIgnoreProperties("organisation")
    private List<Vacancy> vacancies;

    @ManyToOne
    @JsonIgnoreProperties({"password", "email"})
    private User user;

}
