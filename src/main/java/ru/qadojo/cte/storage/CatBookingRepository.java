package ru.qadojo.cte.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.qadojo.cte.domain.CatBooking;

@Repository
public interface CatBookingRepository extends CrudRepository<CatBooking, Long> {
}
