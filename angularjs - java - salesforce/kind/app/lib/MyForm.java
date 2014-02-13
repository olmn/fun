package lib;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Map;

import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import javax.persistence.Id;
import play.Logger;

public class MyForm<T> {

    private T Obj;

    public void create(T o)
    {
        Obj = o;
    }

    public void createFromRequest(T o, Http.Request r)
    {
        Obj = o;
        Map<String,String[]> fields = r.body().asFormUrlEncoded();

        for (Map.Entry<String,String[]> field : fields.entrySet()) {
            String key = field.getKey();
            String[] values = field.getValue();
            if(values.length > 0)
            {
                Logger.info("setting property" + key + "=" + values[0]);
                setObjectPropertyValue(o, key, values[0].toString());
            }
        }
    }

    public String form()
    {
        String html = "";
        Class oClass = Obj.getClass();
        Field[] fields = oClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            final Field f = fields[i];
            MyFormField fld = new MyFormField();
            fld.AttachTo(Obj, f);
            if (fld.dataType == java.lang.Long.class ||
                fld.dataType == java.lang.String.class ||
                fld.dataType == java.lang.Integer.class ||
                fld.dataType == java.lang.Boolean.class)
            {
                if(!fld.inputType.equals(""))
                {
                    html += fld.Render();
                }

                 /*
                for (final Annotation annotation : fld.getDeclaredAnnotations()) {
                    html += ("<br>Annotation: " + annotation);
                }
                */
            }
        }

        return html;
    }

    public String hidden(String names)
    {
        String html = "";
        String[] nams = names.split(",");
        for(int i=0; i < nams.length; i++)
        {
            html += input(nams[i], new MyFormField() {{ inputType="hidden"; }});
        }

        return html;
    }

    public String input(String key, MyFormField f)
    {
        if(f == null)
            f = new MyFormField();
        String html = "";

        if(key != null)
        {
            Field field = getObjectField(Obj, key);
            if(field != null)
            {
                f.AttachTo(Obj, field);
            }
        }
        html = f.Render();
        return html;
    }

    public String submit(String val)
    {
        return "<input type='submit' name='submitAction' value='" + val + "'>";
    }

    public String get(String key)
    {
       String val = Toolbox.ObjToStringOrBlank(getObjectPropertyValue(Obj, key));
        if(val == null)
            val = "";
        return val;
    }

    public void set(String key, String v)
    {
        setObjectPropertyValue(Obj, key, v) ;
    }

    public static Field getObjectField(Object object, String fieldName) {

        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                if(field != null)
                {
                    field.setAccessible(true);
                    return field;
                }
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                Logger.info("error finding property: " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    public static boolean setObjectPropertyValue(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                if(field != null)
                {
                    field.setAccessible(true);
                    Class typ = field.getType();
                    if(typ.toString().equals("int")) // typ.equals(java.lang.Integer.class)) //
                    {
                        field.set(object, Integer.parseInt(fieldValue.toString()));
                    }
                    else if(typ == java.lang.Boolean.class)
                        field.set(object, Boolean.parseBoolean(fieldValue.toString()));
                    else
                        field.set(object, fieldValue);
                    return true;
                }
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                Logger.info("error setting property: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public static <E> E getObjectPropertyValue(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                if(field != null)
                {
                    field.setAccessible(true);
                    return (E) field.get(object);
                }
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
