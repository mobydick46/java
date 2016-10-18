package org.csw.service.baseinfo.impl;

import java.util.Comparator;

import org.csw.entity.baseinfo.DecisionPlan;

public class MyCmp implements Comparator<DecisionPlan>{

	@Override
	public int compare(DecisionPlan plan1, DecisionPlan plan2) {
		int x= 0;
		x = Integer.valueOf(plan1.getNumber()) > Integer.valueOf(plan2.getNumber()) ? 1 : -1;
		if(Integer.valueOf(plan1.getNumber()) == Integer.valueOf(plan2.getNumber())){
			x= 0;
		}
		return x;
	}

}
