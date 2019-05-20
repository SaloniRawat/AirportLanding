// 
// Class:  AirplaneQueue 
// Author: Saloni Rawat
// 
// Description: 
//
// Creates a array of Airplane type. The class has methods to add/remove from queue
//
// See methods for more information.
//
public class AirplaneQueue {

	private Airplane array[];
	private int count, totalTakeoff = 0, totalLanding = 0, totalCrash = 0;
	private int rear;
	private final int CAPACITY = 10;
	private boolean busy;
	// private boolean full;

/////////////////////////////////////////////////////////////////////// 
///   AirplaneQueue()                   	     					/// 
/// Input : no input												/// 
/// Description: initializes the data for the takeoff and landing 	///
/// queue 															///
///////////////////////////////////////////////////////////////////////

	// @SuppressWarnings("unchecked")
	public AirplaneQueue() {
		array = new Airplane[100];
		// full = false;

//		for (int i = 0; i < CAPACITY; i++) {
//
//			Airplane temp = new Airplane();
//			// String s = "Plane: "+ i;
//			// System.out.println(" Temp");
//			temp.setId("Null", "Null");
//			// System.out.println(temp.getId());
//			temp.setData(0, 0);
//			temp.setStatus(" null");
//			temp.setWaitingTime(0);
//
//			array[i] = temp;
//		}

	}

/////////////////////////////////////////////////////////////////////////////// 
///   AirplaneQueue(int,int,int)              								/// 
/// Input : Landing time, take off time, waiting time						/// 
/// Description: initializes and assigns data for the takeoff and landing 	///
/// queue with the landing, take off and waiting time 						///
///////////////////////////////////////////////////////////////////////////////

	// @SuppressWarnings("unchecked")
	public AirplaneQueue(int landingTime, int takeoffTime, int waitingTime, String name) {
		array = new Airplane[100];

		// Intialise the objects for array

/////////////////////////////////////////////////////////////////// 
///   Adds 10 planes to the queue								///
///////////////////////////////////////////////////////////////////
		for (count = 0; count < CAPACITY; count++) {

			Airplane temp = new Airplane();
			String s = "Plane: " + count;
			// System.out.println(" Temp");
			temp.setId(s, name);
			// System.out.println(temp.getId());
			temp.setData(landingTime, takeoffTime);

			temp.setWaitingTime(waitingTime);
			temp.setStartTime(0);

			array[count] = temp;
		}

	}

/////////////////////////////////////////////////////////////////// 
///   returnLength()	                      					/// 
/// Input : No input needed						   				/// 
/// Output: no output 											///
/// Returns the length of the list							   	///
///////////////////////////////////////////////////////////////////

	public int returnLength() {
		return count;

	}

/////////////////////////////////////////////////////////////////// 
///   toString()	         		    						/// 
/// Input : No input needed						   				/// 
/// Output: no output 											///
/// Returns the entire list										///
///////////////////////////////////////////////////////////////////

// to Display the entire Linked List
	public String toString() {
		String rtn = "";

////////////////////////////////////////////////////////////////////////
///   concats all the words and their counts into a single	string	///
//////////////////////////////////////////////////////////////////////
		if (count == 0) {
			System.out.println("Queue is empty");
		} else {
			if (count < array.length) {
				for (int i = 0; i < count - 1; i++) {
					System.out.println("Array length is: " + array.length);
					System.out.println("Count is: " + count);
					rtn = rtn + "Id: " + array[i].getId();
					System.out.println("i: " + i);
					System.out.println("array id: " + array[i].getId());
					System.out.println(rtn);
				}
			} else {
				for (int i = 0; i < array.length; i++) {
					System.out.println("Array length is: " + array.length);
					System.out.println("Count is: " + count);
					rtn = rtn + "Id: " + array[i].getId();
					System.out.println("i: " + i);
					System.out.println("array id: " + array[i].getId());
					System.out.println(rtn);
				}
			}
		}
		return rtn;

	}

/////////////////////////////////////////////////////////////////////// 
///   removeFromQueue(int)                     						/// change for Take off only
/// Input : current time in simulation			   					/// 
/// Output: if there are no planes, error message will be shown		///
/// Returns the plane that has been removed from the queue	   		///
///////////////////////////////////////////////////////////////////////

