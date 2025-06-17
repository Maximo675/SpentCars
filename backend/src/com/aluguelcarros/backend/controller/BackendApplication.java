package com.aluguelcarros.backend.controller; // OU o pacote base do seu projeto

import com.aluguelcarros.backend.controller.BackendApplication; // Importe sua classe principal

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BackendApplication.class) // <--- Esta anotação é crucial
class BackendApplicationTests {

	@Test
	void contextLoads() {
		// Este teste verifica se o contexto do Spring Boot carrega corretamente
	}

}
