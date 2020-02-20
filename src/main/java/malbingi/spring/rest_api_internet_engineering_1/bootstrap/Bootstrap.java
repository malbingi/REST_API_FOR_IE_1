package malbingi.spring.rest_api_internet_engineering_1.bootstrap;

import malbingi.spring.rest_api_internet_engineering_1.domain.Competition;
import malbingi.spring.rest_api_internet_engineering_1.domain.Couple;
import malbingi.spring.rest_api_internet_engineering_1.domain.Dancer;
import malbingi.spring.rest_api_internet_engineering_1.domain.STATUS;
import malbingi.spring.rest_api_internet_engineering_1.repositories.CompetitionRepository;
import malbingi.spring.rest_api_internet_engineering_1.repositories.CoupleRepository;
import malbingi.spring.rest_api_internet_engineering_1.repositories.DancerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static malbingi.spring.rest_api_internet_engineering_1.domain.CLASS.*;
import static malbingi.spring.rest_api_internet_engineering_1.domain.STATUS.*;

@Component
public class Bootstrap implements CommandLineRunner {

    private final DancerRepository dancerRepository;
    private final CompetitionRepository competitionRepository;
    private final CoupleRepository coupleRepository;

    public Bootstrap(DancerRepository dancerRepository, CompetitionRepository competitionRepository, CoupleRepository coupleRepository) {
        this.dancerRepository = dancerRepository;
        this.competitionRepository = competitionRepository;
        this.coupleRepository = coupleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (dancerRepository.count().block() == 0) {

            dancerRepository.save(Dancer.builder().classLA(S).classST(B).dateOfJoin(LocalDate.now()).firstName("Janusz").lastName("Randomowy").pointsLA(0).pointsST(0).status(Chairman).build()).block();
            dancerRepository.save(Dancer.builder().classLA(A).classST(S).dateOfJoin(LocalDate.now()).firstName("Janina").lastName("Januszeska").pointsLA(0).pointsST(0).status(Judge).build()).block();

            dancerRepository.save(Dancer.builder().classLA(G).classST(H).dateOfJoin(LocalDate.now()).firstName("Katarzyna").lastName("Frytka").pointsLA(1).pointsST(3).status(ActiveDancer).build()).block();
            dancerRepository.save(Dancer.builder().classLA(D).classST(E).dateOfJoin(LocalDate.now()).firstName("Ambrozy").lastName("Colaska").pointsLA(5).pointsST(8).status(STATUS.Ordinary).build()).block();

        }

        if (competitionRepository.count().block() == 0) {

            competitionRepository.save(Competition.builder().localDateTime(LocalDateTime.now()).location("Kraków Reymonta ").name("Wieczysty").build()).block();
            competitionRepository.save(Competition.builder().localDateTime(LocalDateTime.now()).location("Gdańsk Dubois ").name("Turniej o puchar Rektora PG").build()).block();

        }


        if (coupleRepository.count().block() == 0) {

            coupleRepository.save(Couple.builder().boostLA(1).pointLA(0).boostST(1).pointST(0).build()).block();

        }
    }
}
