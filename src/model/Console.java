package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Console {

    Scanner leia = new Scanner(System.in);
    Stack<String> pilhaDeLivros = new Stack<>();

    // Criando a fila
    Queue<String> filaLivros = new LinkedList<>();

    public int menuOpcoes(){
        IO.println("\n----------------------------------------------");
        System.out.println("1. Colocar livro ");
        System.out.println("2. Retirar livro");
        System.out.println("3. Ver título do livro do topo da pilha ");
        System.out.println("4. Quantidade de livros que estão empilhados ");
        System.out.println("5. Mostrar todos os títulos de livros que estão empilhados. ");
        System.out.print("Para sair digite 0: ");
        return leia.nextInt();
    }

    //tornando o metodo menuOpcoes() em um metodo mais versatio.

    public int principal(){
        int opcao;
        do{
            opcao = menuOpcoes();
            /*
            * Utilizar uma variavel opção para evitar pulos de entrada
            * chamar o menu apenas no inicio do loop.
            * */
            switch (opcao) {
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                case 1:
                    colocarLivro();
                    break;
                case 2:
                    retirarLivro();
                    break;
                case 3:
                    verificarTopo();
                    break;
                case 4:
                    verificarQTDeLivro();
                    break;
                case 5:
                    mostrarObjetos();
                    break;
                default:
                    System.out.println("!! ERRO: Opção inválida. Informe novamente!!");
                    break;
            }
        }while(opcao!= 0);
        leia.close();//fechamento do scanner.
        return 0;
    }
    public void ColocarLivroFila(){
        leia.nextLine(); // limpa o buffer
        System.out.print("Digite o nome do livro: ");
        String nomeLivro = leia.nextLine();
        filaLivros.add(nomeLivro);

    }

    public void colocarLivro() {
        leia.nextLine(); // limpa o buffer
        System.out.print("Digite o nome do livro: ");
        String nomeLivro = leia.nextLine();
        pilhaDeLivros.push(nomeLivro);
        System.out.print("-> Livro \"" + nomeLivro + "\" adicionado com sucesso!\n");
    }

    public void retirarLivro() {
        if (verificarVazia()) {
            System.out.println(">> A pilha está vazia.");
        } else {
            System.out.println("\"" + pilhaDeLivros.peek() + "\" saiu da pilha.");
            pilhaDeLivros.pop();
        }
    }

    public void verificarTopo(){
        if (verificarVazia()) {
            System.out.println(">> A pilha está vazia.");
        } else {
            System.out.println("O livro " + pilhaDeLivros.peek() + " está no TOPO da pilha.");
        }
    }

    public boolean verificarVazia(){
        return pilhaDeLivros.isEmpty();
    }

    public int verificarQTDeLivro(){
        if (verificarVazia()) {
            System.out.println(">> A pilha está vazia.");
        } else {
            System.out.println("-> A pilha possui "+  pilhaDeLivros.size() +" livro(s)." );
            return pilhaDeLivros.size();
        }
        return 0;
    }

    public void mostrarObjetos(){
        if (verificarVazia()) {
            System.out.println(">> A pilha está vazia.");
        } else {
            System.out.println("--- Livros Empilhados ---");
            for (String livro : pilhaDeLivros.reversed()) {//O reversed informa o primeiro que foi colocado até o ultimo. Reverso.
                System.out.println("- " + livro);
            }System.out.print("---------------FIM DA LISTA-------------------\n");
        }
    }
}
