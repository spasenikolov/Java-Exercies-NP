package secondexam.footballtable;

public class Team implements Comparable<Team>{
    private final String teamName;
    private Integer numOfGames;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private Integer numOfGoalsGiven;
    private Integer numOfGoalsTaken;

    public Team(String teamName) {
        this.teamName = teamName;
        this.numOfGames = 0;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.numOfGoalsGiven = 0;
        this.numOfGoalsTaken = 0;
    }


    public Integer getTotalPoints(){
        return wins * 3 + draws;
    }

    public String getTeamName() {
        return teamName;
    }

    public Integer getNumOfGames() {
        return numOfGames;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public Integer getDraws() {
        return draws;
    }

    public Integer getNumOfGoalsTaken() {
        return numOfGoalsTaken;
    }
    public Integer getNumOfGoalsGiven(){
        return numOfGoalsGiven;
    }

    public void incrementNumOfGames() {
        ++this.numOfGames;
    }

    public void incrementWins() {
        ++this.wins;
    }

    public void incrementLosses() {
        ++this.losses;
    }

    public void incrementDraws() {
        ++this.draws;
    }

    public void incrementNumOfGoalsTaken(Integer numOfGoals) {
        this.numOfGoalsTaken+=numOfGoals;
    }
    public void incrementNumOfGoalsGiven(Integer numOfGoals){
        this.numOfGoalsGiven+=numOfGoals;
    }
    public void setParameters(Integer thisTeamGoals, Integer otherTeamGoals){
        this.incrementNumOfGames();
        if(thisTeamGoals > otherTeamGoals){
            this.incrementWins();
        }
        else if( thisTeamGoals < otherTeamGoals){
            this.incrementLosses();
        }
        else incrementDraws();

        this.incrementNumOfGoalsGiven(thisTeamGoals);
        this.incrementNumOfGoalsTaken(otherTeamGoals);
    }
    public Integer getGoalLambda(){
        return this.numOfGoalsGiven - this.numOfGoalsTaken;
    }

    @Override
    public String toString() {
        return String.format("%-19s%5s%5s%5s%5s%5s",teamName,numOfGames,wins,draws,losses,getTotalPoints());
    }

    @Override
    public int compareTo(Team o) {
        return this.getTeamName().compareTo(o.teamName);
    }
}
