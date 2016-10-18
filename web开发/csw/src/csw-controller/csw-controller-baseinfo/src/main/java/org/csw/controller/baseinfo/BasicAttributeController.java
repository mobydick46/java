package org.csw.controller.baseinfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.csw.entity.baseinfo.BasicAttribute;
import org.csw.service.baseinfo.BasicAttributeService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/basicattribute")
public class BasicAttributeController {
     @Resource
     private BasicAttributeService basicAttributeService;
     public void setBasicAttributeService(BasicAttributeService basicAttributeService){
    	 this.basicAttributeService = basicAttributeService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="id",required=false)String id,
 			@RequestParam(value="name",required=false)String name,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, basicAttributeService.getBasicAttribute(id, name));
     }
     
     @RequestMapping("/add")
     public void add(
 			BasicAttribute basicAttribute,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, basicAttributeService.addBasicAttribute(basicAttribute));
     }
     
     @RequestMapping("/update")
     public void update(
 			BasicAttribute basicAttribute,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, basicAttributeService.modifyBasicAttribute(basicAttribute));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, basicAttributeService.deleteBasicAttribute(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, basicAttributeService.getMaxID());
     }
     
     @RequestMapping("/download")
     public void download(
    		@RequestParam(value="path",required=true)String path,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, basicAttributeService.download(path));
     }
}
