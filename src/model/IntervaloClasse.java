package model;

public class IntervaloClasse {

    private double limiteInferior;
    private double limiteSuperior;
    private int frequenciaAbsoluta;//fi
    private int frequenciaAcumulada;//Fi
    private double frequenciaRelativa;//fri
    private double frequenciaRelativaAcumulada;//Fri

    // Construtor
    public IntervaloClasse(double limiteInferior, double limiteSuperior) {
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.frequenciaAbsoluta = 0; // Começa zerada para podermos contar
    }

    // Metodo para verificar se um valor pertence a esta classe [inferior, superior)
    public boolean contem(double valor) {
        return valor >= limiteInferior && valor < limiteSuperior;
    }

    // Metodo para incrementar a contagem
    public void incrementarFrequencia() {
        this.frequenciaAbsoluta++;
    }

    // Getters e Setters para acessar e salvar os dados depois
    public double getLimiteInferior() { return limiteInferior; }
    public double getLimiteSuperior() { return limiteSuperior; }
    public int getFrequenciaAbsoluta() { return frequenciaAbsoluta; }
    public void setFrequenciaAcumulada(int Fi) { this.frequenciaAcumulada = Fi; }
    public int getFrequenciaAcumulada() { return frequenciaAcumulada; }
    public void setFrequenciaRelativa(double fri) { this.frequenciaRelativa = fri; }
    public double getFrequenciaRelativa() { return frequenciaRelativa; }
    public void setFrequenciaRelativaAcumulada(double Fri) { this.frequenciaRelativaAcumulada = Fri; }
    public double getFrequenciaRelativaAcumulada() { return frequenciaRelativaAcumulada; }
}