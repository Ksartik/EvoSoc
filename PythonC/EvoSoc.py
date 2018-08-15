# import statements
import gc
import random as rd

import numpy as np

import Constant as C
import world


# import graphics.py

# win = GraphWin()


class Human:
    class Strenght:
        def __init__(self, value):
            self.setStr(value)

        def getStr(self):
            return self.currStrenght

        def setStr(self, value):
            self.currStrenght = value

        def increaseStr(self, Human):  # Human should increase his Strenght only below a threshold
            while self.currStrenght < C.strenghtThreshold and world.ResourceP[Human.posX][Human.posY] > 0:
                self.setStr(self.getStr() + 1)
                world.ResourceP[Human.posX][Human.posY] -= 1
        # definition to add Strenght and to check if it is below the threshold
        # definition of currStrenght makes it impossible for initial Strenght to cross threshold

    hStrength = Strenght(rd.randint(50, 80))

    '''def setFreiendarray(self,value,value1):
        self.friendarray = value
        self.interactioncoeff = value1'''

    def die(self):
        self.setlife(0)

    def setlife(self, value):
        self.Life = value

    def setCuriosity(self, value):
        self.curiosity = value

    # def setpos(self, x, y):
    #     self.posX = x
    #     self.posY = y

    def setpos(self, v):
        if isinstance(v, np.ndarray):
            v.tolist()
            self.posX = v[0][0]
            self.posY = v[0][1]
        elif isinstance(v, list):
            self.posX = v[0]
            self.posY = v[1]

    def setCuriosityThres(self, value):
        self.curiosityThres = value

    def setLifespan(self, value):
        self.lifeSpan = value

    def setpositionMat(self, value):
        np.append(self.positionMat, value)

    def setfriendVector(self):
        for i in range(len(self.neighbours("humNear"))):
            if self.friendVector[i] in self.neighbours(("humNear")):
                self.interactionCoeff[i] += C.interactionConstant
            else:
                self.friendVector[i] = self.neighbours("humNear")
                self.interactionCoeff[i] = self.neighbours("humNear")

    def __init__(self):  # Human initialisation contains parameters that have to be varied
        self.gender = rd.randint(0, 1)
        self.setLifespan(rd.gauss(200, 100))  # parameter could be made random
        self.setCuriosity(rd.gauss(.5, .3))  # parameter of born curiosity
        self.setCuriosityThres(rd.gauss(.7, .2))  # parameter of curiosity threshold, assumption of gaussian
        # distribution self.hungerThresh = rd.gauss(20,10) this will bw added later to further complexity this
        # will be a critical juncture when a Human goes below a hunger threshold when he has to move at least or
        # threaten a neighbouring Human if even that is not present then die!!
        self.setpos([int(rd.randint(0, C.gridRow - 1)), int(rd.randint(0, C.gridCol - 1))])
        while world.HumanP[self.posX][self.posY] == 1:
            self.setpos([int(rd.randint(0, C.gridRow - 1)), int(rd.randint(0, C.gridCol - 1))])
        world.HumanP[self.posX][self.posY] == id(self)
        self.positionMat = np.empty([1, 2], dtype=int)
        self.setlife(1)
        add = np.array([self.posX, self.posY])
        self.setpositionMat(add)
        self.friendVector = []
        self.interactionCoeff = []
        self.setfriendVector()

    def _initchild(self, father, mother, x, y):

        # initialisation conditions that will be in part inherited from parents
        # code defines inherited properties
        self.gender = rd.choice([True, False])
        self.setLifespan(rd.gauss((father.lifeSpan + mother.lifeSpan) / 2, 10))
        # could we add a random variable to the lifeSpan
        self.setCuriosity(rd.gauss((father.curiosity + mother.curiosity) / 2, .1))
        # should curiosity be inherited?
        self.curiosityThres((max(father.curiosityThres, mother.curiosityThres)))
        # same question as above. i have chosen the max function
        # as inheriting principle will vary
        self.setpos([x, y])
        self.setlife(1)
        add = np.array([self.posX, self.posY])
        np.vstack(self.positionMat, add)
        world.HumanP[self.posX][self.posY] == id(self)
        self.friendVector = []
        self.interactionCoeff = []
        self.setfriendVector()

    # basically a interaction matrix of previous interactions with fellow Humans and move will trigger a response
    #  from the fellows that Human has interacted have higher preferance for interacted females and more so for
    # mated females could be added the interaction is stored in form of coefficients that go from 0 to 1 need of
    # a unique identifier for each Human to correpond to each will be run after each move is made hence will be
    # put in the move function this function will hence not be called

    '''def friends(self):
        self.neighbours(None)
        friendarray = []
        interactioncoeff = []
        for p in range(len(self.neighbours("humNear"))):
            if neighbours("humNear")[p] in friendarray:
                q = friendarray.index[self.neighbours("humNear")[p]]
                interactioncoeff[q] += C.interactionconstant
            else:
                friendarray.append(self.neighbours("humNear")[p])
                interactioncoeff.append(C.interactionconstant)
        return friendarray, interactioncoeff '''

    def neighbours(self, returnstring):
        # this function will also determine the mating (could)
        # this function will determine the above move function
        # call this class to
        resNear = []
        resNearval = []
        humNear = []
        emptyNear = []
        for i in range(-1, 2):
            for j in range(-1, 2):
                if world.HumanP[(self.posX + i) % C.gridRow][(self.posY + j) % C.gridCol] != 0:
                    humNear.append(world.selfP[(self.posX + i) % C.gridRow][(self.posY + j) % C.gridCol])
                elif world.ResourceP[(self.posX + i) % C.gridRow][(self.posY + j) % C.gridCol] != 0 and \
                        world.HumanP[(self.posX + i) % C.gridRow][(self.posY + j) % C.gridCol] == 0:
                    resNear.append([(self.posX + i) % C.gridRow, (self.posY + j) % C.gridCol])
                    resNearval.append(
                        world.ResourceP[(self.posX + i) % C.gridRow][(self.posY + j) % C.gridCol])
                else:
                    emptyNear.append([[(self.posX + i) % C.gridRow, (self.posY + j) % C.gridCol]])

        if returnstring == "humNear":
            h1 = np.asarray(humNear)
            return h1
        elif returnstring == "resNear":
            re = np.asarray(resNear)
            return re
        elif returnstring == "resNearval":
            res = np.asarray(resNearval)
            return res
        elif returnstring == "emptyNear":
            empty = np.asarray(emptyNear)
            return empty
        else:
            pass

    def move(self):
        # how to move a Human so that he doesnt come back to his original positions?
        # make Humans move to cells only that are not occupied
        # move where there are no Humans(in those cells), if interlocked? then
        # curiosity coeff could be transferred also the exploratory activity must have conditions of the people being
        # friends also moving together and if not coeff could be affected
        # will be called in each loop
        # moving should also change the Strenght of the Human
        # self.neighbours("None")
        if self.neighbours("resNear").size > 1:
            # find position of max resource value in neighbours also reduce Strenght
            i = np.argmax(self.neighbours("resNearval"))
            self.setpos(self.neighbours("resNear")[i])
            if ([self.posX, self.posY]) in self.positionMat:
                self.setCuriosity(self.curiosity - C.trackedboredem)
            else:
                self.setCuriosity(self.curiosity - C.untrackedoboredom)
                np.vstack(self.positionMat, [self.posX, self.posY])
            self.hStrength.setStr(self.hStrength.getStr() - C.moveStrenght)
            self.hStrength.increaseStr(self)
        elif np.ma.size(self.neighbours("emptyNear"), axis=0) > 0 and self.curiosity > self.curiosityThres:
            # we will have a interaction matrix to call upon to minimize distance between them
            min_dis = []
            k = 0
            for w in range(np.ma.size(self.neighbours("emptyNear"), axis=0)):
                for e in range(len(self.friendVector)):
                    k = self.interactionCoeff[e] * distance(self.posX, self.posY,
                                                            getObj(self.friendVector[e]).posX,
                                                            getObj(self.friendVector[e]).posY) * self.Life + \
                        k
                min_dis.append(k)
            a = min(min_dis)
            if a != 0:
                misdisindex = min_dis.index(a)
                self.setpos(self.neighbours("emptyNear")[misdisindex])
            else:
                r = rd.randint(0, np.ma.size(self.neighbours("emptyNear"), axis=0) - 1)
                self.setpos(self.neighbours("emptyNear")[r])

            # o = rd.randint(0, np.ma.size(self.neighbours("emptyNear"),axis=0)-1)
            # print(self.neighbours("emptyNear")[o][0], self.neighbours("emptyNear")[o][1])
            # self.setpos(self.neighbours("emptyNear")[o][0],self.neighbours("emptyNear")[o][1])
            if ([self.posX, self.posY]) in self.positionMat:
                self.setCuriosity(self.curiosity - C.trackedBoredem)
            else:
                self.setCuriosity(self.curiosity - C.untrackedoBoredom)
                self.setpositionMat([self.posX, self.posY])
            # self.setFriendarray()
            self.setfriendVector()
            self.hStrength.setStr(self.hStrength.getStr() - C.moveStrenght)
            self.hStrength.increaseStr(self)
        else:
            # this loop will determine how staying on the same cell would affect the Human present
            # decrease in Strenght and increase in curiosity will result
            if self.curiosity > self.curiosityThres:
                self.setCuriosity(min(self.curiosity + C.bored, 1))
                self.hStrength.setStr(self.hStrength.getStr() - C.boreStrenght)
                # if we add the increaseStr method it will be back to square one but hence moving will be rewarding
                # self.setFriendarray()
                self.setfriendVector()
            else:
                self.setCuriosity(self.curiosity - C.bored)
                # self.setFriendarray()
                self.setfriendVector()


