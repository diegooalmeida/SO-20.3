package filosofos.jantar;

import java.util.concurrent.Semaphore;

import java.util.ArrayList;

public class JantarComSemaforos extends Jantar {

    private Semaphore mutex;

    public JantarComSemaforos (int numDeFilosofos) {
        super();
        this.mutex = new Semaphore(1);
        this.numDeFilosofos = numDeFilosofos;
        this.estados = new ArrayList<>(numDeFilosofos);
        this.filosofos = new Semaphore[this.numDeFilosofos];

        for(int i = 0; i < this.numDeFilosofos; i++) {
            this.estados.add(Estados.PENSANDO);
            this.filosofos[i] = new Semaphore(0);
        }
        System.out.println(estados);
    }

    @Override
    public void pegaTalheres (int idDoFilosofo) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        estados.set(idDoFilosofo, Estados.COMFOME);
        if (podeComer(idDoFilosofo)) {
            ((Semaphore) filosofos[idDoFilosofo]).release();
            estados.set(idDoFilosofo, Estados.COMENDO);
        }
        mutex.release();
        try {
            ((Semaphore) filosofos[idDoFilosofo]).acquire();
            System.out.println(estados);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
    }

    @Override
    public void devolveTalheres (int idDoFilosofo) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        System.out.println(estados);
        estados.set(idDoFilosofo, Estados.PENSANDO);
        if (getEstadoDireita(idDoFilosofo) == Estados.COMFOME &&
                getEstadoDireita(getDireita(idDoFilosofo)) != Estados.COMENDO) {
            estados.set(getDireita(idDoFilosofo),  Estados.COMENDO);
            ((Semaphore) filosofos[getDireita(idDoFilosofo)]).release();
        }
        if (getEstadoEsquerda(idDoFilosofo) == Estados.COMENDO.COMFOME &&
                getEstadoEsquerda(getEsquerda(idDoFilosofo)) != Estados.COMENDO) {
            estados.set(getEsquerda(idDoFilosofo), Estados.COMENDO);
            ((Semaphore) filosofos[getEsquerda(idDoFilosofo)]).release();
        }
        mutex.release();
    }
}