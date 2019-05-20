import java.util.Random;
import java.util.Scanner;

public class Simulation {

	private AirplaneQueue landingQueue;
	private AirplaneQueue takeoffQueue;
	private AirplaneQueue runway;
	private int landingTime, takeoffTime, inbetweenTOTime, inbetweenLTime, maxTime, totalTime;
	private int curTime = 0, lCount = 0, tCount = 0;
	private int arrivalTime[], deptTime[];
	private int avgLTime = 0, avgTTime = 0;
	private Airplane onRunway = new Airplane();
	private Airplane addToTakeoff = new Airplane();
	private Airplane addToLanding = new Airplane();
	private boolean lWaiting, tWaiting;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Simulation s = new Simulation();

		s.startSimulation();
		s.printInput();
	}

///////////////////////////////////////////////////////////////////////////////
///   Simulation(	)                   	     							/// 
/// Input : no input														/// 
/// Description: initializes the data for the queues and the user input 	///
///////////////////////////////////////////////////////////////////////////////
	// @SuppressWarnings("rawtypes")
	public Simulation() {

//		landingTime = 5;
//		takeoffTime = 2;
//		inbetweenTOTime = 2;
//		inbetweenLTime = 2;
//		maxTime = 5;
//		totalTime = 20;

		input();

		arrivalTime = new int[10];
		deptTime = new int[10];

		landingQueue = new AirplaneQueue(landingTime, takeoffTime, maxTime, "Landing");
		takeoffQueue = new AirplaneQueue(landingTime, takeoffTime, maxTime, "Take off");
		runway = new AirplaneQueue();
	}

/////////////////////////////////////////////////////////////////// 
///   calculateLAverage(data to be added)						/// 
/// Input : Current plane(Airplane)								/// 
/// Output: no output 											///
/// Returns the new average time for landing					///
///////////////////////////////////////////////////////////////////	

	public int calculateLAverage(Airplane a) {

		int startTime = a.getStartTime();
		int endTime = a.getEndTime();
		int wt = endTime - startTime;

		if (avgTTime == 0) {
			avgTTime = wt;
		} else {
			// System.out.println("WT" + wt);
			System.out.println("prev avg L: " + avgLTime);
			avgLTime = (avgLTime + wt) / 2;
			System.out.println("new avg L: " + avgLTime);
		}
		return avgLTime;
	}

/////////////////////////////////////////////////////////////////// 
///   calculateLAverage(data to be added)						/// 
/// Input : Current plane(Airplane)								/// 
/// Output: no output 											///
/// Returns the new average time for take off					///
///////////////////////////////////////////////////////////////////	

	public int calculateTAverage(Airplane a) {

		int startTime = a.getStartTime();
		int endTime = a.getEndTime();
		int wt = endTime - startTime;

		// System.out.println("WT: " + wt);
		System.out.println("prev avg T: " + avgTTime);
		if (avgTTime == 0) {
			avgTTime = wt;
		} else {
			avgTTime = (avgTTime + wt) / 2;
			System.out.println("new avg T: " + avgTTime);
		}
		return avgTTime;
	}

/////////////////////////////////////////////////////////////////// 
///   input()													/// 
/// Input : no input											/// 
/// Output: no output 											///
/// Returns the new average time								///
/// Description: Prompts user for input							///
///////////////////////////////////////////////////////////////////

	public void input() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the amount of time needed to land one plane");
		landingTime = sc.nextInt();

		System.out.println("Enter the amount of time needed to take off one plane");
		takeoffTime = sc.nextInt();

		System.out.println("Enter time between arrival to landing queue");
		inbetweenTOTime = sc.nextInt();

		System.out.println("Enter time between arrival to takeoff queue");
		inbetweenLTime = sc.nextInt();

		System.out.println("Enter max time a plane can stay in queue");
		maxTime = sc.nextInt();

		System.out.println("Enter total time to simulated");
		totalTime = sc.nextInt();
	}

/////////////////////////////////////////////////////////////////// 
///   newLanding(int)											/// 
/// Input : current time										/// 
/// Output: no output 											///
/// Returns the new average time								///
/// Description: generates a new arrival time					///
///////////////////////////////////////////////////////////////////

