package model;
/**
 *  Deze klasse bevat alle variabelen + getters en setters die een semster bezit.
 * @author CURSIST
 */
public class Semester {
    private int semesterID;
    private String semester;

    public int getSemsterID() {
        return semesterID;
    }

    public void setSemsterID(int semsterID) {
        this.semesterID = semsterID;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
