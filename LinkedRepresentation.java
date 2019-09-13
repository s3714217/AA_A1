import java.io.PrintWriter;
import java.util.Arrays;


/**
 * Linked Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016. 
 * @author Yongli Ren, 2019.
 */
public class LinkedRepresentation<T> implements BSPTree<T> {

   
	private T rootNode = null;
	private int NumOfNode = 1;
	private T[]tree; //main array contain Node
	private int[]left; //array of left child reference
	private int[]right; // array of right child reference
	private String inOrder = "";
	private String postOrder = "";
	private String preOrder = "";
	 
    @SuppressWarnings("unchecked")
	public LinkedRepresentation() {
        //initialize all variable
    	
    	tree = (T[]) new Object[NumOfNode];
    	left = new int[NumOfNode];
    	right = new int[NumOfNode];
        rootNode = null;
    } 

    @Override
    public void setRootNode(T nodeLabel) {
    	//set first index in the tree as rootNode
    	
        this.rootNode = nodeLabel;
        tree[0] = this.rootNode;
        
    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        
    	for(int x=0; x < tree.length; x++)
    	{
    		if(srcLabel.equals(tree[x]) == true)
    		{
    			this.NumOfNode += 2; 
    			//increasing the length of 3 arrays
    			tree = Arrays.copyOf(tree, this.NumOfNode);
    			left = Arrays.copyOf(left, this.NumOfNode);
    			right = Arrays.copyOf(right, this.NumOfNode);
    			//add right and left child to the tree
    			tree[2*x +2] = rightChild;
    			tree[2*x +1] = leftChild;
    			
    			//save the index of left and right child to the reference arrays 
    			left[x] = 2*x+1;
    			right[x] = 2*x +2;
    		
    			break;
    		}
    	}
    	
    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {
    	boolean existed = false;
    	for(int x = 0; x < tree.length; x++)
    	{
    		if (nodeLabel.equals(tree[x]))
    		{//check if the node exist in the array
    			existed = true;break; 
    		}
    	}
    	
    	return existed;
    	
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {
    	String message = nodeLabel.toString() + " ";
    	
    	for(int x = 0; x < tree.length; x++)
    	{
    		//check if the giving node is left child
    		if(nodeLabel.equals(tree[left[x]]) == true)
    		{	
    			//trace back and print parent
    			message += tree[x].toString();break;
    		}
    		//check if the giving node is right child
    		else if (nodeLabel.equals(tree[right[x]]) == true)
    		{	
    			//trace back and add parent to printing message
    			message += tree[x].toString();break;
    		}
    		else if (nodeLabel.equals(this.rootNode) == true)
    		{
    			break;
    		}
   
    	}
        return message;
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
    	
    	String message = nodeLabel.toString() + " ";
    	
    	for(int x = 0; x < tree.length; x++)
    	{
    		if (nodeLabel.equals(tree[x]) == true)
    		{
    			//checking if there is left child
    			if(tree[left[x]] != null  && left[x] != 0)
    			{
    				// add left child to printing message
    				message += tree[left[x]].toString() +" ";
    			}
    			//checking if there is right child
    			if(tree[right[x]] != null && right[x] != 0)
    			{
    				// add right child to printing message
    				message += tree[right[x]].toString();
   				}
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
		//reset ordered string
		this.preOrder = "";
    } // end of printInPreorder

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

    private T getRight(T Node)
    {
    	for(int x = 0; x < tree.length; x++)
    	{
    		if(2*x +2 < tree.length)
    		{
    		if(tree[x] == Node)
    		{
    			if(tree[right[x]] != null && right[x] != 0)
    			{
    			  return tree[right[x]];
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
   
    private T getLeft(T Node)
    {
    	for(int x = 0; x < tree.length; x++)
    	{
    		if(2*x +1 < tree.length)
    		{
    		if(tree[x] == Node)
    		{
    			if(tree[left[x]] != null && left[x] != 0)
    			{
    			  return tree[left[x]];
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
    
    private void InPreorder(T Node) 
    {
    	if(Node != null)
    	{//recursive method
    		this.preOrder += Node.toString() + " ";
    		this.InPreorder(this.getLeft(Node));
    		this.InPreorder(this.getRight(Node));
    	}
    }
    
    private void InInOrder (T Node)
    {
    	if(Node != null)
    	{//recursive method
    		this.InInOrder(this.getLeft(Node));
    		this.inOrder += Node.toString() + " ";
    		this.InInOrder(this.getRight(Node));
    	}
    }
    
    private void InPostOrder (T Node)
    {//recursive method
    	if(Node != null)
    	{
    		this.InPostOrder(this.getLeft(Node));
    		this.InPostOrder(this.getRight(Node));
    		this.postOrder += Node.toString() + " ";
    	}
    }
    
} // end of class LinkedRepresentation
