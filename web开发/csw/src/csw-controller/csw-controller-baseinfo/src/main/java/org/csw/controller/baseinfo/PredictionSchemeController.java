package org.csw.controller.baseinfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.csw.entity.baseinfo.PredictionScheme;
import org.csw.service.baseinfo.PredictionSchemeService;
import org.csw.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/predictionscheme")
public class PredictionSchemeController {
     @Resource
     private PredictionSchemeService predictionSchemeService;
     public void setPredictionSchemeService(PredictionSchemeService predictionSchemeService){
    	 this.predictionSchemeService = predictionSchemeService;
     }
     
     @RequestMapping("/load")
     public void load(
    		@RequestParam(value="id",required=false)String id,
 			@RequestParam(value="name",required=false)String name,
 			@RequestParam(value="funCalculationId",required=false)String funCalculationId,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, predictionSchemeService.getPredictionScheme(id, name,funCalculationId));
     }
     
     @RequestMapping("/add")
     public void add(
 			PredictionScheme predictionScheme,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, predictionSchemeService.addPredictionScheme(predictionScheme));
     }
     
     @RequestMapping("/update")
     public void update(
 			PredictionScheme predictionScheme,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, predictionSchemeService.modifyPredictionScheme(predictionScheme));
     }
     
     @RequestMapping("/delete")
     public void delete(
    		@RequestParam(value="ids",required=false)String ids,
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, predictionSchemeService.deletePredictionScheme(ids));
     }
     
     @RequestMapping("/getmaxid")
     public void getmaxid(
 			HttpServletResponse response)throws Exception{
    	    ResponseUtil.write(response, predictionSchemeService.getMaxID());
     }
}
