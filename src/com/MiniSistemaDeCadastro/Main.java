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
            opc = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch(opc) {
                case 1: menuFuncionario(); break;
                case 2: menuProduto(); break;
                case 3: menuCliente(); break;5
                case 4: cadastrarCliente(); break;
                case 5: cadastrarProduto(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while(opc != 0);
    }

    // ================= FUNCIONÁRIO =================
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
            op = sc.nextInt();
            sc.nextLine();

            switch(op) {
                case 1: cadastrarFuncionario(); break;
                case 2: listarFuncionarios(); break;
                case 3: buscarFuncionario(); break;
                case 4: atualizarFuncionario(); break;
                case 5: excluirFuncionario(); break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while(op != 0);
    }

    static void cadastrarFuncionario() {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Salário: "); double sal = sc.nextDouble(); sc.nextLine();
        System.out.print("Matrícula: "); String mat = sc.nextLine();
        Funcionario f = new Funcionario(nome, sal, mat);
        funcRepo.adicionar(f);
        System.out.println("Funcionário cadastrado: " + f);
    }

    static void listarFuncionarios() {
        funcRepo.listar().forEach(System.out::println);
    }

    static void buscarFuncionario() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        Funcionario f = funcRepo.buscarPorId(id);
        System.out.println(f != null ? f : "ID não encontrado");
    }

    static void atualizarFuncionario() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Salário: "); double sal = sc.nextDouble(); sc.nextLine();
        System.out.print("Matrícula: "); String mat = sc.nextLine();
        boolean ok = funcRepo.atualizar(id, nome, sal, mat);
        System.out.println(ok ? "Atualizado com sucesso!" : "ID não encontrado!");
    }

    static void excluirFuncionario() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        boolean ok = funcRepo.removerPorId(id);
        System.out.println(ok ? "Removido com sucesso!" : "ID não encontrado!");
    }

    // ================= PRODUTO =================
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
            op = sc.nextInt(); sc.nextLine();

            switch(op) {
                case 1: cadastrarProduto(); break;
                case 2: listarProdutos(); break;
                case 3: buscarProduto(); break;
                case 4: atualizarProduto(); break;
                case 5: excluirProduto(); break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while(op != 0);
    }

    static void cadastrarProduto() {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Preço: "); double preco = sc.nextDouble(); sc.nextLine();
        System.out.print("Quantidade em estoque: "); int qtd = sc.nextInt(); sc.nextLine();
        Produto p = new Produto(nome, preco, qtd);
        prodRepo.adicionar(p);
        System.out.println("Produto cadastrado: " + p);
    }

    static void listarProdutos() {
        prodRepo.listar().forEach(System.out::println);
    }

    static void buscarProduto() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        Produto p = prodRepo.buscarPorId(id);
        System.out.println(p != null ? p : "ID não encontrado");
    }

    static void atualizarProduto() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Preço: "); double preco = sc.nextDouble(); sc.nextLine();
        System.out.print("Quantidade: "); int qtd = sc.nextInt(); sc.nextLine();
        boolean ok = prodRepo.atualizar(id, nome, preco, qtd);
        System.out.println(ok ? "Atualizado com sucesso!" : "ID não encontrado!");
    }

    static void excluirProduto() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        boolean ok = prodRepo.removerPorId(id);
        System.out.println(ok ? "Removido com sucesso!" : "ID não encontrado!");
    }

    // ================= CLIENTE =================
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
            op = sc.nextInt(); sc.nextLine();

            switch(op) {
                case 1: cadastrarCliente(); break;
                case 2: listarClientes(); break;
                case 3: buscarCliente(); break;
                case 4: atualizarCliente(); break;
                case 5: excluirCliente(); break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while(op != 0);
    }

    static void cadastrarCliente() {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Telefone: "); String tel = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        Cliente c = new Cliente(nome, tel, email);
        cliRepo.adicionar(c);
        System.out.println("Cliente cadastrado: " + c);
    }

    static void listarClientes() {
        cliRepo.listar().forEach(System.out::println);
    }

    static void buscarCliente() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        Cliente c = cliRepo.buscarPorId(id);
        System.out.println(c != null ? c : "ID não encontrado");
    }

    static void atualizarCliente() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Telefone: "); String tel = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        boolean ok = cliRepo.atualizar(id, nome, tel, email);
        System.out.println(ok ? "Atualizado com sucesso!" : "ID não encontrado!");
    }

    static void excluirCliente() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        boolean ok = cliRepo.removerPorId(id);
        System.out.println(ok ? "Removido com sucesso!" : "ID não encontrado!");
    }
}
