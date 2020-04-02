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
	private String procList[];
	private int front[], back[];
	private int procVT[];
	private final int INI_SIZE = 1;
	
    
    public OrderedLinkedListRQ() {
    	size = 2;
        procList = new String[size];
    	procVT = new int[size];
    	front = new int[size];
    	back = new int[size];
    }  // end of OrderedLinkedList()


    @Override
    public void enqueue(String procLabel, int vt) {
        
    	//Assign the proc and its vt
    	this.procList[this.size-this.INI_SIZE] = procLabel;
		this.procVT[this.size-this.INI_SIZE] = vt;
		
		int checkingPos = 1;
		while(checkingPos != 0)
		{
		 if(this.size > 2)
		 {
		  if(this.procList[checkingPos] != null)
		  {
			if(this.procVT[checkingPos] <= vt)
			{
				
				
			  if(back[checkingPos] != 0)
			  {
				if(vt < this.procVT[back[checkingPos]])
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
			else if(this.procVT[checkingPos] > vt)
			{
				
				
				
			  if(front[checkingPos] != 0)
			  {
				if(vt >= this.procVT[this.front[checkingPos]])
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
		
		this.procVT = Arrays.copyOf(this.procVT, this.size);
		this.procList = Arrays.copyOf(this.procList, this.size);
		this.front = Arrays.copyOf(this.front, this.size);
		this.back = Arrays.copyOf(this.back, this.size);
		

    } // end of enqueue()


    @Override
    public String dequeue() {
        
    	String dequeue = null;
    	for(int x = 1; x < this.size; x++)
    	{
    		if (this.front[x] == 0 && this.procList[x] != null)
    		{
    			dequeue = this.procList[x];
    			this.procList[x] = null;
    			this.procVT[x] = 0;
    			this.front[this.back[x]] = 0;
    			this.back[x] = 0;break;
    			
    		}
    	}
 
        return dequeue; // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
    	boolean existed = false;
    
        for(String x : this.procList)
        {
        	if(x != null && x.equals(procLabel))
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
        	if(this.procList[x] != null && this.procList[x].equals(procLabel))
        	{
        		
        		this.procList[x] = null;
        		this.procVT[x] = 0;
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
    		if(this.procList[x] != null && this.procList[x].equals(procLabel))
    		{
    			TotalPT++;
    			int checkingPos = this.front[x];
    			while(checkingPos !=0)
    			{
    				TotalPT += this.procVT[checkingPos];
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
    		if(this.procList[x] != null && this.procList[x].equals(procLabel))
    		{
    			TotalPT++;
    			int checkingPos = this.back[x];
    			while(checkingPos !=0)
    			{
    				TotalPT += this.procVT[checkingPos];
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
    		if(this.procList[x] != null)
    		{
    			if(this.front[x] == 0)
    			{
    				int checkingPos = x;
        			while(checkingPos != 0)
        			{
        				AllProc += this.procList[checkingPos] + " ";
        				checkingPos = this.back[checkingPos];
        			}
        			break;
    			}
    		}
    	}
    	
    	
    	
   /* 	
    	String Allproc = "";
    	
    	for(int x = 0 ; x < this.size; x++)
    	{
    		if(this.procList[x] != null)
    		{
    			Allproc += this.procList[x] + " ";
    			Allproc += "front: " + this.front[x] + " ";
    			Allproc += "back: " +this.back[x] + " " + '\n';
    		}
    	}
    	*/
    	os.println(AllProc);
    	
    	
    	
    } // end of printAllProcess()

} // end of class OrderedLinkedListRQ
