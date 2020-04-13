import java.io.PrintWriter;
import java.lang.String;
import java.util.Arrays;

/**
 * Implementation of the Runqueue interface using a Binary Search Tree.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class BinarySearchTreeRQ implements Runqueue {

	private final int INI_SIZE = 1;
	private int noNode=2, tempsize;
	private Proc[] proctree, sortedArr;
	
	
    public BinarySearchTreeRQ() {
        
        proctree = new Proc[noNode];
        proctree[0] = new Proc(null,-1);
        proctree[1] = new Proc(null,-1);
        tempsize = 2;        
    }  // end of BinarySearchTreeRQ()


    @Override
    public void enqueue(String procLabel, int vt) {	
    	/*
    	 * Assign the new proc to bace of the array
    	 * Looping through the array to find the left and right child node
    	 * Assign the left and right child node
    	 * Increase the size of array
    	 * 
    	 */
    	this.proctree[this.noNode-this.INI_SIZE] = new Proc(procLabel, vt);
		
		int checkingPos = 1;
		while(checkingPos != 0)
		{
		  if(this.noNode > 2)
		  {
			if(this.proctree[checkingPos].vt <= vt)
			{
				if(proctree[checkingPos].neighbor2 > 0)
				{
					checkingPos = proctree[checkingPos].neighbor2;
				}
				else
				{
					proctree[checkingPos].neighbor2 = this.noNode - this.INI_SIZE;
					
					break;
					
				}
			}
			else 
			{
				if(proctree[checkingPos].neighbor1 > 0)
				{
					checkingPos = proctree[checkingPos].neighbor1;
				}
				else
				{
					proctree[checkingPos].neighbor1 = this.noNode - this.INI_SIZE;break;
				}
			}
		  }
		  else
		  {
			  break;
		  }
		  
		  
		}
		 this.noNode++;
		  this.proctree = Arrays.copyOf(this.proctree, this.noNode);
		  proctree[this.noNode - this.INI_SIZE] = new Proc(null,-1);
		  
	
    }


    @Override
    public String dequeue() {
    
    	/*
    	 * Sorting the tree
    	 * Add all sorted node to an array
    	 * return the front node of the sorted array 
    	 */
    	String dequeue = null;
    	this.setSortedArr();
    	
    	for(int x =1; x < this.noNode; x++)
    	{
    		if(this.sortedArr[1] == this.proctree[x])
    		{ 
    			dequeue = this.proctree[x].label;
    			this.proctree[x].label = null;
    			this.proctree[x].vt = -1;break;
    		}
    	}
    	
        return dequeue; // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
    	/*
    	 * looping through the array to find the proc
    	 * return true if found the node
    	 */
       
        for(int x=1; x <this.tempsize;x++)
        {
        	if(this.proctree[x] != null &&procLabel.equals(this.proctree[x].label))
        	{
        		return true;
        	}
        }
        
        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
    	/*
    	 * looping through the array to find the proc
    	 * remove the proc
    	 * return true if found the node and removed
    	 */
    	for(int x =1; x < this.noNode; x++)
    	{
    		if(procLabel.equals(this.proctree[x].label))
    		{
    			this.proctree[x].label = null;
    			this.proctree[x].vt = -1;
    			
    			return true;
    		}
    	}
        return false; // placeholder, modify this
    } // end of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
    	
    	/*
    	 * looping through the sorted array of the tree
    	 * find the location of the node
    	 * calculate all vt of previous node
    	 * return total vt
    	 */
    	int totalPt = -1;
    	this.setSortedArr();
    	if(this.findProcess(procLabel))
    	{
    	for(int x =1; x < this.tempsize; x++)
    	{
    		if(this.sortedArr[x] != null )
    		{	
    			
    			
    				  if(procLabel.equals(this.sortedArr[x].label))
    				{
    					  totalPt++;
    					  break;
    				}
    				  
    				  totalPt += this.sortedArr[x].vt;
    			}
    			
    		}
    	}
    		return totalPt;
    	

         // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
    	/*
    	 * looping through the sorted array of the tree
    	 * find the location of the node
    	 * calculate all vt of after node
    	 * return total vt
    	 */
    	int totalPt = 0;
    	this.setSortedArr();
    	
    	for(int x =1; x < this.tempsize; x++)
    	{
    		if(this.sortedArr[x] != null &&procLabel.equals(this.sortedArr[x].label))
    		{	
    			
    			for(int y = 0; y < this.tempsize; y++)
    			{
    				if(x < y && this.sortedArr[x].vt <= this.sortedArr[y].vt)
    				{
    					totalPt += this.sortedArr[y].vt;
    				}
    			}
    			return totalPt;
    		}
    	}

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
    	/*
    	 * looping through the sorted array of the tree
    	 * add all node label to a string
    	 * return a string
    	 */
        String str ="";
    	this.setSortedArr();
     for(int x =1; x < this.noNode; x++)
     {
    	 if(this.sortedArr[x] != null)
    	 {
    		 str += this.sortedArr[x].label + " ";
    		 
    	 }
     }
     os.println(str);
    } // end of printAllProcess()
    
    private void Treesort(int root)
    {/*
       Using tree sort to sort the proctree and add all sorted node to an array
     	*/
    	if(root != 0)
    	{	
    		Treesort(proctree[root].neighbor1);
    		
    		if(this.proctree[root].label != null)
    		{
    			this.sortedArr[tempsize] = this.proctree[root];
    			
    			tempsize++;
    		}
    		Treesort(proctree[root].neighbor2);
    		
    	}
    }
    
    private void setSortedArr()
    {
    	/*
    	 * Initializing the sortedArr
    	 * Calling the recursive Treesort()
    	 */
    	this.tempsize = 1;
    	this.sortedArr= new Proc[this.noNode];
    	this.Treesort(1);
    	
    }
    
    
    
    

} // end of class BinarySearchTreeRQ