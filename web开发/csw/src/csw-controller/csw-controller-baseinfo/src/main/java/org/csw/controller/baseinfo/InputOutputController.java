package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.entity.baseinfo.InputOutput;
import org.csw.service.baseinfo.InputOutputService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/inputoutput")
public class InputOutputController {
     @Resource
     private InputOutputService inputOutputService;
     public void setInputOutputService(InputOutputService inputOutputService){
    	 this.inputOutputService = inputOutputService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="id",required=false)String id,
 			@RequestParam(value="name",required=false)String name,
 			@RequestParam(value="type",required=false)String type,
 			@RequestParam(value="modelCode",required=false)String modelCode,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, inputOutputService.getInputOutput(id, name,type,modelCode));
     }
     
     @RequestMapping("/add")
     public void add(
 			InputOutput inputOutput,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, inputOutputService.addInputOutput(inputOutput));
     }
     
     @RequestMapping("/update")
     public void update(
 			InputOutput inputOutput,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, inputOutputService.modifyInputOutput(inputOutput));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, inputOutputService.deleteInputOutput(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, inputOutputService.getMaxID());
     }
     
     @RequestMapping("/copy")
     public void copy(
    		@RequestParam(value="codes",required=false)String codes,
    		@RequestParam(value="code",required=false)String code,
    		@RequestParam(value="type",required=false)String type,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, inputOutputService.copyInputOutput(codes,code,type));
     }
}
