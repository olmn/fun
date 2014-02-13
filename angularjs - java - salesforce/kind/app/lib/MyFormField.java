package lib;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Map;

import play.*;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.*;
import views.html.*;
import javax.annotation.*;

import javax.persistence.Id;
import play.Logger;

public class MyFormField  {

    public int    seq = 1;
    public String prompt = null;
    public int size = 0;
    public String options = "";
    public String inputType = "text";
    public int maxLength = 50;
    public Class dataType = java.lang.String.class;
    public String name = "";
    public boolean required = false;
    public String value = "";
    public int rows = 3;
    public int cols = 50;

    public MyFormField()
    {

    }

    public void AttachTo(Object obj, Field f)
    {
        name = f.getName();
        dataType = f.getType();

        if(f.isAnnotationPresent(Id.class))
        {
            inputType = "hidden";
        }
        if(name.contains("_EBEAN_MARKER"))
        {
            inputType = "hidden";
        }
        if(f.isAnnotationPresent(Constraints.Required.class))
        {
            required = true;
        }

        try
        {
            value = Toolbox.ObjToStringOrBlank(f.get(obj));
        }
        catch (Exception e) {
            Logger.info("error getting value of property: " + e.getMessage());
        }

        MyFormInfo info = f.getAnnotation(MyFormInfo.class);
        if(info != null)
        {
            seq = Toolbox.CoalesceInt(info.seq(), seq);
            prompt = (info.prompt().equals("")) ? null: info.prompt();
            size = Toolbox.CoalesceInt(info.size(), size);
            options =  Toolbox.Coalesce(info.options(), options);
            inputType = Toolbox.Coalesce(info.inputType(), inputType);
            Toolbox.Log("initializing inputType: " + inputType);
            maxLength =  Toolbox.CoalesceInt(info.maxLength(), maxLength);
        }
    }

    public String Render()
    {
        String theTemplate = "<div class='MyFormField'>#prompt##required#<br>#input#</div>";
        String thePrompt = prompt;
        String theInput = "<input size=\"#size#\" type=\"#typ#\" name=\"#nam#\" value=\"#val#\">";
        String theSize = "";
        String theType = inputType;
        String theRequired = required ? "<div class='MyFormFieldRequired'>*</div>" : "";
        String theOptions = "";
        if(thePrompt == null)
            thePrompt = Toolbox.Proper(name);

        if(inputType.equals("textarea"))
        {
            theInput = "<textarea rows='#rows#' cols='#cols#' name='#nam#'>#val#</textarea>";
        }
        else if(inputType.equals("select"))
        {
            theOptions = Toolbox.RenderSelectOptions(options.split(","), value);
            theInput = "<select name='#nam#'>#options#</select>";
        }
        else if(inputType.equals("static"))
        {
            theInput = "#val#";
        }
        else if(inputType.equals("hidden"))
        {
            theTemplate = "#input#";
        }

        String html = theTemplate;
        html = html.replace("#prompt#", thePrompt);
        html = html.replace("#input#", theInput);
        html = html.replace("#size#", theSize);
        html = html.replace("#nam#", name);
        html = html.replace("#typ#", theType);
        html = html.replace("#val#", value);
        html = html.replace("#rows#", Integer.toString(rows));
        html = html.replace("#cols#", Integer.toString(cols));
        html = html.replace("#options#", theOptions);
        html = html.replace("#required#", theRequired);

        Toolbox.Log("rendering input " + name + ": type=" + theType);

        return html;
    }

}

