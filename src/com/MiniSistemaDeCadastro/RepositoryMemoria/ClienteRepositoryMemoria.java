package com.MiniSistemaDeCadastro.RepositoryMemoria;
import com.MiniSistemaDeCadastro.entidades.Cliente;

import java.util.ArrayList;

public class ClienteRepositoryMemoria {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    // Add clientes
    public Cliente adicioanar(Cliente c){   
        c.setId(proximoId++);
        clientes.add(c);
        return c;

    }
    // Listar todos
    public ArrayList<Cliente> listar(){
        return clientes;
    }
    // Buscar id
    public Cliente buscarPorId(int id){
        for(Cliente c: clientes){
            if (c.getId() == id){
                return c;
            }
        }
        return null;
    }
    
    // Atualizar dados 
    public boolean atualizar(int id, String nome, String telefone, String email){
        Cliente c = buscarPorId(id);
        if (c == null){
            return false;
        }
        c.setNome(nome);
        c.setTelefone(telefone);
        c.setEmail(email);
        return true;
    }
    // Remover dados
    public boolean removerPorId(int id){
        Cliente c = buscarPorId(id);
        if (c != null) {
            clientes.remove(c);
            return true;
        }
        return false;
    } 
}