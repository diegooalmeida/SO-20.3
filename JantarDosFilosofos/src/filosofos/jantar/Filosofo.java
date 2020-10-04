package filosofos.jantar;

public class Filosofo implements Runnable {

	private int id;
	private int tempoPensando;
	private int tempoComendo;
	private Jantar jantar;

	public Filosofo(int id, int tempoPensando, int tempoComendo, Jantar jantar) {
		this.id = id;
		this.tempoPensando = tempoPensando;
		this.tempoComendo = tempoComendo;
		this.jantar = jantar;
		new Thread((Runnable)this, "Filosofo" + id).start();
	}

	@Override
	public void run() {
		while(true) {
			pensa();
			pegaTalheres();
			come();
			devolveTalheres();
		}
	}

	private void pensa() {
		try {
			Thread.sleep(this.tempoPensando);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void pegaTalheres() {
		jantar.pegaTalheres(this.id);
	}

	private void come() {
		try {
			Thread.sleep(this.tempoComendo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void devolveTalheres() {
		jantar.devolveTalheres(this.id);
	}
}