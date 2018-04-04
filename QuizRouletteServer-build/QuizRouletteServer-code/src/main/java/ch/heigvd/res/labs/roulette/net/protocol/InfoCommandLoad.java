package ch.heigvd.res.labs.roulette.net.protocol;

/**
 * Created by elien on 04.04.2018.
 */
public class InfoCommandLoad {
    private String status;
    private int numberOfNewStudents;

    public InfoCommandLoad() {
    }

    public InfoCommandLoad(String status, int numberOfNewStudents) {
        this.status = status;
        this.numberOfNewStudents = numberOfNewStudents;
    }

    public String getstatus() {
        return status;
    }

    public void setLoadstatus(String protocolVersion) {
        this.status = status;
    }

    public int getNumberOfNewStudents() {
        return numberOfNewStudents;
    }

    public void setNumberOfNewStudents(int numberOfStudents) {
        this.numberOfNewStudents = numberOfNewStudents;
    }
}
