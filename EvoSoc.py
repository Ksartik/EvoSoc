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


    
class resource:
    #contain consumeable resource value in a cluster that (should implementaion
    #have a set of resource objects or rather have the resource object as a set 
    #that can be put anywhere on the map maybe regrowth factor could be implemented
    #in the resource object
    #no cluster implemeantation of resources
    value
    posx
    posy
    def _init(self):
        self.value = rd.gauss(80,20)
        self.posx = rd.random(0,C.gridCol-1)
        self.posy = rd.random(0,C.gridRow-1)
        world.Resource[self.posx][self.posy] = self.value

    
class human:
    class strenght:
        currStrenght
        def _init_(self):
            self.currStrenght = rd.randint(50,80)
        def increasestr(self):#human should increase his strenght only below a threshold
            while self.currStrenght < C.strenghtThreshold and world.Resource[self.posx][self.posy]>0:
                human.hstrength += 1
                world.Resource[self.posx][self.posy] -= 1
         #definition to add strenght and to check if it is below the threshold   
         #definition of currstrenght makes it impossible for initial strenght to cross threshold
    posx,posy
    hstrength = strenght()   
    gender #false implies female
    lifespan
    curiosity#curiosity of human how to increase it or decrease it based on social factors or on boredom ?
    curiosityThresh
    hungerThresh
    positionmatrix = np.array()
    friendsmatrix = np.array()
    name

    
    def _init_(self):#Human intialisation contains parameters that have to be varied
        self.gender = True if rd.randint(0,1) == 1 else False
        self.lifespan = rd.gauss(200,100)#parameter could be made random
        self.curiosity = rd.gauss(.5,.3)#parameter of born curiosity
        self.curiosityThres = rd.gauss(.7,.2)#parameter of curiosity threhold, assumption of gaussian distribution 
        hungerThresh = rd.gauss(20,10)
        self.posx = rd.gauss(500,C.nHumans)
        self.posy = rd.gauss(500,C.nHumans)
        while world.Human[self.posx][self.posy] == 1:
            self.posx = rd.gauss(500,C.nHumans)
            self.posy = rd.gauss(500,C.nHumans)
        world.Human[self.posx][self.posy] == 1
        np.vstack(self.positionmatrix,[self.posx,self.posy])
        
    def initchild(self,father,mother,x,y):
        #intialisation conditions that will be in part inherited from 
        #parents 
        self.gender = rd.choice([True,False])
        self.lifespan = rd.gauss((father.lifespan + mother.lifespan)/2,10) #could we add a random variable to the lifespan
        self.curiosity = rd.gauss((father.curiosity + mother.curiosity)/2,.1) #should curiosity be inherited?
        self.curiosityThres = (max(father.curiosityThres, mother.curiosityThres))#same question as above. i have chosen the max function as inheriting principle will vary
        self.posx = x
        self.posy = y
        np.vstack(self.positionmatrix,[self.posx,self.posy])
    
       
    def friends(self):
        #basically a interaction matrix of previous interactions with fellow humans and move will trigger a response from the fellows
        #that human has interacted have higher preferance for interacted females and more so for mated females could be added
        #the interaction is stored in form of coefficients that go from 0 to 1
        #need of a unique identifier for each human to correpond to each 
        friendarray = np,array()
        for i in range(self.neighbours.humnear.size):
            if self.neighbours.humnear[i][2] in friendarray
        
        
    class neighbours():
        #this function will also determine the mating 
        #this function will determine the above move function
        resnear = np.array()
        resnearval = np.array()
        humnear = np.array()
        emptynear = np.array()
        def _init_(self,human):
            for i in range(-1,1):
                for j in range(-1,1):
                    while i!= 0 and j!= 0:
                        if world.Human[human.posx+i][human.posy+j] == 1:
                            np.vstack(self.humnear,[human.posx+i,human.posy+j,id(human)])
                        elif world.Resource[human.posx+i][human.posy+j] != 0 and world.Human[human.posx+i][human.posy+j] == 0:
                            np.vstack(self.resnear,[human.posx+i,human.posy+j])
                            np.vstack(self.resnearval,world.Resource[human.posx+i][human.posy+j])
                        else:
                            np.vstack(self.emptynear,[human.posx+i,human.posy+j])
    
    def move(self):
    #how to move a human so that he doesnt come back to his original positions?
    #make humans move to cells only that are not occupied    
    #move where there are no humans(in those cells), if interlocked? then 
    #curiosity coeff could be transferred also the exploratory activity must have conditions of the people being 
    #friends also moving together and if not coeff could be affected
        n = neighbours(self)
        i = 0 
        if n.resnear.size != 0:
            #find position of max reoucrce value in neighbours also reduce strenght
            i = np.argmax(n.resnearval)
            self.posx = n.resnear[i][0]
            self.posy = n.resnear[i][1]
            if ([self.posx,self.posy]) in self.positionmatrix:
                self.curiosity -= C.trackedboredem
            else:
                self.curiosity -= C.untrackedoboredom
            np.vstack(self.positionmatrix,[self.posx,self.posy])
        elif n.emptynear.size != 0 and self.curiosity>self.curiosityThres:#we will have a interaction matrix to call upon to minimize distance between them 
            
            if ([self.posx,self.posy]) in self.positionmatrix:
                self.curiosity -= C.trackedboredem
            else:
                self.curiosity -= C.untrackedoboredom
            np.vstack(self.positionmatrix,[self.posx,self.posy])
        else:
            #this loop will determine how staying on the same cell would affect the humann present
            #decrease in strenght and increase in curiosity will result
            if self.curiosity > self.curiosityThres:
                self.curiosity = min(self.curiosity + C.bored,1)
                self.hstrenght.currStrenght = int(self.hstrenght.currStrenght*(2/3))
            else:
                self.curiosity += C.bored
        
        
    
    

    
    def mate():#will definie if the (is this function even needed?)
    
    def die():
        

#implementation of classes to form multiple humans and resource values on grid
#will have a resource position matrix , a human position matrix and a 