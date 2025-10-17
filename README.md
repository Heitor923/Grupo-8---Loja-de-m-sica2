# Mini Sistema de Cadastro (Java, Console)

Este é um sistema simples em Java que roda no **console** e permite cadastrar, listar, atualizar e excluir **Funcionários**, **Produtos** e **Clientes**. Tudo fica guardado em memória usando `ArrayList`, sem precisar de banco de dados.

O `id` é automático; você só digita os outros campos.

## Como usar

**Menus principais:**
- 1) Funcionários
- 2) Produtos
- 3) Clientes
- 4) Cadastrar Cliente rápido
- 5) Cadastrar Produto rápido
- 0) Sair

**Submenu de cada entidade:**
- 1) Cadastrar
- 2) Listar
- 3) Buscar por ID
- 4) Atualizar
- 5) Excluir
- 0) Voltar

> Dica: se deixar o campo em branco ao atualizar, o valor atual continua.

## Regras simples
- Nome é obrigatório.
- Salário, preço e quantidade não podem ser negativos.
- Mensagens no console mostram se deu sucesso ou erro.
- `id` sempre gerado pelo sistema.

## Como compilar e rodar

**No terminal:**
```bash
# Compilar
javac -d out src/com/MiniSistemaDeCadastro/entidades/*.java src/com/MiniSistemaDeCadastro/RepositoryMemoria/*.java src/com/MiniSistemaDeCadastro/Main.java

# Rodar
java -cp out com.MiniSistemaDeCadastro.Main
```

**Na IDE:**  
Importe o projeto, marque `src` como fonte, e execute a classe `Main`.

## Teste rápido
1. Cadastre 2 funcionários, 2 produtos e 2 clientes.  
2. Tente atualizar um deles.  
3. Tente excluir outro.  
4. Liste tudo pra ver se funcionou.

---

É isso! O sistema é bem simples e feito só com Java puro.