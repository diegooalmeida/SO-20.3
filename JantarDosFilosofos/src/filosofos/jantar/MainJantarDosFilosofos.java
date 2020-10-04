package filosofos.jantar;

import java.util.Scanner;

public class MainJantarDosFilosofos {

    private static final int numDeFilosofos = 7;
    private static final int tempoComendo = 1000;
    private static final int tempoPensando = 1000;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Jantar jantar;

        System.out.println("Escolha um método: <semaforo, monitor>");
        String opcao = sc.nextLine();

        if (opcao.equals("semaforo")) {
            jantar = new JantarComSemaforos(numDeFilosofos);
        } else if (opcao.equals("monitor")) {
            jantar = new JantarComMonitores(numDeFilosofos);
        } else {
            jantar = null;
            System.out.println("Opção inválida! Opte entre 'semaforo' ou 'monitor'");
            System.exit(1);
        }

        for (int i = 0; i < numDeFilosofos; i++) {
            new Filosofo(i, tempoComendo, tempoPensando, jantar);
        }

        sc.close();
    }
}