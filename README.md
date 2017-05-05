# LPOO1617-T6G13

![bulletborne_class _diagram](https://cloud.githubusercontent.com/assets/25772341/25558803/32c7263a-2d26-11e7-9e8c-a767a2e41d6a.png)

![bulletborne](https://cloud.githubusercontent.com/assets/25772341/25558804/32f774e8-2d26-11e7-8c59-497d9ca3e623.png)


Design Patterns

Flyweight Pattern (informação repetida para objetos com várias instâncias por ex.: naves inimigas);

Observer (usado para o achievement unlocking);

State (para definir o estado do jogo);

Strategy (utilizado para definir os vários tipos de comportamento caso existam vários tipos de naves inimigas ou mesmo vários tipos de naves do jogador);

Decorator ( utilizado caso powerup's sejam implementados);

Factory (Para criação de inimigos, ou até mesmo Powerup's);

Game Loop (Loop do jogo);

Singleton( apenas 1 jogo a decorrer e uma nave por jogador);

Proxy (usado caso networking seja implementado);

Object Pool (reutilização de objetos já criados como por ex.: naves inimigas e balas);


Main Menu

![bulletborne_main_menu](https://cloud.githubusercontent.com/assets/25772341/25525924/2bd058c4-2c09-11e7-96b0-4851bfcd485d.png)

Playing

![bulletborne_play](https://cloud.githubusercontent.com/assets/25772341/25525925/2bd5f90a-2c09-11e7-984c-e89e57bd231c.png)

Choose your character

![bulletborne_hangar](https://cloud.githubusercontent.com/assets/25772341/25525920/2b9fccb8-2c09-11e7-81a3-df8be0aa779b.png)

Settings

![bulletborne_settings](https://cloud.githubusercontent.com/assets/25772341/25525923/2bcf8bd8-2c09-11e7-94da-6aaabc6a8314.png)

Achievements

![bulletborne_achievements](https://cloud.githubusercontent.com/assets/25772341/25525926/2bdf5ba8-2c09-11e7-8905-81b72250a9c9.png)

High Scores

![bulletborne_highscores](https://cloud.githubusercontent.com/assets/25772341/25525922/2bbb3386-2c09-11e7-825d-32999c8e8109.png)


Tests

public class testBulletborne{

public void physicsShipMovingUp(){
	/*Testa o movimento da nave do jogador dado o input correto para ela subir*/
}

public void physicsShipMovingDown(){
	/*Testa se a nave começa a "cair" na ausência de input (para a fazer subir) do jogador, atuação da 
gravidade sobre a nave*/
}

public void moveShipIntoSuperiorLimit(){
	/*Testa o acontecimento da nave do jogador tentar sair do limite superior do jogo*/
}

public void moveShipIntoInferiorLimit(){
	/*Testa o acontecimento da nave do jogador tentar sair do limite inferior do jogo*/
}

public void shootingOfPlayerShip(){
	/*Testa o disparo da nave do utilizador*/
}

public void bulletPlayerShip(){
	/*Testa a trajetória da bala que a nave do utilizador disparou*/
}

public void loseHealth(){
	/*Testa se a nava perda vida consoante a colisão com outro objeto*/
}

public void gameState(){
	/*Testa a veracidade do estado de jogo (por ex.: a decorrer, perdeu, pausa)*/
}

public void loseGame(){
	/*Testa o estado de jogo caso o jogador perder todas as suas vidas*/
}

public void shipCaracteristics(){
	/*Caso existam vários tipos de naves para o jogador escolher:
	Testa se as caracteristicas da nave do jogador correspondem ás mesmas que ele escoheu 
(ex.:vida,velocidade)*/
}

/**************************************************************************/

funções repetidas caso haja mais que 1 tipo de naves inimigas:

public void creationOfEnemyShip(){
	/*Testa a criação de naves inimigas*/
}

public void movimentOfEnemyShip(){
	/*Testa o movimento de naves inimigas*/
}

public void shootingOfEnemyShip(){
	/*Testa o disparo de naves inimigas*/
}

public void bulletEnemyShip(){
	/*Testa a trajetória da bala que uma nave inimiga disparou*/
}

public void typeEnemyShipCaracteristics(){
	/*Testa se as caracteristicas de um tipo de nave inimiga são as corretas (por ex.: velocidade, vida)*/
}

/**************************************************************************/

public void collisionWithEnemyShip(){
	/* Testa a perda de vida/jogo aquando de uma colisão com uma nave inimiga*/
}

public void collisionWithEnemyBullet(){
	/* Testa a perda de vida/jogo aquando de uma colisão com uma bala inimiga*/
}


public void collisionWithTerrain(){
	/* Testa a perda de vida/jogo aquando de uma colisão com o terreno caso exista*/
}

public void enemyShipDestroyed(){
	/* Testa se uma nave inimiga é destruída/perde vida caso seja atinijida por uma bala do jogador*/
}

public void enemyCollision(){
	/*Testa se a colisão de uma nave/bala inimiga com outra nave/bala inimiga é possivel
	se for o teste não passa*/
}

public void traveledDistance(){
	/*Testa se o contador de distância percorrida está a funcionar corretamente*/
}

public void achievementUnlocking(){
	/*Testa se os achievement's estão a ser desbloqueados com o progresso*/
}

public void difficultyIncreasing(){
	/*testa se a dificuldade de jogo está aumenta com a distância percorida
	A dificuldade é dada pelo numero de objetos (naves inimigas, balas) presentes nesse exato momento*/
}
public void randomCreationOfEnemyShips(){
	/*Caso sejam implementados vários tipos de naves inimigas, testa as suas criações que devem ser 
aleatórias*/
}

public void bulletDisappears(){
	/*Testa se as balas desaparecem após teram transbordado os limites do jogo*/
}

public void highScoreSaving(){
	/*Testa se o melhor score do jogador está a ser guardado corretamente*/
}

public void achievementSave(){
	/*Testa se todos os achievement's desbloqueados estão a ser guardados corretamente*/
}
}
