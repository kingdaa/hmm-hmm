import java.util.ArrayList;
import java.util.List;

class NaryTreeNode {
	int value;
	List<NaryTreeNode> children;

	public NaryTreeNode() {
		children = new ArrayList<>();
	}

	public NaryTreeNode(int value) {
		this.value = value;
		children = new ArrayList<>();
	}

	public void addChild(NaryTreeNode node) {
		children.add(node);
	}

	public int getChildrenCount() {
		return children.size();
	}
}