# AirportLanding
Airport Landing Simulation

## Class: Airplane.java 
### Class variables: 
1.	landingTime – int
2.	takeoffTime– int
3.	id – String
4.	waitingTime – int
5.	startTime – int 
6.	endTime –int 
### Methods: 
1.	getLandingTime () – Returns the time taken to land
2.	getTakeOffTime () – Returns the time to take off	
3.	setData(int, int)	– adds the data
4.	getWaitingTime () – Returns the maximum waiting time in the queue
5.	setWaitingTime () – Sets the max waiting time in queue
6.	getId() – returns the id of plane
7.	setId(String, String) – adds the id of the plane
8.	setStartTime(int) – sets the time the plane joined the queue
9.	getStartTime()- returns startTime
10.	setEndTime(int) – sets the time the plane left the queue
11.	getEndTime()- returns the end time
12.	isCrashed(int) – check if plane is crashed.


## Class: AirplaneQueue.java
### Class variables: 
1.	array – type Airplane[]
2.	count, totalTakeoff, totalLanding, totalCrash, rear – type int
3.	busy – type boolean
### Methods: 
1.	AirplaneQueue() – Constructor with no parameters
2.	Airplane(int,int,int,String) - _initializes and assigns data for the takeoff and landing queue with the landing, take off and waiting time_ <br>
a.	Add 10 planes to take off and landing queues
3.	returnLength () – Returns the length of the list	
4.	toString() – return the entire array
5.	removeFromQueue(int) – returns the plane removed from the queue
a.	check if take off plane and waiting time exceeded – crash
b.	add to crash
c.	if not crash, remove and return plane
6.	add(Airplane, String) – adds the new plane to the queue
7.	remove() – removes the plane from the array 
8.	setBusy(Boolean) – sets runway as busy or not
9.	returnBusy() – returns if busy or not
10.	addToTotalTakeoff() – increments the total take off
11.	getTotalTakeoff() – returns total take off
12.	addToTotalLanding() – increments total landing
13.	getTotalLanding() – returns total landing 
14.	getTotalCrash() – returns total crash
15.	addToTotalCrash() – increments total crash

## Class: Simulation.java
### Class variables: 
1.	landingQueue, takeoffQueue, runway – type AirplaneQueue
2.	all the user inputs
3.	arrivalTime[],deptTime[] – type int[]
4.	curTime, lCount, tcount – type int
5.	onRunway, addToTakeoff, addToLanding – type Airplane
6.	lWaiting, tWaiting - boolean
### Methods: 
1.	Main – Calls all function 
2.	Simulation() – initialized all variables
a.	Prompt for user input and assign to variables
3.	calculateLAverage(Airplane) – returns the new average for landing 
a.	waiting time = endtime- start time
b.	average = (average + waiting time)/2
4.	calculateTAverage(Airplane) – returns the new average for take off 
a.	waiting time = endtime- start time
b.	average = (average + waiting time)/2
5.	input() – prompts for user input
6.	newLanding(int) – generates new arrival time
a.	check probability
b.	make sure new time > curTime and prev arrival time
7.	newTakeoff(int) – generates new dept time
a.	check probability
b.	make sure new time > curTime and prev dept time
8.	StartSimulation() – runs the simulation
a.	Run for loop for total duration
i.	If multiple of 4 – new landing time added
ii.	If multiple of 5 – new dept time added
iii.	If  count >0 and curtime = total time to move to takeoff queue – add to take off queue
iv.	If  count >0 and curtime = total time to move to arrival queue – add to arrival queue
v.	If current time = arrival time
1.	Check if runway is busy
2.	If yes, wait
3.	If no start landing, move plane to runway
4.	Calculate average
5.	Make runway busy
6.	Skip to next loop
vi.	If landing in progress and current time = arr+ landing time 
1.	Remove from runway
2.	Add to total landings
3.	Make runway not busy
vii.	If landing is not in progress, runway is empty, landing plane is waiting
1.	Set new arrival time
2.	Move plane to runway
3.	Calculate average
4.	Make runway busy
5.	Skip to next loop
viii.	Check after landing if current time = arrival time
1.	Check if runway is busy
2.	If yes, wait
3.	If no start landing, move plane to runway
4.	Calculate average
5.	Make runway busy
6.	Skip to next loop
ix.	If current time = dept time 
1.	Check if runway is busy
2.	If yes, wait
3.	If no start departure, move plane to runway
4.	Calculate average
5.	Make runway busy
6.	Skip to next loop
x.	If no take off in progress and run is not busy and plane is waiting 
1.	Set new arrival time
2.	Move plane to runway
3.	Calculate average
4.	Make runway busy
5.	Skip to next loop
xi.	Check if take off in progress and current time = complete take off time 
1.	Remove from runway
2.	Add to total landings
3.	Make runway not busy
xii.	printInput() – prints input for the simulation


