package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.entity.baseinfo.FunctionCalculation;
import org.csw.service.baseinfo.FunctionCalculationService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/functioncalculation")
public class FunctionCalculationController {
     @Resource
     private FunctionCalculationService functionCalculationService;
     public void setFunctionCalculationService(FunctionCalculationService functionCalculationService){
    	 this.functionCalculationService = functionCalculationService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="parentCode",required=false)String parentCode,
 			@RequestParam(value="nodeType",required=false)String nodeType,
 			@RequestParam(value="functionId",required=false)String functionId,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionCalculationService.getFunctionCalculation(parentCode, nodeType,functionId));
     }
     
     @RequestMapping("/add")
     public void add(
 			FunctionCalculation functionCalculation,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionCalculationService.addFunctionCalculation(functionCalculation));
     }
     
     @RequestMapping("/update")
     public void update(
 			FunctionCalculation functionCalculation,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionCalculationService.modifyFunctionCalculation(functionCalculation));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionCalculationService.deleteFunctionCalculation(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionCalculationService.getMaxID());
     }
     
     @RequestMapping("/refresh")
     public void refresh(
    		@RequestParam(value="functionId",required=true)String functionId,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionCalculationService.refreshFunctionCalculation(functionId));
     }
     
     @RequestMapping("/getType")
     public void getType(
    		@RequestParam(value="functionId",required=true)String functionId,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionCalculationService.getType(functionId));
     }
     
     @RequestMapping("/getType2")
     public void getType2(
    		@RequestParam(value="functionId",required=true)String functionId,
    		@RequestParam(value="isCalculation",required=true)String isCalculation,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionCalculationService.getType2(functionId,isCalculation));
     }
     
     @RequestMapping("/getParent")
     public void getParent(
    		@RequestParam(value="functionId",required=true)String functionId,
    		@RequestParam(value="nodeType",required=true)String nodeType,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionCalculationService.getParent(functionId,nodeType));
     }
}
