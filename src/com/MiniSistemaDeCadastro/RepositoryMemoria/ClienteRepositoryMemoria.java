package com.MiniSistemaDeCadastro.RepositoryMemoria;
import com.MiniSistemaDeCadastro.entidades.Cliente;
import java.util.ArrayList;

public class ClienteRepositoryMemoria {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    public Cliente adicionar(Cliente c) {
        if (c == null) throw new IllegalArgumentException("Cliente é obrigatório");
        if (c.getNome() == null || c.getNome().trim().isEmpty())
            throw new IllegalArgumentException("Nome é obrigatório");

        c.setId(proximoId++);
        if (c.getTelefone() == null) c.setTelefone("");
        if (c.getEmail() == null) c.setEmail("");
        clientes.add(c);
        return c;
    }

    public ArrayList<Cliente> listar() { return clientes; }

    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) if (c.getId() == id) return c;
        return null;
    }

    public boolean atualizar(int id, String nome, String telefone, String email) {
        Cliente c = buscarPorId(id);
        if (c == null) return false;
        if (nome != null && !nome.trim().isEmpty()) c.setNome(nome.trim());
        if (telefone != null) c.setTelefone(telefone.trim());
        if (email != null) c.setEmail(email.trim());
        return true;
    }

    public boolean removerPorId(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.remove(i);
                return true;
            }
        }
        return false;
    }
}