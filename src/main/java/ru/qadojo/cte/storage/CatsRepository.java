package ru.qadojo.cte.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.qadojo.cte.domain.Cat;
import ru.qadojo.cte.domain.CatGender;

import java.util.List;

@Repository
public interface CatsRepository extends CrudRepository<Cat, Long> {

    List<Cat> findAllByBreedAndGender(String breed, CatGender gender);

    List<Cat> findAllByBreed(String breed);

    List<Cat> findAllByGender(CatGender gender);
}
