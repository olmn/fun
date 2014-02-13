package models;
import java.util.*;
import javax.persistence.*;

import lib.MyFormInfo;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity public class Response extends Model {

    @Id
    public int id;

    public int questionId;
    public int respondentId;

    @Column(columnDefinition = "TEXT")
    public String question;

    @MyFormInfo(inputType = "textarea")
    @Column(columnDefinition = "TEXT")
    public String response;

    public static List<Response> FindByRespondent(int respondentId)
    {
        return Response.find.where("respondentId=" + respondentId).findList();
    }

    public static Response InitializeResponse(Respondent r, Question q)
    {
        Response resp = new Response();
        resp.questionId = q.id;
        resp.respondentId = r.id;
        resp.question = q.question;

        return resp;
    }

    public static Finder<Integer,Response> find = new Finder<Integer,Response>(
            Integer.class, Response.class
    );
}
