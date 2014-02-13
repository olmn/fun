package lib;

import play.Logger;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class Toolbox {

    public static String Coalesce(String s1, String s2)
    {
        String result = "";
        if(s1 != null && (!s1.equals(""))) result = s1;
        else if(s2 != null && (!s2.equals(""))) result = s2;

        return result;
    }

    public static int CoalesceInt(int s1, int s2)
    {
        int result = 0;
        if(s1 != 0)
            result = s1;
        else if(s2 != 0)
            result = s2;

        return result;
    }

    public static String ObjToStringOrBlank(Object o)
    {
        if(o != null) return o.toString();

        return "";
    }

    public static String RenderSelectOptions(String[] options, String val)
    {
        String result = "";
        for (int i = 0; i < options.length; i++) {
            final String opt = options[i];
            final String sel = (opt == val) ? " selected " : "";
            result += "<option " + sel + "  value='" + opt + "'>" + opt + "</option>\r\n";
        }

        return result;
    }

    public static String Proper(String s)
    {
        s = s.trim();
        String result = "";
        if(s.length() > 0)
        {
            result += s.substring(0, 1).toUpperCase();

            for(int i = 1; i < s.length(); i++)
            {
                Character c = s.charAt(i);
                if(Character.isUpperCase(c))
                {
                    result += " " + c;
                }
                else
                    result += c;
            }
        }

        return result;

    }

    public static void Log(String s)
    {
        Logger.info(s);
    }

    public static void Debug(String s)
    {
        Logger.info(s);
    }

    public static String MyDumpObject(Object o, int callCount) {
        callCount++;
        StringBuffer tabs = new StringBuffer();
        for (int k = 0; k < callCount; k++) {
            tabs.append("\t");
        }
        StringBuffer buffer = new StringBuffer();
        Class oClass = o.getClass();
        if (oClass.isArray()) {
            buffer.append("\n");
            buffer.append(tabs.toString());
            buffer.append("[");
            for (int i = 0; i < Array.getLength(o); i++) {
                if (i < 0)
                    buffer.append(",");
                Object value = Array.get(o, i);
                if (value.getClass().isPrimitive() ||
                        value.getClass() == java.lang.Long.class ||
                        value.getClass() == java.lang.String.class ||
                        value.getClass() == java.lang.Integer.class ||
                        value.getClass() == java.lang.Boolean.class
                        ) {
                    buffer.append(value);
                } else {
                    buffer.append(MyDumpObject(value, callCount));
                }
            }
            buffer.append(tabs.toString());
            buffer.append("]\n");
        } else {
            buffer.append("\n");
            buffer.append(tabs.toString());
            buffer.append("{\n");
            while (oClass != null) {
                Field[] fields = oClass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    buffer.append(tabs.toString());
                    fields[i].setAccessible(true);
                    buffer.append(fields[i].getName());
                    buffer.append("=");
                    try {
                        Object value = fields[i].get(o);
                        if (value != null) {
                            if (value.getClass().isPrimitive() ||
                                    value.getClass() == java.lang.Long.class ||
                                    value.getClass() == java.lang.String.class ||
                                    value.getClass() == java.lang.Integer.class ||
                                    value.getClass() == java.lang.Boolean.class
                                    ) {
                                buffer.append(value);
                            } else {
                                buffer.append(MyDumpObject(value, callCount));
                            }
                        }
                    } catch (IllegalAccessException e) {
                        buffer.append(e.getMessage());
                    }
                    buffer.append("\n");
                }
                oClass = oClass.getSuperclass();
            }
            buffer.append(tabs.toString());
            buffer.append("}\n");
        }
        return buffer.toString();
    }

}
