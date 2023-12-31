package com.live.debate.resolver.clients;

import com.live.debate.resolver.dtos.CandidateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
@Slf4j
public class CandidateServiceClient {

    private final RestTemplate restTemplate;
    private final String candidateServiceUrl;

    public CandidateServiceClient(
            RestTemplateBuilder builder,
            @Value("http://localhost:8081") final String candidateServiceUrl
    ){
        this.restTemplate = builder.build();
        this.candidateServiceUrl=candidateServiceUrl;
    }

    public CandidateDTO getCandidate(Long id){
        String callUrl = candidateServiceUrl + "/candidates" + "?id=" + id;
        ResponseEntity<CandidateDTO> result = null;


        try {
            result = restTemplate.getForEntity(callUrl, CandidateDTO.class);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return null;
        }

        return result.getBody();
    }

    public CandidateDTO getCandidate(String name){
        String callUrl = candidateServiceUrl + "/candidates/byname" + "?name=" + name;

        ResponseEntity<CandidateDTO> result = null;

        try {
            result = restTemplate.getForEntity(callUrl, CandidateDTO.class);
        }
        catch (Exception e){
            log.error(e.getMessage());
            // TODO: Should I add a candidate that does not exist to the db??
            return null;
        }

        return result.getBody();
    }




}
