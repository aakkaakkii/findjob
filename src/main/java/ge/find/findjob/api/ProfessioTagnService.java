package ge.find.findjob.api;

import ge.find.findjob.domain.ProfessionTag;

import java.util.List;

public interface ProfessioTagnService {
    List<ProfessionTag> load();
    ProfessionTag get(long id);
    ProfessionTag getByTitle(String title);
    ProfessionTag add(ProfessionTag professionTag);
    void delete(long id);
}
