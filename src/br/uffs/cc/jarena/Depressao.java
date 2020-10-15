package br.uffs.cc.jarena;

public class Depressao extends Agente
{
	int count = 0;
	Boolean parar = false;
	Boolean chegouDestino = false;
	int group = this.getId() % 5;

	public Depressao(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
		setDirecao(this.NENHUMA_DIRECAO);
		System.out.println("Group = " + this.group);
	}


	public void pensa() {
		System.out.println("ID: " + this.getId() + " group: " + this.group + " || Posx: " + this.getX() + " : " + this.getY());
		this.count++;

		if(count % 5 == 0) {
			this.parar = false;
		}

		Quad(this.group);

		if(parar) {
			setDirecao(this.NENHUMA_DIRECAO);
			return;
		}
	}

	public void Quad(int quadrante) {
		int[] PlayerPos = {this.getX(), this.getY()};
		int[] Destino = {0,0};
		int gap = Constants.LARGURA_MAPA / 6;

		switch (quadrante) {
			// Center
			case 0:
				Destino[0] = Constants.LARGURA_MAPA / 2;
				Destino[1] = Constants.ALTURA_MAPA / 2;
				break;
			// upper right
			case 1:
				Destino[0] = Constants.LARGURA_MAPA - gap;
				Destino[1] = Constants.ALTURA_MAPA / 3;
				break;
			// upper left
			case 2:
				Destino[0] = gap;
				Destino[1] = Constants.ALTURA_MAPA / 3;
				break;
			// lower left
			case 3:
				Destino[0] = gap;
				Destino[1] = Constants.ALTURA_MAPA - gap;
				break;
			// lower right
			case 4:
				Destino[0] = Constants.LARGURA_MAPA - gap;
				Destino[1] = Constants.ALTURA_MAPA - gap;
				break;
			default:
				setDirecao(this.NENHUMA_DIRECAO);
				break;
		}

		if((Destino[0] == PlayerPos[0]) && (Destino[1] == PlayerPos[1]))
			setDirecao(this.NENHUMA_DIRECAO);
			// return true;

		// Horizontal
		if(this.count % 2 == 0) {
			if(PlayerPos[0] < Destino[0]) {
				// System.out.println("indo Direita");
				setDirecao(this.DIREITA);
			}
			else if (PlayerPos[0] > Destino[0]) {
				// System.out.println("indo esquerda");
				setDirecao(this.ESQUERDA);
			}
		}
		// Vertical
		else {
			if(PlayerPos[1] < Destino[1]) {
				// System.out.println("indo baixo");
				setDirecao(this.BAIXO);
			}
			else if(PlayerPos[1] > Destino[1]) {
				// System.out.println("indo cima");
				setDirecao(this.CIMA);
			}
		}
	}

	public void goRandom() {
		setDirecao(geraDirecaoAleatoria());
	}

	public void recebeuEnergia() {
		this.setDirecao(this.NENHUMA_DIRECAO);
		this.parar = true;
		this.count = 0;
	}

	public void tomouDano(int energiaRestanteInimigo) {
		// moveTowardsCenter();
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
