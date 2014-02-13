
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
object summary extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[Respondent,List[Response],play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(r: Respondent, answers: List[Response]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.42*/("""

"""),_display_(Seq[Any](/*3.2*/main("Thank you")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""
        <p>
        Thank you so much for completing the study, """),_display_(Seq[Any](/*5.54*/r/*5.55*/.firstName)),format.raw/*5.65*/(""".  It's really appreciated.
        Below is a summary of your answers.
                                              <p>
            Please feel free to send me a note,
            if you would like to be notified
            when the research is finally available to view,
            or if you might be interested in participating in the video interview.
            - Art
        </p>

"""),_display_(Seq[Any](/*15.2*/for(q <- answers) yield /*15.19*/ {_display_(Seq[Any](format.raw/*15.21*/("""
<div class='responseTable'>
    """),_display_(Seq[Any](/*17.6*/q/*17.7*/.question)),format.raw/*17.16*/("""
    <br>
    """),_display_(Seq[Any](/*19.6*/q/*19.7*/.response)),format.raw/*19.16*/("""
</div>
""")))})),format.raw/*21.2*/("""

""")))})),format.raw/*23.2*/("""
"""))}
    }
    
    def render(r:Respondent,answers:List[Response]): play.api.templates.HtmlFormat.Appendable = apply(r,answers)
    
    def f:((Respondent,List[Response]) => play.api.templates.HtmlFormat.Appendable) = (r,answers) => apply(r,answers)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Dec 10 17:41:43 PST 2013
                    SOURCE: C:/dev/kindness/kindnessStudy/app/views/summary.scala.html
                    HASH: 1c63d74a8c9efaeb9a9bd2c600f287cb37ec0ae8
                    MATRIX: 795->1|929->41|966->44|991->61|1030->63|1131->129|1140->130|1171->140|1597->531|1630->548|1670->550|1739->584|1748->585|1779->594|1829->609|1838->610|1869->619|1909->628|1943->631
                    LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5|33->5|43->15|43->15|43->15|45->17|45->17|45->17|47->19|47->19|47->19|49->21|51->23
                    -- GENERATED --
                */
            