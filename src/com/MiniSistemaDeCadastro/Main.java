package com.MiniSistemaDeCadastro;

import java.util.Scanner;
import com.MiniSistemaDeCadastro.entidades.*;
import com.MiniSistemaDeCadastro.RepositoryMemoria.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static FuncionarioRepositoryMemoria funcRepo = new FuncionarioRepositoryMemoria();
    static ProdutoRepositoryMemoria prodRepo = new ProdutoRepositoryMemoria();
    static ClienteRepositoryMemoria cliRepo = new ClienteRepositoryMemoria();

    public static void main(String[] args) {
        int opc;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1) Gerenciar Funcionários");
            System.out.println("2) Gerenciar Produtos");
            System.out.println("3) Gerenciar Clientes");
            System.out.println("4) Cadastrar Cliente (atalho)");
            System.out.println("5) Cadastrar Produto (atalho)");
            System.out.println("0) Sair");
            opc = lerInt("Opção: ");

            switch (opc) {
                case 1: menuFuncionario(); break;
                case 2: menuProduto(); break;
                case 3: menuCliente(); break;
                case 4: cadastrarCliente(); break;
                case 5: cadastrarProduto(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }


    private static int lerInt(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String linha = sc.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

   
    private static Integer lerIntOpcional(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String linha = sc.nextLine().trim();
            if (linha.isEmpty()) return null;
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida. Digite um número inteiro ou deixe em branco para manter.");
            }
        }
    }

 
    private static double lerDouble(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String linha = sc.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida. Digite um número (ex.: 123.45).");
            }
        }
    }

    private static Double lerDoubleOpcional(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String linha = sc.nextLine().trim();
            if (linha.isEmpty()) return null; 
            linha = linha.replace(',', '.');
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida. Digite um número (ex.: 123.45) ou deixe em branco para manter.");
            }
        }
    }


    private static String lerTextoObrigatorio(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String s = sc.nextLine();
            if (s != null && !s.trim().isEmpty()) return s.trim();
            System.out.println("Campo obrigatório. Tente novamente.");
        }
    }

   
    private static String lerTextoOpcional(String rotulo) {
        System.out.print(rotulo);
        String s = sc.nextLine();
        if (s == null) return null;
        s = s.trim();
        return s.isEmpty() ? null : s;
    }

   
    static void menuFuncionario() {
        int op;
        do {
            System.out.println("\n--- Funcionários ---");
            System.out.println("1) Cadastrar");
            System.out.println("2) Listar");
            System.out.println("3) Buscar por ID");
            System.out.println("4) Atualizar por ID");
            System.out.println("5) Excluir por ID");
            System.out.println("0) Voltar");
            op = lerInt("Opção: ");

            switch (op) {
                case 1: cadastrarFuncionario(); break;
                case 2: listarFuncionarios(); break;
                case 3: buscarFuncionario(); break;
                case 4: atualizarFuncionario(); break;
                case 5: excluirFuncionario(); break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    static void cadastrarFuncionario() {
        String nome = lerTextoObrigatorio("Nome: ");
        double sal = lerDouble("Salário: ");
        String mat = lerTextoObrigatorio("Matrícula: ");

        try {
            Funcionario f = new Funcionario(nome, sal, mat);
            funcRepo.adicionar(f);
            System.out.println("Sucesso: Funcionário cadastrado. " + f);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar funcionário.");
        }
    }

    static void listarFuncionarios() {
        System.out.println("\nLista de Funcionários:");
        boolean vazio = true;
        for (Funcionario f : funcRepo.listar()) {
            System.out.println(f);
            vazio = false;
        }
        if (vazio) System.out.println("(vazio)");
    }

    static void buscarFuncionario() {
        int id = lerInt("ID: ");
        Funcionario f = funcRepo.buscarPorId(id);
        if (f != null) {
            System.out.println("Encontrado: " + f);
        } else {
            System.out.println("Erro: ID não encontrado.");
        }
    }

    static void atualizarFuncionario() {
        int id = lerInt("ID: ");
        Funcionario atual = funcRepo.buscarPorId(id);
        if (atual == null) {
            System.out.println("Erro: ID não encontrado.");
            return;
        }
        System.out.println("Atual (deixe em branco para manter): " + atual);

        String nome = lerTextoOpcional("Novo nome: ");
        Double sal = lerDoubleOpcional("Novo salário: ");
        String mat = lerTextoOpcional("Nova matrícula: ");

       
        boolean ok = funcRepo.atualizar(
            id,
            nome,
            (sal == null ? -1 : sal),
            mat
        );
        System.out.println(ok ? "Sucesso: Funcionário atualizado." : "Erro: não foi possível atualizar.");
    }

    static void excluirFuncionario() {
        int id = lerInt("ID: ");
        boolean ok = funcRepo.removerPorId(id);
        System.out.println(ok ? "Sucesso: Funcionário removido." : "Erro: ID não encontrado.");
    }

   
    static void menuProduto() {
        int op;
        do {
            System.out.println("\n--- Produtos ---");
            System.out.println("1) Cadastrar");
            System.out.println("2) Listar");
            System.out.println("3) Buscar por ID");
            System.out.println("4) Atualizar por ID");
            System.out.println("5) Excluir por ID");
            System.out.println("0) Voltar");
            op = lerInt("Opção: ");

            switch (op) {
                case 1: cadastrarProduto(); break;
                case 2: listarProdutos(); break;
                case 3: buscarProduto(); break;
                case 4: atualizarProduto(); break;
                case 5: excluirProduto(); break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    static void cadastrarProduto() {
        String nome = lerTextoObrigatorio("Nome: ");
        double preco = lerDouble("Preço: ");
        int qtd = lerInt("Quantidade em estoque: ");

        try {
            Produto p = new Produto(nome, preco, qtd);
            prodRepo.adicionar(p);
            System.out.println("Sucesso: Produto cadastrado. " + p);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar produto.");
        }
    }

    static void listarProdutos() {
        System.out.println("\nLista de Produtos:");
        boolean vazio = true;
        for (Produto p : prodRepo.listar()) {
            System.out.println(p);
            vazio = false;
        }
        if (vazio) System.out.println("(vazio)");
    }

    static void buscarProduto() {
        int id = lerInt("ID: ");
        Produto p = prodRepo.buscarPorId(id);
        if (p != null) {
            System.out.println("Encontrado: " + p);
        } else {
            System.out.println("Erro: ID não encontrado.");
        }
    }

    static void atualizarProduto() {
        int id = lerInt("ID: ");
        Produto atual = prodRepo.buscarPorId(id);
        if (atual == null) {
            System.out.println("Erro: ID não encontrado.");
            return;
        }
        System.out.println("Atual (deixe em branco para manter): " + atual);

        String nome = lerTextoOpcional("Novo nome: ");
        Double preco = lerDoubleOpcional("Novo preço: ");
        Integer qtd = lerIntOpcional("Nova quantidade: ");

        boolean ok = prodRepo.atualizar(
            id,
            nome,
            (preco == null ? -1 : preco),
            (qtd == null ? -1 : qtd)
        );
        System.out.println(ok ? "Sucesso: Produto atualizado." : "Erro: não foi possível atualizar.");
    }

    static void excluirProduto() {
        int id = lerInt("ID: ");
        boolean ok = prodRepo.removerPorId(id);
        System.out.println(ok ? "Sucesso: Produto removido." : "Erro: ID não encontrado.");
    }

 
    static void menuCliente() {
        int op;
        do {
            System.out.println("\n--- Clientes ---");
            System.out.println("1) Cadastrar");
            System.out.println("2) Listar");
            System.out.println("3) Buscar por ID");
            System.out.println("4) Atualizar por ID");
            System.out.println("5) Excluir por ID");
            System.out.println("0) Voltar");
            op = lerInt("Opção: ");

            switch (op) {
                case 1: cadastrarCliente(); break;
                case 2: listarClientes(); break;
                case 3: buscarCliente(); break;
                case 4: atualizarCliente(); break;
                case 5: excluirCliente(); break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    static void cadastrarCliente() {
        String nome = lerTextoObrigatorio("Nome: ");
        String tel = lerTextoObrigatorio("Telefone: ");
        String email = lerTextoObrigatorio("Email: ");

        try {
            Cliente c = new Cliente(nome, tel, email);
            cliRepo.adicionar(c);
            System.out.println("Sucesso: Cliente cadastrado. " + c);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar cliente.");
        }
    }

    static void listarClientes() {
        System.out.println("\nLista de Clientes:");
        boolean vazio = true;
        for (Cliente c : cliRepo.listar()) {
            System.out.println(c);
            vazio = false;
        }
        if (vazio) System.out.println("(vazio)");
    }

    static void buscarCliente() {
        int id = lerInt("ID: ");
        Cliente c = cliRepo.buscarPorId(id);
        if (c != null) {
            System.out.println("Encontrado: " + c);
        } else {
            System.out.println("Erro: ID não encontrado.");
        }
    }

    static void atualizarCliente() {
        int id = lerInt("ID: ");
        Cliente atual = cliRepo.buscarPorId(id);
        if (atual == null) {
            System.out.println("Erro: ID não encontrado.");
            return;
        }
        System.out.println("Atual (deixe em branco para manter): " + atual);

        String nome = lerTextoOpcional("Novo nome: ");
        String tel = lerTextoOpcional("Novo telefone: ");
        String email = lerTextoOpcional("Novo email: ");

        boolean ok = cliRepo.atualizar(id, nome, tel, email);
        System.out.println(ok ? "Sucesso: Cliente atualizado." : "Erro: não foi possível atualizar.");
    }

    static void excluirCliente() {
        int id = lerInt("ID: ");
        boolean ok = cliRepo.removerPorId(id);
        System.out.println(ok ? "Sucesso: Cliente removido." : "Erro: ID não encontrado.");
    }
}