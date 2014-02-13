// @SOURCE:C:/dev/kindness/kindnessStudy/conf/routes
// @HASH:b772652dd1c3562026f32bfc52467ca9d3118ffa
// @DATE:Sun Dec 01 19:54:37 PST 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:15
// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
package controllers {

// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def question(respondentId:Integer, questionId:Integer): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "question/" + implicitly[PathBindable[Integer]].unbind("respondentId", respondentId) + "/" + implicitly[PathBindable[Integer]].unbind("questionId", questionId))
}
                                                

// @LINE:7
def indexPost(): Call = {
   Call("POST", _prefix)
}
                                                

// @LINE:10
def questionPost(respondentId:Integer, questionId:Integer): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "question/" + implicitly[PathBindable[Integer]].unbind("respondentId", respondentId) + "/" + implicitly[PathBindable[Integer]].unbind("questionId", questionId))
}
                                                

// @LINE:12
def summary(id:Integer): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "summary/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:15
// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
package controllers.javascript {

// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def question : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.question",
   """
      function(respondentId,questionId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "question/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("respondentId", respondentId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("questionId", questionId)})
      }
   """
)
                        

// @LINE:7
def indexPost : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.indexPost",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:10
def questionPost : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.questionPost",
   """
      function(respondentId,questionId) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "question/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("respondentId", respondentId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("questionId", questionId)})
      }
   """
)
                        

// @LINE:12
def summary : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.summary",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "summary/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:15
// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:12
// @LINE:10
// @LINE:9
// @LINE:7
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def question(respondentId:Integer, questionId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.question(respondentId, questionId), HandlerDef(this, "controllers.Application", "question", Seq(classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """question/$respondentId<[^/]+>/$questionId<[^/]+>""")
)
                      

// @LINE:7
def indexPost(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.indexPost(), HandlerDef(this, "controllers.Application", "indexPost", Seq(), "POST", """""", _prefix + """""")
)
                      

// @LINE:10
def questionPost(respondentId:Integer, questionId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.questionPost(respondentId, questionId), HandlerDef(this, "controllers.Application", "questionPost", Seq(classOf[Integer], classOf[Integer]), "POST", """""", _prefix + """question/$respondentId<[^/]+>/$questionId<[^/]+>""")
)
                      

// @LINE:12
def summary(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.summary(id), HandlerDef(this, "controllers.Application", "summary", Seq(classOf[Integer]), "GET", """""", _prefix + """summary/$id<[^/]+>""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          
}
        
    