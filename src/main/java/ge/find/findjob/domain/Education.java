package ge.find.findjob.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String school;
    private String degree;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JsonIgnoreProperties({"password", "email"})
    private User user;
}
