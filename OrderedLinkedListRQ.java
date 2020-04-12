import java.io.PrintWriter;
import java.lang.String;
import java.util.Arrays;

/**
 * Implementation of the run queue interface using an Ordered Link List.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan.
 */
public class OrderedLinkedListRQ implements Runqueue {
	
	private int size;
	//private String procList[];
	private int front[], back[];
	//private int procVT[];
	private Proc procArr[];
	private final int INI_SIZE = 1;
	
    
    public OrderedLinkedListRQ() {
    	size = 2;
        procArr = new Proc[size];
    	front = new int[size];
    	back = new int[size];
    }  // end of OrderedLinkedList()


    @Override
    public void enqueue(String procLabel, int vt) {
        
    	//Assign the proc and its vt
    	this.procArr[this.size-this.INI_SIZE] = new Proc(procLabel,vt);
		
		
		int checkingPos = 1;
		while(checkingPos != 0)
		{
		 if(this.size > 2)
		 {
		  if(this.procArr[checkingPos] != null )
		  {
			if(this.procArr[checkingPos].vt <= vt)
			{
				
				
			  if(back[checkingPos] != 0)
			  {
				if(vt < this.procArr[back[checkingPos]].vt )
				{
					this.front[this.size - this.INI_SIZE] = checkingPos;
					this.back[this.size - this.INI_SIZE] = back[checkingPos];
					this.front[this.back[checkingPos]] = this.size - this.INI_SIZE;
					this.back[checkingPos] = this.size - this.INI_SIZE;break;
					
				}
				else 
				{
					checkingPos = this.back[checkingPos];
				}
			  }
			  else
			  {
				  this.front[this.size - this.INI_SIZE] = checkingPos;
				  this.back[this.size - this.INI_SIZE] = this.back[checkingPos];
				  this.back[checkingPos] = this.size - this.INI_SIZE;break;
			  }
			  
			  
			  
			}
			else if(this.procArr[checkingPos].vt > vt)
			{
				
				
				
			  if(front[checkingPos] != 0)
			  {
				if(vt >= this.procArr[this.front[checkingPos]].vt)
				{
					this.front[this.size-this.INI_SIZE] = this.front[checkingPos];
					this.back[this.size - this.INI_SIZE] = checkingPos;
					this.back[this.front[checkingPos]] = this.size - this.INI_SIZE;
					this.front[checkingPos] = this.size - this.INI_SIZE;break;
					
				}
				else
				{
					checkingPos = this.front[checkingPos];
				}
			  }
			  else 
			  {
				
				this.back[this.size - this.INI_SIZE] = checkingPos;
				this.front[this.size-this.INI_SIZE] = this.front[checkingPos];
				this.front[checkingPos] = this.size - this.INI_SIZE;break;
			  }
			  
			  
			  
			  
		  }
		 }
		  else {checkingPos++;}
		 }
		  else
		  {
			  checkingPos = 0;break;
		  }
		}
		
		this.size++;

		//Expanding the array    
		
	
		this.procArr = Arrays.copyOf(this.procArr, this.size);
		this.front = Arrays.copyOf(this.front, this.size);
		this.back = Arrays.copyOf(this.back, this.size);
		

    } // end of enqueue()


    @Override
    public String dequeue() {
        
    	Proc dequeue = null;
    	for(int x = 1; x < this.size; x++)
    	{
    		if (this.front[x] == 0 && this.procArr[x] != null)
    		{
    			dequeue = this.procArr[x];
    			this.procArr[x] = null;
    			this.front[this.back[x]] = 0;
    			this.back[x] = 0;break;
    			
    		}
    	}
 
        return dequeue.label; // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
    	boolean existed = false;
    
        for(Proc x : this.procArr)
        {
        	if(x != null && x.label.equals(procLabel))
        	{
        		existed = true;
        	}
        }
        return existed;
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
    	boolean removed = false;
        
        for(int x = 1; x< this.size;x++)
        {
        	if(this.procArr[x] != null && this.procArr[x].label.equals(procLabel))
        	{
        		
        		this.procArr[x] = null;
        		this.front[this.back[x]] = this.front[x];
        		this.back[this.front[x]] = this.back[x];
        		this.front[x] = 0;
        		this.back[x] = 0;
        		removed = true;break;
        		
        	}
        }
       

        return removed;// placeholder modify this
    } // End of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        
    	int TotalPT = -1;
    	for (int x = 1; x < this.size; x++)
    	{
    		if(this.procArr[x] != null && this.procArr[x].label.equals(procLabel))
    		{
    			TotalPT++;
    			int checkingPos = this.front[x];
    			while(checkingPos !=0)
    			{
    				TotalPT += this.procArr[checkingPos].vt;
    				checkingPos = this.front[checkingPos];
    			}
    			break;
    		}
    	}

        return TotalPT; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
    	int TotalPT = -1;
    	for (int x = 1; x < this.size; x++)
    	{
    		if(this.procArr[x] != null && this.procArr[x].label.equals(procLabel))
    		{
    			TotalPT++;
    			int checkingPos = this.back[x];
    			while(checkingPos !=0)
    			{
    				TotalPT += this.procArr[checkingPos].vt;
    				checkingPos = this.back[checkingPos];
    			}
    			break;
    		}
    	}

        return TotalPT;
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
     
    	String AllProc = "";
    	for (int x = 1; x < this.size; x++)
    	{
    		if(this.procArr[x] != null)
    		{
    			if(this.front[x] == 0)
    			{
    				int checkingPos = x;
        			while(checkingPos != 0)
        			{
        				AllProc += this.procArr[checkingPos].label + " ";
        				checkingPos = this.back[checkingPos];
        			}
        			break;
    			}
    		}
    	}
    	
    	
    	
   
    	os.println(AllProc);
    	
    	
    	
    } // end of printAllProcess()

} // end of class OrderedLinkedListRQ
