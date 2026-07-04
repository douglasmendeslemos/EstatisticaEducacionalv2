package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Console {

    Scanner leia = new Scanner(System.in);
//    // Criando a fila e pilha codigo antigo.
//    Queue<String> filaLivros = new LinkedList<>();
//    Stack<String> pilhaDeLivros = new Stack<>();
    //instanciando um objeto estatistica para realizar os calculos.
    Estatistica estatistica = new Estatistica();
    // 1. CORREÇÃO DE ESCOPO: Declaramos a tabela a nível de classe.
    // Ela inicia como 'null' para sabermos se o usuário já rodou a opção 1.
    private TabelaEstatistica minhaTabela = null;

    //vetor para testes caso necessario.
    //int[] meuVetorAmostra2 = {150,154,155,157,160,161,162,164,166,169,151,155,156,158,160,161,162,164,167,170,152,155,156,158,160,161,163,164,168,172,153,155,156,160,160,161,163,165,168,173};


    public int menuOpcoes(){
        System.out.println("1. Informar dados e gerar Tabela de frequencia (Estatistica)");
        System.out.println("2. Medidas Descritivas");
        System.out.println("3. Medidas Descritivas detalhadamente");

//        System.out.println("3. Ver título do livro do topo da pilha ");
//        System.out.println("4. Quantidade de livros que estão empilhados ");
//        System.out.println("5. Mostrar todos os títulos de livros que estão empilhados. ");
        System.out.print("Para sair digite 0: ");
        return leia.nextInt();
    }
    //tornando o metodo menuOpcoes() em um metodo mais versatio.

    public int principal(){
        int opcao;
        do{
            opcao = menuOpcoes();
            switch (opcao) {
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                case 1:
                    System.out.println("Lendo dados e gerando estatísticas...");
                    // 2. CORREÇÃO: Alimenta a variável global da classe.
                    // Passamos o array gerado por estatistica.lerDados()
                    int[] dadosLidos = estatistica.lerDados();
                    minhaTabela = CalculadorEstatistico.gerarTabelaFrequencias(dadosLidos);

                    // Imprime o resultado do toString() automaticamente
                    System.out.println(minhaTabela);
                    break;
                case 2:
                    // 3. CORREÇÃO DE FLUXO: Validação crucial para não quebrar o sistema
                    if (minhaTabela == null) {
                        System.out.println("\n[!] AVISO: Você precisa gerar a tabela de frequências (Opção 1) antes de calcular as medidas descritivas!");
                    } else {
                        // Usando o metodo atualizado que recebe o objeto TabelaEstatistica direto
                        // Se você estiver calculando em cima dos dados lidos dinamicamente, o ideal é passar o tamanho deles.
                        // Aqui usei o tamanho do vetor dinâmico que o usuário digitou na opção 1 para manter o cálculo preciso.
                        CalculadorEstatistico.imprimirMedidasTendenciaCentral(minhaTabela, minhaTabela.getLinhas()[minhaTabela.getLinhas().length - 1].getFrequenciaAcumulada());
                    }
                    break;
                case 3:

                    break;
                default:
                    System.out.println("!! ERRO: Opção inválida. Informe novamente!!");
                    break;
            }
        } while(opcao != 0);
        leia.close();//fechamento do scanner.
        return 0;
    }

    /*
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
    }*/
}
