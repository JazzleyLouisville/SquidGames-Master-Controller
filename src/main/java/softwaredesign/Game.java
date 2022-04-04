package softwaredesign;

public class Game {
    String gameName;
    int rounds;
    float winPercentage;
    GameState gameState;

    public Game(String gameName, int rounds, float winPercentage, GameState gameState) {
        this.gameName =  gameName;
        this.rounds =  rounds;
        this.winPercentage =  winPercentage;
        this.gameState =  gameState;
    }
}
