package malbingi.spring.rest_api_internet_engineering_1.repositories;

import malbingi.spring.rest_api_internet_engineering_1.domain.Dancer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DancerRepository extends ReactiveMongoRepository<Dancer, String> {
}
