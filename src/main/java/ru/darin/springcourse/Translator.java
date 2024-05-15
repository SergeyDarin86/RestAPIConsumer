package ru.darin.springcourse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translator {

    static String sentenceToTranslate = "";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + "t1.9euelZqVnpiUkM-KkYyXmZSVl56YzO3rnpWanZvNm5bNzIzIzcaKz5mcypTl8_dDb2xN-e9BAjgB_d3z9wMeak3570ECOAH9zef1656VmpaJic-Wio6Sy42Rx5Sdyc7L7_zF656VmpaJic-Wio6Sy42Rx5Sdyc7L.LIOXfXSI56erX5hsNAtEwNj7c_g5rEmcTuh3XY-AemqSKSmRW_zyhkGY1KovZ6VdLAIRlPejCDXg1z1LeadtDw");

        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("folderId","b1gdr734nd3lb35hbdj5");
        jsonData.put("targetLanguageCode","en");

        while (!sentenceToTranslate.equals("В")) {
            System.out.println("Введите предложение на русском языке");
            Scanner scanner = new Scanner(System.in);
            sentenceToTranslate = scanner.nextLine();
            jsonData.put("texts", "[" + sentenceToTranslate + "]");
            HttpEntity<Map<String,String>>request = new HttpEntity<>(jsonData,httpHeaders);
            String response = restTemplate.postForObject(url,request,String.class);
            System.out.println(response);
        }

    }
}
