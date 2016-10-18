package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.entity.baseinfo.FunctionType;
import org.csw.service.baseinfo.FunctionTypeService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/functiontype")
public class FunctionTypeController {
     @Resource
     private FunctionTypeService functionTypeService;
     public void setFunctionTypeService(FunctionTypeService functionTypeService){
    	 this.functionTypeService = functionTypeService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="id",required=false)String id,
 			@RequestParam(value="projectName",required=false)String projectName,
 			@RequestParam(value="isSet",required=false)String isSet,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionTypeService.getFunctionType(id, isSet,projectName));
     }
     
     @RequestMapping("/add")
     public void add(
 			FunctionType functionType,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionTypeService.addFunctionType(functionType));
     }
     
     @RequestMapping("/update")
     public void update(
 			FunctionType functionType,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionTypeService.modifyFunctionType(functionType));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionTypeService.deleteFunctionType(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, functionTypeService.getMaxID());
     }
}
