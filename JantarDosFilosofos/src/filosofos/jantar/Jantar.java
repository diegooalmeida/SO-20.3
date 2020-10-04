package filosofos.jantar;

import java.util.ArrayList;

public abstract class Jantar {

    protected ArrayList<Estados> estados;
    protected Object[] filosofos;
    protected int numDeFilosofos;

    public void pegaTalheres(int idDoFilosofo) {};

    public void devolveTalheres(int idDoFilosofo) {};

    protected boolean podeComer (int idDoFilosofo) {
        return (getEstadoDireita(idDoFilosofo) != Estados.COMENDO &&
                getEstadoEsquerda(idDoFilosofo) != Estados.COMENDO);
    }

    protected Estados getEstadoDireita (int idDoFilosofo) {
        return estados.get(getDireita(idDoFilosofo));
    }

    protected int getDireita (int posicao) {
        return (posicao + 1) % numDeFilosofos;
    }

    protected Estados getEstadoEsquerda (int idDoFilosofo) {
        return estados.get(getEsquerda((idDoFilosofo)));
    }

    protected int getEsquerda (int posicao) {
        return (posicao + numDeFilosofos - 1) % numDeFilosofos;
    }

}