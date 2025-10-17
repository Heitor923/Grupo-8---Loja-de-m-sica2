package com.MiniSistemaDeCadastro.RepositoryMemoria;

import com.MiniSistemaDeCadastro.entidades.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositoryMemoria {
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    public Produto adicionar(Produto p) {
        p.setId(proximoId++);
        produtos.add(p);
        return p;
    }

    public List<Produto> listar() { return produtos; }

    public Produto buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public boolean atualizar(int id, String nome, double preco, int quantidade) {
        Produto p = buscarPorId(id);
        if (p != null) {
            p.setNome(nome);
            p.setPreco(preco);
            p.setQuantidadeEmEstoque(quantidade);
            return true;
        }
        return false;
    }

    public boolean removerPorId(int id) {
        return produtos.removeIf(p -> p.getId() == id);
    }
}
