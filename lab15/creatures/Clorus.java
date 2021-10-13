package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;

import javax.management.relation.RelationNotFoundException;
import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Random;

/** An implementation of a motile pacifist photosynthesizer.
 *  @author Josh Hug
 */
public class Clorus extends Creature {

    // red color
    private int r;
    // green color
    private int g;
    // blue color
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus() {
        this(1.0);
    }

    public Color color() {
        return color(this.r, this.g, this.b);
    }

    public void move() {
        energy = energy - 0.03;
    }

    public void stay() {
        energy = energy - 0.01;
    }

    public void attack(Creature c) {
        /* if a clorus attack another creature, it should gain its energy*/
        energy += c.energy();
    }

    public Clorus replicate() {
        Clorus result = new Clorus(energy * 0.5);
        energy = energy * 0.5;
        return result;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> space = getNeighborsOfType(neighbors, "empty");
        List<Direction> plip = getNeighborsOfType(neighbors, "plip");
        Random random = new Random(26);

        if (space.size() != 0) {
            int choice = random.nextInt(space.size());
            if (plip.size() != 0) {
                int attack = random.nextInt(plip.size());
                return new Action(Action.ActionType.ATTACK, plip.get(attack));
            }
            else if (energy >= 1.0){
                return new Action(Action.ActionType.REPLICATE, space.get(choice));
            }
            else {
                return new Action(Action.ActionType.MOVE, space.get(choice));
            }
        }
        return new Action(Action.ActionType.STAY);
    }

}