# EvoSoc
Analyzing the development of social networks in an interactive environment through generations
We are trying to simulate the real world in 2d GRAPHICS based on some basic rules and requirments( food , interaction ).
 
## Motive:
We are expecting some cool results from which we can learn about how the societies would have evolved - from bands to tribes to chiefdoms to states and what are the necessary requirements for such evolution to occur.

## Walkthrough 
 * Green represents human (in the latest commit - green represents a male and yellow represents a female
 * Red represents resources (static currently) - when eaten by a human, they increase his strength
 * At each iteration, each human will move to one location out of its 8 neighbors if his strength allows and there is space
 * Friends (counted by more interaction as neighbors provided they are not enemies) tend to remain closer while enemies (counted by having sex with the same female) tend to have fight whenever they meet as neighbors again
 * Each person needs to maintain his curiosity less than some threshold (inheritable trait) by moving to newer places or by mating otherwise he/she will have to use his/her energy/strength to cope up. 
 * Inheritable traits - life, strength, curiosity threshold.
 
## Some of the significant Out Comes..
This a screenshot of our first simulations.
Interestingly we could see two different clusters of being.
![simulation](https://user-images.githubusercontent.com/35027192/44126950-9260ea1c-a058-11e8-9c23-2825728d46dc.png)

We are now able to get a proper developing of a society and its bifurcations and then dying due to wrong choice in the area or something and then further development elsewhere and further bifurcations. With some particular constants, we are also getting a stable system to go all the way.

This is a simulation when starting with 100 people (there is some delay at the start) -

[Simulation2](https://drive.google.com/file/d/1djNtuQw4KjDcDOL9RKD4r1bWMQLcgMld/view?usp=sharing)

### SCRIPT
To run the program through bash script use the following on terminal inside the Java/src directory


```bash
bash simulator.sh
```
## To DOs..
* Update the code to include multithreading to achieve better interaction between members.
* Update Mating Algorithm to achive more realisation.
* Changing the nHumans variable and determine critical population for explosive growth in terms of mating, and other variables
* Adding moving resources representing Animals and Disease resources
* Actually code different sociteties and include enimity between unknown people



### THINK THINK THINK...
## Stay Foolish !! Stay Hungry !!
  

