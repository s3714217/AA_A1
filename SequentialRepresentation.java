import java.io.PrintWriter;
import java.util.Arrays;


/**
 * Sequential Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Yongli Ren, 2019.
 */
public class SequentialRepresentation<T> implements BSPTree<T> {

    private int NumOfNode = 1;
    private T rootNode;
	private T[] tree;
	private String inOrder = "";
	private String postOrder = "";
	private String preOrder = "";
	
	
	
	
    @SuppressWarnings("unchecked")
	public SequentialRepresentation() {
        tree = (T[]) new Object[NumOfNode];
        rootNode = null;
    } // end of SequentialRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
    	if (this.rootNode == null)
    	{
    		this.rootNode = nodeLabel;
    		tree[0] = rootNode;
    	}
    	
    	
    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
    
    	for(int x = 0; x < this.NumOfNode; x++)
    	{
    		if(srcLabel.equals(tree[x]))
    		{	
    			this.NumOfNode += 2; 
    			//Increasing the length
    			tree = Arrays.copyOf(tree, this.NumOfNode);
    			tree[2*x +2] = rightChild;
    			tree[2*x +1] = leftChild;
    			//Assign two Child
    			break;
    		} 
    	}
    	
    }

    @Override
    public boolean findNode(T nodeLabel) {
    	
    	boolean existed = false;
    	
    	for(int x = 0; x < tree.length; x++)
    	{//looping through the array
    		if (nodeLabel.equals(tree[x]))
    		{//check if the node exist
    			existed = true;break; 
    		}
    	}
    	
    	return existed;
    	
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {
    	String message = nodeLabel.toString() + " ";
    	
    	for(int x = 1; x < tree.length; x++)
    	{//looping through the array
    	  if (nodeLabel.equals(tree[x]))
    	  {//identify the checkingNode
    		int parentNodeindex;
    		if(x%2 == 0)
    		//checking if the node is rightChild
    			
    		{//trace back parentNode
    			parentNodeindex = (x-2)/2;
    		}
    		else
    		{//trace back parentNode
    			parentNodeindex = (x-1)/2;
    		}
    		
    		//add parentNode to printing message
    		message += tree[parentNodeindex].toString();
    		break;
    	  }
    	}
    	
    	return message;
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
    	
        String message = nodeLabel.toString();
        
        for(int x = 0; x < tree.length; x++)
    	{
    	  if (nodeLabel.equals(tree[x]) && 2*x +2 < tree.length)
    	  {//finding checkingNode
    		  
    		if(tree[2*x +1] != null)
    		{//check for left child
    			message += " " + tree[2*x +1].toString();
    		}
    		if(tree[2*x +2] != null)
    		{//check for right child
    			message += " " + tree[2*x +2].toString();
    		}
    		break;
    	  }
    	}
        return message;
    } // end of findChildren

	@Override
    public void printInPreorder(PrintWriter writer) {
		//sorting array in pre-order
		this.InPreorder(this.rootNode);
		//print ordered string
		writer.print(this.preOrder);
	//	System.out.println(this.preOrder);
		this.preOrder = "";
    }// end of printInPreorder

    @Override
    public void printInInorder(PrintWriter writer) {
    	//sorting array in in-order
    	this.InInOrder(this.rootNode);
    	//print ordered string
		writer.print(this.inOrder);
	//	System.out.println(this.inOrder);
		//reset ordered string
		this.inOrder = "";
    } // end of printInInorder

    @Override
    public void printInPostorder(PrintWriter writer) {
    	//sorting array in post-order
    	this.InPostOrder(this.rootNode);
    	//print ordered string
		writer.print(this.postOrder);
	//	System.out.println(this.postOrder);
		//reset ordered string
		this.postOrder = "";
    } // end of printInPostorder
    
    //helper method to get right child
    private T getRight(T Node)
    {
    	for(int x = 0; x < tree.length; x++)
    	{
    		if(2*x +2 < tree.length)
    		{
    		if(tree[x] == Node)
    		{
    			if(tree[2*x +2] != null)
    			{
    			return tree[2*x +2];
    			}
    			else
    			{
    			return null;
    			}
    		}
    		}
    		else
    		{
    			return null;
    		}
    	}
    	
    	return null;
    }
    //helper method to get left child
    private T getLeft(T Node)
    {
    	for(int x = 0; x < tree.length; x++)
    	{
    		if(2*x +1 < tree.length)
    		{
    			if(tree[x] == Node)
    			{
    				if(tree[2*x +1] != null)
    				{
    					return tree[2*x +1];
    				}
    				else
    				{
    					return null;
    				}
    			}
    		}
    		else
    		{
    			return null;
    		}
    	}
    	
    	return null;
    }
    //helper method to recurs preorder
    private void InPreorder(T Node) 
    {
    	if(Node != null)
    	{	//recursive method
    		this.preOrder += Node.toString() + " ";
    		this.InPreorder(this.getLeft(Node));
    		this.InPreorder(this.getRight(Node));
    	}
    }
    //helper method to recurs inorder
    private void InInOrder (T Node)
    {
    	if(Node != null)
    	{	//recursive method
    		this.InInOrder(this.getLeft(Node));
    		this.inOrder += Node.toString() + " ";
    		this.InInOrder(this.getRight(Node));
    	}
    }
    //helper method to recurs postorder
    private void InPostOrder (T Node)
    {
    	if(Node != null)
    	{	//recursive method
    		this.InPostOrder(this.getLeft(Node));
    		this.InPostOrder(this.getRight(Node));
    		this.postOrder += Node.toString() + " ";
    	}
    }
    
 
} // end of class SequentialRepresentation