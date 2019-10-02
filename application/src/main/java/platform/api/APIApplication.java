package platform.api;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.*;
import org.springframework.boot.autoconfigure.security.servlet.*;
import org.springframework.boot.autoconfigure.web.servlet.error.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;

import javax.annotation.*;

@ComponentScan({"platform"})
@EntityScan({"platform.api"})
@EnableJpaRepositories({"platform.api"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ErrorMvcAutoConfiguration.class})
public class APIApplication {

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void setUp() {

        objectMapper.registerModule(new JavaTimeModule());

    }

    public static void main(String[] args) {

        SpringApplication.run(APIApplication.class, args);

    }

}
