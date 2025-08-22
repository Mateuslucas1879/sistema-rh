package sistema_rh.sistema_rh.config;

import sistema_rh.sistema_rh.service.MeuUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity  // ativa @PreAuthorize
@Configuration
public class SecurityConfig {

    private final MeuUserDetailsService userDetailsService;

    public SecurityConfig(MeuUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desativa CSRF para testes, ative em produção
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/login").permitAll()
                        .requestMatchers("/funcionarios/deletar/**").hasRole("ADMIN")
                        .requestMatchers("/funcionarios/editar/**").hasAnyRole("ADMIN", "RH")
                        .requestMatchers("/funcionarios/**").hasAnyRole("ADMIN", "RH", "USUARIO")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/funcionarios", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .headers(headers -> headers.frameOptions(frame -> frame.disable())); // permite H2 console

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
