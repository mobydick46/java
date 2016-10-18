package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.entity.baseinfo.ModelMethod;
import org.csw.service.baseinfo.ModelMethodService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/modelmethod")
public class ModelMethodController {
     @Resource
     private ModelMethodService modelMethodService;
     public void setModelMethodService(ModelMethodService modelMethodService){
    	 this.modelMethodService = modelMethodService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="id",required=false)String id,
 			@RequestParam(value="name",required=false)String name,
 			@RequestParam(value="combCalculation",required=false)String combCalculation,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, modelMethodService.getModelMethod(id, name,combCalculation));
     }
     
     @RequestMapping("/add")
     public void add(
 			ModelMethod modelMethod,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, modelMethodService.addModelMethod(modelMethod));
     }
     
     @RequestMapping("/update")
     public void update(
 			ModelMethod modelMethod,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, modelMethodService.modifyModelMethod(modelMethod));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, modelMethodService.deleteModelMethod(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, modelMethodService.getMaxID());
     }
     
     @RequestMapping("/addInputOutput")
     public void addInputOutput(
 			@RequestParam(value="combCalculation",required=false)String combCalculation,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, modelMethodService.addInputOutput(combCalculation));
     }
}
