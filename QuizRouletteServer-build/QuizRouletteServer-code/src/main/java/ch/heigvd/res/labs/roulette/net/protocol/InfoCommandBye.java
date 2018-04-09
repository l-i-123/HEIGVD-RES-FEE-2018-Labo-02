package ch.heigvd.res.labs.roulette.net.protocol;

/**
 * Created by elien on 04.04.2018.
 */
public class InfoCommandBye {
    private String status;
    private int numberOfCommands;

    public InfoCommandBye() {
    }

    public InfoCommandBye(String status, int numberOfCommands) {
        this.status = status;
        this.numberOfCommands = numberOfCommands;
    }

    public String getstatus() {
        return status;
    }

    public void setByestatus(String protocolVersion) {
        this.status = status;
    }

    public int getNumberOfCommands() {
        return numberOfCommands;
    }

    public void setNumberOfCommands(int numberOfStudents) {
        this.numberOfCommands = numberOfCommands;
    }
}
