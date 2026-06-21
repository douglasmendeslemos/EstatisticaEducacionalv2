import model.CalculadorEstatistico;
import model.Estatistica;
import model.IntervaloClasse;
import model.TabelaEstatistica;

public class Main {
    //dados para teste.
    /*84;68;33;52;47;73;68;61;73;77;74;71;81;91;65;55;57;35;85;88;59;80;41;50;53;65;76;85;73;60;67;41;78;56;94;35;45;55;64;74;65;94;66;48;39;69;89;98;42;54*/
    public static void main(String[] args) {
        //Inicialmente será tudo pensando nos dados Não Agrupados.
        //titulo
        System.out.println("___________________________________________");
        System.out.println("|  Aplicativo educacional de Estatistica  |");
        System.out.println("|           Análise estatística           |");
        System.out.println("|                                         |");
        System.out.println("|       TIPO DE DADOS: Não Agrupados      |");
        System.out.println("-------------------------------------------");
        //Estatistica estatistica = new Estatistica();

        //estatistica.lerDados();

        int[] meuVetorAmostra = {84,68,33,52,47,73,68,61,73,77,74,71,81,91,65,55,57,35,85,88,59,80,41,50,53,65,76,85,73,60,67,41,78,56,94,35,45,55,64,74,65,94,66,48,39,69,89,98,42,54};

        // Gera e ARMAZENA os cálculos estruturados
        TabelaEstatistica minhaTabela = CalculadorEstatistico.gerarTabelaFrequencias(meuVetorAmostra);
        System.out.println(minhaTabela);

        /**
        // Exemplo: Imprimindo os dados armazenados a partir da variável
        // Cabeçalho com larguras fixas:
        // %-22s reserva 22 espaços alinhados à esquerda para o título "Classe"
        // %-6s, %-6s, %-8s, %-8s reservam espaços padronizados para as colunas de frequência
        System.out.printf("%-22s %-6s %-6s %-8s %-8s%n", "Classe", "fi", "Fi", "fri", "Fri");

        for (IntervaloClasse linha : minhaTabela) {
            // Monta a String do intervalo da classe (ex: "[33.0 |---- 42.3)")
            String textoClasse = String.format("[%.1f |---- %.1f)",
                    linha.getLimiteInferior(),
                    linha.getLimiteSuperior()
            );


             %-22s  -> Desenha a String da classe com tamanho fixo de 22 caracteres
             %-6d   -> Imprime o inteiro (fi) ocupando 6 espaços
             %-6d   -> Imprime o inteiro (Fi) ocupando 6 espaços
             %-8.2f -> Imprime o ponto flutuante (fri) com 2 casas decimais em 8 espaços
             %-8.2f -> Imprime o ponto flutuante (Fri) com 2 casas decimais em 8 espaços
             **
            System.out.printf("%-22s %-6d %-6d %-8.2f %-8.2f%n",
                    textoClasse,
                    linha.getFrequenciaAbsoluta(),
                    linha.getFrequenciaAcumulada(),
                    linha.getFrequenciaRelativa(),
                    linha.getFrequenciaRelativaAcumulada()
            );
        }
**/

    }
}