import model.Estatistica;

public class Main {
    public static void main(String[] args) {
        //Inicialmente será tudo pensando nos dados Não Agrupados.
        //titulo
        System.out.println("___________________________________________");
        System.out.println("|  Aplicativo educacional de Estatistica  |");
        System.out.println("|           Análise estatística           |");
        System.out.println("|                                         |");
        System.out.println("|       TIPO DE DADOS: Não Agrupados      |");
        System.out.println("-------------------------------------------");
        Estatistica estatistica = new Estatistica();

        estatistica.lerDados();

        System.out.println("Numero de classe: k = "+ estatistica.CalcularKlasse(50));


    }
}