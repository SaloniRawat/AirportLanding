// 
// Class:  Airplane
// Author: Saloni Rawat
// 
// Description: 
// Creates a Airplane object (to be used in AirplaneQueue) which can 
// stores the time taken to land, take off, id, waiting time, start, and end time.
// The class also contains setters and getters for landing/waiting/Take off time and id, start and end time
// 
// See methods for more information.
//

public class Airplane {
	private int landingTime;
	private int takeoffTime;
	//private Airplane next;
	//private String status;
	private String id;
	private int waitingTime;
	private int startTime;
	private int endTime;
	
/////////////////////////////////////////////////////////////////// 
///   getLandingTime()                         					/// 
/// Input : No input needed						   				/// 
/// Output: no output 											///
/// Returns the time taken to land							   	///
///////////////////////////////////////////////////////////////////

	public int getLandingTime() {
		return landingTime;
	}
	
/////////////////////////////////////////////////////////////////// 
///   getTakeOffTime()	                       					/// 
/// Input : No input needed						   				/// 
/// Output: no output 											///
/// Returns the time to take off							   	///
///////////////////////////////////////////////////////////////////

	public int getTakeOffTime() {
		return takeoffTime;
	}

/////////////////////////////////////////////////////////////////// 
///   setData(int, int)						  					/// 
/// Input : the landing and take off time for the plane 		///
/// Output: no output 											///
/// Returns nothing 										   	///
///////////////////////////////////////////////////////////////////
	
	public void setData(int landingTime, int takeoffTime) {
		this.landingTime = landingTime;
		this.takeoffTime = takeoffTime;
	}

/////////////////////////////////////////////////////////////////// 
///   getWaitingTime()	                       					/// 
/// Input : No input needed						   				/// 
/// Output: no output 											///
/// Returns the maximum waiting time in the queue			   	///
///////////////////////////////////////////////////////////////////

	public int getWaitingTime() {
		return waitingTime;
	}

/////////////////////////////////////////////////////////////////// 
///   setWaitingTime(int)					  					/// 
/// Input : the maximum waiting in the queue			 		///
/// Output: no output 											///
/// Returns nothing 										   	///
///////////////////////////////////////////////////////////////////
	
	
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

/////////////////////////////////////////////////////////////////// 
///   getId()			                       					/// 
/// Input : No input needed						   				/// 
/// Output: no output 											///
/// Returns the id of the plane								   	///
///////////////////////////////////////////////////////////////////
	
	public String getId() {
		return id;
	}

/////////////////////////////////////////////////////////////////// 
///   setId(String, String)					  					/// 
/// Input : the id and type of plane - landing/takeoff	 		///
/// Output: no output 											///
/// Returns nothing 										   	///
///////////////////////////////////////////////////////////////////
	public void setId(String id, String name) {
		this.id = id+name;
	}
	
/////////////////////////////////////////////////////////////////// 
///   setStartTime(int)						  					/// 
/// Input : the time when plane joins the queue					///
/// Output: no output 											///
/// Returns nothing 										   	///
///////////////////////////////////////////////////////////////////

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
/////////////////////////////////////////////////////////////////// 
///   getStartTime()	                       					/// 
/// Input : No input needed						   				/// 
/// Output: no output 											///
/// Returns the time the plane was added to the queue		   	///
///////////////////////////////////////////////////////////////////
		
	public int getStartTime() {
		return startTime;
	}

/////////////////////////////////////////////////////////////////// 
///   setEndTime(int)						  					/// 
/// Input : the time the plane leaves the queue					///
/// Output: no output 											///
/// Returns nothing 										   	///
///////////////////////////////////////////////////////////////////
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
/////////////////////////////////////////////////////////////////// 
///   getEndTime(	)	                       					/// 
/// Input : No input needed						   				/// 
/// Output: no output 											///
/// Returns the time the plane leaves the queue				   	///
///////////////////////////////////////////////////////////////////
	
	public int getEndTime() {
		return endTime;
	}
	
/////////////////////////////////////////////////////////////////// 
///   isCrashed(int)	                       					/// 
/// Input : current time						   				/// 
/// Output: no output 											///
/// Returns true if the plane is crashed					   	///
///////////////////////////////////////////////////////////////////
	
	public boolean isCrashed(int curTime) {

		if (curTime >= (startTime + waitingTime)) {
			System.out.println("Testing isCrashed()");
			System.out.println("CurTime : " + curTime);
			System.out.println("Start Time :" + startTime);
			System.out.println("Waiting Time: " + waitingTime);
			System.out.println("Plane Crashed");

			return true;
		} else {
			System.out.println("Testing isCrashed()");
			System.out.println("CurTime : " + curTime);
			System.out.println("Start Time :" + startTime);
			System.out.println("Waiting Time: " + waitingTime);
			return false;
		}
	}

}