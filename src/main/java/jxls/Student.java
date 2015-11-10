package jxls;

public class Student {

    private String name;
    private String subject;
    private String idname;
    private String scorename;

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname;
    }

    public String getScorename() {
        return scorename;
    }

    public void setScorename(String scorename) {
        this.scorename = scorename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", subject=" + subject + ", idname=" + idname + ", scorename=" + scorename + '}';
    }

}
