/**
You are given two non-empty linked lists representing two 
non-negative integers. The digits are stored in reverse 
order and each of their nodes contain a single digit. Add 
the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading 
zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

@author: Christopher Gervacio Chico
@date:   03/29/2019

*/

public class ListNodeCommunication 
{
    public static void main(String[] args) throws Exception 
    {
        ListNode e1 = new ListNode(2);
        ListNode e2 = new ListNode(4);
        ListNode e3 = new ListNode(3);
        ListNode e4 = new ListNode(5);
        ListNode e5 = new ListNode(6);
        ListNode e6 = new ListNode(4);
        ListNode e7 = new ListNode(7);
        ListNode e8 = new ListNode(0);
        ListNode e9 = new ListNode(8);
        
        e1.NextNode = e2;
        e2.NextNode = e3;
        e4.NextNode = e5;
        e5.NextNode = e6;
        e7.NextNode = e8;
        e8.NextNode = e9;
        
        //e7 is the expected answer
        
        ListNode result = null;
        
        //Execution
        result = addTwoNumbers(e1, e4);
        
        //Final comparison
        if( CompareExpectedToActual(e7, result) )
        {
            System.out.println("Result matches!");
        }
        else
        {
            System.out.println("Result does not match..."); 
        }
    }
    
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode CurrentNode1 = l1;
        ListNode CurrentNode2 = l2;
        int      CountL1      = GetLengthOfNode(CurrentNode1);
        int      CountL2      = GetLengthOfNode(CurrentNode2);
        int      FirstAdd     = BuildAndInt(CurrentNode1, CountL1);
        int      SecondAdd    = BuildAndInt(CurrentNode2, CountL2);
        int      ResultSum    = FirstAdd + SecondAdd;
        return ReturnIntToListNode(ResultSum);
    }
    
    private static int BuildAndInt(ListNode node, int count)
    {
        String   Load        = "";
        int      subcount    = count;
        ListNode CurrentNode = node;
        ListNode Next        = null;
        
        while( count > 0 )
        {
            while( subcount > 1 )
            {
                Next        = CurrentNode.NextNode;
                CurrentNode = Next;
                subcount--;
            }
            
            //End of the line for subcount, grab node value
            Load = Load + Integer.toString(CurrentNode.Value);
            //If you notice, this is forming the new number in mirrored fashion
            
            //Get ready for the next loop
            count--;
            subcount    = count;
            CurrentNode = node;
            Next        = null;
        }
        
        //Convert Load to int and return
        return Integer.valueOf(Load);
    }
    
    private static ListNode ReturnIntToListNode(int num)
    {
        String     Load         = Integer.toString(num);
        ListNode[] ListNodeBank = new ListNode[Load.length()];
        
        //Build the ListNodes
        for( int i = Load.length() - 1; i > -1; i-- )
        {
            ListNode e      = new ListNode( Integer.parseInt(String.valueOf( Load.charAt(i) )) );
            ListNodeBank[i] = e;
        }
        
        //Linking the ListNodes
        for( int i = ListNodeBank.length - 1; i > 0; i-- )
        {
            ListNodeBank[i].NextNode = ListNodeBank[i - 1];
        }

        return ListNodeBank[ListNodeBank.length -1];
    }
    
    private static boolean CompareExpectedToActual(ListNode l1, ListNode l2)
    {
        ListNode CurrentNode1 = l1;
        ListNode CurrentNode2 = l2;
        ListNode NextNode1    = null;
        ListNode NextNode2    = null;
        
        //First off the two ListNodes should have equal length
        if( GetLengthOfNode(CurrentNode1) != GetLengthOfNode(CurrentNode2) )
        {
            return false;
        }
        
        //They're equal length?  Good, now compare the values
        
        while( CurrentNode1.NextNode != null )
        {
            if( CurrentNode1.Value != CurrentNode2.Value )
            {
                return false;
            }
            else
            {
                NextNode1    = CurrentNode1.NextNode;
                NextNode2    = CurrentNode2.NextNode;
                CurrentNode1 = NextNode1;
                CurrentNode2 = NextNode2;
            }
        }
        
        //Comparison passes
        return true;
    }
    
    private static int GetLengthOfNode(ListNode node)
    {
        int Count            = 0;
        ListNode CurrentNode = node;
        ListNode Next        = null;
        while( CurrentNode != null )
        {
            Next        = CurrentNode.NextNode;
            CurrentNode = Next;
            Count++;
        }
        
        return Count;
    }
}