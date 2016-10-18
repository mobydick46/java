<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表格插件</title>
<link rel="stylesheet" type="text/css"	href="lib/jquery-easyui-1.5/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"	href="lib/jquery-easyui-1.5/themes/icon.css">
<script type="text/javascript"	
	src="${pageContext.request.contextPath}/lib/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript"	
	src="${pageContext.request.contextPath}/lib/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript"	
	src="${pageContext.request.contextPath}/lib/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	$(function(){
		createForm();
	});

	function newForm(){
		$('#dlg-NF').dialog('open').dialog('setTitle','新建表格');
	}

	function createForm(){
		var title=$('#title').textbox('getText');
		var colNum=$('#colNum').numberbox('getValue');
		var rowNum=$('#rowNum').numberbox('getValue');

		var columns=initColumns(colNum);  //定义数组

		$('#dg').datagrid({
			rownumbers : true,
			fit:true,
			striped:true,
			fitColumns:true,
			columns:[columns],
			loadFilter : function(result) {
				if (null != result.data) {
					return result.data;
				}else{
					return {total:0,rows:''};
				}
			},
			onClickCell: onClickCell,
            onEndEdit: onEndEdit,
            onHeaderContextMenu:colDefine
		});	

		initRow(rowNum,colNum,$('#dg'));

    	$('#dlg-NF').dialog('close');
	}

	function initColumns(colNum){
		var columns=[];
		for(var i=1;i<=colNum;i++){
			var temp={};
			temp.width=150;
			temp.editor={
				type:'textbox',
			};
			temp.field="col"+i;
			temp.title="列"+i;
			columns.push(temp);
		}

		return columns;
	}

	var editIndex = undefined;
    function endEditing(){
        if (editIndex == undefined){return true}
        if ($('#dg').datagrid('validateRow', editIndex)){
            $('#dg').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field){
        if (editIndex != index){
            if (endEditing()){
                $('#dg').datagrid('selectRow', index)
                        .datagrid('beginEdit', index);
                var ed = $('#dg').datagrid('getEditor', {index:index,field:field});
                if (ed){
                    ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                }
                editIndex = index;
            } else {
                setTimeout(function(){
                    $('#dg').datagrid('selectRow', editIndex);
                },0);
            }
        }
    }
    function onEndEdit(index, row){
        // $('#dg').datagrid('endEdit', index);
    }
    function append(){
        if (endEditing()){
            $('#dg').datagrid('appendRow',{status:'P'});
            editIndex = $('#dg').datagrid('getRows').length-1;
            $('#dg').datagrid('selectRow', editIndex)
                    .datagrid('beginEdit', editIndex);
        }
    }
    function removeit(){
        if (editIndex == undefined){return}
        $('#dg').datagrid('cancelEdit', editIndex)
                .datagrid('deleteRow', editIndex);
        editIndex = undefined;
    }

	function initRow(rowNum,colNum,dg){
		var data={};
		var rows=[];

		for(var i=1;i<=rowNum;i++){
			rows.push(getNewRow());
		}

		data["rows"]=rows;
		data["total"]=rowNum;

		var result={};
		result.data=data;

		$('#dg').datagrid('loadData',result);
	}

	function getNewRow(){
		var row={};
		var colField;
		for(var i=1;i<=colNum;i++){
			colField="col"+i;
			row[colField]="";
		}

		return row;
	};

	function changeBorder(cls){
       $('#dg').datagrid('getPanel').removeClass('lines-both lines-no lines-right lines-bottom').addClass(cls);
   }

   function append(){
       if (endEditing()){
           $('#dg').datagrid('appendRow',{});
           editIndex = $('#dg').datagrid('getRows').length-1;
           $('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
       }
   }

   function addColumn(){

   }

   var fieldModyfing=null;
   function colDefine(e,field){
   		//列定义菜单
   		e.preventDefault();
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
        fieldModyfing=field;
   }

   function menuHandler(item){
   		if(item.name="colName"){
   			$("#dlg-col-name").dialog("open").dialog("setTitle", "新列名");
   			var tempCol=$('#dg').datagrid('getColumnOption',fieldModyfing);
   			$('#newColName').textbox('setText',tempCol.title);
   		}
   }

   function changeName(){

   }

</script>
<style>
	body{
		margin: 0;
		padding: 0;
	}
	.toolbar{
		padding: 3px;
		background-color:#0099CC;
		height: auto;
		width: 100%;
		border: 1px solid;
	}
	/*.easyui-dialog{
		padding: 3px;
		height: auto;
		width: 200px;
	}*/
	.attr{
		display: inline;
		font-size: 1.35em;
	}
	.easyui-textbox .easyui-numberbox .easyui-combobox{
		width: 200px;
		float: left;
	}
	.overall{
		padding: 0px;
		margin: 5px;
	}

  	.lines-both .datagrid-body td{
	}
	.lines-no .datagrid-body td{
	    border-right:1px solid transparent;
	    border-bottom:1px solid transparent;
	}
	.lines-right .datagrid-body td{
	    border-bottom:1px solid transparent;
	}
	.lines-bottom .datagrid-body td{
	    border-right:1px solid transparent;
	}
</style>
</head>
<body>
	<div class="toolbar" id="toolbar">
		<a href="javascript:newForm()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新建表格</a>
		<span>边框</span>
		<select onChange="changeBorder(this.value)" id="border" style="width: 120px;height: 25px">
            <option value="lines-both">全边框</option>
            <option value="lines-no">无边框</option>
            <option value="lines-right">左右边框</option>
            <option value="lines-bottom">上下边框</option>
        </select>
        <a href="javascript:append()" class="easyui-linkbutton" plain="true">插入行</a> 
        <a href="javascript:append()" class="easyui-linkbutton" plain="true">插入列</a> 
	</div>
	<div class="easyui-dialog" id="dlg-NF" closed="true" closable="false" data-options="buttons:'#NF-bar'">
		<div class="overall">
			<div class="attr">标题</div>
			<input type="text" class="easyui-textbox" id="title"
				data-options="prompt:'新建表格'">
		</div>
		<div class="overall">
			<div class="attr">列数</div>
			<input type="text" class="easyui-numberbox" id="colNum"
				data-options="value:4">
		</div>
		<div class="overall">
			<div class="attr">行数</div>
			<input type="text" class="easyui-numberbox" id="rowNum"
				data-options="value:4">
		</div>
	</div>

	<div style="width: 100%;height: 1000px">
		<table id="dg" style="width: 100%;height: 100%">
		</table>
	</div>
	<div id="NF-bar">
		<a href="javascript:createForm()" class="easyui-linkbutton">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg-NF').dialog('close')">取消</a>
	</div>

	<!-- 列属性菜单 -->
	<div id="mm" class="easyui-menu" data-options="onClick:menuHandler" style="width:120px;">
    <div>
        <span>列定义</span>
        <div style="width:150px;">
        	<div data-options="name:'colName'">列名</div>
            <div data-options="name:'colWidth'">列宽度</div>
            <div data-options="name:'colCon'">控件</div>
        </div>
    </div>

    <div class="easyui-dialog" id="dlg-col-name" closed="true" closable="false" 
    	data-options="buttons:'#col-name-bar'" style="height: 300px;width: 200px">
		<div class="overall">
			<div class="attr">新列名</div>
			<input type="text" class="easyui-textbox" id="newColName">
		</div>
	</div>
	<div id="col-name-bar">
		<a href="javascript:changeName()" class="easyui-linkbutton">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" 
        	onclick="$('#dlg-col-name').dialog('close')">取消</a>
	</div>

</div>
</body>
</html>
