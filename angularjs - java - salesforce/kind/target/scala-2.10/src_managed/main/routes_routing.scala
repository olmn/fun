// @SOURCE:C:/dev/kindness/kindnessStudy/conf/routes
// @HASH:b772652dd1c3562026f32bfc52467ca9d3118ffa
// @DATE:Sun Dec 01 19:54:37 PST 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:7
private[this] lazy val controllers_Application_indexPost1 = Route("POST", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_Application_question2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("question/"),DynamicPart("respondentId", """[^/]+""",true),StaticPart("/"),DynamicPart("questionId", """[^/]+""",true))))
        

// @LINE:10
private[this] lazy val controllers_Application_questionPost3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("question/"),DynamicPart("respondentId", """[^/]+""",true),StaticPart("/"),DynamicPart("questionId", """[^/]+""",true))))
        

// @LINE:12
private[this] lazy val controllers_Application_summary4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("summary/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:15
private[this] lazy val controllers_Assets_at5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""POST""", prefix,"""controllers.Application.indexPost()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """question/$respondentId<[^/]+>/$questionId<[^/]+>""","""controllers.Application.question(respondentId:Integer, questionId:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """question/$respondentId<[^/]+>/$questionId<[^/]+>""","""controllers.Application.questionPost(respondentId:Integer, questionId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """summary/$id<[^/]+>""","""controllers.Application.summary(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:7
case controllers_Application_indexPost1(params) => {
   call { 
        invokeHandler(controllers.Application.indexPost(), HandlerDef(this, "controllers.Application", "indexPost", Nil,"POST", """""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_Application_question2(params) => {
   call(params.fromPath[Integer]("respondentId", None), params.fromPath[Integer]("questionId", None)) { (respondentId, questionId) =>
        invokeHandler(controllers.Application.question(respondentId, questionId), HandlerDef(this, "controllers.Application", "question", Seq(classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """question/$respondentId<[^/]+>/$questionId<[^/]+>"""))
   }
}
        

// @LINE:10
case controllers_Application_questionPost3(params) => {
   call(params.fromPath[Integer]("respondentId", None), params.fromPath[Integer]("questionId", None)) { (respondentId, questionId) =>
        invokeHandler(controllers.Application.questionPost(respondentId, questionId), HandlerDef(this, "controllers.Application", "questionPost", Seq(classOf[Integer], classOf[Integer]),"POST", """""", Routes.prefix + """question/$respondentId<[^/]+>/$questionId<[^/]+>"""))
   }
}
        

// @LINE:12
case controllers_Application_summary4(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        invokeHandler(controllers.Application.summary(id), HandlerDef(this, "controllers.Application", "summary", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """summary/$id<[^/]+>"""))
   }
}
        

// @LINE:15
case controllers_Assets_at5(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     