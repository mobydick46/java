package org.csw.entity.baseinfo;

/*
 * 默认数据集合类
 */

public class DefaultData {
	
	static String[][] SingleInput = {{"期初水位","调度期初起算水位"},{"期末水位","调度期末控制水位"},
			                         {"期初入库","调度期初入库流量"},{"期初出库","调度期初出库流量"},
			                         {"期初尾水位","调度期初下游水位"},{"防洪库容使用量","防洪库容控制使用量"},
			                         {"综合出力系数K之","电站综合出力系数"},{"水量耗水率","平均水量耗水率"},
			                         {"流量耗水率","平均流量耗水率"},{"出库至下级传播时间","出库传播至下级水库耗时"},
			                         {"负荷率","电站平均负荷率(0~1)"},{"平均尾水位偏差","出库~尾水位折算偏差"},
			                         {"平均水头损失","电站平均水头损失"}};
	static String[][] ProcessInput = {{"预报流量","调度期初起算水位"},{"最高库水位","调度期末控制水位"},
						            {"最低库水位","调度期初入库流量"},{"最大出库","调度期初出库流量"},
						            {"最小出库","调度期初下游水位"},{"最大出力","防洪库容控制使用量"},
						            {"最小出力","电站综合出力系数"},{"计划出库","平均水量耗水率"},
						            {"计划泄流","平均流量耗水率"},{"计划发电流量","出库传播至下级水库耗时"},
						            {"计划出力","电站平均负荷率(0~1)"},{"汛限水位","出库~尾水位折算偏差"},
						            {"其他出库流量","电站平均水头损失"},{"库区引水流量","电站平均水头损失"}};
	static String[][] BoundaryConstraints = {{"是否计算到机组","调度期初起算水位"},{"期初机组状态","调度期末控制水位"},
								            {"机组开启顺序","调度期初入库流量"},{"机组关闭顺序","调度期初出库流量"},
								            {"是否计算到闸门","调度期初下游水位"},{"期初闸门状态","防洪库容控制使用量"},
								            {"闸门开启顺序","电站综合出力系数"},{"闸门关闭顺序","平均水量耗水率"},
								            {"水库调度图","平均流量耗水率"},{"出力流量换算方式","出库传播至下级水库耗时"},
								            {"机组检修计划","电站平均负荷率(0~1)"},{"闸门检修计划","出库~尾水位折算偏差"}};
   static String[][] OutputResult = {{"入库流量","调度期初起算水位"},{"出库流量","调度期末控制水位"},
						           {"发电流量","调度期初入库流量"},{"泄洪流量","调度期初出库流量"},
						           {"其他出库流量","调度期初下游水位"},{"库区引水流量","防洪库容控制使用量"},
						           {"时段初水位","电站综合出力系数"},{"时段末水位","平均水量耗水率"},
						           {"平均出力","平均流量耗水率"},{"平均尾水位","出库传播至下级水库耗时"},
						           {"毛水头","电站平均负荷率(0~1)"},{"净水头","出库~尾水位折算偏差"},
						           {"水头损失","调度期初起算水位"},{"入库水量","调度期末控制水位"},
						           {"出库水量","调度期初入库流量"},{"发电水量","调度期初出库流量"},
						           {"泄洪量","调度期初下游水位"},{"其他出库水量","防洪库容控制使用量"},
						           {"库区引水量","电站综合出力系数"},{"时段初库容","平均水量耗水率"},
						           {"时段末库容","平均流量耗水率"},{"发电量","出库传播至下级水库耗时"},
						           {"蓄水流量","电站平均负荷率(0~1)"},{"蓄水量","出库~尾水位折算偏差"}};
public static String[][] getSingleInput() {
	return SingleInput;
}
public static void setSingleInput(String[][] singleInput) {
	SingleInput = singleInput;
}
public static String[][] getProcessInput() {
	return ProcessInput;
}
public static void setProcessInput(String[][] processInput) {
	ProcessInput = processInput;
}
public static String[][] getBoundaryConstraints() {
	return BoundaryConstraints;
}
public static void setBoundaryConstraints(String[][] boundaryConstraints) {
	BoundaryConstraints = boundaryConstraints;
}
public static String[][] getOutputResult() {
	return OutputResult;
}
public static void setOutputResult(String[][] outputResult) {
	OutputResult = outputResult;
}
}
