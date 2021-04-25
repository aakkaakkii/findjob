package ge.find.findjob.services;

import ge.find.findjob.api.ProfessioTagnService;
import ge.find.findjob.domain.ProfessionTag;
import ge.find.findjob.repo.ProfessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionTagServiceImpl implements ProfessioTagnService {
    private final ProfessionRepository professionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProfessionTag> load() {
        return professionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ProfessionTag get(long id) {
        return professionRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfessionTag getByTitle(String title) {
        return professionRepository.findByTitle(title);
    }

    @Override
    public ProfessionTag add(ProfessionTag professionTag) {
        return professionRepository.save(professionTag);
    }

    @Override
    public void delete(long id) {
        professionRepository.deleteById(id);
    }
}
