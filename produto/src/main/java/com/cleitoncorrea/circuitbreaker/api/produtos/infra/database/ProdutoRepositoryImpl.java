package com.cleitoncorrea.circuitbreaker.api.produtos.infra.database;

import com.cleitoncorrea.circuitbreaker.api.produtos.domain.Produto;
import com.cleitoncorrea.circuitbreaker.api.produtos.domain.ProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProdutoRepositoryImpl implements ProdutoRepository {

	private static final List< Produto > PRODUTOS = new ArrayList<>();
	private static long id = 1;

	//Inicializador dos statics
	static {
		PRODUTOS.add(new Produto(nextId(), "Desktop 4GB"));
		PRODUTOS.add(new Produto(nextId(), "Notebook 4GB"));
		PRODUTOS.add(new Produto(nextId(), "Notebook 8GB"));
	}

	@Override
	public void save(Produto produto) {
		produto.setId(nextId());
		PRODUTOS.add(produto);
	}

	@Override
	public Optional<Produto> getOne(Long id) {
		return PRODUTOS.stream().filter(e -> e.getId().equals(id)).findFirst();
	}

	@Override
	public List<Produto> getAll() {
		return new ArrayList<>(PRODUTOS);
	}

	private static long nextId() {
		return id++;
	}
}
