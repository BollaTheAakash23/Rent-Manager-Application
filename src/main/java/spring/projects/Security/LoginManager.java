package spring.projects.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class LoginManager
{
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
    {
        UserDetails landLord = User.builder().username("yoyoboy")
                                             .password(passwordEncoder.encode("123456"))
                                             .roles("ADMIN")
                                             .build();
        
        return new InMemoryUserDetailsManager(landLord);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                                        .formLogin(form -> form
                                                    .loginPage("index.html")
                                                    .defaultSuccessUrl("/home-action/")
                                                    .permitAll())
                                        .logout(Customizer.withDefaults());

        return http.build();
    }
}