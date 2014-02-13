
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
object question extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[lib.MyForm[Response],play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(f: lib.MyForm[Response]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.27*/("""
"""),_display_(Seq[Any](/*2.2*/main("Question")/*2.18*/ {_display_(Seq[Any](format.raw/*2.20*/("""
<b>"""),_display_(Seq[Any](/*3.5*/f/*3.6*/.get("question"))),format.raw/*3.22*/("""</b>
"""),_display_(Seq[Any](/*4.2*/Html(f.input("response", new lib.MyFormField() {{ rows=10; cols=55; inputType = "textarea"; }})))),format.raw/*4.98*/("""
"""),_display_(Seq[Any](/*5.2*/Html(f.submit("Continue")))),format.raw/*5.28*/("""
"""),_display_(Seq[Any](/*6.2*/Html(f.hidden("id,respondentId,questionId,question")))),format.raw/*6.55*/("""
""")))})),format.raw/*7.2*/("""
"""))}
    }
    
    def render(f:lib.MyForm[Response]): play.api.templates.HtmlFormat.Appendable = apply(f)
    
    def f:((lib.MyForm[Response]) => play.api.templates.HtmlFormat.Appendable) = (f) => apply(f)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Dec 10 19:46:56 PST 2013
                    SOURCE: C:/dev/kindness/kindnessStudy/app/views/question.scala.html
                    HASH: 3310d88c2f1718c5eac4de233513be5334c4b825
                    MATRIX: 791->1|910->26|946->28|970->44|1009->46|1048->51|1056->52|1093->68|1133->74|1250->170|1286->172|1333->198|1369->200|1443->253|1475->255
                    LINES: 26->1|29->1|30->2|30->2|30->2|31->3|31->3|31->3|32->4|32->4|33->5|33->5|34->6|34->6|35->7
                    -- GENERATED --
                */
            