package model;

public class TabelaEstatistica {
    private IntervaloClasse[] linhas;

    // Construtor que recebe as linhas calculadas
    public TabelaEstatistica(IntervaloClasse[] linhas) {
        this.linhas = linhas;
    }

    public IntervaloClasse[] getLinhas() {
        return linhas;
    }

    public void setLinhas(IntervaloClasse[] linhas) {
        this.linhas = linhas;
    }

    // Sobrescrita do metodo toString() para renderizar a tabela automaticamente
    @Override
    public String toString() {
        if (linhas == null || linhas.length == 0) {
            return "Tabela vazia.";
        }

        StringBuilder sb = new StringBuilder();
        
        // 1. Adiciona o Cabeçalho estruturado
        sb.append(String.format("%-22s %-6s %-6s %-8s %-8s%n", "Classe", "fi", "Fi", "fri", "Fri"));
        sb.append("---------------------------------------------------------\n");

        // 2. Adiciona cada linha formatada
        for (IntervaloClasse linha : linhas) {
            String textoClasse = String.format("[%.1f |---- %.1f ]",
                    linha.getLimiteInferior(), 
                    linha.getLimiteSuperior()
            );

            sb.append(String.format("%-22s %-6d %-6d %-8.2f %-8.2f%n",
                    textoClasse,
                    linha.getFrequenciaAbsoluta(),
                    linha.getFrequenciaAcumulada(),
                    linha.getFrequenciaRelativa(),
                    linha.getFrequenciaRelativaAcumulada()
            ));
        }
        
        return sb.toString();
    }
}