package malbingi.spring.rest_api_internet_engineering_1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Couple {

    @Id
    private String id;
    private List<Integer> dancersId;
    private Integer competitionId;

    private Integer pointLA;
    private Integer pointST;
    private Integer boostST;
    private Integer boostLA;
}
