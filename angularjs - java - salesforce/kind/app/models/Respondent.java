package models;
import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import lib.*;


@Entity public class Respondent extends Model {

    @Id
    public int id;

    @Constraints.Required
    @MyFormInfo(prompt = "First Name")
    public String firstName;

    @Constraints.Required
    @MyFormInfo(prompt = "Last Name")
    public String lastName;

    @Constraints.Required
    public String email;

    public String city;

    public String age;

    @MyFormInfo(inputType="select", options = ",Male,Female")
    public String gender;

    public static Finder<Integer,Respondent> find = new Finder<Integer,Respondent>(
            Integer.class, Respondent.class
    );

}