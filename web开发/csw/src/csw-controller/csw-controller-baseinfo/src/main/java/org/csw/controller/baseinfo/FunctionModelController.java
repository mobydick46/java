package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.entity.baseinfo.FunctionModel;
import org.csw.service.baseinfo.FunctionModelService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/functionmodel")
public class FunctionModelController {
     @Resource
     private FunctionModelService functionModelService;
     public void setFunctionModelService(FunctionModelService functionModelService){
    	 this.functionModelService = functionModelService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="type",required=false)String type,
 			@RequestParam(value="functionId",required=false)String functionId,
 			@RequestParam(value="funCalculationId",required=false)String funCalculationId,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionModelService.getFunctionModel(type, functionId,funCalculationId));
     }
     
     @RequestMapping("/add")
     public void add(
 			FunctionModel functionModel,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionModelService.addFunctionModel(functionModel));
     }
     
     @RequestMapping("/update")
     public void update(
 			FunctionModel functionModel,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionModelService.modifyFunctionModel(functionModel));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionModelService.deleteFunctionModel(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionModelService.getMaxID());
     }
     
     @RequestMapping("/refresh")
     public void refresh(
    		@RequestParam(value="functionId",required=false)String functionId,
 			@RequestParam(value="funCalculationId",required=false)String funCalculationId,
 			@RequestParam(value="type",required=false)String type,
 			@RequestParam(value="functionType",required=false)String functionType,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionModelService.refreshFunctionModel(functionId, funCalculationId,type,functionType));
     }
     
     @RequestMapping("/batchset")
     public void batchset(
 			@RequestParam(value="funCalculationId",required=false)String funCalculationId,
 			@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionModelService.batchsetFunctionModel(funCalculationId,ids));
     }
}
