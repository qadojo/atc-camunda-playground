package ru.qadojo.cte.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.qadojo.cte.domain.CatRequest;

@Repository
public interface CatRequestsRepository extends JpaRepository<CatRequest, Long> {

    @Modifying
    @Query("update CatRequest cr set cr.state = ?2 where cr.id = ?1")
    void setStateById(Long catRequestId, CatRequest.State catRequestState);
}
