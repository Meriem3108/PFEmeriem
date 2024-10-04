package com.example.testpfe.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactiver CSRF
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/home","/guide", "/traduction","/traductionscenario", "/register", "/login","/registration","/users").permitAll() // Ajout de /register et /login
                        .requestMatchers("/api/compiler/template/compile").permitAll()
                        .requestMatchers("/api/compiler/scenario/compile").permitAll()
                        .requestMatchers("/api/uploadTemplate").permitAll()
                        .requestMatchers("/api/uploadScenario").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/header.html", "/footer.html", "/sidebar.html").permitAll()
                        .anyRequest().authenticated()

                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Page de connexion personnalisée
                        .defaultSuccessUrl("/home", true) // Redirection après connexion réussie
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL de déconnexion
                        .logoutSuccessUrl("/login?logout") // Redirection après déconnexion
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Pour le cryptage des mots de passe
    }
}
