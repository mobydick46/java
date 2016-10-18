<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>长江水利局可视化配置工具</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/demo/demo.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/plugins/jquery.textbox.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$.extend($.fn.datagrid.methods, {
		editCell: function(jq,param){
			return jq.each(function(){
				var opts = $(this).datagrid('options');
				var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor1 = col.editor;
					if (fields[i] != param.field){
						col.editor = null;
					}
				}
				$(this).datagrid('beginEdit', param.index);
				for(var i=0; i<fields.length; i++){
					var col = $(this).datagrid('getColumnOption', fields[i]);
					col.editor = col.editor1;
				}
			});
		}
	});

	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#dg').datagrid('validateRow', editIndex)){
			var field = new Array("datatype","controlformat","stateDefinition","timeUnit","typeUnit");
			var fieldName = new Array("datatypeName","controlformatName","stateDefinitionName","timeUnitName","typeUnitName");
			for(var i=0;i<field.length;i++){
				var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:field[i]});
				if(null != ed){
					$('#dg').datagrid('getRows')[editIndex][fieldName[i]] = $(ed.target).combobox('getText');
				}
			}
			$('#dg').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell(index, field){
		if (endEditing()){
			$('#dg').datagrid('selectRow', index)
					.datagrid('editCell', {index:index,field:field});
			editIndex = index;
		}
	}
	var rowIndex1 = undefined;
	var rowData1 = undefined;
	var rowIndex2 = undefined;
	var rowData2 = undefined;
	/*flag标志：
	*1：工程项目
	*2:计算对象
	*/
	var flag = undefined;
	
	/*添加工程项目-基本属性*/
	function addBasicAttribute(){
		var name = rowData1.name;
		if(null==name) name = "";
		var content = rowData1.content;
		if(null==content) content = "";
		var remark = rowData1.remark;
		if(null==remark) remark = "";
		
		$.post("${pageContext.request.contextPath}/basicattribute/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : {
							id:id,
							name:name,
							content:content,
							remark:remark,
						}
					    });
					$.post("${pageContext.request.contextPath}/basicattribute/add.do",
							{id:id,name:name,content:content,remark:remark},
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添加工程项目-数据连接*/
	function addDataConnection(){
		var name = rowData1.name;
		if(null==name) name = "";
		var content = rowData1.content;
		if(null==content) content = "";
		var remark = rowData1.remark;
		if(null==remark) remark = "";
		$.post("${pageContext.request.contextPath}/dataconnection/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : {
							id:id,
							name:name,
							content:content,
							remark:remark,
						}
					    });
					$.post("${pageContext.request.contextPath}/dataconnection/add.do",
							{id:id,name:name,content:content,remark:remark},
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添加工程项目-数据单位*/
	function addDataUnit(){
		var datatype = rowData1.datatype;
		if(null==datatype) datatype = "";
		var standardunit = rowData1.standardunit;
		if(null==standardunit) standardunit = "";
		var displayunit = rowData1.displayunit;
		if(null==displayunit) displayunit = "";
		var times = rowData1.times;
		if(null==times) times = "";
		var controlformat = rowData1.controlformat;
		if(null==controlformat) controlformat = "";
		var figure = rowData1.figure;
		if(null==figure) figure = "";
		$.post("${pageContext.request.contextPath}/dataunit/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					var row = {
							id:id,
							datatype:datatype,
							standardunit:standardunit,
							displayunit:displayunit,
							times:times,
							controlformat:controlformat,
							figure:figure,
						};
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : row,
					    });
					$.post("${pageContext.request.contextPath}/dataunit/add.do",row,
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添加工程项目-机组/闸门状态*/
	function addUnitState(stateType){
		var stateDefinition = rowData1.stateDefinition;
		if(null==stateDefinition) stateDefinition = "";
		var stateID = rowData1.stateID;
		if(null==stateID) stateID = "";
		var stateName = rowData1.stateName;
		if(null==stateName) stateName = "";
		$.post("${pageContext.request.contextPath}/unitstate/getmaxid.do",{flag:stateType},
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					var row = {
							id:id,
							stateDefinition:stateDefinition,
							stateID:stateID,
							stateName:stateName,
							flag:stateType
						};
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : row,
					    });
					$.post("${pageContext.request.contextPath}/unitstate/add.do",row,
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添加计算对象*/
	function addCalculationObject(nodeType){
		var objectCode = rowData1.objectCode;
		if(null==objectCode) objectCode = "";
		var objectName = rowData1.objectName;
		if(null==objectName) objectName = "";
		var isSet = rowData1.isSet;
		if(null==isSet) isSet = "";
		var parentCode  = "";
		var parentName = "";
		if(11 == nodeType || 12 == nodeType){
			if(0!=parentObject.length && ""!=parentObject[0].parentCode){
				parentCode = parentObject[nodeTypeid].parentCode;
				parentName = parentObject[nodeTypeid].parentName;
			}else{
				$.messager.alert("系统提示", "水库/电站列表设置项为空，无法添加机组或闸门对象！");
			}
			
		}
		$.post("${pageContext.request.contextPath}/calculationobject/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					var row = {
							id:id,
							objectCode:objectCode,
							objectName:objectName,
							isSet:isSet,
							nodeType:nodeType,
							parentCode:parentCode,
							parentName:parentName
						};
					if((0!=parentObject.length && ""!=parentObject[0].parentCode)||(11!=nodeType && 12!=nodeType)){
						$('#dg').datagrid('insertRow',{
							//index: 1,	// index start with 0
							row : row,
						    });
					}
					$.post("${pageContext.request.contextPath}/calculationobject/add.do",row,
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添加模型方法*/
	function addModelMethod(){
		var code = rowData1.code;
		if(null==code) code = "";
		var name = rowData1.name;
		if(null==name) name = "";
		var description = rowData1.description;
		if(null==description) description = "";
		
		var type = rowData1.type;
		if(null==type) type = "";
		var combCalculation = rowData1.combCalculation;
		if(null==combCalculation) combCalculation = "";
		var sourceClass = rowData1.sourceClass;
		if(null==sourceClass) sourceClass = "";
		$.post("${pageContext.request.contextPath}/modelmethod/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					var row = {
							id:id,
							code:code,
							name:name,
							description:description,
							type:type,
							combCalculation:combCalculation,
							sourceClass:sourceClass,
						};
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : row,
					    });
					$.post("${pageContext.request.contextPath}/modelmethod/add.do",row,
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添输入输出*/
	function addInputOutput(nodeID){
		var projectName = rowData1.projectName;
		if(null==projectName) projectName = "";
		var isSet = rowData1.isSet;
		if(null==isSet) isSet = "";
		var description = rowData1.description;
		if(null==description) description = "";
		var type = nodeID % 5;
    	var modelCode = $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).attributes.code;
		$.post("${pageContext.request.contextPath}/inputoutput/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					var row = {
							id:id,
							projectName:projectName,
							isSet:isSet,
							description:description,
							type:type,
							modelCode:modelCode,
						};
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : row,
					    });
					$.post("${pageContext.request.contextPath}/inputoutput/add.do",row,
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添功能类型*/
	function addFunctionType(){
		var projectName = rowData1.projectName;
		if(null==projectName) projectName = "";
		var isSet = rowData1.isSet;
		if(null==isSet) isSet = "";
		var remark = rowData1.remark;
		if(null==remark) remark = "";
		var type = rowData1.type;
		if(null==type) type = "";
		$.post("${pageContext.request.contextPath}/functiontype/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					var row = {
							id:id,
							projectName:projectName,
							isSet:isSet,
							type:type,
							remark:remark,
						};
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : row,
					    });
					$.post("${pageContext.request.contextPath}/functiontype/add.do",row,
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添功能类型-调度期时间*/
	function addScheduleTime(nodeID){
		var timeNumber = rowData1.timeNumber;
		if(null==timeNumber) timeNumber = "";
		var timeUnit = rowData1.timeUnit;
		if(null==timeUnit) timeUnit = "";
		var defaultNumber = rowData1.defaultNumber;
		if(null==defaultNumber) defaultNumber = "";
		var isDefault = rowData1.isDefault;
		if(null==isDefault) isDefault = "";
		var functionId = $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).attributes.functionId;
		$.post("${pageContext.request.contextPath}/scheduletime/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					var row = {
							id:id,
							timeNumber:timeNumber,
							timeUnit:timeUnit,
							defaultNumber:defaultNumber,
							isDefault:isDefault,
							functionId:functionId,
						};
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : row,
					    });
					$.post("${pageContext.request.contextPath}/scheduletime/add.do",row,
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*功能类型 - 计算对象各分页标签（即类型）的ID,默认为第一个*/
	var nodeTypeid = 0;
	/*添功能类型-计算对象*/
	function addFunctionCalculation(nodeID){
		var objectCode = rowData1.objectCode;
		if(null==objectCode) objectCode = "";
		var objectName = rowData1.objectName;
		if(null==objectName) objectName = "";
		var isCalculation = rowData1.isCalculation;
		if(null==isCalculation) isCalculation = "";
		var nodeType = barIndex[nodeTypeid];
		var functionId = $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).attributes.functionId;
		$.post("${pageContext.request.contextPath}/functioncalculation/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					var row = {
							id:id,
							objectCode:objectCode,
							objectName:objectName,
							isCalculation:isCalculation,
							nodeType:nodeType,
							functionId:functionId,
						};
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : row,
					    });
					$.post("${pageContext.request.contextPath}/functioncalculation/add.do",row,
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添功能类型-预报方案*/
	function addPredictionScheme(nodeID){
		var predictionCode = rowData1.predictionCode;
		if(null==predictionCode) predictionCode = "";
		var typeNumber = rowData1.typeNumber;
		if(null==typeNumber) typeNumber = "";
		var typeUnit = rowData1.typeUnit;
		if(null==typeUnit) typeUnit = "";
		var isDefault = rowData1.isDefault;
		if(null==isDefault) isDefault = "";
		var funCalculationId = barArray2[nodeTypeid].funCalculationId;
		//var functionId = $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).attributes.functionId;
		$.post("${pageContext.request.contextPath}/predictionscheme/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					var row = {
							id:id,
							predictionCode:predictionCode,
							typeNumber:typeNumber,
							typeUnit:typeUnit,
							isDefault:isDefault,
							funCalculationId:funCalculationId,
						};
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : row,
					    });
					$.post("${pageContext.request.contextPath}/predictionscheme/add.do",row,
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添功能类型-模型方法*/
	function addFunctionModel(nodeID){
		var modelCode = rowData1.modelCode;
		if(null==modelCode) modelCode = "";
		var modelName = rowData1.modelName;
		if(null==modelName) modelName = "";
		var description = rowData1.description;
		if(null==description) description = "";
		var isApply = rowData1.isApply;
		if(null==isApply) isApply = "";
		var funCalculationId = "";
		var type = "1";
		if(0==nodes.id%8){
			funCalculationId = barArray2[nodeTypeid].funCalculationId;
			type = "2";
		}
		var functionId = $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).attributes.functionId;
		$.post("${pageContext.request.contextPath}/functionmodel/getmaxid.do",
			function(result) {
				if (result.success) {
					var maxid = result.id;
					if(null == maxid||""==maxid){
						maxid = "0";
					}
					var id = parseInt(maxid)+1;
					var row = {
							id:id,
							modelCode:modelCode,
							modelName:modelName,
							description:description,
							modelType:"F",
							isApply:isApply,
							funCalculationId:funCalculationId,
							functionId:functionId,
							type:type
						};
					$('#dg').datagrid('insertRow',{
						//index: 1,	// index start with 0
						row : row,
					    });
					$.post("${pageContext.request.contextPath}/functionmodel/add.do",row,
							function(result) {
								if (null != result) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, "json");
				}else {
					$.messager.alert(result.errormsg, result.errorinfo);
				}
			}, "json");
	}
	/*添功能类型-决策计划*/
	function addDecisionPlan(){
		var rowSources = $('#dg').datagrid('getRows');
		var rowSource = rowSources[rowSources.length -1];
		if(null != rowSource && "" == rowSource.planName){
			$.messager.alert("系统提示", "请首先完善上一条插入的信息，并确保添加的项目为模型方法-结果输出的选项！");
			return;
		}
		$.post("${pageContext.request.contextPath}/decisionplan/getmaxnumber.do",
				function(result) {
					if (result.success) {
						var maxnumber = result.number;
						if(null == maxnumber||""==maxnumber){
							maxnumber = "0";
						}
						var number = parseInt(maxnumber)+1;
						var row = {number:number,planName:""};
						for(var i=0;i<calculations.length;i++){
							row[calculations[i].objectCode] = "";
						}
						$('#dg').datagrid('insertRow',{
							//index: 1,	// index start with 0
							row : row,
						    });
						$.post("${pageContext.request.contextPath}/decisionplan/add.do",{number:number,numberSource:rowSource.number},
								function(result) {
									if (null != result) {
									} else {
										$.messager.alert(result.errormsg, result.errorinfo);
									}
								}, "json");
					}else {
						$.messager.alert(result.errormsg, result.errorinfo);
					}
				}, "json");
	}
	/*总的添加信息调度*/
	function addData(){
		var nodes = $('#tt').tree('getSelected');
		switch(flag){
		    case 1:switch(nodes.id){
		          case 1:addBasicAttribute();break;
		          case 2:addDataConnection();break;
		          case 3:addDataUnit();break;
		          case 4:addUnitState(0);break;
		          case 5:addUnitState(1);break;
		           };break;
		    case 2:addCalculationObject(nodes.id);break;
		    case 3: if(1 == nodes.id){addModelMethod();}
				    else {
				    	addInputOutput(nodes.id);
				    }break;
		    case 4: if(1 == nodes.id){addFunctionType();}
				    else if(3==nodes.id%8){
				    	addScheduleTime(nodes.id);
				    }else if(4==nodes.id%8){
				    	addFunctionCalculation(nodes.id);
				    }else if(5==nodes.id%8){
				    	addPredictionScheme(nodes.id);
				    }else if(7==nodes.id%8 || 0==nodes.id%8){
				    	addFunctionModel(nodes.id);
				    }else if(1==nodes.id%8){
				    	addDecisionPlan();
				    }
		            break;
		}
	}
	/*删除工程项目-基本属性*/
	function deleteData(){
		var url = undefined;
		var nodes = $('#tt').tree('getSelected');
		var strIds = [];
		if(4 == flag && 1 != nodes.id && 1 == nodes.id%8){
			strIds.push(rowData2.number);
		}else{
			strIds.push(rowData2.id);
		}
		var ids = strIds.join(",");
		/*为批量删除模型方法做准备*/
		var stringIds = [];
		var rowsData = $('#dg').datagrid('getChecked');
		for(var i=0;i<rowsData.length;i++){
			stringIds.push(rowsData[i].id);
			$('#dg').datagrid('deleteRow',$('#dg').datagrid('getRowIndex',rowsData[i]));
		}
		if(3!=flag||1!=nodes.id){
			$('#dg').datagrid('deleteRow',rowIndex2);
		}else if(0==rowsData.length){
			$('#dg').datagrid('deleteRow',rowIndex2);
		}
		/*删除功能类型 - 决策计划时需要记录当前时间调度ID*/
		var nowID = nodeTypeid;
		switch(flag){
		    case 1:switch(nodes.id){
		          case 1:url="${pageContext.request.contextPath}/basicattribute/delete.do";break;
		          case 2:url="${pageContext.request.contextPath}/dataconnection/delete.do";break;
		          case 3:url="${pageContext.request.contextPath}/dataunit/delete.do";break;
		          case 4:url="${pageContext.request.contextPath}/unitstate/delete.do?flag=0";break;
		          case 5:url="${pageContext.request.contextPath}/unitstate/delete.do?flag=1";break;
		           };break;
		    case 2:url="${pageContext.request.contextPath}/calculationobject/delete.do";break;
		    case 3:if(1==nodes.id){
		    	       url="${pageContext.request.contextPath}/modelmethod/delete.do";
                       if(0!=rowsData.length){ids = stringIds.join(",");}
                    }else{
                    	url="${pageContext.request.contextPath}/inputoutput/delete.do";
                    }break;
		    case 4: if(1==nodes.id){url="${pageContext.request.contextPath}/functiontype/delete.do";}
				    else if(3 == nodes.id%8){
				    	url="${pageContext.request.contextPath}/scheduletime/delete.do";
				    }else if(4 == nodes.id%8){
				    	url="${pageContext.request.contextPath}/functioncalculation/delete.do";
				    }else if(5 == nodes.id%8){
				    	url="${pageContext.request.contextPath}/predictionscheme/delete.do";
				    }else if(7 == nodes.id%8 || 0 == nodes.id%8){
				    	url="${pageContext.request.contextPath}/functionmodel/delete.do";
				    }else if(1 == nodes.id%8){
				    	url="${pageContext.request.contextPath}/decisionplan/delete.do";
				    }break;
		}
		$.post(url,{ids:ids},
				function(result) {
					if (result.success) {
						if(1 == nodes.id%8){
							var rows = $('#dg').datagrid('getRows');
							if(0 == rows.length){
								var functionId = $('#tt').tree('getParent', $('#tt').tree('find', nodes.id).target).attributes.functionId;
								var url="${pageContext.request.contextPath}/decisionplan/getInitialization.do";
								$.post(url,{functionId:functionId},function(result) {
											if (result.success) {
												calculations = result.calculations;
												times = result.times;
												list(node.id);
											} else {
												$.messager.alert(result.errormsg, result.errorinfo);
											}
										}, 
								"json");
								$('#'+(nowID+1)).linkbutton('select');
								$('#dg').datagrid('load',{timeID:times[nowID].timeID});
				                nodeTypeid = nowID;
							}
						}
					} else {
						$.messager.alert(result.errormsg, result.errorinfo);
					}
				}, "json");
	}
	/*更新数据*/
	function updateData(rowData,changes){
		var url = undefined;
		var map = undefined;
		var nodes = $('#tt').tree('getSelected');
		
		switch(flag){
		    case 1:switch(nodes.id){
		          case 1:url="${pageContext.request.contextPath}/basicattribute/update.do";
		                 map={id:rowData.id,name:changes.name,content:changes.content,remark:changes.remark};break;
		          case 2:url="${pageContext.request.contextPath}/dataconnection/update.do";
	                     map={id:rowData.id,name:changes.name,content:changes.content,remark:changes.remark};break;
		          case 3:url="${pageContext.request.contextPath}/dataunit/update.do";
                         map={id:rowData.id,datatype:changes.datatype,standardunit:changes.standardunit,displayunit:changes.displayunit,
                        	  times:changes.times,controlformat:changes.controlformat,figure:changes.figure};break;
		          case 4:url="${pageContext.request.contextPath}/unitstate/update.do";
                         map={id:rowData.id,stateDefinition:changes.stateDefinition,stateID:changes.stateID,
    						  stateName:changes.stateName,flag:0};break;
		          case 5:url="${pageContext.request.contextPath}/unitstate/update.do";
                         map={id:rowData.id,stateDefinition:changes.stateDefinition,stateID:changes.stateID,
						  stateName:changes.stateName,flag:1};break;
		           };break;
		    case 2:url="${pageContext.request.contextPath}/calculationobject/update.do";
		           var change = "";
				   for(var key in changes){
			    		change = key;
			       }
                   map={change:change,id:rowData.id,objectCode:changes.objectCode,objectName:changes.objectName,isSet:changes.isSet};break;
		    case 3:if(1 == nodes.id){
				    	url="${pageContext.request.contextPath}/modelmethod/update.do";
		                map={id:rowData.id,code:changes.code,name:changes.name,description:changes.description,
		             		   type:changes.type,combCalculation:changes.combCalculation,sourceClass:changes.sourceClass};
				    }else{
				    	url="${pageContext.request.contextPath}/inputoutput/update.do";
		                map={id:rowData.id,projectName:changes.projectName,isSet:changes.isSet,description:changes.description};
				    }break;
		    case 4:if(1 == nodes.id){
				    	url="${pageContext.request.contextPath}/functiontype/update.do";
				    	map={id:rowData.id,projectName:changes.projectName,isSet:changes.isSet,type:changes.type,remark:changes.remark};
				    }else if(3 == nodes.id%8){
				    	url="${pageContext.request.contextPath}/scheduletime/update.do";
				    	map={id:rowData.id,timeNumber:changes.timeNumber,timeUnit:changes.timeUnit,defaultNumber:changes.defaultNumber,isDefault:changes.isDefault};
				    }else if(4 == nodes.id%8){
				    	url="${pageContext.request.contextPath}/functioncalculation/update.do";
				    	map={id:rowData.id,objectCode:changes.objectCode,objectName:changes.objectName,isCalculation:changes.isCalculation};
				    }else if(5 == nodes.id%8){
				    	url="${pageContext.request.contextPath}/predictionscheme/update.do";
				    	map={id:rowData.id,predictionCode:changes.predictionCode,typeNumber:changes.typeNumber,typeUnit:changes.typeUnit,isDefault:changes.isDefault};
				    }else if(7 == nodes.id%8 || 0 == nodes.id%8){
				    	url="${pageContext.request.contextPath}/functionmodel/update.do";
				    	map={id:rowData.id,modelCode:changes.modelCode,modelName:changes.modelName,description:changes.description,modelType:changes.modelType,isApply:changes.isApply};
				    }else if(1 == nodes.id%8){
				    	url="${pageContext.request.contextPath}/decisionplan/update.do";
				    	var functionId = $('#tt').tree('getParent', nodes.target).attributes.functionId;
				    	
				    	for(var key in changes){
				    		map = {key:key,value:changes[key]};
				    	}
				    	map.functionId = functionId;
				    	map.timeID = times[nodeTypeid].timeID;
				    	map.number = rowData.number;
				    }break;
		}
		$.post(url,map,
				function(result) {
					if (null != result) {
						if(4 == flag && 1 == nodes.id%8 && false == result.success){
							$.messager.alert("系统提示", result.reason);
							$('#dg').datagrid({});
						}else if(2 == flag&& false == result.success){
							$.messager.alert("系统提示", result.reason);
							$('#dg').datagrid({});
						}
					} else {
						$.messager.alert(result.errormsg, result.errorinfo);
					}
				}, "json");
	}
	/*批量更新计算对象信息*/
	function updateCalculationObject(type){
		var rowsData = $('#dg').datagrid('getChecked');
		if(0==rowsData.length){
			rowsData = $('#dg').datagrid('getRows');
		}
		var isUpdate = undefined;
		var isSet = undefined;
		for(var i=0;i<rowsData.length;i++){
			isUpdate = true;
			switch(type){
				case 1:if("√"==rowsData[i].isSet){isUpdate=false;}isSet="√";break;
				case 2:
					if("√"==rowsData[i].isSet){
						isSet="";
					}else{
						isSet="√";
					}break;
				case 3:if(""==rowsData[i].isSet){isUpdate=false;}isSet="";break;
			    default : $.messager.alert("系统提示", "操作类型错误"+type); break;
		    }
			var url="${pageContext.request.contextPath}/calculationobject/update.do";
	        var map={id:rowsData[i].id,isSet:isSet};
	        if(isUpdate){
	        	var index = $('#dg').datagrid('getRowIndex',rowsData[i]);
	        	rowsData[i].isSet = isSet;
	        	var row = rowsData[i];
	        	$('#dg').datagrid('updateRow',{
		        		index: index,
		        		row:row,
	        	});
	        	/*
	        	$.post(url,map,
						function(result) {
							if (result.success) {
								alert(i);
							} else {
								$.messager.alert(result.errormsg, result.errorinfo);
							}
						}, "json");*/
	        	$.ajax({
	                url : url,
	                data: map,
	                cache : false, 
	                async : false,
	                type : "POST",
	                dataType : 'json',
	                success : function (result){
	                	if (result.success) {
	                		//alert(i);
						} else {
							$.messager.alert(result.errormsg, result.errorinfo);
						}
	                }
	            });
	        }
		}
	}
	/*批量更新功能类型 - 计算对象信息*/
	function updateFunctionCalculation(type){
		var rowsData = $('#dg').datagrid('getChecked');
		if(0==rowsData.length){
			rowsData = $('#dg').datagrid('getRows');
		}
		var isUpdate = undefined;
		var isCalculation = undefined;
		for(var i=0;i<rowsData.length;i++){
			isUpdate = true;
			switch(type){
				case 1:if("√"==rowsData[i].isCalculation){isUpdate=false;}isCalculation="√";break;
				case 2:
					if("√"==rowsData[i].isCalculation){
						isCalculation="";
					}else{
						isCalculation="√";
					}break;
				case 3:if(""==rowsData[i].isCalculation){isUpdate=false;}isCalculation="";break;
			    default : $.messager.alert("系统提示", "操作类型错误"+type); break;
		    }
			var url="${pageContext.request.contextPath}/functioncalculation/update.do";
	        var map={id:rowsData[i].id,isCalculation:isCalculation};
	        if(isUpdate){
	        	var index = $('#dg').datagrid('getRowIndex',rowsData[i]);
	        	rowsData[i].isCalculation = isCalculation;
	        	var row = rowsData[i];
	        	$('#dg').datagrid('updateRow',{
		        		index: index,
		        		row:row,
	        	});
	        	/*
	        	$.post(url,map,
						function(result) {
							if (result.success) {
								alert(i);
							} else {
								$.messager.alert(result.errormsg, result.errorinfo);
							}
						}, "json");*/
	        	$.ajax({
	                url : url,
	                data: map,
	                cache : false, 
	                async : false,
	                type : "POST",
	                dataType : 'json',
	                success : function (result){
	                	if (result.success) {
	                		//alert(i);
						} else {
							$.messager.alert(result.errormsg, result.errorinfo);
						}
	                }
	            });
	        }
		}
	}
	/*批量更新功能类型 - 模型方法信息*/
	function updateFunctionModel(type){
		var rowsData = $('#dg').datagrid('getChecked');
		if(0==rowsData.length){
			rowsData = $('#dg').datagrid('getRows');
		}
		var isUpdate = undefined;
		var isApply = undefined;
		for(var i=0;i<rowsData.length;i++){
			isUpdate = true;
			switch(type){
				case 1:if("√"==rowsData[i].isApply){isUpdate=false;}isApply="√";break;
				case 2:
					if("√"==rowsData[i].isApply){
						isApply="";
					}else{
						isApply="√";
					}break;
				case 3:if(""==rowsData[i].isApply){isUpdate=false;}isApply="";break;
			    default : $.messager.alert("系统提示", "操作类型错误"+type); break;
		    }
			var url="${pageContext.request.contextPath}/functionmodel/update.do";
	        var map={id:rowsData[i].id,isApply:isApply};
	        if(isUpdate){
	        	var index = $('#dg').datagrid('getRowIndex',rowsData[i]);
	        	rowsData[i].isApply = isApply;
	        	var row = rowsData[i];
	        	$('#dg').datagrid('updateRow',{
		        		index: index,
		        		row:row,
	        	});
	        	/*
	        	$.post(url,map,
						function(result) {
							if (result.success) {
							} else {
								$.messager.alert(result.errormsg, result.errorinfo);
							}
						}, "json");*/
	        	$.ajax({
	                url : url,
	                data: map,
	                cache : false, 
	                async : false,
	                type : "POST",
	                dataType : 'json',
	                success : function (result){
	                	if (result.success) {
	                		//alert(i);
						} else {
							$.messager.alert(result.errormsg, result.errorinfo);
						}
	                }
	            });
	        }
		}
	}
	/*功能类型-决策计划 编号变幅*/
	var field2 = undefined;
	function getNextValue(v,times){
		var length = v.length;
		var tmp = parseInt(v) + parseInt(times);
		var new_v = tmp.toString();
		var l = new_v.length;
		if(l < length){
			for(var j=0;j<length - l;j++){
				new_v = "0"+new_v;
			}
		}
		return new_v;
	}
	function numberChange(){
		$("#dlg").dialog("open");
	}
	function Cancel(){
		$("#dlg").dialog("close");
		$("#times").val("");
	}
	function OK(){
		var times = $("#times").val();
		if(!/^\d+$/.test(times)){
			$.messager.alert("系统提示", "请输入一个整数！");
			return;
		}
		var rows = $('#dg').datagrid('getRows');
		if(1 >= rows.length){
			//$.messager.alert("系统提示", "请输入一个整数！");
			return;
		}
		var strIds = [];
		for (var i = 0; i < rows.length; i++) {
			strIds.push(rows[i].number);
		}
		var map = undefined;
		var numbers = strIds.join(",");
		var value = undefined;
		var v = undefined;
		var left = undefined;
		for(var key in rows[0]){
			if(field2 == key){
				//if(rows[0][key][[rows[0][key].length - 1]]>='0' && rows[0][key][[rows[0][key].length - 1]]<='9'){
				//	/^*\d+$/.test(rows[0][key]);
				//}
				//.................................进行中
				value = rows[0][key];
				var i = value.length - 1;
				v = value.substring(i);
				if(!/^\d+$/.test(v)){
					$.messager.alert("系统提示", "所选字段的顶部值尾部不为数字，无法继续操作！");
					return;
				}else{
					while(/^\d+$/.test(v) && i>0){
						v = value.substring(--i);
					}
					v = value.substring(i+1);
				}
				left = value.substring(0,i+1);
				map = {key:key,value:value,numbers:numbers,times:times};
			}
		}
		var old_v = v;
		for(var i= 1;i<rows.length;i++){
			old_v = getNextValue(old_v,times);
			rows[i][field2] = left + old_v;
			$('#dg').datagrid('updateRow',{
        		index: i,
        		row:rows[i],
    	    });
		}
		var url = "${pageContext.request.contextPath}/decisionplan/change.do";
		$.post(url,map,
				function(result) {
					if (result.success) {
					} else {
						$.messager.alert(result.errormsg, result.errorinfo);
					}
				}, 
		"json");
	}
	/*
	*设置各表单中下拉框选项
	*/
	var data1_31 = [{datatype: 1,datatypeName: '入库流量'},{datatype: 2,datatypeName: '出库流量'},{datatype: 3,datatypeName: '发电流量'},
	                {datatype: 4,datatypeName: '泄洪流量'},{datatype: 5,datatypeName: '其他流量'},{datatype: 6,datatypeName: '面积'},
	                {datatype: 7,datatypeName: '水位'},{datatype: 8,datatypeName: '水头'},{datatype: 9,datatypeName: '库容'},
	                {datatype: 10,datatypeName: '水量'},{datatype: 11,datatypeName: '出力'},{datatype: 12,datatypeName: '电量'},
	                {datatype: 13,datatypeName: '电价'},{datatype: 14,datatypeName: '发电收益'},{datatype: 15,datatypeName: '时间'}];
	var data1_32 = new Array("m³/s","m³/s","m³/s","m³/s","m³/s","k㎡","m","m","百万m³","百万m³","MW","MW.h","元","万元","h");
	var data1_33 = [{controlformat:"1",controlformatName:'有效位数'},{controlformat:"2",controlformatName:'小数位数'}];
	var data1_41 = [{stateDefinition:0,stateDefinitionName:'不定态'},{stateDefinition:1,stateDefinitionName:'运行'},
	                {stateDefinition:2,stateDefinitionName:'备用'},{stateDefinition:3,stateDefinitionName:'空转'},
	                {stateDefinition:4,stateDefinitionName:'空载'},{stateDefinition:5,stateDefinitionName:'检修'},
	                {stateDefinition:6,stateDefinitionName:'临时检修'}];
	var data1_51 = [{stateDefinition:0,stateDefinitionName:'检修'},{stateDefinition:1,stateDefinitionName:'开启'},
	                {stateDefinition:2,stateDefinitionName:'备用'},{stateDefinition:3,stateDefinitionName:'临时检修'}];
	var data4_21 = [{timeUnit:0,timeUnitName:'月'},{timeUnit:1,timeUnitName:'旬'},{timeUnit:2,timeUnitName:'日'},
	                {timeUnit:3,timeUnitName:'小时'},{timeUnit:4,timeUnitName:'分钟'}];
	var data4_41 = [{typeUnit:0,typeUnitName:'月'},{typeUnit:1,typeUnitName:'旬'},{typeUnit:2,typeUnitName:'日'},
	                {typeUnit:3,typeUnitName:'小时'},{typeUnit:4,typeUnitName:'分钟'}];
	/*设置表单属性、事件、方法
	 *
	*/
	function list(nodeID){
		var columns = undefined;
		var url = undefined;
		var singleSelect = false;
		var toolbar = "";
		var params = "";
		$("#div-dg").show();
		switch(flag){
		    case 1:
		    	switch(nodeID){
		    	    case 1:
		    	    	columns=[[
		    	  	               {field:'id',title:'',width:80,hidden:'true'},
		    		               {field:'name',title:'名称',width:200,align:'center',
		    		            	   styler: function(value,row,index){
		    		       					return 'background-color:#ffee00;color:red;';
		    		       					// the function can return predefined css class and inline style
		    		       					// return {class:'c1',style:'color:red'}
		    		       				}
		    		       			},
		    		               {field:'content',title:'内容',width:300,align:'center',editor:'text'},
		    		               {field:'remark',title:'备注',width:400,align:'center',editor:'text'},
		    	       ]];url="${pageContext.request.contextPath}/basicattribute/load.do";break;
		    	    case 2:
		    	    	columns=[[
		    	  	               {field:'id',title:'',width:80,hidden:'true'},
		    		               {field:'name',title:'名称',width:200,align:'center',editor:'text',
		    		            	   styler: function(value,row,index){
		    		       					return 'background-color:#ffee00;color:red;';
		    		       					// the function can return predefined css class and inline style
		    		       					// return {class:'c1',style:'color:red'}
		    		       				}
		    		       			},
		    		               {field:'content',title:'内容',width:300,align:'center',editor:'text'},
		    		               {field:'remark',title:'备注',width:400,align:'center',editor:'text'},
		    	       ]];url="${pageContext.request.contextPath}/dataconnection/load.do";break;
		    	    case 3:
		    	       columns=[[
		    	  	               {field:'id',title:'',width:80,hidden:'true'},
		    	  	               {field:'number',title:'序号',width:50,align:'center',
		    	  	            	    styler: function(value,row,index){
		    		       					return 'background-color:#ffee00;color:red;';
		    		       					// the function can return predefined css class and inline style
		    		       					// return {class:'c1',style:'color:red'}
		    		       				},formatter: function(value,row,index){
				    	  	   				return index+1;
				    	  	   		    }
		    		       			},
		    		               {field:'datatype',title:'数据类型',width:200,align:'center',
		    		       				formatter:function(value,row){
		    		       					if(""==value){
		    		       						return "";
		    		       					}
		    		       					return data1_31[value-1].datatypeName;
		    							},
		    		            	    editor:{
											type:'combobox',
											options:{
												panelHeight:'auto',
												valueField:'datatype',
												textField:'datatypeName',
												data : data1_31,
												onSelect:function(record){
													$('#dg').datagrid('selectRow', editIndex).datagrid('editCell', {index:editIndex,field:'standardunit'});
													var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'standardunit'});
													$('#dg').datagrid('getRows')[editIndex]['standardunit'] = data1_32[record.datatype-1];
													$('#dg').datagrid('endEdit', editIndex);
													$('#dg').datagrid('selectRow', editIndex).datagrid('editCell', {index:editIndex,field:'datatype'});
												}
											}
										}
		    		       			},
		    		               {field:'standardunit',title:'标准单位',width:180,align:'center',
		    		       				formatter:function(value,row){
		    		       					if(null==row.datatype || ""==row.datatype){
		    		       						return "";
		    		       					}
		    		       					return data1_32[row.datatype-1];
		    							}},
		    		               {field:'displayunit',title:'显示单位',width:180,align:'center',editor:'text'},
		    		               {field:'times',title:'倍数',width:120,align:'center',editor:'numberbox'},
		    		               {field:'controlformat',title:'控制格式',width:180,align:'center',
		    		            	   formatter:function(value,row){
		    		            		   if(""==value){
		    		       						return "";
		    		       					}
		    		            		   return data1_33[value-1].controlformatName;
		    							},
		    		            	   editor:{
											type:'combobox',
											options:{
												panelHeight:'auto',
												valueField:'controlformat',
												textField:'controlformatName',
												data : data1_33,
											}
										}
		    		       			},
		    		       			{field:'figure',title:'位数',width:120,align:'center',editor:'numberbox'},
		    	       ]];url="${pageContext.request.contextPath}/dataunit/load.do";break;
		    	    case 4:
			    	       columns=[[
			    	  	               {field:'id',title:'',width:80,hidden:'true'},
			    	  	               {field:'number',title:'序号',width:50,align:'center',
			    	  	            	    styler: function(value,row,index){
			    		       					return 'background-color:#ffee00;color:red;';
			    		       					// the function can return predefined css class and inline style
			    		       					// return {class:'c1',style:'color:red'}
			    		       				},formatter: function(value,row,index){
					    	  	   				return index+1;
					    	  	   		    }
			    		       			},
			    		               {field:'stateDefinition',title:'状态定义',width:200,align:'center',
			    		       				formatter:function(value,row){
			    		       					if(""==value){
			    		       						return "";
			    		       					}
			    		       					return data1_41[value].stateDefinitionName;
			    							},
			    		            	   editor:{
												type:'combobox',
												options:{
													panelHeight:'auto',
													valueField:'stateDefinition',
													textField:'stateDefinitionName',
													data : data1_41,
													onSelect:function(record){
														$('#dg').datagrid('selectRow', editIndex).datagrid('editCell', {index:editIndex,field:'stateID'});
														var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'stateID'});
														$('#dg').datagrid('getRows')[editIndex]['stateID'] = data1_41[record.stateDefinition];
														$('#dg').datagrid('endEdit', editIndex);
														$('#dg').datagrid('selectRow', editIndex).datagrid('editCell', {index:editIndex,field:'stateDefinition'});
													}
												}
											}
			    		       			},
			    		               {field:'stateID',title:'状态标识',width:200,align:'center',editor:'numberbox',
			    		       				formatter:function(value,row){
			    		       					if(null==row.stateDefinition || ""==row.stateDefinition){
			    		       						return "";
			    		       					}
			    		       					return row.stateDefinition;
			    							}
			    						},
			    		       		   {field:'stateName',title:'状态显示名',width:200,align:'center',editor:'text'},
			    	       ]];url="${pageContext.request.contextPath}/unitstate/load.do?flag=0";break;
		    	    case 5:
		    	    	columns=[[
		    	  	               {field:'id',title:'',width:80,hidden:'true'},
		    	  	               {field:'number',title:'序号',width:50,align:'center',
		    	  	            	    styler: function(value,row,index){
		    		       					return 'background-color:#ffee00;color:red;';
		    		       					// the function can return predefined css class and inline style
		    		       					// return {class:'c1',style:'color:red'}
		    		       				},formatter: function(value,row,index){
				    	  	   				return index+1;
				    	  	   		    }
		    		       			},
		    		               {field:'stateDefinition',title:'状态定义',width:200,align:'center',
		    		       				formatter:function(value,row){
		    		       					if(""==value){
		    		       						return "";
		    		       					}
		    		       					return data1_51[value].stateDefinitionName;
		    							},
		    		            	   editor:{
											type:'combobox',
											options:{
												panelHeight:'auto',
												valueField:'stateDefinition',
												textField:'stateDefinitionName',
												data : data1_51,
												onSelect:function(record){
													$('#dg').datagrid('selectRow', editIndex).datagrid('editCell', {index:editIndex,field:'stateID'});
													var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'stateID'});
													$('#dg').datagrid('getRows')[editIndex]['stateID'] = data1_51[record.stateDefinition];
													$('#dg').datagrid('endEdit', editIndex);
													$('#dg').datagrid('selectRow', editIndex).datagrid('editCell', {index:editIndex,field:'stateDefinition'});
												}
											}
										}
		    		       			},
		    		               {field:'stateID',title:'状态标识',width:200,align:'center',editor:'numberbox',
		    		       				formatter:function(value,row){
		    		       					if(null==row.stateDefinition || ""==row.stateDefinition){
		    		       						return "";
		    		       					}
		    		       					return row.stateDefinition;
		    							}
		    						},
		    		       		   {field:'stateName',title:'状态显示名',width:200,align:'center',editor:'text'},
		    	       ]];url="${pageContext.request.contextPath}/unitstate/load.do?flag=1";break;
		    	       default : $("#div-dg").hide();break;
			    };break;
		    case 2:if(11 == nodeID || 12 == nodeID){
			    	columns=[[
	   	  	               {field:'id',title:'',width:80,hidden:'true'},
	   	  	               {field:'ck',checkbox:'true'},
	   	  	               {field:'number',title:'序号',width:50,align:'center',
	   	  	            	    styler: function(value,row,index){
	   		       					return 'background-color:#ffee00;color:red;';
	   		       				},formatter: function(value,row,index){
			    	  	   				return index+1;
			    	  	   		    }
	   		       			},
	   		               {field:'objectCode',title:'编码',width:200,align:'center',editor:'text'},
	   		               {field:'objectName',title:'名称',width:300,align:'center',editor:'text'},
	   		               {field:'isSet',title:'是否设置',width:400,align:'center',editor:{type:'checkbox',options:{on:'√',off:''}}},
	   	           ]];
		           toolbar = [{iconCls: 'icon-edit',text:'全选',handler: function(){updateCalculationObject(1)}},'-',{iconCls: 'icon-edit',text:'反选',handler: function(){updateCalculationObject(2)}
		    	              },'-',{iconCls: 'icon-edit',text:'清除',handler: function(){updateCalculationObject(3)}},'-',{iconCls: 'icon-edit',text:'刷新',handler: function(){alert('help')}
		    	              }];
		           if(!(0 == parentObject.length || "" == parentObject[0].parentCode)){
		        	   toolbar.push('                ');
	        		   params = {parentCode:parentObject[0].parentCode,nodeType:nodeID};
		    		   for(var i=0;i<parentObject.length;i++){
			        	   var selected = false;
			        	   if(0==i){
			        		   selected = true;
			        	   }
			        	   toolbar.push({id:i+1,text:parentObject[i].parentName,toggle:true,group:'g1',plain:true,selected:selected,
			        		             handler: function(){
			        	                 $('#dg').datagrid('load',{parentCode:parentObject[this.id-1].parentCode,nodeType:nodeID});
			        	                 nodeTypeid = this.id-1;}});
			           }
		    		   url="${pageContext.request.contextPath}/calculationobject/load.do";
		    	   }else{
		    		   url="${pageContext.request.contextPath}/calculationobject/load.do?parentCode=parentCodeparentCode";
		    	   }
		    	}else{
		    		columns=[[
	    	  	               {field:'id',title:'',width:80,hidden:'true'},
	    	  	               {field:'ck',checkbox:'true'},
	    	  	               {field:'number',title:'序号',width:50,align:'center',
	    	  	            	    styler: function(value,row,index){
	    		       					return 'background-color:#ffee00;color:red;';
	    		       				},formatter: function(value,row,index){
			    	  	   				return index+1;
			    	  	   		    }
	    		       			},
	    		               {field:'objectCode',title:'编码',width:200,align:'center',editor:'text'},
	    		               {field:'objectName',title:'名称',width:300,align:'center',editor:'text'},
	    		               {field:'isSet',title:'是否设置',width:400,align:'center',editor:{type:'checkbox',options:{on:'√',off:''}}},
	    	       ]];
		           url="${pageContext.request.contextPath}/calculationobject/load.do?nodeType="+nodeID;
		           toolbar = [{id : '0',iconCls: 'icon-edit',text:'全选',handler: function(){updateCalculationObject(1)}},'-',{id : '3',iconCls: 'icon-edit',text:'反选',handler: function(){updateCalculationObject(2)}
		    	              },'-',{id : '2',iconCls: 'icon-edit',text:'清除',handler: function(){updateCalculationObject(3)}},'-',{id : '1',iconCls: 'icon-edit',text:'刷新',handler: function(){alert('help')}
		    	              }];
		    	}
		    	break;
		    case 3:if(1==nodeID){
    	             columns=[[
  	    	  	               {field:'id',title:'',width:80,hidden:'true'},
  	    	  	               {field:'ck',checkbox:'true'},
  	    	  	               {field:'number',title:'序号',width:50,align:'center',
  	    	  	            	    styler: function(value,row,index){
  	    		       					return 'background-color:#ffee00;color:red;';
  	    		       				},formatter: function(value,row,index){
  			    	  	   				return index+1;
  			    	  	   		    }
  	    		       			},
  	    		               {field:'code',title:'编码',width:120,align:'center',editor:'text'},
  	    		               {field:'name',title:'名称',width:200,align:'center',editor:'text'},
  	    		               {field:'description',title:'描述',width:200,align:'center',editor:'text'},
  	    		               {field:'type',title:'类型',width:120,align:'center',editor:'text'},
  	    		               {field:'combCalculation',title:'联算',width:120,align:'center',editor:{type:'checkbox',options:{on:'√',off:''}}},
  	    		               {field:'sourceClass',title:'源类',width:200,align:'center',editor:'text'},
    	    		   ]];
    		           url="${pageContext.request.contextPath}/modelmethod/load.do";
    		           toolbar = [{iconCls: 'icon-edit',text:'提取',handler: function(){alert(1)}},'-',{iconCls: 'icon-edit',text:'确定',handler: function(){getNewTree()}}];}
				    else if(2!=nodeID%5){
				    	columns=[[
	  	    	  	               {field:'id',title:'',width:80,hidden:'true'},
	  	    	  	               {field:'number',title:'序号',width:50,align:'center',
	  	    	  	            	    styler: function(value,row,index){
	  	    		       					return 'background-color:#ffee00;color:red;';
	  	    		       				},formatter: function(value,row,index){
	  			    	  	   				return index+1;
	  			    	  	   		    }
	  	    		       			},
	  	    		               {field:'projectName',title:'项目',width:200,align:'center',editor:'text'},
	  	    		               {field:'isSet',title:'是否设置',width:120,align:'center',editor:{type:'checkbox',options:{on:'√',off:''}}},
	  	    		               {field:'description',title:'备注',width:200,align:'center',editor:'text'},
	    	    		   ]];
				    	var type = nodeID % 5;
				    	var code = $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).attributes.code;
				    	url="${pageContext.request.contextPath}/inputoutput/load.do?type="+type+"&modelCode="+code;
				    }else{
				    	$("#div-dg").hide();
				    }
				    break;
		    case 4:if(1 == nodeID){
				    	columns=[[
		    	  	               {field:'id',title:'',width:80,hidden:'true'},
		    	  	               {field:'number',title:'序号',width:50,align:'center',
		    	  	            	    styler: function(value,row,index){
		    		       					return 'background-color:#ffee00;color:red;';
		    		       				},formatter: function(value,row,index){
				    	  	   				return index+1;
				    	  	   		    }
		    		       			},
		    		               {field:'projectName',title:'项目',width:200,align:'center',editor:'text'},
		    		               {field:'type',title:'功能类型',width:200,align:'center',editor:'text'},
		    		               {field:'isSet',title:'是否设置',width:200,align:'center',editor:{type:'checkbox',options:{on:'√',off:''}}},
		    		               {field:'remark',title:'备注',width:300,align:'center',editor:'text'},
		    		   ]];
			    	   url="${pageContext.request.contextPath}/functiontype/load.do";
    		           toolbar = [{iconCls: 'icon-edit',text:'确定',handler: function(){getNewFunctionTree()}}];
		           }else if(3 == nodeID%8){
		        	   columns=[[
		    	  	               {field:'id',title:'',width:80,hidden:'true'},
		    	  	               {field:'number',title:'序号',rowspan:2,width:50,align:'center',
		    	  	            	    styler: function(value,row,index){
		    		       					return 'background-color:#ffee00;color:red;';
		    		       				},formatter: function(value,row,index){
				    	  	   				return index+1;
				    	  	   		    }
		    		       			},
		    		       		   {colspan : '2',title : '时段类型'},
		    		       		   {field:'defaultNumber',title:'默认时段数',rowspan:2,width:200,align:'center',editor:'text'},
		    		               {field:'isDefault',title:'是否为默认项',rowspan:2,width:200,align:'center',editor:{type:'checkbox',options:{on:'√',off:''}}}],
		    		               [{field:'timeNumber',title:'倍数',width:200,align:'center',editor:'text'},
		    		               {field:'timeUnit',title:'单位',width:200,align:'center',
		    		            	   formatter:function(value,row){
		    		            		   if(""==value){
		    		       						return "";
		    		       					}
		    		            		   return data4_21[value].timeUnitName;
		    							},
		    		            	   editor:{
											type:'combobox',
											options:{
												panelHeight:'auto',
												valueField:'timeUnit',
												textField:'timeUnitName',
												data : data4_21,
											}
										}
		    						},
		    		               
		    		   ]];
		        	   var functionId = $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).attributes.functionId;
			    	   url="${pageContext.request.contextPath}/scheduletime/load.do?functionId="+functionId;
		           }else if(4 == nodeID%8){
		        	   columns=[[
		    	  	               {field:'id',title:'',width:80,hidden:'true'},
		    	  	               {field:'ck',checkbox:'true'},
		    	  	               {field:'number',title:'序号',width:50,align:'center',
		    	  	            	    styler: function(value,row,index){
		    		       					return 'background-color:#ffee00;color:red;';
		    		       				},formatter: function(value,row,index){
				    	  	   				return index+1;
				    	  	   		    }
		    		       			},
		    		               {field:'objectCode',title:'编码',width:200,align:'center',editor:'text'},
		    		               {field:'objectName',title:'名称',width:300,align:'center',editor:'text'},
		    		               {field:'isCalculation',title:'是否设置',width:400,align:'center',editor:{type:'checkbox',options:{on:'√',off:''}}},
		    	       ]];
		        	   var functionId = $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).attributes.functionId;
			           params = {nodeType:barIndex[0]};
			           url="${pageContext.request.contextPath}/functioncalculation/load.do?functionId="+functionId;
			           toolbar = [{iconCls: 'icon-edit',text:'全选',handler: function(){updateFunctionCalculation(1)}},'-',{iconCls: 'icon-edit',text:'反选',handler: function(){updateFunctionCalculation(2)}
			    	              },'-',{iconCls: 'icon-edit',text:'清除',handler: function(){updateFunctionCalculation(3)}},'-',{iconCls: 'icon-edit',text:'刷新',handler: function(){refreshFunctionCalculation(nodeID)}
			    	              }];
			           if(0<barIndex.length){
			        	   toolbar.push('                ');
			           }
			           for(var i=0;i<barIndex.length;i++){
			        	   var selected = false;
			        	   if(0==i){
			        		   selected = true;
			        	   }
			        	   toolbar.push({id:i+1,text:barArray[barIndex[i]-1],toggle:true,group:'g1',plain:true,selected:selected,
			        		             handler: function(){
			        		             var nodeType = barIndex[this.id-1];
			        		             if(11 == nodeType || 12 == nodeType){
			        		            	 var url2 = "${pageContext.request.contextPath}/functioncalculation/getParent.do"
		        		 					 $.post(url2,{functionId:functionId,nodeType:nodeType},
		        		 							function(result) {
		        		 								if (result.success) {
		        		 									var toolbar2 = [];
		        		 									var parent = result.data;
		        		 									if(0<parent.length){
		        		 										$('#dg').datagrid('load',{nodeType:nodeType,parentCode:parent[0].parentCode});
		        		 									}
		        		 									for(var j=0;j<parent.length;j++){
		        		 										var selected2 = false;
		        		 						        	    if(0==j){
		        		 						        		   selected2 = true;
		        		 						        	    }
		        		 										toolbar2.push({id:parent[j].parentCode,text:parent[j].parentName,toggle:true,group:'g2',plain:true,selected:selected2,
		        						        		             handler: function(){
		        						        		            	 alert(this.id);
		        							        	                 $('#dg').datagrid('load',{nodeType:nodeType,parentCode:this.id});
		        							        	                 }});
		        		 									}
		       			        		            	 $('#dg').datagrid({
		       			        		            		 toolbar:toolbar2 
		       			        		            	 });
		        		 								} else {
		        		 									$.messager.alert(result.errormsg, result.errorinfo);
		        		 								}
		        		 							}, 
		        		 					 "json");
			        		             }else{
			        		            	 toolbar2 = [];
			        		            	 $('#dg').datagrid({
			        		            		 toolbar:toolbar2 
			        		            	 });
			        		            	 $('#dg').datagrid('load',{nodeType:nodeType});
			        		             }
			        	                 nodeTypeid = this.id-1;}});
			           }
		           }else if(5 == nodeID%8){
		        	   columns=[[
    	  	               {field:'id',title:'',width:80,hidden:'true'},
    	  	               {field:'number',title:'序号',rowspan:2,width:50,align:'center',
    	  	            	    styler: function(value,row,index){
    		       					return 'background-color:#ffee00;color:red;';
    		       				},formatter: function(value,row,index){
		    	  	   				return index+1;
		    	  	   		    }
    		       			},
    		       		   {colspan : '2',title : '预报时段类型'},
    		       		   {field:'predictionCode',title:'预报方案编码',rowspan:2,width:200,align:'center',editor:'text'},
    		               {field:'isDefault',title:'是否为默认项',rowspan:2,width:200,align:'center',editor:{type:'checkbox',options:{on:'√',off:''}}}],
    		               [{field:'typeNumber',title:'倍数',width:200,align:'center',editor:'text'},
    		               {field:'typeUnit',title:'单位',width:200,align:'center',
    		            	   formatter:function(value,row){
    		            		   if(""==value){
    		       						return "";
    		       					}
    		            		   return data4_41[value].typeUnitName;
    							},
    		            	   editor:{
									type:'combobox',
									options:{
										panelHeight:'auto',
										valueField:'typeUnit',
										textField:'typeUnitName',
										data : data4_41,
									}
								}
    						},
		    		               
		    		   ]];
		        	   toolbar = [];
			    	   url="${pageContext.request.contextPath}/predictionscheme/load.do";
			    	   var funCalculationId = "";
	        		   if(0<barArray2.length){
	        			   funCalculationId = barArray2[0].funCalculationId;
	        		   }
	        		   params = {funCalculationId:funCalculationId};
			    	   if(!(1 == barArray2.length && "" == barArray2[0].funCalculationId)){
			    		   for(var i=0;i<barArray2.length;i++){
				        	   var selected = false;
				        	   if(0==i){
				        		   selected = true;
				        	   }
				        	   toolbar.push({id:i+1,text:barArray2[i].objectName,toggle:true,group:'g1',plain:true,selected:selected,
				        		             handler: function(){
				        	                 $('#dg').datagrid('load',{funCalculationId:barArray2[this.id-1].funCalculationId});
				        	                 nodeTypeid = this.id-1;}});
				           }
			    	   }
		           }else if(7 == nodeID%8 || 0 == nodeID%8){
		        	   columns=[[
    	  	               {field:'id',title:'',width:80,hidden:'true'},
    	  	             {field:'ck',checkbox:'true'},
    	  	               {field:'number',title:'序号',rowspan:2,width:50,align:'center',
    	  	            	    styler: function(value,row,index){
    		       					return 'background-color:#ffee00;color:red;';
    		       				},formatter: function(value,row,index){
		    	  	   				return index+1;
		    	  	   		    }
    		       			},
    		       		   {field:'modelCode',title:'编码',rowspan:2,width:200,align:'center',editor:'text'},
    		               {field:'modelName',title:'名称',width:200,align:'center',editor:'text'},
    		               {field:'description',title:'描述',width:200,align:'center'},
    		               {field:'modelType',title:'类型',width:200,align:'center'},
    		               {field:'isApply',title:'应用',rowspan:2,width:200,align:'center',editor:{type:'checkbox',options:{on:'√',off:''}}},
		    		   ]];
		        	   toolbar = [{iconCls: 'icon-edit',text:'全选',handler: function(){updateFunctionModel(1)}},'-',{iconCls: 'icon-edit',text:'反选',handler: function(){updateFunctionModel(2)}
	    	              },'-',{iconCls: 'icon-edit',text:'清除',handler: function(){updateFunctionModel(3)}},'-',{iconCls: 'icon-edit',text:'刷新',handler: function(){refreshFunctionModel(nodeID)}
	    	              }];
		        	   var functionId = $('#tt').tree('getParent', $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).target).attributes.functionId;
		        	   params = {type:"1",functionId:functionId};
		        	   if(0 == nodeID%8){
		        		   var funCalculationId = "";
		        		   if(0<barArray2.length){
		        			   funCalculationId = barArray2[0].funCalculationId;
		        		   }
		        		   params = {type:"2",functionId:functionId,funCalculationId:funCalculationId};
		        		   toolbar.push('-',{iconCls: 'icon-edit',text:'批量设置',handler: function(){BatchSet()}});
		        		   if(!(1 == barArray2.length && "" == barArray2[0].funCalculationId)){
		        			   toolbar.push('             ');
				    		   for(var i=0;i<barArray2.length;i++){
					        	   var selected = false;
					        	   if(0==i){
					        		   selected = true;
					        	   }
					        	   toolbar.push({id:i+1,text:barArray2[i].objectName,toggle:true,group:'g1',plain:true,selected:selected,
					        		             handler: function(){
					        	                 $('#dg').datagrid('load',{funCalculationId:barArray2[this.id-1].funCalculationId});
					        	                 nodeTypeid = this.id-1;}});
					           }
				    	   }
		        	   }
			    	   url="${pageContext.request.contextPath}/functionmodel/load.do";
		           }else if(1 == nodeID%8 && !(0 == times.length || "" == times[0].timeID)){
		        	   column=[
		    	  	             {field:'number',title:'',width:80,hidden:'true'},
		    	  	             {field:'ck',checkbox:'true'},
		    	  	             {field:'number2',title:'序号',width:50,align:'center',
		    	  	            	    styler: function(value,row,index){
		    		       					return 'background-color:#ffee00;color:red;';
		    		       				},formatter: function(value,row,index){
				    	  	   				return index+1;
				    	  	   		    }
		    		       		 },
		    		       		{field:'planName',title:'项目',width:200,align:'center',editor:'text'},
		    		   ];
		        	   if(!(0 == calculations.length || "" == calculations[0].objectCode)){
		        		   for(var i=0;i<calculations.length;i++){
		        			   column.push({field:calculations[i].objectCode,title:calculations[i].objectName,width:200,align:'center',editor:'text'});
		        		   }
		        	   }
		        	   var columns = [];
		        	   columns.push(column);
		        	   //alert(JSON.stringify(column));
		        	   //alert(JSON.stringify(columns));
		        	   toolbar = [];
		        	   if(!(0 == times.length || "" == times[0].timeID)){
		        		   params = {timeID:times[0].timeID};
			    		   for(var i=0;i<times.length;i++){
				        	   var selected = false;
				        	   if(0==i){
				        		   selected = true;
				        	   }
				        	   toolbar.push({id:i+1,text:times[i].timeName,toggle:true,group:'g1',plain:true,selected:selected,
				        		             handler: function(){
				        	                 $('#dg').datagrid('load',{timeID:times[this.id-1].timeID});
				        	                 nodeTypeid = this.id-1;}});
				           }
			    	   }
		        	   url="${pageContext.request.contextPath}/decisionplan/load.do";
		        	   
		           }else{
		        	   $("#div-dg").hide();
		           }
		    break;
		}
		$('#dg1').datagrid({toolbar:toolbar});
		$('#dg').datagrid({
			width:'400px',
        	height:'auto',
        	checkOnSelect:false,
        	selectOnCheck:false,
			//rownumbers : true,
			//singleSelect: true,
			//toolbar:toolbar2,
			queryParams:params,
			method:'get',
			onClickCell: onClickCell,
			columns:columns,
            url : url,
		   loadFilter : function(result) {
				if (null != result.data) {
					return result.data;
				} else {
					$.messager.alert(result.errormsg, result.errorinfo);
					return;
				}
			},
			onAfterEdit : function(rowIndex, rowData, changes){
				if('{}'!=JSON.stringify(changes)){
					updateData(rowData, changes);
				}
			},
			onRowContextMenu : function(e, rowIndex, rowData){
				rowIndex1 = "";
				rowData1 = "";
				rowIndex2 = rowIndex;
				rowData2 = rowData;
				//addBasicAttribute();
				e.preventDefault();
				$('#mm').menu('show', {
		            left: e.pageX,
		            top: e.pageY
		        });
			},
			onHeaderContextMenu :function(e,field){
				rowIndex1 = "";
				rowData1 = "";
				//addBasicAttribute();
				e.preventDefault();
				var node = $('#tt').tree('getSelected');
				var id = '#mm';
				if((4 == flag && 1 == node.id%8) && "number2" != field && "planName" != field){
					id = '#mm3';
					field2 = field;
				}
				$(id).menu('show', {
		            left: e.pageX,
		            top: e.pageY
		        });
			},
		});
	}
	/*复制输入输出到其他所有模型*/
	var ioNode = undefined;//模型方法中复制功能时，通过右键获取到的节点
	function  copyInputOutput(){
		var parentNode = $('#tt').tree('getParent', ioNode.target);
		var str = "";
		if(null == $('#tt').tree('getSelected') || $('#tt').tree('getSelected').id != ioNode.id){
			str = "当前列表不是"+ioNode.text+"的设置列表，";
		}
		$.messager.confirm("系统提示",str+"您确认要将<font color=red>" + parentNode.text +"</font>模型的<font color=red>"+ ioNode.text + "</font>复制到其他模型吗？",
			function(r) {
				if (r){
					var strCodes = [];
					var childrenNodes = $('#tt').tree('getChildren', $('#tt').tree('find', 1).target);
					for(var i=0;i<childrenNodes.length/5;i++){
						var modelCode = childrenNodes[5*i].attributes.code;
						if(parentNode.id!=childrenNodes[5*i].id){
							strCodes.push(modelCode);
						}
						var codes = strCodes.join(",");
					}
					var url = "${pageContext.request.contextPath}/inputoutput/copy.do"
					$.post(url,{codes:codes,code:parentNode.attributes.code,type:ioNode.id%5},
							function(result) {
								if (result.success) {
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, 
					"json");
				}
			});
	}
	/*功能类型 - 计算对象刷新按钮*/
	function refreshFunctionCalculation(nodeID){
		var functionId = $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).attributes.functionId;
		var url = "${pageContext.request.contextPath}/functioncalculation/refresh.do?functionId="+functionId;
		$.post(url,
				function(result) {
					if (result.success) {
						var functionId = $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).attributes.functionId;
						var url="${pageContext.request.contextPath}/functioncalculation/getType.do?functionId="+functionId;
						$.post(url,function(result) {
									if (result.success) {
										barIndex = result.data;
										list(nodeID);
									} else {
										$.messager.alert(result.errormsg, result.errorinfo);
									}
								}, 
						"json");
					} else {
						$.messager.alert(result.errormsg, result.errorinfo);
					}
				}, 
		"json");
	}
	/*功能类型 - 模型方法刷新按钮*/
	function refreshFunctionModel(nodeID){
		var functionId = $('#tt').tree('getParent', $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).target).attributes.functionId;
		var funCalculationId = "";
		var type = "1";
		var functionType = $('#tt').tree('getParent', $('#tt').tree('getParent', $('#tt').tree('find', nodeID).target).target).attributes.type;
		if(0 == nodeID%8){
			if(0<barArray2.length){
				funCalculationId = barArray2[nodeTypeid].funCalculationId;
			}
			type = "2";
		}
		if(!(2 == type && "" == funCalculationId)){
			var url = "${pageContext.request.contextPath}/functionmodel/refresh.do";
			$.post(url,{functionId:functionId,funCalculationId:funCalculationId,type:type,functionType:functionType},
					function(result) {
						if (result.success) {
							if("2" == type){
								var url="${pageContext.request.contextPath}/functioncalculation/getType2.do";
								$.post(url,{functionId:functionId,isCalculation:'√'},function(result) {
											if (result.success) {
												barArray2 = result.data;
												list(nodeID);
											} else {
												$.messager.alert(result.errormsg, result.errorinfo);
											}
										}, 
								"json");
							}else if("1" == type){
								list(nodeID);
							} 
						}else {
							$.messager.alert(result.errormsg, result.errorinfo);
						}
					}, 
			"json");
		}
	}
	/*功能类型 - 模型方法 - 单库模型 批量设置*/
	function BatchSet(){
		if(0 == barArray2.length){
			$.messager.alert("系统提示", "不存在水库标签，无法批量设置！");
			return;
		}
		var funCalculationId = barArray2[nodeTypeid].funCalculationId;
		var strIds = [];
		for(var i=0;i<barArray2.length;i++){
			if(funCalculationId != barArray2[i].funCalculationId){
				strIds.push(barArray2[i].funCalculationId);
			}
		}
		var ids = strIds.join(",");
		var url="${pageContext.request.contextPath}/functionmodel/batchset.do";
		
		
		if(0 == ids.length){
			$.messager.alert("系统提示", "不存在其他水库标签，无法批量设置！");
			return;
		}
		$.messager.confirm("系统提示","您确认要将<font color=red>" + barArray2[nodeTypeid].objectName +"</font>水库的单库模型复制到其他水库标签吗？",
			function(r) {
				if (r){
					$.post(url,{funCalculationId:funCalculationId,ids:ids},function(result) {
						if (result.success) {
							$.messager.alert("系统提示", "批量设置成功！");
						} else {
							$.messager.alert(result.errormsg, result.errorinfo);
						}
					}, "json");
				}
			});
	}
	/*点击树节点后的节点*/
	var treeNode = undefined;
	/*功能类型-计算对象分页标签的当前ID*/
	var nowID = undefined;
	/*键盘左右方向键监听事件*/
	$(document).keydown(function(event){ 
		//判断当event.keyCode 为37时（即左方面键），执行; 
		//判断当event.keyCode 为39时（即右方面键），执行; 
		if(event.keyCode == 37 && undefined != treeNode){
			if(2 == flag && (11 == treeNode.id || 12 == treeNode.id)){
                nowID = (nodeTypeid-1+parentObject.length)%parentObject.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{parentCode:parentObject[nowID-1].parentCode,nodeType:treeNode.id});
                nodeTypeid = nowID-1;
			}else if(4 == flag && 5 == treeNode.id%8){
				nowID = (nodeTypeid-1+barArray2.length)%barArray2.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{funCalculationId:barArray2[nowID-1].funCalculationId});
                nodeTypeid = nowID-1;
			}else if(4 == flag && 4 == treeNode.id%8){
				nowID = (nodeTypeid-1+barIndex.length)%barIndex.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{nodeType:barIndex[nowID-1]});
                nodeTypeid = nowID-1;
			}else if(4 == flag && 5 == treeNode.id%8){
				nowID = (nodeTypeid-1+barArray2.length)%barArray2.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{funCalculationId:barArray2[nowID-1].funCalculationId});
                nodeTypeid = nowID-1;
			}else if(4 == flag && 0 == treeNode.id%8){
				nowID = (nodeTypeid-1+barArray2.length)%barArray2.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{funCalculationId:barArray2[nowID-1].funCalculationId});
                nodeTypeid = nowID-1;
			}else if(4 == flag && 1 == treeNode.id%8){
				nowID = (nodeTypeid-1+times.length)%times.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{timeID:times[nowID-1].timeID});
                nodeTypeid = nowID-1;
			}
		}else if (event.keyCode == 39 && undefined != treeNode){
			if(2 == flag && (11 == treeNode.id || 12 == treeNode.id)){
                nowID = (nodeTypeid+1)%parentObject.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{parentCode:parentObject[nowID-1].parentCode,nodeType:treeNode.id});
                nodeTypeid = nowID-1;
			}else if(4 == flag && 4 == treeNode.id%8){
				nowID = (nodeTypeid+1)%barIndex.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{nodeType:barIndex[nowID-1]});
                nodeTypeid = nowID-1;
			}else if(4 == flag && 5 == treeNode.id%8){
				nowID = (nodeTypeid+1)%barArray2.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{funCalculationId:barArray2[nowID-1].funCalculationId});
                nodeTypeid = nowID-1;
			}else if(4 == flag && 0 == treeNode.id%8){
				nowID = (nodeTypeid+1)%barArray2.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{funCalculationId:barArray2[nowID-1].funCalculationId});
                nodeTypeid = nowID-1;
			}else if(4 == flag && 1 == treeNode.id%8){
				nowID = (nodeTypeid+1)%times.length+1;
				$('#'+nowID).linkbutton('select');
				$('#dg').datagrid('load',{timeID:times[nowID-1].timeID});
                nodeTypeid = nowID-1;
			}
		}
	});
	/*模型方法中确定，并生成新的树*/
	function getNewTree(){
	    //重置树结构
		$('#tt').tree({
			data: [{
				text: '模型方法',
				//state: 'open',
				id : 1
			    }],
			onClick: function(node){
				treeNode = node;
				list(node.id);
			},
			onContextMenu: function(e, node){
				//$('#tt').tree('select', node.target);
				ioNode = node;
				if(1!=node.id&&2!=node.id%5){
					e.preventDefault();
					$('#mm2').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				}
			}
		});
		$.post("${pageContext.request.contextPath}/modelmethod/load.do",{combCalculation:"√"},
				function(result) {
					if (result.success) {
						if(result.data){
							var j=2;
							for(var i=0;i<result.data.total;i++){
								var row = result.data.rows[i];
								var selected = $('#tt').tree('find',1);
								$('#tt').tree('append', {
									parent: selected.target,
									data: [{
										id: j++,
										text: row.name,
										state: "closed",
										attributes : {code:row.code},
										children:[{
											id: j++,
											text:'单值输入' ,
										},{
											id: j++,
											text:'过程输入' ,
										},{
											id: j++,
											text:'边界约束' ,
										},{
											id: j++,
											text:'结果输出' ,
										}],
									}],
								});
								$('#tt').tree('expand',$('#tt').tree('find',2).target);
							}
						}
					} else {
						$.messager.alert(result.errormsg, result.errorinfo);
					}
				}, "json");
	}
	/*功能类型中确定，并生成新的树*/
	function getNewFunctionTree(){
	    //重置树结构
		$('#tt').tree({
			data: [{
				text: '功能类型',
				//state: 'open',
				id : 1
			    }],
			onClick: function(node){
				treeNode = node;
				//list(node.id);
				if(4 == flag && 4 == node.id%8){
					var functionId = $('#tt').tree('getParent', $('#tt').tree('find', node.id).target).attributes.functionId;
					var url="${pageContext.request.contextPath}/functioncalculation/getType.do?functionId="+functionId;
					$.post(url,function(result) {
								if (result.success) {
									barIndex = result.data;
									list(node.id);
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, 
					"json");
				}else if(4 == flag && 5 == node.id%8){
					var functionId = $('#tt').tree('getParent', $('#tt').tree('find', node.id).target).attributes.functionId;
					var url="${pageContext.request.contextPath}/functioncalculation/getType2.do";
					$.post(url,{functionId:functionId,isCalculation:'√'},function(result) {
								if (result.success) {
									barArray2 = result.data;
									list(node.id);
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, 
					"json");
				}else if(4 == flag && 0 == node.id%8){
					var functionId = $('#tt').tree('getParent', $('#tt').tree('getParent', $('#tt').tree('find', node.id).target).target).attributes.functionId;
					var url="${pageContext.request.contextPath}/functioncalculation/getType2.do";
					$.post(url,{functionId:functionId,isCalculation:'√'},function(result) {
								if (result.success) {
									barArray2 = result.data;
									list(node.id);
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, 
					"json");
				}else if(4 == flag && 1 != node.id && 1 == node.id%8){
					var functionId = $('#tt').tree('getParent', $('#tt').tree('find', node.id).target).attributes.functionId;
					var url="${pageContext.request.contextPath}/decisionplan/getInitialization.do";
					$.post(url,{functionId:functionId},function(result) {
								if (result.success) {
									calculations = result.calculations;
									times = result.times;
									list(node.id);
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, 
					"json");
				}
				else{
					list(node.id);
				}
			}
		});
		$.post("${pageContext.request.contextPath}/functiontype/load.do",{isSet:"√"},
				function(result) {
					if (result.success) {
						if(result.data){
							var j=2;
							for(var i=0;i<result.data.total;i++){
								var row = result.data.rows[i];
								var selected = $('#tt').tree('find',1);
								$('#tt').tree('append', {
									parent: selected.target,
									data: [{
										id: j++,
										text: row.projectName,
										state: "closed",
										attributes : {functionId:row.id,type:row.type},
										children:[{
											id: j++,
											text:'调度期时间' ,
										},{
											id: j++,
											text:'计算对象' ,
										},{
											id: j++,
											text:'预报方案' ,
										},{
											id: j++,
											text:'模型方法' ,
											state: "closed",
											children:[{
												id:j++,
												text:'联算模型',
											},{
												id : j++,
											    text : '单库模型',
											}]
										},{
											id : j++,
											text : '决策计划',
										}],
									}],
								});
								$('#tt').tree('expand',$('#tt').tree('find',2).target);
							}
						}
					} else {
						$.messager.alert(result.errormsg, result.errorinfo);
					}
				}, "json");
	}
	/*功能类型-计算对象类型*/
	var barArray = ['水库','控制站','提防','蓄滞洪区','防洪控制断面','防洪对象','汇河口','水资源控制断面','引调水工程','河道测量断面','机组','闸门'];
	/*功能类型-计算对象类型标识*/
	var barIndex = undefined;
	/*功能类型-预报方案类型标识*/
	var barArray2 = [{funCalculationId:'',objectName:''}];
	/*功能类型 - 决策计划新增水库列*/
	var calculations = [{objectCode:'',ObjectName:''}];
	/*功能类型 - 决策计划类型标识*/
	var times = [{timeID:'',timeName:''}];
	/*计算对象 - 机组闸门父对象标识*/
	var parentObject = [{parentCode:'',parentName:''}];
	/*加载树结构*/
	function getTree(type){
		var data = undefined;
		flag = type;
		switch(type){
			case 1:data = [{
					text: '配置属性',
					state: 'open',
					children: [{
						id : 1,
						text: '基本属性'
					},{
						id : 2,
						text: '数据连接'
					},{
						id : 3,
						text: '数据单位'
					},{
						id : 4,
						text: '机组状态'
					},{
						id : 5,
						text: '闸门状态'
					}]
			}];break;
			case 2:data = [{
					text: '水库/电站',
					state: 'open',
					id : 1,
					children: [{
						id : 11,
						text: '机组'
					},{
						id : 12,
						text: '闸门'
					}],
				},{
					text: '控制站',
					id : 2,
				},{
					text: '分蓄洪区',
					id : 4,
				},{
					text: '提防',
					id : 3,
				},{
					text: '引水调工程',
					id : 9,
				}];break;
			case 3:data = [{
				text: '模型方法',
				//state: 'open',
				id : 1
			    }];break;
			case 4:data = [{
				text: '功能类型',
				//state: 'open',
				id : 1
			    }];break;
		}
		$('#tt').tree({
			data: data,
			onClick: function(node){
				treeNode = node;
				if(2 == flag && (11 == node.id || 11 == node.id)){
					var parentType = $('#tt').tree('getParent', $('#tt').tree('find', node.id).target).id;
					var url="${pageContext.request.contextPath}/calculationobject/getParentObject.do";
					$.post(url,{nodeType:parentType,isSet:"√"},function(result) {
								if (result.success) {
									parentObject = result.parentObject;
									list(node.id);
								} else {
									$.messager.alert(result.errormsg, result.errorinfo);
								}
							}, 
					"json");
				}else{
					list(node.id);
				}
			}
		});
		var node = $('#tt').tree('find', 1);
		$('#tt').tree('select', node.target);
		list(node.id);
	}
	
	function download(){
		var url = "${pageContext.request.contextPath}/basicattribute/download.do";
		var path = "config.xml";
		$.post(url,{path:path},function(result) {
			if (result.success) {
			} else {
				$.messager.alert(result.errormsg, result.errorinfo);
			}
		}, 
"json");
	}
	
</script>
</head>
<body class="easyui-layout" >
   <div id="mm" class="easyui-menu"  style="width:120px;">

		<div data-options="iconCls:'icon-add'" onclick="javascript:addData()">添加信息</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-cancel'" onclick="javascript:deleteData()">删除信息</div>
		<div class="menu-sep"></div>
	</div>
	<div id="mm2" class="easyui-menu"  style="width:120px;">
		<div data-options="iconCls:'icon-add'" onclick="javascript:copyInputOutput()">复制到其他模型</div>
		<div class="menu-sep"></div>
	</div>
	<div id="mm3" class="easyui-menu"  style="width:120px;">
		<div data-options="iconCls:'icon-add'" onclick="javascript:numberChange()">编号变幅</div>
		<div class="menu-sep"></div>
	</div>
	<div id="dlg" class="easyui-dialog" title = "编号变幅"
	     style="width: 260px; height: auto; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
		   <table cellspacing="8px">
			  <tr>
				<td><input type="text" id="times" name="times" class="easyui-validatebox"  style="width: 150px"/></td>
			  </tr>
		   </table>
	</div>
	<div id="dlg-buttons">
			<a href="javascript:OK()" class="easyui-linkbutton">确定</a> 
			<a href="javascript:Cancel()"class="easyui-linkbutton">取消</a>
		</div>
	<div region="north" style="height:120px;">
		<div >
		   <a href="#"><img src="${pageContext.request.contextPath}/image/3.png" height="116px" width="1351px" usemap="#Map"/></a>
			<map name="Map" id="Map">  
				<area shape="rect" coords="40,10,105,100" href="javascript:getTree(1)"/>
				<area shape="rect" coords="190,10,260,100" href="javascript:getTree(2)"/>
			    <area shape="rect" coords="315,10,385,100"  href="javascript:getTree(3)"/>
			    <area shape="rect" coords="450,10,520,100"	href="javascript:getTree(4)"/>
			    <area shape="rect" coords="640,10,715,100" href="javascript:download()"/>
			    <area shape="rect" coords="760,10,855,100" href="javascript:getTree(6)"/>
			</map>
		</div>
	</div>
	<div region="center">
		<div id='div-dg' style="margin:20px 10px;">
		   <table id="dg1"></table>
		   <table id="dg"></table>
		</div>
	</div>
	<div region="west" style="width: 200px" title="属性配置" split="true">
	    <ul id="tt"></ul>
	</div>
	<div region="south" style="height: 5px;background-color: #D3D3D3" align="center">
	</div>
</body>
</html>

<script type="text/javascript">
	if ('${errorMsg}' != '') {
		$.messager.alert("系统提示", '${errorMsg}');
	}
</script>