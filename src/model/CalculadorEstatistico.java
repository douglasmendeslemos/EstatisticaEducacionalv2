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
        System.out.println(k);
        // 3. Calcula a amplitude de cada classe (h)
        int amplitude = calcularAmplitudeTotal(limiteInf, limiteSup, k);

        // 4. Inicializa os objetos de cada classe (Modularizado)
        IntervaloClasse[] tabela = inicializarIntervalosClasses(k, limiteInf, limiteSup, amplitude);

        // 5. Conta a Frequência Absoluta (fi) (Modularizado)
        calcularFrequenciasAbsolutas(dados, tabela);

        // 6. Calcula as frequências acumuladas e relativas (Modularizado)
        calcularFrequenciasDerivadas(tabela, quantidadeValores);
        return new TabelaEstatistica(tabela);
    }
    //novo 4. inicialização dos intervalos
    public static IntervaloClasse[] inicializarIntervalosClasses(int k, int limiteInf, int limiteSup, int amplitude) {
        IntervaloClasse[] tabela = new IntervaloClasse[k];
        double limiteAtual = limiteInf;

        for (int i = 0; i < k; i++) {
            double proximoLimite = limiteAtual + amplitude;

            // Se for a ÚLTIMA classe real, garante que o limite superior englobe o valor máximo
            if (i == k - 1) {
                proximoLimite = Math.max(proximoLimite, limiteSup + 0.0001);
            }

            tabela[i] = new IntervaloClasse(limiteAtual, proximoLimite);
            limiteAtual = proximoLimite;
        }

        return tabela;
    }
    //novo 5. calculo das Frequencias absolutas
    public static void calcularFrequenciasAbsolutas(int[] dados, IntervaloClasse[] tabela) {
        for (int valor : dados) {
            for (IntervaloClasse classe : tabela) {
                if (classe.contem(valor)) {
                    classe.incrementarFrequencia();
                    break; // Achou a classe correspondente, avança para o próximo valor
                }
            }
        }
    }
    //novo 6. metodo para calcular Frequencias
    public static void calcularFrequenciasDerivadas(IntervaloClasse[] tabela, int quantidadeValores) {
        int acumuladoFi = 0;
        for (IntervaloClasse classe : tabela) {
            acumuladoFi += classe.getFrequenciaAbsoluta();

            classe.setFrequenciaAcumulada(acumuladoFi);
            classe.setFrequenciaRelativa((double) classe.getFrequenciaAbsoluta() / quantidadeValores);
            classe.setFrequenciaRelativaAcumulada((double) acumuladoFi / quantidadeValores);
        }
    }

    //Amplitude de um intervalo de classe
    public static double calcularDiferencaLimites(int limiteInferior, int limiteSuperior){
        return (limiteSuperior - limiteInferior);
    }//Ficar ligado, aqui está retornando um Double. isso pode interferir nos resultados.

    //Amplitude Total de uma distribuição.
    public static int calcularAmplitudeTotal(int limiteInferior, int limiteSuperior, int QTDclasse){
        int amplitude = (int) Math.ceil(calcularDiferencaLimites(limiteInferior, limiteSuperior) / QTDclasse);
        System.out.println("Amplitude total: " + amplitude);
        return amplitude;
    }//é a diferença entre o limite superior da última classe e o limite inferior da primeira classe

    public static int sturges(int[] quantidadeDados){
        return (int) Math.ceil(1 + 3.322 * Math.log10(quantidadeDados.length));
    }//metodo auxiliar para calcular as classes.


    // Calcula a Média Aritmética Ponderada para dados agrupados
    public static double calcularMedia(IntervaloClasse[] tabela, int totalValores) {
        double somaExiFi = 0;
        for (IntervaloClasse classe : tabela) {
            // Ponto médio da classe = (Limite Inferior + Limite Superior) / 2
            double pontoMedio = (classe.getLimiteInferior() + classe.getLimiteSuperior()) / 2;
            somaExiFi += pontoMedio * classe.getFrequenciaAbsoluta();
        }
        return somaExiFi / totalValores;
    }

    // Calcula a Mediana (Aproximação por interpolação linear na classe mediana)
    public static double calcularMediana(IntervaloClasse[] tabela, int totalValores) {
        double posicao = totalValores / 2.0;
        IntervaloClasse classeMediana = null;
        int fiAnteriorAcumulado = 0;

        // Encontra a classe mediana (onde Fi atinge ou passa a metade dos dados)
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i].getFrequenciaAcumulada() >= posicao) {
                classeMediana = tabela[i];
                if (i > 0) {
                    fiAnteriorAcumulado = tabela[i - 1].getFrequenciaAcumulada();
                }
                break;
            }
        }

        if (classeMediana == null) return 0;

        double limiteInfMediana = classeMediana.getLimiteInferior();
        double amplitudeClasse = classeMediana.getLimiteSuperior() - limiteInfMediana;
        int fiMediana = classeMediana.getFrequenciaAbsoluta();

        // Fórmula: Md = l_inf + [((n/2) - F_ant) / f_md] * h
        return limiteInfMediana + ((posicao - fiAnteriorAcumulado) / fiMediana) * amplitudeClasse;
    }

    // Calcula a Moda (Método de Czuber ou a Moda Bruta pelo maior fi)
    public static double calcularModa(IntervaloClasse[] tabela) {
        IntervaloClasse classeModala = tabela[0];
        int maxFi = tabela[0].getFrequenciaAbsoluta();
        int indexModala = 0;

        // 1. Encontra a classe modal (maior fi)
        for (int i = 1; i < tabela.length; i++) {
            if (tabela[i].getFrequenciaAbsoluta() > maxFi) {
                maxFi = tabela[i].getFrequenciaAbsoluta();
                classeModala = tabela[i];
                indexModala = i;
            }
        }

        double delta1 = classeModala.getFrequenciaAbsoluta();
        double delta2 = classeModala.getFrequenciaAbsoluta();

        // Frequência da classe anterior
        if (indexModala > 0) {
            delta1 -= tabela[indexModala - 1].getFrequenciaAbsoluta();
        }
        // Frequência da classe posterior
        if (indexModala < tabela.length - 1) {
            delta2 -= tabela[indexModala + 1].getFrequenciaAbsoluta();
        }

        double limiteInfModal = classeModala.getLimiteInferior();
        double amplitudeClasse = classeModala.getLimiteSuperior() - limiteInfModal;

        // Fórmula de Czuber: Mo = l_inf + [Δ1 / (Δ1 + Δ2)] * h
        if ((delta1 + delta2) == 0) return (limiteInfModal + classeModala.getLimiteSuperior()) / 2; // Ponto médio simples se platô

        return limiteInfModal + (delta1 / (delta1 + delta2)) * amplitudeClasse;
    }



    public static void imprimirMedidasTendenciaCentral(IntervaloClasse[] tabela, int totalValores) {
        double media = calcularMedia(tabela, totalValores);
        double mediana = calcularMediana(tabela, totalValores);
        double moda = calcularModa(tabela);

        System.out.println("\n=======================================");
        System.out.println("  MEDIDAS DE TENDÊNCIA CENTRAL (AGRUPADAS) ");
        System.out.println("=======================================");
        // %-15s -> Reserva 15 caracteres alinhados à esquerda para o nome da medida
        // %.2f   -> Exibe o resultado matemático com duas casas decimais
        System.out.printf("%-15s : %.2f%n", "Média Ponderada", media);
        System.out.printf("%-15s : %.2f%n", "Mediana", mediana);
        System.out.printf("%-15s : %.2f%n", "Moda (Czuber)", moda);
        System.out.println("=======================================");
    }

    // Agora ele recebe o objeto TabelaEstatistica diretamente na assinatura
    public static void imprimirMedidasTendenciaCentral(TabelaEstatistica tabelaEstatistica, int totalValores) {

        // Essa é a única linha nova: extraímos o array interno para que o restante do metodo funcione igual
        IntervaloClasse[] tabela = tabelaEstatistica.getLinhas();

        // Daqui para baixo, a lógica matemática que passamos antes continua EXATAMENTE a mesma:
        double media = calcularMedia(tabela, totalValores);
        double mediana = calcularMediana(tabela, totalValores);
        double moda = calcularModa(tabela);

        System.out.println("\n=======================================");
        System.out.println("  MEDIDAS DE TENDÊNCIA CENTRAL (AGRUPADAS) ");
        System.out.println("=======================================");
        System.out.printf("%-15s : %.2f%n", "Média Ponderada", media);
        System.out.printf("%-15s : %.2f%n", "Mediana", mediana);
        System.out.printf("%-15s : %.2f%n", "Moda (Czuber)", moda);
        System.out.println("=======================================");
    }
}