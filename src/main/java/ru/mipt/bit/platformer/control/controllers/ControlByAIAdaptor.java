package ru.mipt.bit.platformer.control.controllers;

import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import org.awesome.ai.strategy.NotRecommendingAI;
import ru.mipt.bit.platformer.control.aicontrol.GameStateCreator;
import ru.mipt.bit.platformer.control.commands.*;
import org.awesome.ai.AI;
import ru.mipt.bit.platformer.driver.LogicLevel;
import ru.mipt.bit.platformer.objects.gameObjects.Tank;

import java.util.ArrayList;
import java.util.List;
/**
 * Use case
 */
public class ControlByAIAdaptor implements Controller {
    private final GameState gameState;
    private final AI ai;
    private final List<Command> tanksCommands;

    public ControlByAIAdaptor(LogicLevel logicLevel){

        ai = new NotRecommendingAI();

        GameStateCreator creator = new GameStateCreator();
        gameState = creator.createGameState(logicLevel.getTrees(),
                                            logicLevel.getTanks(),
                                            logicLevel.getWidth(),
                                            logicLevel.getHeight());

        tanksCommands = new ArrayList<>();
    }

    @Override
    public void generateCommand(){

    }

    @Override
    public void executeCommand() {
        recommendCommands();
        for (Command command : tanksCommands) {
            command.execute();
        }
    }

    public void recommendCommands() {
        List<Recommendation> recommendations = ai.recommend(gameState);
        for (Recommendation rec : recommendations) {
            tanksCommands.add(commandFromRecommendation(rec));
        }
    }

    Command commandFromRecommendation(Recommendation recommendation){
        Tank actor = (Tank) recommendation.getActor().getSource();
        Action action = recommendation.getAction();
        switch(action) {
            case MoveEast:
                return new MoveRightCommand(actor);
            case MoveWest:
                return new MoveLeftCommand(actor);
            case MoveNorth:
                return new MoveUpCommand(actor);
        }
        return new MoveDownCommand(actor);
    }
}
