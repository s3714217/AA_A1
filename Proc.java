
public class Proc {

	public String label = null;
	public int vt = -1;
	public int neighbor1 = 0;//for BST and linkedlist only
	public int neighbor2=0;//for BST and linkedlist only
	public int parent=0;//for BST only
	
	public Proc(String label, int vt)
	{
		this.label = label;
		this.vt = vt;
	}
}
