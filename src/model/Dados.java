package model;

import java.util.Arrays;

public class Dados {

    private int[] vetorDados;

    @Override
    public String toString() {
        if (vetorDados == null || vetorDados.length == 0) {
            return "Nenhum dado cadastrado.";
        }
        StringBuilder sb = new StringBuilder();

        sb.append("Valores adicionados ao vetor:\n");
        int colunas = 10;
        for (int i = 0; i < vetorDados.length; i++) {
            sb.append(String.format("%5d", vetorDados[i]));
            if ((i + 1) % colunas == 0)
                sb.append("\n");
        }
        if (vetorDados.length % colunas != 0)
            sb.append("\n");
        return sb.toString();
    }

    //Getter and Setter
    public int[] getVetorDados() {
        return vetorDados;
    }
    public void setVetorDados(int[] vetorDados) {
        this.vetorDados = vetorDados;
    }
}
