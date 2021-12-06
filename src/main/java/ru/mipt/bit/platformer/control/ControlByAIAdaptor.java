package ru.mipt.bit.platformer.control;

import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import org.awesome.ai.strategy.NotRecommendingAI;
import ru.mipt.bit.platformer.control.aicontrol.GameStateCreator;
import ru.mipt.bit.platformer.control.commands.*;
import org.awesome.ai.AI;
import ru.mipt.bit.platformer.objects.Tank;
import ru.mipt.bit.platformer.objects.Tree;

import java.util.ArrayList;
import java.util.List;


public class ControlByAIAdaptor implements Manager{
    private GameState gameState;
    private AI ai;
    private final List<Command> tanksCommands;

    public ControlByAIAdaptor(List<Tree> trees, List<Tank> tanks, int width, int height){
        ai = new NotRecommendingAI();

        GameStateCreator creator = new GameStateCreator();
        gameState = creator.createGameState(trees, tanks, width, height);

        tanksCommands = new ArrayList<>();
    }

    @Override
    public void executeCommand() {
        System.out.println("Execute Command");
        recommendCommands();
        for (Command command : tanksCommands) {
            command.execute();
        }
    }

    public void recommendCommands() {
//        tanksCommands.clear();
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
