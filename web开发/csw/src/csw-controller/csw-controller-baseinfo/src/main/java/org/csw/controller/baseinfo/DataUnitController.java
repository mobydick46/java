package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.entity.baseinfo.DataUnit;
import org.csw.service.baseinfo.DataUnitService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/dataunit")
public class DataUnitController {
     @Resource
     private DataUnitService dataUnitService;
     public void setDataUnitService(DataUnitService dataUnitService){
    	 this.dataUnitService = dataUnitService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="id",required=false)String id,
 			@RequestParam(value="name",required=false)String name,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, dataUnitService.getDataUnit(id, name));
     }
     
     @RequestMapping("/add")
     public void add(
 			DataUnit dataUnit,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, dataUnitService.addDataUnit(dataUnit));
     }
     
     @RequestMapping("/update")
     public void update(
 			DataUnit dataUnit,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, dataUnitService.modifyDataUnit(dataUnit));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, dataUnitService.deleteDataUnit(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, dataUnitService.getMaxID());
     }
}
