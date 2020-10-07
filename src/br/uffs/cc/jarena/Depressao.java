/**
 * Um exemplo de agente que anda aleatoriamente na arena. Esse agente pode ser usado como base
 * para a criação de um agente mais esperto. Para mais informações sobre métodos que podem
 * ser utilizados, veja a classe Agente.java.
 *
 * Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
 */

package br.uffs.cc.jarena;

public class Depressao extends Agente
{
	int count = 0;

	public Depressao(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
		setDirecao(this.NENHUMA_DIRECAO);
	}


	public void pensa() {
		this.count++;

		// System.out.println(this.getId());
		// System.out.println(this.count);
		// if(recebeuEnergia()) {
		// 	setDirecao(this.NENHUMA_DIRECAO);
		// }
		// else {
		MoveTowardsCenter();
		// }
	}

	public void MoveTowardsCenter() {
		int[] Destino = {Constants.LARGURA_MAPA / 2, Constants.ALTURA_MAPA / 2};
		int[] PlayerPos = {this.getX(), this.getY()};

		if(Destino == PlayerPos) {
			return;
		}
		// Horizontal
		if(this.count % 2 == 0) {
			if(PlayerPos[0] < Destino[0]) {
				System.out.println("ta na esquerda");
				setDirecao(this.DIREITA);
			}
			else if(PlayerPos[0] > Destino[0]) {
				System.out.println("ta na direita");
				setDirecao(this.ESQUERDA);
			}
		}
		else {
			// Vertical
			if(PlayerPos[1] < Destino[1]) {
				System.out.println("ta pra cima");
				setDirecao(this.BAIXO);
			}
			else if(PlayerPos[1] > Destino[1]) {
				System.out.println("ta pra baixo");
				setDirecao(this.CIMA);
			}
		}
	}

	public void recebeuEnergia() {
		// Invocado sempre que o agente recebe energia.
	}

	public void tomouDano(int energiaRestanteInimigo) {
		// Invocado quando o agente está na mesma posição que um agente inimigo
		// e eles estão batalhando (ambos tomam dano).
	}

	public void ganhouCombate() {
		// Invocado se estamos batalhando e nosso inimigo morreu.
	}

	public void recebeuMensagem(String msg) {
		// Invocado sempre que um agente aliado próximo envia uma mensagem.
	}

	public String getEquipe() {
		// Definimos que o nome da equipe do agente é "Fernando".
		return "Depressao";
	}
}
