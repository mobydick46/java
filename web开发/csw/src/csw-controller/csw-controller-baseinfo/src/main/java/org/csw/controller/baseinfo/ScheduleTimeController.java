package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.entity.baseinfo.ScheduleTime;
import org.csw.service.baseinfo.ScheduleTimeService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/scheduletime")
public class ScheduleTimeController {
     @Resource
     private ScheduleTimeService scheduleTimeService;
     public void setScheduleTimeService(ScheduleTimeService scheduleTimeService){
    	 this.scheduleTimeService = scheduleTimeService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="id",required=false)String id,
 			@RequestParam(value="name",required=false)String name,
 			@RequestParam(value="functionId",required=false)String functionId,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, scheduleTimeService.getScheduleTime(id, name,functionId));
     }
     
     @RequestMapping("/add")
     public void add(
 			ScheduleTime scheduleTime,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, scheduleTimeService.addScheduleTime(scheduleTime));
     }
     
     @RequestMapping("/update")
     public void update(
 			ScheduleTime scheduleTime,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, scheduleTimeService.modifyScheduleTime(scheduleTime));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, scheduleTimeService.deleteScheduleTime(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, scheduleTimeService.getMaxID());
     }
}
