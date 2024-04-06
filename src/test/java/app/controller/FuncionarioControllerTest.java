package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import app.entity.Funcionario;
import app.repository.FuncionarioRepository;

@SpringBootTest
public class FuncionarioControllerTest {

	@Autowired
	FuncionarioController controller;
	
	@MockBean
	FuncionarioRepository repository;
	
	@BeforeEach
	void setup() {
		List<Funcionario> list = new ArrayList<Funcionario>();
		Funcionario funcionario = new Funcionario();
		
		funcionario.setId(1L);
		funcionario.setNome("marcio");
		funcionario.setIdade(38);
		funcionario.setMatricula("345678");
		funcionario.setVendas(null);
		
		Optional<Funcionario> funcionarioOp = Optional.of(funcionario);
		
		list.add(funcionario);
		
		FuncionarioRepository repositoryMock = spy(FuncionarioRepository.class);
		
		when(this.repository.save(funcionario)).thenReturn(funcionario);
		when(this.repository.findAll()).thenReturn(list);
		when(this.repository.findById(1L)).thenReturn(funcionarioOp);
		doNothing().when(repositoryMock).deleteById(1L);
		when(this.repository.findByNome("marcio")).thenReturn(list);
		when(this.repository.findByMatricula("345678")).thenReturn(list);
		when(this.repository.findByOlderFuncionario(38)).thenReturn(list);
	}
	
	@Test
	@DisplayName("TESTE METODO SAVE()")
	void testSave() {
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setId(1L);
		funcionario.setNome("marcio");
		funcionario.setIdade(38);
		funcionario.setMatricula("345678");
		funcionario.setVendas(null);
		
		ResponseEntity<String> response = this.controller.save(funcionario);
		String msg = response.getBody();
		
		assertEquals("Salvo com sucesso!", msg);
		
	}
	@Test
	@DisplayName("TESTE METODO listAll()")
	void testFindAll() {
		ResponseEntity<List<Funcionario>> response = this.controller.listAll();
		List<Funcionario> lista = response.getBody();
		
		assertEquals(1, lista.size());
	}
	@Test
	@DisplayName("TESTE METODO findById()")
	void testFindById() {
		
		ResponseEntity<Funcionario> response = this.controller.findById(1L);
		Funcionario obj = response.getBody();
		
		assertEquals(1, obj.getId());
		
	}
	@Test
	@DisplayName("TESTE MÉTODO update()")
	void testUpdate() {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(1L);
		funcionario.setNome("marcio");
		funcionario.setIdade(38);
		funcionario.setMatricula("345678");
		funcionario.setVendas(null);
		
		ResponseEntity<String> response = this.controller.update(funcionario, 1);
		String msg = response.getBody();
		
		assertEquals("Sucesso!", msg);
		//teste
	}
}