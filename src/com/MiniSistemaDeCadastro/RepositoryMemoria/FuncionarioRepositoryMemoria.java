package com.MiniSistemaDeCadastro.RepositoryMemoria;

import com.MiniSistemaDeCadastro.entidades.Funcionario;
import java.util.ArrayList;

public class FuncionarioRepositoryMemoria {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

    // adiciona o objeto e atribui o id aqui
    public Funcionario adicionar(Funcionario f) {
        if (f == null) throw new IllegalArgumentException("Funcionário é obrigatório");
        if (f.getNome() == null || f.getNome().trim().isEmpty())
            throw new IllegalArgumentException("Nome é obrigatório");
        if (f.getSalario() < 0) throw new IllegalArgumentException("Salário inválido");

        f.setId(proximoId++); // AQUI o id é gerado
        if (f.getMatricula() == null) f.setMatricula("");
        funcionarios.add(f);
        return f;
    }

    public ArrayList<Funcionario> listar() { return funcionarios; }

    public Funcionario buscarPorId(int id) {
        for (Funcionario f : funcionarios) if (f.getId() == id) return f;
        return null;
    }

    public boolean atualizar(int id, String nome, double salario, String matricula) {
        Funcionario f = buscarPorId(id);
        if (f == null) return false;
        if (nome != null && !nome.trim().isEmpty()) f.setNome(nome.trim());
        if (salario >= 0) f.setSalario(salario);
        if (matricula != null) f.setMatricula(matricula.trim());
        return true;
    }

    public boolean removerPorId(int id) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == id) {
                funcionarios.remove(i);
                return true;
            }
        }
        return false;
    }
}