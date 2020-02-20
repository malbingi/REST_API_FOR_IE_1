package malbingi.spring.rest_api_internet_engineering_1.repositories;

import malbingi.spring.rest_api_internet_engineering_1.domain.Couple;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CoupleRepository extends ReactiveMongoRepository<Couple, String> {
}
