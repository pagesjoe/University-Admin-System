package com.ltp.gradesubmission.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception{

        http
            .securityMatcher("/api/**")
            .authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/api/register/**").permitAll()
                .requestMatchers("/api/**").hasRole("ADMIN")
            );

        http.httpBasic(Customizer.withDefaults());
        
        http.csrf(csrf -> csrf.disable());

        return http.build();

    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
            .securityMatcher("/**")
            .authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/**").hasRole("ADMIN")
            )
            .formLogin(form -> form
                .loginPage("/user/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSION_ID")
                .invalidateHttpSession(true)
            );

        return http.build();

    }


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        // JdbcUserDetailsManager a = new JdbcUserDetailsManager(dataSource);
        // a.setUsersByUsernameQuery(null);
         return new JdbcUserDetailsManager(dataSource);
    }


    //Creating a Bean of BcryptPasswordEncoder to use it to encode passwords
    @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}	
}
