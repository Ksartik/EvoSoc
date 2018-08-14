
#import statements
import numpy as np
import random as rd
import Constant as C
import gc
import world
import Turtle as gr
#class definations for a human resources  etc
win = gr.GraphicWin()
humansgraphics = np.array()

    
class resource:
    #contain consumeable resource value in a cluster that (should implement)
    #have a set of resource objects or rather have the resource object as a set 
    #that can be put anywhere on the map maybe regrowth factor could be implemented
    #in the resource object
    #no cluster implemeantation of resources
    def _init(self):
        self.value = rd.gauss(80,20)
        self.posx = rd.random(0,C.gridCol-1)
        self.posy = rd.random(0,C.gridRow-1)
        world.Resource[self.posx][self.posy] = self.value
        pt = gr.Point(self.posx,self.posy)
        cir = gr.Circle(pt,1)
        cir.setFill("")
        cir.draw(win) 

    
class human:
    class strenght():
        def _init_(self):
            self.currStrenght = rd.randint(50,80)
        def getstr(self):
            return self.currStrenght
        def setstr(self,value):
            self.currStrenght = value
        def increasestr(self):#human should increase his strenght only below a threshold
            while self.currStrenght < C.strenghtThreshold and world.Resource[self.posx][self.posy]>0:
                self.currStrength += 1
                world.Resource[self.posx][self.posy] -= 1
        #definition to add strenght and to check if it is below the threshold   
        #definition of currstrenght makes it impossible for initial strenght to cross threshold

    hstrength = strenght()
    def _init_(self):#Human intialisation contains parameters that have to be varied
        self.gender = True if rd.randint(0,1) == 1 else False
        self.lifespan = rd.gauss(200,100)#parameter could be made random
        self.curiosity = rd.gauss(.5,.3)#parameter of born curiosity
        self.curiosityThres = rd.gauss(.7,.2)#parameter of curiosity threhold, assumption of gaussian distribution 
        #self.hungerThresh = rd.gauss(20,10) this will bw added later to further complexity this will be a critical jucture when a human goes below a hungerthreshold when he 
        #has to move atleast or threaten a neighbouring human if even that is not present then die!!
        self.posx = rd.gauss(500,C.nHumans)
        self.posy = rd.gauss(500,C.nHumans)
        while world.Human[self.posx][self.posy] == 1:
            self.posx = rd.gauss(500,C.nHumans)
            self.posy = rd.gauss(500,C.nHumans)
        world.Human[self.posx][self.posy] == 1
        np.vstack(self.positionmatrix,[self.posx,self.posy])
        pt = gr.Point(self.posx,self.posy)
        cir = gr.Circle(pt,1)
        cir.setFill("blue")
        cir.draw(win)
        
    def initchild(self,father,mother,x,y):
        #intialisation conditions that will be in part inherited from 
        #parents 
        #code defines inherited properties
        self.gender = rd.choice([True,False])
        self.lifespan = rd.gauss((father.lifespan + mother.lifespan)/2,10) #could we add a random variable to the lifespan
        self.curiosity = rd.gauss((father.curiosity + mother.curiosity)/2,.1) #should curiosity be inherited?
        self.curiosityThres = (max(father.curiosityThres, mother.curiosityThres))#same question as above. i have chosen the max function as inheriting principle will vary
        self.posx = x
        self.posy = y
        np.vstack(self.positionmatrix,[self.posx,self.posy])
        world.Human[self.posx][self.posy] == 1
        pt = gr.Point(self.posx,self.posy)
        cir = gr.Circle(pt,1)
        cir.setFill("blue")
        cir.draw(win)
    
       
    def friends(self):
        #basically a interaction matrix of previous interactions with fellow humans and move will trigger a response from the fellows
        #that human has interacted have higher preferance for interacted females and more so for mated females could be added
        #the interaction is stored in form of coefficients that go from 0 to 1
        #need of a unique identifier for each human to correpond to each 
        #will be run after each move is made hence will be put in the move function
        #this function will hence not be called
        n = self.neighbours(self)
        for i in range(self.neighbours.humnear.size):
            if n.humnear[i][2] in self.friendarray:
                j = self.friendarray.index[self.n.humnear[i][2]]
                self.interactioncoeff[j] += C.interactionconstant
            else:
                self.friendarray.append(self.n.humnear[i][2])
                self.interactioncoeff.append(C.interactionconstant)       
                
        
        
    class neighbours():
        #this function will also determine the mating (could)
        #this function will determine the above move function
        #call this class to 
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
    #will be called in each loop 
    #moving should also change the strenght of the human
        n = self.neighbours(self)
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
            self.friends()
            self.hstrenght.setstr(self.hstrenght.getstr-C.movestrenght)
            self.hstrenght.increasestr() 
        elif n.emptynear.size != 0 and self.curiosity>self.curiosityThres:#we will have a interaction matrix to call upon to minimize distance between them 
            mindis = np.array()
            for i in range(n.emptynear.size):
                for j in range(self.friendarray.size):
                    mindis[i] += self.interactioncoeff[j]*distance(self.posx,self.posy,getobj(self.friendarray[j]).posx,getobj(self.friendarray[j]).posy)*self.life
                misdisindex = mindis.index(mindis.min())
            self.posx = n.emptynear[misdisindex][0]
            self.posy = n.emptynear[misdisindex][1]
            if ([self.posx,self.posy]) in self.positionmatrix:
                self.curiosity -= C.trackedboredem
            else:
                self.curiosity -= C.untrackedoboredom
                np.vstack(self.positionmatrix,[self.posx,self.posy])
            self.friends()
            self.hstrenght.setstr(self.hstrenght.getstr-C.movestrenght)
            self.hstrenght.increasestr() 
        else:
            #this loop will determine how staying on the same cell would affect the human present
            #decrease in strenght and increase in curiosity will result
            if self.curiosity > self.curiosityThres:
                self.curiosity = min(self.curiosity + C.bored,1)
                self.hstrength.setstr(self.hstrength.getstr()-C.borestrenght)
                #if we add the increasestr method it will be back to square one but hence moving will be rewarding
                self.friends()
            else:
                self.curiosity += C.bored
                self.friends()
    def die(self):
        self.life = 0  
        
        
