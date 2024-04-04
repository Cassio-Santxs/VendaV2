package app.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import app.entity.Funcionario;
import app.entity.Venda;
import app.repository.FuncionarioRepository;

@SpringBootTest
public class FuncionarioControllerTest {
	
	@Autowired
	FuncionarioController funcionario;
	
	@MockBean
	FuncionarioRepository repository;
	
	@BeforeEach
	void setup() {
		List<Funcionario> list = new ArrayList<>();
		Funcionario funcionario = new Funcionario();
		
		funcionario.setId(1L);
		funcionario.setNome("clovis");
		funcionario.setIdade(30);
		funcionario.setMatricula("456789");
		
		List<Venda> vendas = new ArrayList<>();
		
	//	Venda venda1 = new Venda();
		//vendas.add(venda1);
		funcionario.setVendas(vendas);
		
		Optional<Funcionario> FuncionarioOp = Optional.of(funcionario);
		list.add(funcionario);
		
		FuncionarioRepository repositorymock = spy(FuncionarioRepository.class);
		
		when(this.repository.findById(1L)).thenReturn(FuncionarioOp);
		when(this.repository.save(funcionario)).thenReturn(funcionario);
		when(this.repository.findAll()).thenReturn(list);
		when(this.repository.findByMatricula("456789")).thenReturn(list);
		when(this.repository.findByNome("clovis")).thenReturn(list);
		doNothing().when(repositorymock).delete(funcionario);
	}
	
}
