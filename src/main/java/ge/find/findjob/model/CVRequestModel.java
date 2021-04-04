package ge.find.findjob.model;

import java.util.List;

public class CVRequestModel {
    public long id;
    public String description;
    public double expectedSalary;
    public List<Long> professionTags;
    public List<Long> educations;
    public List<Long> experiences;

}
