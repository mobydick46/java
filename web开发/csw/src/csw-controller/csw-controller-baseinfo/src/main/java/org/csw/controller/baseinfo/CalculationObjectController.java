package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.entity.baseinfo.CalculationObject;
import org.csw.service.baseinfo.CalculationObjectService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/calculationobject")
public class CalculationObjectController {
     @Resource
     private CalculationObjectService calculationObjectService;
     public void setCalculationObjectService(CalculationObjectService calculationObjectService){
    	 this.calculationObjectService = calculationObjectService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="parentCode",required=false)String parentCode,
 			@RequestParam(value="name",required=false)String name,
 			@RequestParam(value="nodeType",required=false)String nodeType,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, calculationObjectService.getCalculationObject(parentCode, name, nodeType));
     }
     
     @RequestMapping("/add")
     public void add(
 			CalculationObject calculationObject,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, calculationObjectService.addCalculationObject(calculationObject));
     }
     
     @RequestMapping("/update")
     public void update(
    		@RequestParam(value="change",required=false)String change,
 			CalculationObject calculationObject,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, calculationObjectService.modifyCalculationObject(change,calculationObject));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, calculationObjectService.deleteCalculationObject(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, calculationObjectService.getMaxID());
     }
     
     @RequestMapping("/getParentObject")
     public void getParentObject(
 			@RequestParam(value="isSet",required=false)String isSet,
 			@RequestParam(value="nodeType",required=false)String nodeType,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, calculationObjectService.getParentObject(isSet, nodeType));
     }
}
