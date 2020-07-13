package curs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring/applicationContext.xml");
        SpringApplication application = new SpringApplication(Application.class);
//        Map<String, Object> map = new HashMap<>();
//        map.put("server.servlet.context-path", "/WEB-INF/spring");
//        application.setDefaultProperties(map);
        application.run(args);
    }

}