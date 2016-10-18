package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.service.baseinfo.DecisionPlanService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/decisionplan")
public class DecisionPlanController {
     @Resource
     private DecisionPlanService decisionPlanService;
     public void setDecisionPlanService(DecisionPlanService decisionPlanService){
    	 this.decisionPlanService = decisionPlanService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="id",required=false)String id,
 			@RequestParam(value="timeID",required=false)String timeID,
 			@RequestParam(value="functionId",required=false)String functionId,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, decisionPlanService.getDecisionPlan(id, timeID,functionId));
     }
     
     @RequestMapping("/add")
     public void add(
    		@RequestParam(value="number",required=false)String number,
  			@RequestParam(value="numberSource",required=false)String numberSource,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, decisionPlanService.addDecisionPlan(number,numberSource));
     }
     
     @RequestMapping("/add2")
     public void add2(
    		 @RequestParam(value="number",required=false)String number,
    		@RequestParam(value="timeID",required=false)String timeID,
  			@RequestParam(value="functionId",required=false)String functionId,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, decisionPlanService.addDecisionPlan2(number,timeID,functionId));
     }
     
     @RequestMapping("/update")
     public void update(
   			@RequestParam(value="key",required=false)String key,
   			@RequestParam(value="value",required=false)String value,
  			@RequestParam(value="number",required=false)String number,
  			@RequestParam(value="functionId",required=false)String functionId,
  			@RequestParam(value="timeID",required=false)String timeID,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, decisionPlanService.modifyDecisionPlan(key,value,number,functionId,timeID));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, decisionPlanService.deleteDecisionPlan(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, decisionPlanService.getMaxID());
     }
     
     @RequestMapping("/getmaxnumber")
     public void getmaxnumber(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, decisionPlanService.getMaxNumber());
     }
     
     @RequestMapping("/getInitialization")
     public void getInitialization(
    		 @RequestParam(value="functionId",required=true)String functionId,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, decisionPlanService.getInitialization(functionId));
     }
     
     @RequestMapping("/change")
     public void change(
   			@RequestParam(value="key",required=false)String key,
   			@RequestParam(value="value",required=false)String value,
  			@RequestParam(value="numbers",required=false)String numbers,
  			@RequestParam(value="times",required=false)String times,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, decisionPlanService.changeDecisionPlan(key,value,numbers,times));
     }
}
