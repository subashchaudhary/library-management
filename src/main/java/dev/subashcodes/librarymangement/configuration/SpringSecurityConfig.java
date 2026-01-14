package dev.subashcodes.librarymangement.configuration;


import dev.subashcodes.librarymangement.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c->c.disable()) //csrf disable for postman testing
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/auth/**", "/auth/login/**").permitAll() //permit to all the user
                                 .requestMatchers("/api/books/**", "/issueBook", "/returBook", "/members/**").hasAuthority("ADMIN") //only librarian and admin can access book and member related api
                        .anyRequest().authenticated() //remaining all request should be authenticated
                ) .httpBasic(Customizer.withDefaults()) //
                .formLogin(Customizer.withDefaults())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

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

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
