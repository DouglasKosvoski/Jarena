/**
 * Agente para batalhar na Arena, Trabalho 1 de Programacao1

 * Estrategia
 * 		Consiste em dividir os 15 individuos em pequenos grupos
 * os quais cada grupo ira se situar em um dos 5 quadrantes
 * estipulados (4 quadrantes cartesianos mais o centro)
 * estando cada grupo na sua posicao de batalha, dara inicio a exploracao
 * e ao encontrarem alguma fonte de energia trocaram mensagens para o bem do grupo

 * Douglas Kosvoski - 1911100022
 */

package br.uffs.cc.jarena;

public class Douglas extends Agente
{
	// usado principalmente para movimentacao na horizontal
	// ex: se o cycleCounter for par se move na horizontal se nao na vertical
	int cycleCounter = 0;
	// Atribui cada agente para um de 5 grupos
	int group = this.getId() % 15;
	// controla quando o player chegou ao seu destino ou se esta recebendo energia
	Boolean parar = false;
	Boolean chegouDestino = false;
	int[] Destino = {0,0};

	public Douglas(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
		setDirecao(this.NENHUMA_DIRECAO);
		this.Destino = EscolheDestino(this.group);
	}

	public void pensa() {
		// incrementa a cada ciclo
		this.cycleCounter++;
		// reseta o estado e continua a andar caso nao esteja energizando
		if(this.cycleCounter % 5 == 0) {
			this.parar = false;
			// responsavel por encaminhar os grupos para suas devidas posicoes
			MoveParaDestino(this.Destino);
		}
		if(this.cycleCounter % 20 == 0) {
			this.Destino = EscolheDestino(this.group);
		}

		// se for para parar troca estado para idle
		if(this.parar) {
			setDirecao(this.NENHUMA_DIRECAO);
			return;
		}
	}

	// Escolhe para qual quadrante cada grupo vai
	public int[] EscolheDestino(int groupID) {
		int[] Destino = {0,0};
		// distancia entre as bordas do mapa
		int Gap = 145;

		// Determina o quadrante baseado no id do grupo
		switch (groupID) {
			// Upper left
			case 0:
				Destino[0] = Gap;
				Destino[1] = Constants.ALTURA_MAPA / 3;
				break;
			// Upper center
			case 1:
				Destino[0] = Constants.LARGURA_MAPA / 2;
				Destino[1] = Constants.ALTURA_MAPA / 3;
				break;
			// Upper right
			case 2:
				Destino[0] = Constants.LARGURA_MAPA - Gap;
				Destino[1] = Constants.ALTURA_MAPA / 3;
				break;
			// Center left
			case 3:
				Destino[0] = Gap;
				Destino[1] = Constants.ALTURA_MAPA / 2;
				break;
			// Center center
			case 4:
				Destino[0] = Constants.LARGURA_MAPA / 2;
				Destino[1] = Constants.ALTURA_MAPA / 2;
				break;
			// Center right
			case 5:
				Destino[0] = Constants.LARGURA_MAPA - Gap;
				Destino[1] = Constants.ALTURA_MAPA / 2;
				break;
			// lower left
			case 6:
				Destino[0] = Gap;
				Destino[1] = Constants.ALTURA_MAPA - Gap;
				break;
			// Lower center
			case 7:
				Destino[0] = Constants.LARGURA_MAPA / 2;
				Destino[1] = Constants.ALTURA_MAPA - Gap;
				break;
			// lower right
			case 8:
				Destino[0] = Constants.LARGURA_MAPA - Gap;
				Destino[1] = Constants.ALTURA_MAPA - Gap;
				break;

			default:
				setDirecao(this.NENHUMA_DIRECAO);
				// this.goRandom();
				break;
		}
		return Destino;
	}

	// responsavel por encaminhar os grupos para suas devidas posicoes
	public void MoveParaDestino(int[] Destino) {
		int[] PlayerPos = {this.getX(), this.getY()};

		// se o agente chegou ate seu destino
		if((Destino[0] == PlayerPos[0]) && (Destino[1] == PlayerPos[1]))
			setDirecao(this.NENHUMA_DIRECAO);

		// Horizontal
		if(this.cycleCounter % 2 == 0) {
			if(PlayerPos[0] < Destino[0]) {
				setDirecao(this.DIREITA);
			}
			else if (PlayerPos[0] > Destino[0]) {
				setDirecao(this.ESQUERDA);
			}
		}
		// Vertical
		else {
			if(PlayerPos[1] < Destino[1]) {
				setDirecao(this.BAIXO);
			}
			else if(PlayerPos[1] > Destino[1]) {
				setDirecao(this.CIMA);
			}
		}
	}

	public void goRandom() {
		setDirecao(geraDirecaoAleatoria());
	}

	public void recebeuEnergia() {
		String msg = "E " + this.getX() + " " + this.getY();
		enviaMensagem(msg);

		this.setDirecao(this.NENHUMA_DIRECAO);
		this.parar = true;
		this.cycleCounter = 0;
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
		// System.out.println("recebi msg: "+ msg);
		String array[] = new String[3];
		array = msg.split(" ");

    if(msg.startsWith("E")) {
			int newX = Integer.parseInt(array[1]);
			int newY = Integer.parseInt(array[2]);
			this.Destino[0] = newX;
			this.Destino[1] = newY;
    }
		this.MoveParaDestino(this.Destino);
	}

	public String getEquipe() {
		return "SELECAO ARTIFICIAL";
	}
}
