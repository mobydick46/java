package org.csw.entity.baseinfo;
/*
 * 工程项目 机组状态/闸门状态类
 */

public class UnitState {
	
	String id;
	
	String stateDefinition;
	
	String stateID;
	
	String stateName;

	@Override
	public String toString() {
		return "UnitState [id=" + id + ", stateDefinition=" + stateDefinition + ", stateID=" + stateID + ", stateName="
				+ stateName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((stateDefinition == null) ? 0 : stateDefinition.hashCode());
		result = prime * result + ((stateID == null) ? 0 : stateID.hashCode());
		result = prime * result + ((stateName == null) ? 0 : stateName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitState other = (UnitState) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stateDefinition == null) {
			if (other.stateDefinition != null)
				return false;
		} else if (!stateDefinition.equals(other.stateDefinition))
			return false;
		if (stateID == null) {
			if (other.stateID != null)
				return false;
		} else if (!stateID.equals(other.stateID))
			return false;
		if (stateName == null) {
			if (other.stateName != null)
				return false;
		} else if (!stateName.equals(other.stateName))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStateDefinition() {
		return stateDefinition;
	}

	public void setStateDefinition(String stateDefinition) {
		this.stateDefinition = stateDefinition;
	}

	public String getStateID() {
		return stateID;
	}

	public void setStateID(String stateID) {
		this.stateID = stateID;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}
