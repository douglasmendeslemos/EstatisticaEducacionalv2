package model;

import java.util.Arrays;

public class CalculadorEstatistico {

    public static TabelaEstatistica gerarTabelaFrequencias(int[] dados) {
        int n = dados.length;
        //ALTERADO AQUI: Se o vetor for vazio, retorna uma tabela vazia (ajuste de segurança)
        if (n == 0) return new TabelaEstatistica(new IntervaloClasse[0]);

        // 1. Ordena o vetor para achar Min e Max
        Arrays.sort(dados);
        int min = dados[0];
        int max = dados[n - 1];

        // 2. Define quantidade de classes por Sturges
        int k = (int) Math.ceil(1 + 3.322 * Math.log10(n));

        // 3. Calcula a amplitude de cada classe (h)
        double amplitudeTotal = max - min;
        double h = amplitudeTotal / k;

        // 4. Inicializa os objetos de cada classe
        IntervaloClasse[] tabela = new IntervaloClasse[k];
        double limiteAtual = min;
        for (int i = 0; i < k; i++) {
            double proximoLimite = limiteAtual + h;
            // Para a última classe, somamos uma pequena margem (ou usamos <=) para incluir o valor máximo absoluto
            if (i == k - 1) {
                proximoLimite = max + 0.0001; 
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
            classe.setFrequenciaRelativa((double) classe.getFrequenciaAbsoluta() / n);
            classe.setFrequenciaRelativaAcumulada((double) acumuladoFi / n);
        }

        // Modificado: Envolve o array no nosso objeto de formatação
        return new TabelaEstatistica(tabela);
    }

}