def distance(x1,y1,x2,y2):
        a = ((x1-x2)**2)+((y1-y2)**2)**(1/2)
        return a
    
def getobj(id_):
    for obj in gc.get_objects():
        if id(obj) == id_:
            return obj
#implementation of classes to form multiple humans and resource values on grid
#will have a resource position matrix , a human position matrix and a 
#main function here
#start by creating a world which is now rather imported
#how to create n number of humans? will create a array that stores the humans mating as activity, should decrease strenght by a constant also the curiosty should be decreased   
def mate(father,mother):
    if father.gender != mother.gender and mother.neighbours.emptynear.size != 0: 
        i = rd.randint(0,mother.neighbours.emptynear.size-1)
        h = human.initchild(father,mother,mother.neighbours.emptynear[i][0],mother.neighbours.emptynear[i][1])
        father.hstrenght.setstr(father.hstrenght.getstr()-C.matestrenght)
        mother.hstrenght.setstr(mother.hstrenght.getstr()-C.matestrenght) 
        father.curiosity = father.curiosity - C.matecuriosity
        mother.curiosity = mother.curiosity - C.matecuriosity
    elif father.gender != mother.gender and father.neighbours.emptynear.size != 0:
        i = rd.randint(0,father.neighbours.emptynear.size-1)
        h = human.initchild(father,mother,father.neighbours.emptynear[i][0],father.neighbours.emptynear[i][1])
        father.hstrenght.setstr(father.hstrenght.getstr()-C.matestrenght)
        mother.hstrenght.setstr(mother.hstrenght.getstr()-C.matestrenght)
        father.curiosity = father.curiosity - C.matecuriosity
        mother.curiosity = mother.curiosity - C.matecuriosity
    else:
        h = 0
    return h        
    
    
humans = np.array()
for i in range(C.nHumans):
    x = human()
    humans.append(x)
for i in range(C.generations):
    for human in range(humans):
#here the mate function will be called as it is priority number one even before the move and hence eat function
        #condition to mate when mated then next turn 
        if human.neighbours.humnear.size != 0:
            for i in range(human.neghbours.humnear.size):
                h = mate(human, getobj(human.neighbours.humnear[i][2]))
                if isinstance(h,human):
                    humans.append(h)
                    break
        else:    
            human.move()
            human.lifespan -= 1
        #mate function will follow i guess 
        if human.strenght.getstr() == 0 or human.lifespan == 0:
            human.die()
            humans = np.delete(humans,humans.index(human))

#rest is the simulation part that will graphically output the ongoings of the human behaviour based on the constraints given
            
        
    
      
   
