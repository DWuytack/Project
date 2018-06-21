package model;
/**
 *  Deze klasse bevat alle variabelen + getters en setters die een semster bezit.
 * @author CURSIST
 */
public class Semester {
    
    private int semesterID;
    private String semester;

    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }


    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
