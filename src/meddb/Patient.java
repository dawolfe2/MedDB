
package meddb;

    //Daniel Wolfe
    //Patient class for patient status and information
    //patient variables are specific information related to their admission to the hosipital
public class Patient {

        //patient variables are made private to hide information
    private String first;
    private String last;
    private int age;
    private String sex;
        //string describing the reason the patient is admitted
    private String illness;
        //string describing all allergies of the patient
    private String allergies;
        //date first admitted to the hospital
    private String dateAdmitted;
        //hospital ward and room number
    private String ward;
    private int room;
    
        //simple constructor creating patient object using all patient information as parameters
    public Patient(String first, String last, int age, String sex, String illness, String allergies, String dateAdmitted, String ward, int room){
        
        this.first = first;
        this.last = last;
        this.age = age;
        this.sex = sex;
        this.illness = illness;
        this.allergies = allergies;
        this.dateAdmitted = dateAdmitted;
        this.ward = ward;
        this.room = room;
        
    }
    
        //toString method displaying all patient information
    public String toString(){
        return("Name: " + first + " " + last + "\nAge: " + age + "\nSex: " + sex + "\nReason for Admission: " + illness + "\nAllergies: " + allergies + 
                "\nDate of Admission: " + dateAdmitted + "\nWard: " + ward + "\nRoom Number: " + room);
    }

        //getters and setters for all patient variables
    
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(String dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }
    
    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