	public Airplane removeFromQueue(int curTime, String name) {

		Airplane temp = new Airplane();
		int i = 0;

		if (count == 0) {
			System.out.println("There are no planes in the queue");
		} else {
			for (i = 0; i < count - 1; i++) {

				temp = array[i];

////////////////////////////////////////////////////////////////////////////
///   check if the waiting time has exceeded, if yes crash the plane	///
//////////////////////////////////////////////////////////////////////////

				if (name.equals("Take off") && temp.isCrashed(curTime)) {
					System.out.println("The plane has run out of fuel. " + " Recorded.");
					addToTotalCrash();
					System.out.println("Total number of Crashes: " + getTotalCrash());
					Airplane deleted = remove();

				} else {

////////////////////////////////////////////////////////////////////////
///   if no, remove the plane from the top of the queue				///
//////////////////////////////////////////////////////////////////////

					
					temp.setEndTime(curTime);
					//return runway;
					break;
				}
			}
		}

		return temp;

	}

/////////////////////////////////////////////////////////////////// 
///   add(data to be added)		 			   					/// 
/// Input : Current plane(Airplane), queue name(String)			/// 
/// Output: no output 											///
/// Returns nothing												///
/// Description: adds the new plane to the queue				///
///////////////////////////////////////////////////////////////////	

	public void add(Airplane temp, String queueName) {

///////////////////////////////////////////////////////////////////////////
///   if # of planes exceeds the queue, display error					///
///////////////////////////////////////////////////////////////////////////
		if (count >= array.length) {

			System.out.println("Plane cannot be added. Queue full");

		}

		array[count] = temp;
		count++;

		if (count == 0) {
			//front = 0;
			rear = 0;

		}

		else
			rear = rear + 1;

		array[rear] = temp;
		System.out.println("Count was: " + count);
		count++;
		System.out.println("New Count is: " + count);
	}

/////////////////////////////////////////////////////////////////// 
///   remove()					 			   					/// 
/// Input : no input 											/// 
/// Output: no output 											///
/// Returns the removed plane									///
/// Description: removes the plane from the queue				///
///////////////////////////////////////////////////////////////////

	public Airplane remove() {

		Airplane temp = new Airplane();
		// check if any planes in the queue
		if (returnLength() == 0) {
			System.out.println("No planes in the queue");
		}

		System.out.println("count was: " + count);
		// loop through the array
		for (int i = 0; i < (count - 1); i++) {
			// assign the first item to temp
			// move all the elements to one position earlier
///////////////////////////////////////////////////////////////////////////
///   assign the first item to temp then move all the elements to one	///
///    position earlier 												///
///////////////////////////////////////////////////////////////////////////
			if (i == 0) {

			} else {
				temp = array[(i + 1)];
				array[i] = temp;

				if (temp == null) {
					System.out.println("Temp:" + null);
				} else {

					System.out.println("Temp:" + temp.getId());
				}
			}
		}
		count--;
		return temp;
	}

/////////////////////////////////////////////////////////////////// 
///   setBusy(boolean)						  					/// 
/// Input : if the runway is busy						 		///
/// Output: no output 											///
/// Returns nothing											   	///
///////////////////////////////////////////////////////////////////
	
	public void setBusy(boolean busy) {
		this.busy = busy;
	}

/////////////////////////////////////////////////////////////////// 
///   returnBusy()							  					/// 
/// Input : no input											///
/// Output: no output 											///
/// Returns if the queue is busy or not						   	///
///////////////////////////////////////////////////////////////////

	public boolean returnBusy() {
		return busy;
	}

/////////////////////////////////////////////////////////////////// 
///   addToTotalTakeoff()					  					/// 
/// Input : no input									 		///
/// Output: no output 											///
/// Returns nothing 											///
/// description - increments the total take off 				///
///////////////////////////////////////////////////////////////////

	public void addToTotalTakeoff() {
		totalTakeoff++;
	}

/////////////////////////////////////////////////////////////////// 
///   getTotalTakeoff()						  					/// 
/// Input : no input											///
/// Output: no output 											///
/// Returns total take off 									   	///
///////////////////////////////////////////////////////////////////
	public int getTotalTakeoff() {
		return totalTakeoff;
	}

/////////////////////////////////////////////////////////////////// 
///   addToTotalLanding()					  					/// 
/// Input : no input									 		///
/// Output: no output 											///
/// Returns nothing 											///
/// description - increments the total landings 				///
///////////////////////////////////////////////////////////////////
	public void addToTotalLanding() {
		totalLanding++;
	}

/////////////////////////////////////////////////////////////////// 
///   getTotalLanding()						  					/// 
/// Input : no input											///
/// Output: no output 											///
/// Returns the total landings								   	///
///////////////////////////////////////////////////////////////////
	
	public int getTotalLanding() {
		return totalLanding;
	}

/////////////////////////////////////////////////////////////////// 
///   getTotalCrash()						  					/// 
/// Input : no input											///
/// Output: no output 											///
/// Returns the total crashes								   	///
///////////////////////////////////////////////////////////////////
	
	public int getTotalCrash() {
		return totalCrash;
	}

/////////////////////////////////////////////////////////////////// 
///   addToTotalCrash()						  					/// 
/// Input : no input									 		///
/// Output: no output 											///
/// Returns nothing 											///
/// description - increments the total crash	 				///
///////////////////////////////////////////////////////////////////
	
	public void addToTotalCrash() {
		totalCrash++;

	}

}