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
	private String[] proctree, sortedArr;
	private int[] left, right, vttree, sortedVt;
	
    public BinarySearchTreeRQ() {
        vttree = new int[noNode];
        proctree = new String[noNode];
        left = new int[noNode];
        right = new int[noNode];
        tempsize = 2;        
    }  // end of BinarySearchTreeRQ()


    @Override
    public void enqueue(String procLabel, int vt) {	
    	
    	this.proctree[this.noNode-this.INI_SIZE] = procLabel;
		this.vttree[this.noNode-this.INI_SIZE] = vt;
		
		int checkingPos = 1;
		while(checkingPos != 0)
		{
		  if(this.noNode > 2)
		  {
			if(this.vttree[checkingPos] <= vt)
			{
				if(right[checkingPos] > 0)
				{
					checkingPos = right[checkingPos];
				}
				else
				{
					right[checkingPos] = this.noNode - this.INI_SIZE;
					
					break;
					
				}
			}
			else 
			{
				if(left[checkingPos] > 0)
				{
					checkingPos = left[checkingPos];
				}
				else
				{
					left[checkingPos] = this.noNode - this.INI_SIZE;break;
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
		  this.vttree = Arrays.copyOf(this.vttree, this.noNode);
		  this.left = Arrays.copyOf(left, noNode);
		  this.right = Arrays.copyOf(right, noNode);
	
    }


    @Override
    public String dequeue() {
    	String dequeue = null;
    	this.setSortedArr();
    	for(int x =0; x < this.noNode; x++)
    	{
    		if(this.sortedArr[1] == this.proctree[x])
    		{
    			dequeue = this.proctree[x];
    			this.proctree[x] = null;
    			this.vttree[x] = -1;break;
    		}
    	}
    	
        return dequeue; // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        this.setSortedArr();
        for(int x=0; x <this.tempsize;x++)
        {
        	if(procLabel.equals(this.sortedArr[x]))
        	{
        		return true;
        	}
        }
        
        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        
    	for(int x =0; x < this.noNode; x++)
    	{
    		if(procLabel.equals(this.proctree[x]))
    		{
    			this.proctree[x] = null;
    			this.vttree[x] = -1;
    			return true;
    		}
    	}
        return false; // placeholder, modify this
    } // end of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
    	int totalPt = 0;
    	this.setSortedArr();
    	for(int x =0; x < this.tempsize; x++)
    	{
    		if(procLabel.equals(this.sortedArr[x]))
    		{	
    			
    			for(int y = 0; y < this.tempsize; y++)
    			{
    				if(x > y && this.sortedVt[x] > this.sortedVt[y])
    				{
    					totalPt += this.sortedVt[y];
    				}
    			}
    			return totalPt;
    		}
    	}

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
    	int totalPt = 0;
    	this.setSortedArr();
    	for(int x =0; x < this.tempsize; x++)
    	{
    		if(procLabel.equals(this.sortedArr[x]))
    		{	
    			
    			for(int y = 0; y < this.tempsize; y++)
    			{
    				if(x < y && this.sortedVt[x] <= this.sortedVt[y])
    				{
    					totalPt += this.sortedVt[y];
    				}
    			}
    			return totalPt;
    		}
    	}

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        
    	this.setSortedArr();
     for(int x =0; x < this.noNode; x++)
     {
    	 if(this.sortedArr[x] != null)
    	 {
    		 os.print(this.sortedArr[x] + " ");
    		 
    	 }
     }
     os.print('\n');
    } // end of printAllProcess()
    
    private void Treesort(int root)
    {
    	if(root != 0)
    	{	
    		Treesort(left[root]);
    		if(this.proctree[root] != null)
    		{
    			this.sortedArr[tempsize] = this.proctree[root];
    			this.sortedVt[tempsize] = this.vttree[root];
    			tempsize++;
    		}
    		Treesort(right[root]);
    		
    	}
    }
    
    private void setSortedArr()
    {
    	this.tempsize = 1;
    	this.sortedArr= new String[this.noNode];
    	this.sortedVt = new int[this.noNode];
    	this.Treesort(tempsize);
    }
    
    
    
    

} // end of class BinarySearchTreeRQ
