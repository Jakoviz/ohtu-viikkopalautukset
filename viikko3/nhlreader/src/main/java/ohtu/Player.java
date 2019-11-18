
package ohtu;

public class Player {
    private String name;
    private int goals;
    private int assists;
    private String nationality;
    private int penalties;
    private String team;

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public String getNationality() {
        return nationality;
    }

    public int getPenalties() {
        return penalties;
    }

    public String getTeam() {
        return team;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
      
}
