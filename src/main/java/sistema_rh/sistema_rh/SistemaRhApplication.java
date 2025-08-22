package sistema_rh.sistema_rh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaRhApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaRhApplication.class, args);
        System.out.println("✅ Sistema de RH iniciado com sucesso!");
        System.out.println("➡ Acesse: http://localhost:8080/login");
    }
}
