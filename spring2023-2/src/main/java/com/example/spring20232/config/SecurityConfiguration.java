package com.example.spring20232.config;


import com.example.spring20232.model.enums.UserRoleEnum;
import com.example.spring20232.repository.UserRepository;
import com.example.spring20232.service.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        System.out.println();
        http.

                        authorizeHttpRequests().
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll().
                        requestMatchers("/", "/about", "/users/login", "/users/register", "/users/login-error", "/error")
                .permitAll().
                // only for moderators
          //requestMatchers().hasRole(UserRoleEnum.MODERATOR.name()).
            // only for admins
          requestMatchers("/pages/admins", "/skills/add", "/certificates/add", "/education/add", "/work-experience/add", "/work-experience/edit/{id}", "/profile")
                .hasRole(UserRoleEnum.ADMIN.name()).
        anyRequest().authenticated().
                and().
                formLogin().
                loginPage("/users/login")
                        .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                                        .defaultSuccessUrl("/", true)
                .failureForwardUrl("/users/login-error")
                .and().logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new ApplicationUserDetailsService(userRepository);
    }



}
