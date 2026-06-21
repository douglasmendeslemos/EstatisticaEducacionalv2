package model;

public class Dados {

    private int[] vetorDados;


    //Amplitude de um intervalo de classe
    public Double CalcularAmplitudeClasse(Double limiteInferior, Double limiteSuperior){
        return (limiteSuperior - limiteInferior);
    }//Ficar ligado, aqui está retornando um Double. isso pode interferir nos resultados.

    //Amplitude Total de uma distribuição.
    public Double CalcularAmplitudeTotal(Double limiteInferior, Double limiteSuperior){
        return (limiteSuperior - limiteInferior);
    }//é a diferença entre o limite superior da última classe e o limite inferior da primeira classe

    //Getter and Setter
    public int[] getVetorDados() {
        return vetorDados;
    }
    public void setVetorDados(int[] vetorDados) {
        this.vetorDados = vetorDados;
    }
}
