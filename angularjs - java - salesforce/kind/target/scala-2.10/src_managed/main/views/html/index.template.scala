
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
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[lib.MyForm[Respondent],play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(f: lib.MyForm[Respondent]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.29*/("""

"""),_display_(Seq[Any](/*3.2*/main("Welcome")/*3.17*/ {_display_(Seq[Any](format.raw/*3.19*/("""

    Welcome to the kindness study.
        This is a quick survey with just 3 questions to answer.
        Your name and email are required, but if you would like to provide the other info
        that would add to the insights gained from the study.
    """),_display_(Seq[Any](/*9.6*/Html(f.form()))),format.raw/*9.20*/("""
<p>
    I really appreciate your help.  Now there are just a series of 3 questions that follow.
    Please don't spend too much time, just to put down what comes to mind quickly!
    Thank you.  To begin the quick survey, go ahead and press <b>Continue</b>.
</p>
<p>
    """),_display_(Seq[Any](/*16.6*/Html(f.submit("Continue")))),format.raw/*16.32*/("""
</p>
""")))})),format.raw/*18.2*/("""
"""))}
    }
    
    def render(f:lib.MyForm[Respondent]): play.api.templates.HtmlFormat.Appendable = apply(f)
    
    def f:((lib.MyForm[Respondent]) => play.api.templates.HtmlFormat.Appendable) = (f) => apply(f)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Dec 10 17:58:05 PST 2013
                    SOURCE: C:/dev/kindness/kindnessStudy/app/views/index.scala.html
                    HASH: 67a904b3e9975c310c0aaffc4f0562b27bbdc74c
                    MATRIX: 790->1|911->28|948->31|971->46|1010->48|1302->306|1337->320|1645->593|1693->619|1731->626
                    LINES: 26->1|29->1|31->3|31->3|31->3|37->9|37->9|44->16|44->16|46->18
                    -- GENERATED --
                */
            