// probability of landing 50%
	public void newLanding(int curTime) {

///////////////////////////////////////////////////////////
///   probability of a new time being added is 100%		///
///////////////////////////////////////////////////////////

		if (Math.random() > 0.0) {
			Random r = new Random();
			// The next arrival be only at 1 not at 0
			int tempTime = 1 + r.nextInt(10);

//			if (lCount == 0) {
//				arrivalTime[lCount] = curTime + tempTime;
//				lCount++;
//			} else {
//				arrivalTime[(lCount)] = arrivalTime[(lCount - 1)] + curTime + tempTime;
//				lCount++;
//			}

			if (curTime == 0) {
				// System.out.println("NewLanding lCount - " + lCount);
				// System.out.println("Temp Time" + tempTime);
				arrivalTime[lCount] = tempTime;
				lCount++;
			} else if (lCount == 0 && (curTime > tempTime)) {
				arrivalTime[(lCount)] = curTime + tempTime;
				lCount++;
			} else if (lCount == 0 && (arrivalTime[lCount] > tempTime)) {
				arrivalTime[(lCount)] = curTime + tempTime;
				lCount++;
			} else {
				if (curTime > (arrivalTime[lCount - 1] + tempTime)) {
					arrivalTime[(lCount)] = curTime + tempTime;
					lCount++;
				} else {
					arrivalTime[(lCount)] = arrivalTime[lCount] + tempTime;
					lCount++;

				}

			}

		}

//		System.out.println("Lcount is :" + (lCount));
//
//		for (int i = 0; i < arrivalTime.length; i++) {
//			System.out.println("The Landing times are - " + Integer.toString(arrivalTime[i]));
//		}

	}

/////////////////////////////////////////////////////////////////// 
///   newTakeoff(int)											/// 
/// Input : current time										/// 
/// Output: no output 											///
/// Returns the new average time								///
/// Description: generates a new departure time					///
///////////////////////////////////////////////////////////////////

	// probability of take off 50%
	public void newTakeoff(int curTime) {

///////////////////////////////////////////////////////////
///   probability of a new time being added is 100%		///
///////////////////////////////////////////////////////////

		if (Math.random() > 0.0) {
			Random r = new Random();
			// The next arrival be only at 1 not at 0
			int tempTime = 1 + r.nextInt(10);

//			
//			if (tCount == 0) {
//				arrivalTime[tCount] = curTime + tempTime;
//				tCount++;
//			} else {
//				arrivalTime[(tCount)] = arrivalTime[(tCount - 1)] + curTime + tempTime;
//				tCount++;
//			}

			if (curTime == 0) {
				// System.out.println("NewLanding TCount - " + tCount);
				deptTime[tCount] = tempTime;
				tCount++;
			} else if (tCount == 0 || curTime > tempTime) {
				deptTime[(tCount)] = curTime + tempTime;
				tCount++;
			} else if (tCount == 0 || deptTime[tCount] < tempTime) {
				deptTime[(tCount)] = curTime + tempTime;
				tCount++;

			} else {
				if (curTime > (deptTime[tCount - 1] + tempTime)) {

					deptTime[(tCount)] = curTime + tempTime;
					tCount++;
				} else {

					deptTime[(tCount)] = deptTime[tCount] + tempTime;
					tCount++;
				}

			}

		}
//		System.out.println("Tcount is :" + (tCount));
//
//		for (int i = 0; i < deptTime.length; i++) {
//			System.out.println("The Takeoff times are - " + Integer.toString(deptTime[i]));
//		}
	}

