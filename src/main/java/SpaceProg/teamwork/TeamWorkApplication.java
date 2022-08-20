package SpaceProg.teamwork;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TeamWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamWorkApplication.class, args);
    }

    //    @Bean
    //    PasswordEncoder passwordEncoder() {
    //        return new BCryptPasswordEncoder();
    //    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

