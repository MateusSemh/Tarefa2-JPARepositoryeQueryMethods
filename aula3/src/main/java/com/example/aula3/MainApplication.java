package com.example.aula3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.aula3.models.Categoria;
import com.example.aula3.models.Produto;
import com.example.aula3.repository.CategoriaRepository;
import com.example.aula3.repository.ProdutoRepository;

import java.util.List;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Exemplo de criação de produtos
        Produto produto1 = new Produto();
        produto1.setNome("Produto 1");
        produto1.setPreco(100.0);

        Produto produto2 = new Produto();
        produto2.setNome("Produto 2");
        produto2.setPreco(150.0);

        Produto produto3 = new Produto();
        produto3.setNome("Outro Produto");
        produto3.setPreco(80.0);

        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
        produtoRepository.save(produto3);

        // Exemplo de consulta de produtos por preço
        System.out.println("Produtos com preço maior que 100:");
        List<Produto> produtosPrecoMaiorQue100 = produtoRepository.findByPrecoGreaterThan(100.0);
        produtosPrecoMaiorQue100.forEach(System.out::println);

        // Exemplo de consulta de categorias por nome
        System.out.println("Categorias com nome iniciando por 'Categoria':");
        List<Categoria> categoriasComNome = categoriaRepository.findByNomeStartingWith("Categoria");
        categoriasComNome.forEach(System.out::println);

        // Exemplo de consulta de categoria com produtos (utilizando LEFT JOIN FETCH)
        System.out.println("Categoria com produtos:");
        Categoria categoriaComProdutos = categoriaRepository.findCategoriaWithProdutosById(1L);
        System.out.println("Categoria: " + categoriaComProdutos.getNome());
        System.out.println("Produtos da categoria:");
        categoriaComProdutos.getProdutos().forEach(produto -> System.out.println(produto.getNome()));
    }
}
