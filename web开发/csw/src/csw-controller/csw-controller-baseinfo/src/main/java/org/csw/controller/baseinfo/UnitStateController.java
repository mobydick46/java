package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.entity.baseinfo.UnitState;
import org.csw.service.baseinfo.UnitStateService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/unitstate")
public class UnitStateController {
     @Resource
     private UnitStateService unitStateService;
     public void setUnitStateService(UnitStateService unitStateService){
    	 this.unitStateService = unitStateService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="id",required=false)String id,
 			@RequestParam(value="name",required=false)String name,
 			@RequestParam(value="flag",required=true)Integer flag,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, unitStateService.getUnitState(id, name,flag));
     }
     
     @RequestMapping("/add")
     public void add(
 			UnitState unitState,
 			@RequestParam(value="flag",required=false)Integer flag,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, unitStateService.addUnitState(unitState,flag));
     }
     
     @RequestMapping("/update")
     public void update(
 			UnitState unitState,
 			@RequestParam(value="flag",required=false)Integer flag,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, unitStateService.modifyUnitState(unitState,flag));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
    		@RequestParam(value="flag",required=false)Integer flag,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, unitStateService.deleteUnitState(ids,flag));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
    		@RequestParam(value="flag",required=false)Integer flag,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, unitStateService.getMaxID(flag));
     }
}
