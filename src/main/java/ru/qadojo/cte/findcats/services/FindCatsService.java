package ru.qadojo.cte.findcats.services;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;
import ru.qadojo.cte.domain.Cat;
import ru.qadojo.cte.domain.CatGender;
import ru.qadojo.cte.domain.CatRequest;
import ru.qadojo.cte.findcats.model.FindCatsRequest;
import ru.qadojo.cte.storage.CatRequestsRepository;
import ru.qadojo.cte.storage.CatsRepository;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static ru.qadojo.cte.domain.CatRequest.State.CANCELED;

@Service
@RequiredArgsConstructor
public class FindCatsService {

    public static final String FIND_CATS_PROCESS = "findCatsProcess";

    private final RuntimeService camundaRuntimeService;

    private final CatsRepository catsRepository;
    private final CatRequestsRepository catRequestsRepository;

    public void startCatChoice(FindCatsRequest request) {
        camundaRuntimeService.createProcessInstanceByKey(FIND_CATS_PROCESS)
            .setVariable("catBreed", request.getCatBreed())
            .setVariable("catGender", request.getCatGender())
            .setVariable("userEmail", request.getUserEmail())
            .execute();
    }

    public CatRequest saveCatRequest(CatRequest catRequest) {
        return catRequestsRepository.saveAndFlush(catRequest);
    }

    public void cancelCatRequest(CatRequest catRequest) {
        catRequestsRepository.setStateById(catRequest.getId(), CANCELED);
    }

    public List<Cat> findMatchingCats(String breed, CatGender gender) {
        if (isNotBlank(breed) && gender != null) {
            return catsRepository.findAllByBreedAndGender(breed, gender);
        }

        if (isNotBlank(breed)) {
            return catsRepository.findAllByBreed(breed);
        }

        if (gender != null) {
            return catsRepository.findAllByGender(gender);
        }

        final List<Cat> allCats = new ArrayList<>();
        catsRepository.findAll().forEach(allCats::add);

        return allCats;
    }
}
