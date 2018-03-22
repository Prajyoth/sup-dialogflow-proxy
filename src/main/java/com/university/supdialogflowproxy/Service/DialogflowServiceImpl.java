package com.university.supdialogflowproxy.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.supdialogflowproxy.Data.Article;
import jdk.nashorn.internal.runtime.URIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class DialogflowServiceImpl implements DialogflowService {


    private List<Article> getListofArticles(List<String> paramList) {
        List<Article> queryResultList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        for(String param : paramList) {
            ResponseEntity<List<Article>> response = restTemplate.exchange("http://node:8080/rest/article/search?searchString=" + param, HttpMethod.GET, null, new ParameterizedTypeReference<List<Article>>() {});
            List<Article> articles = response.getBody();
            queryResultList.addAll(articles);
        }

        return queryResultList;
    }

    @Override
    public List<Article> getQueryResult(String query) {
        List<Article> smalltalk = new ArrayList<>();
      //  https://api.dialogflow.com/v1/query?v=20150910&lang=en&query=hi&sessionId=12345&timezone=America/New_York
        List<String> paramList = new ArrayList<String>();
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + "3622c95bc98c4a12ba12c058c22e4b5b");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        try {
        String encodedquery = URLEncoder.encode(query, "UTF-8");
        String encodedUrl = "https://api.dialogflow.com/v1/query?v=20150910&lang=en&query=" + encodedquery + "&sessionId=12345&timezone=America/New_York";
        ResponseEntity<String> response = restTemplate.exchange(encodedUrl,  HttpMethod.GET, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode name = root.path("result").path("parameters");
            for(JsonNode key: name) {
                paramList.add(key.asText());
            }
            System.out.println(name);
            if(name.size() == 0) {
                System.out.println("You've got a message");
                System.out.println(root.path("result").path("fulfillment").path("speech"));
                smalltalk.add(new Article(null, null, null, root.path("result").path("fulfillment").path("speech").asText()));
                return smalltalk;
            }

            smalltalk = getListofArticles(paramList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return smalltalk;

    }
}
