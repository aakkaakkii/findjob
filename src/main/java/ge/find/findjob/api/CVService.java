package ge.find.findjob.api;

import ge.find.findjob.domain.CV;
import ge.find.findjob.model.CVRequestModel;

import java.util.List;

public interface CVService {
    List<CV> load();
    List<CV> loadByUserId(long id);
    CV get(long id);
    CV add(CVRequestModel cvRequest);
    CV update(CVRequestModel cvRequest);
    void delete(long id);
    void adminDelete(long id);
    CV blockCV(long id);
    CV unblockCV(long id);
}
