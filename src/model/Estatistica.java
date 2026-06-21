package model;

import java.util.Arrays;
import java.util.Scanner;

public class Estatistica {
    private int[] vetorDados;

    public void lerDados(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite os valores separados por Ponto-Virgula (ex: 10,20,30,40):");
        String entrada = scanner.nextLine();

        // Remove espaços em branco e divide a string onde tem vírgula
        String[] valoresString = entrada.replaceAll("\\s+", "").split(";");

        // Cria o vetor com o tamanho exato dos elementos digitados
        vetorDados = new int[valoresString.length];

        // Converte as strings para inteiros e adiciona no vetor
        for (int i = 0; i < valoresString.length; i++) {
            vetorDados[i] = Integer.parseInt(valoresString[i]);
        }

        // Exibe os valores para confirmar
        System.out.println("Valores adicionados ao vetor:");
        int colunas = 10; // Defina o número de colunas que deseja
        int contador = 0;
        Arrays.sort(vetorDados);//Ordena os valores do vetor.
        for (int valor : vetorDados) {
            System.out.print(valor + "\t"); // Imprime na mesma linha (com tabulação)
            contador++;

            // Se atingiu o número de colunas, quebra a linha
            if (contador % colunas == 0) {
                System.out.println();
            }
        }
        // Quebra de linha final caso o total de elementos não seja múltiplo do número de colunas
        if (contador % colunas != 0) {
            System.out.println();
        }
        scanner.close();
    }

    public int CalcularKlasse(int n){
        if (n <= 0) {
            return 0;
        }
        // Aplica a fórmula de Sturges: 1 + 3.322 * log10(n)
        double k = 1 + 3.322 * Math.log10(n);

        // Arredonda para cima (Math.ceil) e converte para inteiro
        return (int) Math.ceil(k);
    }


    public void CalcularTabela(){
        //Desenvolver codigo da tabela frenquencia.
    }
}
