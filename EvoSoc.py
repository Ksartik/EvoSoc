# -*- coding: utf-8 -*-
"""
Created on Sun Aug 12 02:00:09 2018

@author: Jay
"""
#import statements
import numpy as np
import random as rd
import Constant as C

#class definations for a human resources  etc
class world:#creates a array for environment, humans and resources
    Environment = np.array((([0]*C.gridCol)*C.gridRow))
    Human = np.array((([0]*C.gridCol)*C.gridRow))
    Resource = np.array((([0]*C.gridCol)*C.gridRow))
    
    

#is there a need of class of position ?
#position class will be inherited by both human and resources(maybe) rather create class position in human
        #resource class
#class position:
#    int x
#    int y
#    bool resourcep = False
#    bool humanp = False
#    def _init(self)
    #position if called by human must intialise a position to the human 
    #position must also intialise a position to a resource object
    #intialisation of values is a distribtion(normal) but not for
    #resources rather they must be given a cluster value and then centered
    #randomly 
    
class resource:
    #contain consumeable resource value in a cluster that (should implementaion
    #have a set of resource objects or rather have the resource object as a set 
    #that can be put anywhere on the map maybe regrowth factor could be implemented
    #in the resource object
    cluster = rd.randint(5,100)

    
class human:
    class strenght:
        currStrenght
        def _init_(self):
            self.currStrenght = rd.randint(50,80)
        def increasestrenght(self):
            while self.currStrenght <= C.strenghtThreshold:
                
         #definition to add strenght and to check if it is below the threshold   
         #definition of currstrenght makes it impossible for initial strenght to cross threshold
    int posx,posy
    hstrength = strenght()   
    bool gender #false implies female
    int lifespan
    float curiosity#curiosity of human how to increase it or decrease it based on social factors or on boredom ?
    float curiosityThresh
    
    def _init_(self):#Human intialisation contains parameters that have to be varied
        self.gender = True if rd.randint(0,1) == 1 else False
        self.lifespan = rd.gauss(30,10)#parameter could be made random
        self.curiosity = rd.gauss(.5,.3)#parameter of born curiosity
        self.curiosityThres = rd.gauss(.7,.2)#parameter of curiosity threhold, assumption of gaussian distribution 
        self.posx = 
        self.posy =
        
    def initchild(self,human father,human mother):
        #intialisation conditions that will be in part inherited from 
        #parents 
        self.gender = rd.choice([True,False])
        self.lifespan = (father.lifespan + mother.lifespan)/2 #could we add a random variable to the lifespan
        self.curiosity = (father.curiosity + mother.curiosity)/2 #should curiosity be inherited?
        self.curiosityThres = (max(father.curiosityThres, mother.curiosityThres))#same question as above. i have chosen the max function as inheriting principle will vary
        self.posx =
        self.posy =
    
       
    def move():
        #move where there are no humans(in those cells), if interlocked? then 
        #curiosity coeff could be transferred also the exploratory activity must have conditions of the people being 
        #friends also moving together and if not coeff could be affected 
    def friends():
        #basically a interaction matrix of previos interactions with fellow humans and move will trigger a response from the fellows
        #that human has interacted have higher preferance for interacted females and moreso for mated females
    def neighbours():#this function will also determine the mating 
        #this function will determine the above move function
    def mating():#will definie if the (is this function even needed?)
        

#implementation of classes to form multiple humans and resource values on grid
#will have a resource position matrix , a human position matrix and a 