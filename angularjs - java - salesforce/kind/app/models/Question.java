package models;
import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity public class Question extends Model {

    @Id
    public int id;

    @Constraints.Required
    @Column(columnDefinition = "TEXT")
    public String question;

    public static Finder<Integer,Question> find = new Finder<Integer,Question>(
            Integer.class, Question.class
    );

}