/////////////////////////////////////////////////////////////////// 
///   startSimulation()						  					/// 
/// Input : no input									 		///
/// Output: no output 											///
/// Returns nothing 										   	///
/// Description: Runs the simulation for the airport runway		///
///////////////////////////////////////////////////////////////////

	public void startSimulation() {
		int tempLCount = 0, tempTCount = 0;
		boolean isLanding = false, isTakeoff = false;

///////////////////////////////////////////////////////////
///   for loop to simulate the total duration 			///
///////////////////////////////////////////////////////////

		for (curTime = 0; curTime < totalTime + 1; curTime++) {

			System.out.println("Cur Time = " + curTime);

///////////////////////////////////////////////////////////////////
///   Every 4 min there is a possibility of a plane landing		///
///////////////////////////////////////////////////////////////////

			if (curTime % 4 == 0) {
				// System.out.println("Curtime: " + curTime + "Func: " + curTime % 3);
				newLanding(curTime);
			}

///////////////////////////////////////////////////////////////////////
///   Every 5 min there is a possibility of a plane taking off		///
///////////////////////////////////////////////////////////////////////

			if (curTime % 5 == 0) {
				// System.out.println("Curtime: " + curTime + "Func: " + curTime % 2);
				newTakeoff(curTime);
			}

///////////////////////////////////////////////////////////////////////
///   add to take off queue once the time is over					///
///////////////////////////////////////////////////////////////////////
			if (tempLCount != 0) {

				if (curTime == (arrivalTime[(tempLCount - 1)] + addToTakeoff.getLandingTime() + inbetweenTOTime)) {
					// System.out.println("test");
					// addToTakeoff = onRunway;
					// System.out.println(addToTakeoff.getId());
					addToTakeoff.setStartTime(curTime);
					takeoffQueue.add(addToTakeoff, "Take off Queue");
					// takeoffQueue.toString();
					System.out.println("Added to Take off ");
				}
			}

///////////////////////////////////////////////////////////////////////
///   add to landing queue once the time is over					///
///////////////////////////////////////////////////////////////////////
			if (tempTCount != 0) {

				if (curTime == (deptTime[(tempTCount - 1)] + addToLanding.getLandingTime() + inbetweenLTime)) {
					// System.out.println("test");
					// addToLanding = onRunway;
					// System.out.println("Runway id: " + onRunway.getId());
					// System.out.println("Add to take off id: " + addToTakeoff.getId());
					addToLanding.setStartTime(curTime);
					landingQueue.add(addToLanding, "Landing Queue");
					System.out.println("Added to landing ");
				}
			}

///////////////////////////////////////////////////////////////////////////////
///  check if current time = next arrival time and arrival time !=0			///
///////////////////////////////////////////////////////////////////////////////

			if (curTime == arrivalTime[tempLCount] && arrivalTime[tempLCount] != 0) {
				System.out.println("Running the current arrival plane");

///////////////////////////////////////////////////////////////
///  check if runway is busy, if yes lWaiting is true		///
///////////////////////////////////////////////////////////////
				if (runway.returnBusy()) {
					System.out.println("Runway is busy. Plane is waiting");
					lWaiting = true;

				} else {

///////////////////////////////////////
///  if no, start landing			///
///////////////////////////////////////

					onRunway = landingQueue.removeFromQueue(curTime, "Landing");
					// set end time
					// onRunway.setEndTime(curTime);
///////////////////////////////
///  calculate average		///
///////////////////////////////
					calculateLAverage(onRunway);
					// System.out.println("Average LAnding is: " + avgLTime);
					// System.out.println("Plane is landing");
					// add to runway queue
					runway.add(onRunway, "Runway");
					// System.out.println("Plane on Runway");
					// set runway to busy
					runway.setBusy(true);
					isLanding = true;
					// System.out.println("Runway is Busy? - " + runway.returnBusy());
					continue;
				}
			}

///////////////////////////////////////////////////////////////////////////////////
///  check if landing in progress and current time = complete landing time		///
///////////////////////////////////////////////////////////////////////////////////

			if (isLanding && (curTime == arrivalTime[tempLCount] + onRunway.getLandingTime())) {
				// landing has completed
				System.out.println("Landing has completed");
				// remove from runway
				addToTakeoff = runway.remove();
				// add to total landing

				runway.addToTotalLanding();
				// System.out.println("Total Landings - " + runway.getTotalLanding());
				// System.out.println(runway.toString());
				// set runway to not busy
				tempLCount++;
				runway.setBusy(false);
				runway.addToTotalTakeoff();
				// System.out.println("Total take off - " + runway.getTotalTakeoff());
				isLanding = false;
				// System.out.println("Runway is Busy? - " + runway.returnBusy());
				// continue;
			}

///////////////////////////////////////////////////////////////////////////////////
///  check if landing is not in progress and runway is empty and there is a 	///
///  a landing plane waiting and the arrival time is not 0						///
///////////////////////////////////////////////////////////////////////////////////		

			if (!isLanding && (!runway.returnBusy()) && lWaiting && arrivalTime[tempLCount] != 0) {
				System.out.println("Running the waiting arrival plane");

				// start landing
				System.out.println("Time: " + curTime + "  Plane is landing");
				// update arrival time
				arrivalTime[tempLCount] = curTime;

				// System.out.println("The new arrival Time is : " + arrivalTime[tempLCount]);
				onRunway = landingQueue.removeFromQueue(curTime, "Landing");

///////////////////////////////
///  calculate average		///
///////////////////////////////

				calculateLAverage(onRunway);
				// System.out.println("Average LAnding is: " + avgLTime);

///////////////////////////////////
///  add plane to runway queue	///
///////////////////////////////////
				runway.add(onRunway, "Runway");
				// System.out.println("Plane on Runway");

///////////////////////////////////
///  make runway busy			///
///////////////////////////////////
				runway.setBusy(true);
				lWaiting = false;
				isLanding = true;
				// System.out.println("Runway is Busy? - " + runway.returnBusy());
				continue;
			}

///////////////////////////////////////////////////////////////////////////////////
///  check if current time is arrivalTime for the next plane after the landing	///
///  and arrival time !=0														///
///////////////////////////////////////////////////////////////////////////////////	

			if (curTime == arrivalTime[tempLCount] && arrivalTime[tempLCount] != 0) {
				// System.out.println("Running the current plane");

///////////////////////////////////////////////////////////////
///  check if runway is busy, if yes lWaiting is true		///
///////////////////////////////////////////////////////////////

				if (runway.returnBusy()) {
					System.out.println("Runway is busy. Plane is waiting");
					lWaiting = true;

				} else {
///////////////////////////////////////
///  if no, start landing			///
///////////////////////////////////////
					// landingQueue.toString();
					onRunway = landingQueue.removeFromQueue(curTime, "Landing");

///////////////////////////////
///  calculate average		///
///////////////////////////////

					calculateLAverage(onRunway);
					// System.out.println("Average LAnding is: " + avgLTime);

					// System.out.println("Plane is landing");
///////////////////////////////
///  add to runway 			///
///////////////////////////////
					runway.add(onRunway, "Runway");
					// System.out.println("Plane on Runway");
///////////////////////////////
///  make runway busy		///
///////////////////////////////
					runway.setBusy(true);

					isLanding = true;
					// System.out.println("Runway is Busy? - " + runway.returnBusy());
					continue;
				}
			}

///////////////////////////////////////////////////////////////////////////////
///  check if current time = next arrival time and arrival time !=0			///
///////////////////////////////////////////////////////////////////////////////	

			if ((curTime == arrivalTime[(tempLCount + 1)]) && (arrivalTime[(tempLCount + 1)] != 0)) {
				System.out.println("Running the next arrival plane");

///////////////////////////////////////////////////////////////
///  check if runway is busy, if yes lWaiting is true		///
///////////////////////////////////////////////////////////////

				if (runway.returnBusy()) {
					System.out.println("Runway is busy. Plane is waiting");
					lWaiting = true;

				} else {
///////////////////////////////////////
///  if no, start landing			///
///////////////////////////////////////
					onRunway = landingQueue.removeFromQueue(curTime, "Landing");
///////////////////////////////
///  calculate average		///
///////////////////////////////
					calculateLAverage(onRunway);
					// System.out.println("Average LAnding is: " + avgLTime);

					// System.out.println("Plane is landing");
///////////////////////////////
///  add to runway 			///
///////////////////////////////
					runway.add(onRunway, "Runway");
					System.out.println("Plane on Runway");
///////////////////////////////
///  make runway busy		///
///////////////////////////////
					runway.setBusy(true);
					isLanding = true;
					// System.out.println("Runway is Busy? - " + runway.returnBusy());
					continue;
				}

			}

///////////////////////////////////////////////////////////////////////////////////
///  check if current time = next departure time and departure time !=0			///
///////////////////////////////////////////////////////////////////////////////////

			if ((curTime == deptTime[tempTCount]) && (deptTime[tempTCount] != 0)) {
///////////////////////////////////////////////////////////////
///  check if runway is busy, if yes tWaiting is true		///
///////////////////////////////////////////////////////////////
				if (runway.returnBusy()) {
					System.out.println("Runway is busy. Plane is waiting");
					tWaiting = true;

				} else {
///////////////////////////////////////
///  if no, start take off			///
///////////////////////////////////////
					System.out.println("Plane is taking off");
///////////////////////////////////////
///  update departure time			///
///////////////////////////////////////
					deptTime[tempTCount] = curTime;
					onRunway = takeoffQueue.removeFromQueue(curTime, "Take off");
///////////////////////////////////////
///  calculate average				///
///////////////////////////////////////
					calculateTAverage(onRunway);
					// System.out.println("Average take off is: " + avgTTime);
///////////////////////////////////////
///  add to runway queue			///
///////////////////////////////////////
					runway.add(onRunway, "Runway");
					// System.out.println("Plane on Runway");
///////////////////////////////////////
///  make runway busy				///
///////////////////////////////////////
					runway.setBusy(true);

					isTakeoff = true;
					// System.out.println("Runway is Busy? - " + runway.returnBusy());
					continue;
				}
			}
///////////////////////////////////////////////////////////////////////////////////
///  check no take off in progress and runway is not busy and a plane waiting 	///
///////////////////////////////////////////////////////////////////////////////////

			if (!isTakeoff && (!runway.returnBusy()) && tWaiting) {

				// start take off
				System.out.println("Time: " + curTime + "  Plane is taking off");
///////////////////////////////////////
///  update departure time			///
///////////////////////////////////////
				deptTime[tempTCount] = curTime;
				System.out.println("The new Dept Time is : " + deptTime[tempTCount]);
				onRunway = takeoffQueue.removeFromQueue(curTime, "Take off");
///////////////////////////////////////
///  add to runway queue			///
///////////////////////////////////////
				runway.add(onRunway, "Runway");
				// System.out.println("Plane on Runway");
///////////////////////////////////////
///  make runway busy				///
///////////////////////////////////////
				runway.setBusy(true);

				// takeoffQueue.addToTotalTakeoff();
				// System.out.println("Total Takeoffs: "+ takeoffQueue.getTotalTakeoff());
				isTakeoff = true;
				// System.out.println("Runway is Busy? - " + runway.returnBusy());
				continue;
			}

///////////////////////////////////////////////////////////////////////////////////
///  check take off in progress and current time = complete take off time 		///
///////////////////////////////////////////////////////////////////////////////////

			if (isTakeoff && (curTime == deptTime[tempTCount] + onRunway.getTakeOffTime())) {
				// Take off has completed
				// System.out.println("Take off has completed");
///////////////////////////////////////
///  remove from runway				///
///////////////////////////////////////
				addToLanding = runway.remove();
				// add to TotalTakeoff
				runway.addToTotalTakeoff();
				// System.out.println("Total take off - " + runway.getTotalTakeoff());
				// System.out.println(runway.toString());
///////////////////////////////////////
///  make runway not busy			///
///////////////////////////////////////
				runway.setBusy(false);
				tWaiting = false;
				tempTCount++;
				// System.out.println("Runway is Busy? - " + runway.returnBusy());
				// continue;
			}
		}
	}

	public void printInput() {

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Output:");
		System.out.println("Landing Time: " + landingTime);
		System.out.println("Take off Time: " + takeoffTime);
		System.out.println("Time between Take off: " + inbetweenTOTime);
		System.out.println("Time between Landing: " + inbetweenLTime);
		System.out.println("Maximum time a plane can stay in queue: " + maxTime);
		System.out.println("Total time: " + totalTime);

		System.out.println("Total number of planes that took off" + runway.getTotalTakeoff());
		System.out.println("Total number of planes that landed" + runway.getTotalLanding());
		System.out.println("Total number of Crashes: " + takeoffQueue.getTotalCrash());
		System.out.println("Average time spent in take off queue " + avgTTime);
		System.out.println("Average time spent in landing queue " + avgLTime);

		// System.out.println("Landing Queue: " + landingQueue.toString());
		// System.out.println("Take off Queue: " + takeoffQueue.toString());
		// System.out.println("Runway: " + runway.toString());

	}

}