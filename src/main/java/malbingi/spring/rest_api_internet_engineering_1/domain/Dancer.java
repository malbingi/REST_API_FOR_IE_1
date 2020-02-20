package malbingi.spring.rest_api_internet_engineering_1.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dancer {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private String firstName;
    private String lastName;

    private LocalDate dateOfJoin;

    private CLASS classLA;
    private CLASS classST;

    private Integer pointsLA;
    private Integer pointsST;

    private STATUS status;
}
