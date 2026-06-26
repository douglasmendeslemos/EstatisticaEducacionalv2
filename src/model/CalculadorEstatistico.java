package model;

import java.util.Arrays;

public class CalculadorEstatistico {

    public static TabelaEstatistica gerarTabelaFrequencias(int[] dados) {
        int quantidadeValores = dados.length;
        //ALTERADO AQUI: Se o vetor for vazio, retorna uma tabela vazia (ajuste de segurança)
        if (quantidadeValores == 0) return new TabelaEstatistica(new IntervaloClasse[0]);

        // 1. Ordena o vetor para achar Min e Max
        Arrays.sort(dados);
        int limiteInf = dados[0];
        int limiteSup = dados[quantidadeValores - 1];


        // 2. Define quantidade de classes por Sturges
        int  k = sturges(dados);

        // 3. Calcula a amplitude de cada classe (h)
        double amplitude = calcularAmplitudeTotal(limiteInf, limiteSup, dados);

        // 4. Inicializa os objetos de cada classe
        IntervaloClasse[] tabela = new IntervaloClasse[k];
        double limiteAtual = limiteInf;
        for (int i = 0; i < k; i++) {
            double proximoLimite = limiteAtual + amplitude;//amplitude
            // Para a última classe, somamos uma pequena margem (ou usamos <=) para incluir o valor máximo absoluto
            if (i == k - 1) {
                proximoLimite = limiteSup + 0.0001;
            }
            tabela[i] = new IntervaloClasse(limiteAtual, proximoLimite);
            limiteAtual = proximoLimite;
        }

        // 5. Conta a Frequência Absoluta (fi) percorrendo o seu vetor
        for (int valor : dados) {
            for (IntervaloClasse classe : tabela) {
                if (classe.contem(valor)) {
                    classe.incrementarFrequencia();
                    break; // Já achou a classe dele, vai para o próximo valor
                }
            }
        }

        // 6. Calcula as frequências acumuladas e relativas (Fi, fri, Fri)
        int acumuladoFi = 0;
        for (IntervaloClasse classe : tabela) {
            acumuladoFi += classe.getFrequenciaAbsoluta();
            
            classe.setFrequenciaAcumulada(acumuladoFi);
            classe.setFrequenciaRelativa((double) classe.getFrequenciaAbsoluta() / quantidadeValores);
            classe.setFrequenciaRelativaAcumulada((double) acumuladoFi / quantidadeValores);
        }

        // Modificado: Envolve o array no nosso objeto de formatação
        return new TabelaEstatistica(tabela);
    }

    //Amplitude de um intervalo de classe
    public static double calcularDiferencaLimites(int limiteInferior, int limiteSuperior){
        return (limiteSuperior - limiteInferior);
    }//Ficar ligado, aqui está retornando um Double. isso pode interferir nos resultados.

    //Amplitude Total de uma distribuição.
    public static double calcularAmplitudeTotal(int limiteInferior, int limiteSuperior, int[] dados){
        double amplitude = (int) Math.ceil(calcularDiferencaLimites(limiteSuperior, limiteInferior) / sturges(dados));
        System.out.println("Amplitude total: " + amplitude);
        return amplitude;
    }//é a diferença entre o limite superior da última classe e o limite inferior da primeira classe

    public static int sturges(int[] quantidadeDados){
        return (int) Math.ceil(1 + 3.322 * Math.log10(quantidadeDados.length));
    }
}