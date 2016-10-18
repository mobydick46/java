package org.csw.entity.baseinfo;
/*
 * 工程项目 数据连接类
 */

public class DataConnection {
	
	String id;
	
	String Name;
	
	String Content;
	
	String Remark;

	@Override
	public String toString() {
		return "BasicAttribute [id=" + id + ", Name=" + Name + ", Content=" + Content + ", Remark=" + Remark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Content == null) ? 0 : Content.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((Remark == null) ? 0 : Remark.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DataConnection other = (DataConnection) obj;
		if (Content == null) {
			if (other.Content != null)
				return false;
		} else if (!Content.equals(other.Content))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (Remark == null) {
			if (other.Remark != null)
				return false;
		} else if (!Remark.equals(other.Remark))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}
}
