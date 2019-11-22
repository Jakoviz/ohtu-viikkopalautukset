package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
	private String score = "";
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (m_score1>=4 || m_score2>=4)
			numericToPhrasalWhenOverThreePoints();
        else
			numericToPhrasalWhenThreePointsOrUnder();
        return score;
    }

	private void numericToPhrasalWhenOverThreePoints() {
		int minusResult = m_score1-m_score2;
		if (minusResult == 0) score = "Deuce";
		else if (minusResult==1) score ="Advantage player1";
		else if (minusResult ==-1) score ="Advantage player2";
		else if (minusResult>=2) score = "Win for player1";
		else score ="Win for player2";
	}

	private void numericToPhrasalWhenThreePointsOrUnder() {
		score = phrasalExpressionWhenGameNotTied(1, m_score1, "");
		if (m_score1 == m_score2) 
			score += "-All";
		else
			score = phrasalExpressionWhenGameNotTied(2, m_score2, score + "-");
	}

	private String phrasalExpressionWhenGameNotTied(int playerNumber, int scoreNumeric, String scorePhrasal) {
		switch(scoreNumeric)
		{
			case 0:
				scorePhrasal+="Love";
				break;
			case 1:
				scorePhrasal+="Fifteen";
				break;
			case 2:
				scorePhrasal+="Thirty";
				break;
			case 3:
				scorePhrasal+="Forty";
				break;
		}
		return scorePhrasal;
	}
}