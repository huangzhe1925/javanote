package com.hz.javanote.thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class WaitTestAtom {
	
		
		private Boolean wait = false;  
		private AtomicBoolean waitAtomic = new AtomicBoolean(false);  
		  
		public boolean pleaseWait() {  
		synchronized (this.wait) {  
		    if (this.wait == true) {  //if changed the wait will be different , so this.wait.wait() will throw exception
		        return false;  
		    }  
		  
		    this.wait =true;  
		      
		    try {  
		        this.wait.wait();  
		    } catch (InterruptedException e) {  
		  
		    }  
		  
		    return true;  
		}  
		}
		
		
		public boolean pleaseWaitAtom() {  
			synchronized (this.waitAtomic) {  
			    if (this.waitAtomic.get()==true) {  
			        return false;  
			    }  
			  
			    this.waitAtomic.set(true);  
			      
			    try {  
			        this.waitAtomic.wait();  
			    } catch (InterruptedException e) {  
			  
			    }  
			  
			    return true;  
			}  
			}
		public static void main(String args[]){
			//System.out.println(new WaitTestAtom().pleaseWait()); 
			System.out.println(new WaitTestAtom().pleaseWaitAtom());
		}
}
