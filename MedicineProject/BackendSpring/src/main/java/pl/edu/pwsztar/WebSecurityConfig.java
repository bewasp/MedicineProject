package pl.edu.pwsztar;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.edu.pwsztar.domain.dto.cure.ClientDto;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
//    ClientDto client = new ClientDto.Builder()
//            .email("mr.gigami@gmail.com")
//            .name("Mykola")
//            .surname("Pylypenko")
//            .phoneNumber("123456789")
//            .password("123456")
//            .build();
}
