package ge.find.findjob.model;

import ge.find.findjob.domain.VacancyType;

import java.util.List;

public class VacancyRequestModel {
    public long id;
    public String title;
    public String description;
    public double salary;
    public VacancyType vacancyType;
    public List<Long> professionTags;
    public long organisationId;
}
