package model;

import java.util.Arrays;
import java.util.Scanner;

public class Estatistica{

    Dados dados = new Dados();

    //Alteração do retorno, antes void, agora retorna um vetor com os valores.
    public int[] lerDados(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite os valores separados por Virgula (ex: 10,20,30,40):");
        String entrada = scanner.nextLine();

        // Remove espaços em branco e divide o texto onde tem vírgula
        String[] valoresString = entrada.replaceAll("\\s+", "").split(",");

        // Cria o vetor com o tamanho exato dos elementos digitados
        dados.setVetorDados(new int[valoresString.length]);

        // Converte os textos para inteiros e adiciona no vetor
        for (int i = 0; i < valoresString.length; i++) {
            //Como arrays são passados por referência.
            dados.getVetorDados()[i] = Integer.parseInt(valoresString[i]);
        }
        Arrays.sort(dados.getVetorDados());//Ordena os valores do vetor.
        //System.out.println("Vetor dados: " + Arrays.toString(dados.getVetorDados()));
        // impressão dos valores ordenados


        return dados.getVetorDados();
    }

    //metodo para retornar o vetor
    public int[] getVetorDados() {
        return dados.getVetorDados();
    }

    public int CalcularKlasse(int n){
        if (n <= 0) {return 0;}
        // Aplica a fórmula de Sturges: 1 + 3.322 * log10(n)
        double k = 1 + 3.322 * Math.log10(n);

        // Arredonda para cima (Math.ceil) e converte para inteiro
        return (int) Math.ceil(k);
    }

    //metodo para calcular a frequencia dos dados.
    //e adicionar num vetor a parte
    public void calcFrequencia(int[] vetor){

    }


    public void CalcularTabela(){
        //Desenvolver codigo da tabela frenquencia.
    }
}
