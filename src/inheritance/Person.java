package inheritance;

import java.util.Date;

public class Person {
    private String suffix,first_name,last_name;
    private Date date_of_birth;

    public Person(String suffix, String first_name, String last_name) {
        setSuffix(suffix);
        setFirst_name(first_name);
        setLast_name(last_name);
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public String getSirName(){
        return getLast_name();
    }
    public String getName(){
        return getFirst_name()+" "+getLast_name();
    }
}
