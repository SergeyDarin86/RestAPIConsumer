import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) {
        String urlGet = "https://reqres.in/api/users/2";

        RestTemplate restTemplate = new RestTemplate();
        // в качестве параметров передаем url, на который отправляем запрос
        // в качестве второго параметра - то, что ожидаем получить в качестве ответа json-объект (пока просто в виде String)
        String responseForGet = restTemplate.getForObject(urlGet,String.class);
        System.out.println(responseForGet);

        // Создание POST-запроса
        String urlPost = "https://reqres.in/api/users";

        Map<String,String>request = new HashMap<>();
        request.put("name", "Sergey");
        request.put("job", "developer");

        // чтобы отправить json, нам необходимо упаковать нашу мапу
        // мы не можем просто так отправить наш Map по сети
        HttpEntity<Map<String,String>>httpEntity = new HttpEntity<>(request);
        System.out.println("==============================================");
        String responseForPost = restTemplate.postForObject(urlPost,httpEntity,String.class);
        System.out.println(responseForPost);

    }
}
