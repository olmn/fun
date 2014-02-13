
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object debug extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(txt: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.15*/("""

<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
"""),_display_(Seq[Any](/*9.2*/txt)),format.raw/*9.5*/("""
</body>
</html>"""))}
    }
    
    def render(txt:String): play.api.templates.HtmlFormat.Appendable = apply(txt)
    
    def f:((String) => play.api.templates.HtmlFormat.Appendable) = (txt) => apply(txt)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sun Dec 01 19:52:29 PST 2013
                    SOURCE: C:/dev/kindness/kindnessStudy/app/views/debug.scala.html
                    HASH: 5d8bfcf5038bbf5f80077e0aa68389a9d0671010
                    MATRIX: 774->1|881->14|983->82|1006->85
                    LINES: 26->1|29->1|37->9|37->9
                    -- GENERATED --
                */
            