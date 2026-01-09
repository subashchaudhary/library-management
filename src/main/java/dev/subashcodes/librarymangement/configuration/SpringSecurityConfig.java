package dev.subashcodes.librarymangement.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c->c.disable()) //csrf disable for postman testing
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/auth/**").permitAll() //permit to all the user
//                                .requestMatchers("/api/books/**", "/issueBook", "/returBook", "/members/**")
//                                .requestMatchers("/api/user/getDetails/**").hasAnyRole("USER")
//                                .requestMatchers("/api/librarian/**").hasAnyRole("LIBRARIAN")
                        .anyRequest().authenticated() //remaining all request should be authenticated
                ) .httpBasic(Customizer.withDefaults()) //
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Default security credentails:
     *
     * username : user
     * password: generated password in the console log
     */


    //Now lets customize the security configurations to user the below user

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin123")
//                .roles("ADMIN") // Assigning USER role
//                .build();
//
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user123")
//                .roles("LIBRARIAN") // Assigning USER role for authorization
//                .build();
//
//
//
//        return new InMemoryUserDetailsManager(user, user2);
//
//    }


    @Bean
    PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(); //recommended
       //return NoOpPasswordEncoder.getInstance(); //not recommended for production use

    }


}
