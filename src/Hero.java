import java.util.Random;

public class Hero {
    String name;
    int hitPoints;
    int wins;
    int oWins;
    String winner;

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Hero(String name) {
        this.name = name;
        hitPoints = 100;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void attack(Hero opponent) {
        Random rand = new Random();
        double number = rand.nextDouble();
        if (number < 0.5) {
            opponent.setHitPoints(opponent.getHitPoints() - 10);
        }
        else {
            hitPoints = hitPoints - 10;
            setHitPoints(hitPoints);
        }
    }

    public void senzuBean() {
        setHitPoints(100);
    }

    private void fightUntilTheDeathHelper(Hero opponent) {
        while (hitPoints > 0 && opponent.getHitPoints() > 0) {
            attack(opponent);
            System.out.println(name + ": " + getHitPoints() + " " + opponent.getName() + ": " + opponent.getHitPoints());
        }
    }

    public String fightUntilTheDeath(Hero opponent) {
        senzuBean();
        opponent.senzuBean();
        fightUntilTheDeathHelper(opponent);
        return name + ": " + getHitPoints() + " " + opponent.getName() + ": " + opponent.getHitPoints();
    }

    private void nFightsToTheDeathHelper(Hero opponent, int n) {
        wins = 0;
        oWins = 0;

        for (int i = 1; i <= n; i++) {
            fightUntilTheDeath(opponent);
            if (hitPoints == 0) {
                oWins = oWins + 1;
                winner = opponent.getName();
            }
            else {
                wins = wins + 1;
                winner = name;
            }
        }
    }

    public String nFightsToTheDeath(Hero opponent, int n) {
        String statement = "";
        nFightsToTheDeathHelper(opponent, n);
        if (wins > oWins || oWins > wins) {
            statement = name + ": " + wins + " wins" + "\n" +
                    opponent.getName() + ": " + oWins + " wins" + "\n" +
                    winner + " wins!";
        }
        else if (wins == oWins) {
            statement = name + ": " + wins + " wins" + "\n" +
                    opponent.getName() + ": " + oWins + " wins" + "\n" +
                    "OMG! It was actually a draw!";
        }
        return statement;
    }
}
