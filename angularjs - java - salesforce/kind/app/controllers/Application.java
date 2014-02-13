package controllers;

import com.avaje.ebean.Ebean;
import org.h2.engine.User;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import models.*;
import lib.*;

import views.html.*;

import service.*;
import lib.*;
import com.sforce.soap.partner.*;
import com.sforce.ws.ConnectionException;
import com.sforce.soap.partner.sobject.SObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Application extends Controller {

    protected static String SubmitAction()
    {
        String[] submitAction = request().body().asFormUrlEncoded().get("submitAction");
        if(submitAction.length > 0)
            return submitAction[0];

        return "";
    }

    public static Result index() {

        Respondent r = new Respondent();
        MyForm<Respondent> f = new MyForm();
        f.create(r);
        return ok(index.render(f));
    }

    public static Result indexPost()
    {
        MyForm<Respondent> f = new MyForm(); // = new MyForm<Respondent>(Respondent.class);
        Respondent r = new Respondent();
        f.createFromRequest(r, request());
        String submitAction = SubmitAction();
        if(submitAction.equals("Continue"))
        {
            r.save();
            return redirect("/question/" + r.id + "/1");
        }
        return ok(index.render(f));
    }

    protected static void CreateTestQuestion(int id, String question)
    {
        Question q = new Question();
        q.id = id;
        q.question = question;
        q.save();
    }

    public static Result question(int respondentId, int questionId) {

        Ebean.delete(Question.find.all());
        CreateTestQuestion(1,"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua?");
        CreateTestQuestion(2,"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione?");
        CreateTestQuestion(3,"Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur?");

        if(questionId == 0)
            questionId = 1;
        Question q = Question.find.byId(questionId);

        Respondent r = Respondent.find.byId(respondentId);
        Response resp = Response.InitializeResponse(r, q);
        MyForm<Response> f = new MyForm();
        f.create(resp);
        return ok(question.render(f));
    }

    public static Result questionPost(int respondentId, int questionId)
    {
        MyForm<Response> f = new MyForm(); // = new MyForm<Respondent>(Respondent.class);
        Response r = new Response();
        f.createFromRequest(r, request());
        String submitAction = SubmitAction();
        if(submitAction.equals("Continue"))
        {
            if(r.response != null && (!r.response.equals("")))
            {
                r.save();
                int newQuestionId = r.questionId + 1;
                if(newQuestionId > 3)
                {
                    return redirect("/summary/" + respondentId);
                }
                String url = "/question/" + r.respondentId + "/" + newQuestionId;
                return redirect(url);
            }
        }
        return ok(question.render(f));
    }

    public static Result summary(int id) {

        Respondent r = Respondent.find.byId(id);
        List<Response> answers = Response.FindByRespondent(id);

        Response[] array = new Response[answers.size()];
        answers.toArray(array);

        Salesforce.SubmitToSalesforce(r, array);


        return ok(summary.render(r, answers));
    }

}
