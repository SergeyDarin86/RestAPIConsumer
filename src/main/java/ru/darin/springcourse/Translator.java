package ru.darin.springcourse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translator {

    static String sentenceToTranslate = "";

    public static void main(String[] args) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + "token");

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

            // Парсим полученный текст вручную, который в формате json с помощью библиотеки Jackson
            // Можно создать отдельный класс для нашего json, чтобы наш Jackson смог его распарсить автоматически

            // В ответе сервер вернет массив "translations", внутри которого ключ "text"
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            System.out.println("===================================================");
            System.out.println(jsonNode.get("translations").get(0).get("text"));

            // Работа с классом YandexResponse для получения ответа
            System.out.println("====================================================");
            TranslatorResponse translatorResponse = restTemplate.postForObject(url,request, TranslatorResponse.class);
            translatorResponse.getTranslations().forEach(translation -> System.out.println(translation.getText()));
        }

    }
}