def distance(x1, y1, x2, y2):
    a = int(((x1 - x2) ** 2) + ((y1 - y2) ** 2) ** (1 / 2))
    return a


def getObj(id_):
    for obj in gc.get_objects():
        if id(obj) == id_:
            return obj
        else:
            pass


# class definitions for a Human resources  etc
# implementation of classes to form multiple Humans and resource values on grid will have a resource position matrix
# , a Human position matrix and a main function here start by creating a world which is now rather imported how to 
# create n number of Humans? will create a array that stores the Humans mating as activity, should decrease Strenght 
# by a constant also the curiosity should be decreased

def mate(father, mother):
    if father.gender != mother.gender and mother.neighbours("emptyNear").size != 0:
        i = rd.randint(0, mother.neighbours("emptyNear").size - 1)
        h = Human.initchild(father, mother, mother.neighbours("emptyNear")[i][0], mother.neighbours("emptyNear")[i][1])
        father.hStrength.setStr(father.hStrength.getStr() - C.mateStrenght)
        mother.hStrength.setStr(mother.hStrength.getStr() - C.mateStrenght)
        father.setCuriosity(father.curiosity - C.matecuriosity)
        mother.setCuriosity(mother.curiosity - C.matecuriosity)
    elif father.gender != mother.gender and mother.neighbours("emptyNear").size != 0:
        i = rd.randint(0, father.neighbours("emptyNear").size - 1)
        h = Human.initchild(father, mother, father.neighbours("emptyNear")[i][0], father.neighbours("emptyNear")[i][1])
        father.hStrength.setStr(father.hStrength.getStr() - C.mateStrenght)
        mother.hStrength.setStr(mother.hStrength.getStr() - C.mateStrenght)
        father.setCuriosity(father.curiosity - C.matecuriosity)
        mother.setCuriosity(mother.curiosity - C.matecuriosity)
    else:
        h = 0
    return h


