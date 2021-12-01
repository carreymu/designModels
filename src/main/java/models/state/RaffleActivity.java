package models.state;

public class RaffleActivity {
    private String state;
    private String canRaffleState;
    private String dispenseState;
    private String noRaffleState;

    public String getNoRaffleState() {
        return noRaffleState;
    }

    public void setNoRaffleState(String noRaffleState) {
        this.noRaffleState = noRaffleState;
    }

    public String getDispenseState() {
        return dispenseState;
    }

    public void setDispenseState(String dispenseState) {
        this.dispenseState = dispenseState;
    }

    public String getCanRaffleState() {
        return canRaffleState;
    }

    public void setCanRaffleState(String canRaffleState) {
        this.canRaffleState = canRaffleState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
