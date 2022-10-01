package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class service {
    public static List<Clientes> clientesList = new ArrayList<>();

    static void metodoPrincipal() {
        LoopUm:
        while (true){
            mensagemInicial();
            Scanner s = new Scanner(System.in);
            int opcaoEscolhida = s.nextInt();
            if (opcaoEscolhida ==1){
                criaCliente();
            }
            else if (opcaoEscolhida ==2){
                Scanner paraRemoverCliente = new Scanner(System.in);
                System.out.println("Digite o cpf que você deseja excluir ");
                String cpf = paraRemoverCliente.nextLine();
                removeClientesByCPF(cpf);
            }else if(opcaoEscolhida ==3){
                Scanner paraRemoverProdutoCliente = new Scanner(System.in);
                System.out.println("Digite o cpf e o id de produto que você deseja excluir ");
                String cpf = paraRemoverProdutoCliente.nextLine();
                String id = paraRemoverProdutoCliente.nextLine();
                atualizarProdutosDoClienteByCpfId(cpf, id);
            }else if(opcaoEscolhida ==4){
                Scanner imprimeCliente = new Scanner(System.in);
                String cpf = imprimeCliente.nextLine();
                getClienteByCpf(cpf);

            }else if(opcaoEscolhida ==5){
                getAll();
            }else if(opcaoEscolhida ==6){
                System.out.println("Saindo");
                break LoopUm;
            }
        }
    }
    static void mensagemInicial(){
        System.out.println("Seja bem vindo!");
        System.out.println("(1) - Para criar um novo cliente e adicionar no banco de dados");
        System.out.println("(2) - Para remover um cliente pelo cpf dele");
        System.out.println("(3) - Para remover um produto de um cliente");
        System.out.println("(4) - Para imprimir as informações de um cliente específico pelo seu cpf");
        System.out.println("(5) - Para imprimir todas as informações dos nossos clientes ");
        System.out.println("(6) - Para sair do sistema");
    }
    static void post(Clientes clientes) {
        clientesList.add(clientes);
    }
    static void removeClientesByCPF(String CPF) {
        for (int i = 0; i < clientesList.size(); i++) {
            if (clientesList.get(i).cpf.equals(CPF)) {
                clientesList.remove(i);
                System.out.println("Cliente removido com sucesso");
            } else {
                System.out.println("Cliente inexistente");
            }
        }
    }
    static void atualizarProdutosDoClienteByCpfId(String cpf, String ID) {
        for (int i = 0; i < clientesList.size(); i++) {
            boolean boolCpf = clientesList.get(i).cpf.equals(cpf);
            if (boolCpf) {
                for (int j = 0; j < clientesList.get(i).produtos.size(); j++) {
                    boolean boolId = clientesList.get(i).produtos.get(j).ID.equals(ID);
                    if (boolId) {
                        clientesList.get(i).produtos.remove(j);
                        System.out.println("Produto removido com sucesso!");
                    } else {
                        System.out.println("Produto inexistente na lisa de compras desse cliente");
                    }
                }
            } else {
                System.out.println("Cliente inexistente nos sistemas");
            }
        }
    }
    static void getClienteByCpf(String cpf) {
        for (int i = 0; i < clientesList.size(); i++) {
            if (clientesList.get(i).cpf.equals(cpf)) {
                System.out.println("CPF: " + clientesList.get(i).cpf);
                System.out.println("Nome: " + clientesList.get(i).nome);
                System.out.println("Total: " + clientesList.get(i).totalCompra);
                for (Produtos p : clientesList.get(i).produtos) {
                    System.out.println("Quantidade: " + p.quantidade);
                    System.out.println("ID: " + p.ID);
                    System.out.println("Preço Individual: " + p.precoIndividual);
                    System.out.println("Total de compras: " + p.precoTotal);
                }
            } else {
                System.out.println("Cliente inexistente");
            }
        }
    }
    static void getAll() {
        if (clientesList.size() != 0 && !clientesList.isEmpty()) {
            for (int i = 0; i < clientesList.size(); i++) {
                System.out.println("CPF: " + clientesList.get(i).cpf);
                System.out.println("Nome: " + clientesList.get(i).nome);
                System.out.println("Total: " + clientesList.get(i).totalCompra);
            }
        }else{
            System.out.println("A lista está vazia!");
        }

    }
    static void criaCliente(){
        Scanner paraCiarNovoCliente = new Scanner(System.in);
        System.out.println("Digite as seguintes informações do cliente: CPF, nome e produtos desejado:");
        String cpf = paraCiarNovoCliente.nextLine();
        String nome = paraCiarNovoCliente.nextLine();
        Produtos produtoDesejado = new Produtos();
        System.out.println("Qual o nome do produto?");
        Scanner paraCriarNovoProduto = new Scanner(System.in);
        String nomeProduto = paraCriarNovoProduto.nextLine();
        System.out.println("Quantos desse produto você deseja?");
        Integer quantidade = Integer.valueOf(paraCriarNovoProduto.nextLine());
        produtoDesejado.ID = String.valueOf(produtoDesejado.hashCode());
        produtoDesejado.nome = nomeProduto;
        produtoDesejado.quantidade = quantidade;
        System.out.println("Preço do produto, por favor");
        produtoDesejado.precoIndividual = paraCriarNovoProduto.nextDouble();
        produtoDesejado.atualizarPrecoTotal();
        Clientes cliente = new Clientes();
        cliente.produtos = new ArrayList<>();
        cliente.produtos.add(produtoDesejado);
        cliente.cpf = cpf;
        cliente.nome = nome;
        cliente.totalCompra = cliente.retornaTotalCompra();
        post(cliente);
    }
}