package malbingi.spring.rest_api_internet_engineering_1.repositories;

import malbingi.spring.rest_api_internet_engineering_1.domain.Competition;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CompetitionRepository extends ReactiveMongoRepository<Competition, String> {
}
