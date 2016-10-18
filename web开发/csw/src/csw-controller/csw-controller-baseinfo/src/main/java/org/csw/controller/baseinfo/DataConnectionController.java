package org.csw.controller.baseinfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.csw.entity.baseinfo.DataConnection;
import org.csw.service.baseinfo.DataConnectionService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/dataconnection")
public class DataConnectionController {
     @Resource
     private DataConnectionService dataConnectionService;
     public void setDataConnectionService(DataConnectionService dataConnectionService){
    	 this.dataConnectionService = dataConnectionService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="id",required=false)String id,
 			@RequestParam(value="name",required=false)String name,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, dataConnectionService.getDataConnection(id, name));
     }
     
     @RequestMapping("/add")
     public void add(
 			DataConnection dataConnection,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, dataConnectionService.addDataConnection(dataConnection));
     }
     
     @RequestMapping("/update")
     public void update(
 			DataConnection dataConnection,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, dataConnectionService.modifyDataConnection(dataConnection));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, dataConnectionService.deleteDataConnection(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, dataConnectionService.getMaxID());
     }
}
