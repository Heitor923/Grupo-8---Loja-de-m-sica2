package com.MiniSistemaDeCadastro.RepositoryMemoria;

import com.MiniSistemaDeCadastro.entidades.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepositoryMemoria {

    private final List<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

    // CREATE → adicionar(...): gera id, salva e retorna o objeto
    public Funcionario adicionar(String nome, double salario, String matricula) {
        // validações simples
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (salario < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo.");
        }

        Funcionario f = new Funcionario();
        f.setId(proximoId++);
        f.setNome(nome.trim());
        f.setSalario(salario);
        f.setMatricula(matricula == null ? "" : matricula.trim());
        funcionarios.add(f);
        return f;
    }

    // READ → listar(): retorna uma cópia da lista para não expor a lista interna
    public List<Funcionario> listar() {
        return new ArrayList<>(funcionarios);
    }

    // READ → buscarPorId(int id): retorna o objeto ou null
    public Funcionario buscarPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    // UPDATE → atualizar(int id, ...campos...): retorna boolean
    public boolean atualizar(int id, String nome, double salario, String matricula) {
        Funcionario f = buscarPorId(id);
        if (f != null) {
            if (nome != null && !nome.trim().isEmpty()) {
                f.setNome(nome.trim());
            }
            if (salario >= 0) {
                f.setSalario(salario);
            }
            if (matricula != null) {
                f.setMatricula(matricula.trim());
            }
            return true;
        }
        return false;
    }

    // DELETE → removerPorId(int id): retorna boolean
    public boolean removerPorId(int id) {
        Funcionario f = buscarPorId(id);
        if (f != null) {
            return funcionarios.remove(f);
        }
        return false;
    }
}