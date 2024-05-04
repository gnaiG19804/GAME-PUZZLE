package newbtl;


public class Player {
    private String playerName;
    private int clickCount;
    private long completionTime;
    private long startTime;
    private long finishTime;
    private long displayTime;

    public Player(String playerName, int clickCount, long completionTime) {
        this.playerName = playerName;
        this.clickCount = clickCount;
        this.completionTime = completionTime;
    }

    public Player() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public long getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime() {
        this.completionTime = getGameDuration();
    }
    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }
    

    public void setFinishTime() {
        this.finishTime = System.currentTimeMillis(); 
    }


    public long getGameDuration() {
        return finishTime - startTime;
    }
    public void displayPlayerInfo() {
        System.out.println("Player Name: " + playerName);
        System.out.println("Click Count: " + clickCount);
        System.out.println("Total Time: " + displayTime);
    }

    public void setCompletionTime(long completionTime) {
        this.completionTime = completionTime;
    }

    public void setDisplayTime(long completionTime) {
        displayTime=completionTime%1000;
    }
    
    @Override
    public String toString() {
        return "Player{" + "playerName=" + playerName + ", clickCount=" + clickCount + ", startTime=" + startTime + ", finishTime=" + finishTime + ", displayTime=" + displayTime + '}';
    }

    
}


