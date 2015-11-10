package test;

import java.util.List;

public class TreeNode {
	/*id：节点ID，对加载远程数据很重要。
	text：显示节点文本。
	state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
	checked：表示该节点是否被选中。
	attributes: 被添加到节点的自定义属性。
	children: 一个节点数组声明了若干节点。*/
	
	private String id;
	private String parindId;
	private String text;
	private String state;
	private String checked;
	private String attributes;
	private List<TreeNode> children;
	
	public TreeNode(String id, String parindId, String text, String state,
			String checked, String attributes) {
		super();
		this.id = id;
		this.parindId = parindId;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
	}
	
	public TreeNode() {
		super();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	public String getParindId() {
		return parindId;
	}
	public void setParindId(String parindId) {
		this.parindId = parindId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((checked == null) ? 0 : checked.hashCode());
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((parindId == null) ? 0 : parindId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		TreeNode other = (TreeNode) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (checked == null) {
			if (other.checked != null)
				return false;
		} else if (!checked.equals(other.checked))
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parindId == null) {
			if (other.parindId != null)
				return false;
		} else if (!parindId.equals(other.parindId))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", parindId=" + parindId + ", text="
				+ text + ", state=" + state + ", checked=" + checked
				+ ", attributes=" + attributes + "]";
	}
}
