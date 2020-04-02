import java.io.PrintWriter;
import java.util.Random;

public class ImplementationTesting {

	public static void main(String[] args) {
		
		PrintWriter print = new PrintWriter(System.out);
		final int STR = 1000;
		Random rand = new Random();

		OrderedArrayRQ AR = new OrderedArrayRQ();
		OrderedLinkedListRQ LL = new OrderedLinkedListRQ();
        BinarySearchTreeRQ BST = new BinarySearchTreeRQ();
		
        for(int x = 1; x < STR; x++)
        {
        	int randx = rand.nextInt(STR);
        	int randy = rand.nextInt(10);
        	String proc = "P"+x;
        	int vt = randy;
        	AR.enqueue(proc, vt);
        	LL.enqueue(proc, vt);
        	BST.enqueue(proc, vt);
        }
        
        print.println("OARQ: ");
        AR.printAllProcesses(print);
        print.println("LLRQ: ");
        LL.printAllProcesses(print);
        print.println("BST RQ: ");
        BST.printAllProcesses(print);
       
        
       /* LL.enqueue("P1", 5);
        LL.enqueue("P2", 4);
        LL.enqueue("P3", 3);
        LL.enqueue("P4", 2);
        LL.enqueue("P5", 1);
        LL.printAllProcesses(print);*/
        
		print.flush();
		print.close();
		
		
	
		
		
	}}