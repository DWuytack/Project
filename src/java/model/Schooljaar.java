package model;

/**
 *  Deze klasse bevat alle variabelen + getters en setters die een schooljaar bezit.
 * @author CURSIST
 */
public class Schooljaar {
    
    private int schooljaarID;
    private String schooljaar;

    public int getSchooljaarID() {
        return schooljaarID;
    }

    public String getSchooljaar() {
        return schooljaar;
    }

    public void setSchooljaarID(int schooljaarID) {
        this.schooljaarID = schooljaarID;
    }

    public void setSchooljaar(String schooljaar) {
        this.schooljaar = schooljaar;
    }

}