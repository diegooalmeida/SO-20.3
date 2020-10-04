package filosofos.jantar;

import java.util.ArrayList;

public class JantarComMonitores extends Jantar{

    public JantarComMonitores(int numDeFilosofos) {
        super();
        this.numDeFilosofos = numDeFilosofos;
        this.estados = new ArrayList<>(this.numDeFilosofos);
        this.filosofos = new Object[this.numDeFilosofos];

        for(int i = 0; i < this.numDeFilosofos; i++) {
            this.estados.add(Estados.PENSANDO);
            this.filosofos[i] = new Object();
        }

        System.out.println(estados);
    }

    @Override
    public void pegaTalheres(int idDoFilosofo) {
        estados.set(idDoFilosofo, Estados.COMFOME);
        synchronized (filosofos[idDoFilosofo]) {
            if (podeComer(idDoFilosofo)) {
                estados.set(idDoFilosofo, Estados.COMENDO);
            } else {
                try {
                    this.filosofos[idDoFilosofo].wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted Exception");
                }
            }
            System.out.println(estados);
        }
    }

    @Override
    public void devolveTalheres(int idDoFilosofo) {
        estados.set(idDoFilosofo, Estados.PENSANDO);
        System.out.println(estados);

        if(getEstadoDireita(idDoFilosofo) == Estados.COMFOME &&
                getEstadoDireita(getDireita(idDoFilosofo)) != Estados.COMENDO) {
            estados.set(getDireita(idDoFilosofo), Estados.COMENDO);
            synchronized (filosofos[getDireita(idDoFilosofo)]) {
                filosofos[getDireita(idDoFilosofo)].notify();
            }
        }
        if(getEstadoDireita(idDoFilosofo) == Estados.COMFOME &&
                getEstadoEsquerda(getEsquerda(idDoFilosofo)) != Estados.COMENDO) {
            estados.set(getEsquerda(idDoFilosofo), Estados.COMENDO);
            synchronized (filosofos[getEsquerda(idDoFilosofo)]) {
                filosofos[getEsquerda(idDoFilosofo)].notify();
            }
        }
    }
}