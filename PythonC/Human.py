import random as rd

class Human:
    class Strenght:
        def __init__(self, value):
            self.set_x(value)

        def getStr(self):
            return self.currStrenght

        def set_x(self, value):
            self.currStrenght = value

        def increaseStr(self):  # Human should increase his Strenght only below a threshold
            while self.currStrenght < C.StrenghtThreshold and world.Resource[self.posX][self.posY] > 0:
                self.set_x(self.getStr()+1)
                world.Resource[self.posX][self.posY] -= 1
        # definition to add Strenght and to check if it is below the threshold
        # definition of currStrenght makes it impossible for initial Strenght to cross threshold

    hStrength = Strenght(rd.randint(50, 80))

    def die(self):
        self.setlife(0)

    def setlife(self, value):
        self.Life = value

    def setCuriosity(self,value):
        self.curiosity = value

    def setpos(self, x , y):
        self.posX = x
        self.posY = y

    def setCuriosityThres(self,value):
        self.curiosityThres = value

    def setLifespan(self,value):
        self.lifeSpan = value

    def setpostionMat(self,value):
        np.vstack(self.positionMat, value)

    def __init__(self): # Human initialisation contains parameters that have to be varied
        self.gender = rd.randint(0,1)
        self.setLifespan(rd.gauss(200, 100))# parameter could be made random
        self.setCuriosity(rd.gauss(.5, .3))  # parameter of born curiosity
        self.setCuriosityThres( rd.gauss(.7, .2))  # parameter of curiosity threshold, assumption of gaussian
        # distribution self.hungerThresh = rd.gauss(20,10) this will bw added later to further complexity this
        # will be a critical juncture when a Human goes below a hunger threshold when he has to move at least or
        # threaten a neighbouring Human if even that is not present then die!!
        self.setpos(rd.gauss(500, C.nHumans),rd.gauss(500, C.nHumans))
        while world.Human[self.posX][self.posY] == 1:
            self.setpos(rd.gauss(500, C.nHumans), rd.gauss(500, C.nHumans))
        world.Human[self.posX][self.posY] == 1
        self.positionMat = np.empty([1, 2], dtype=int)
        self.setlife(1)
        add = np.array([self.posX, self.posY])
        np.vstack(self.positionMat, add)

    def _initchild(father, mother, x, y):

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
        self.setpos(x, y)
        self.setlife(1)
        add = np.array([self.posX, self.posY])
        np.vstack(self.positionMat, add)
        world.Human[self.posX][self.posY] == 1

    # basically a interaction matrix of previous interactions with fellow Humans and move will trigger a response
    #  from the fellows that Human has interacted have higher preferance for interacted females and more so for
    # mated females could be added the interaction is stored in form of coefficients that go from 0 to 1 need of
    # a unique identifier for each Human to correpond to each will be run after each move is made hence will be
    # put in the move function this function will hence not be called

    def friends(self):
        n = self.neighbours(self)
        for p in range(self.neighbours.humNear.size):
            if n.humNear[p][2] in self.friendarray:
                q = self.friendarray.index[self.n.humNear[p][2]]
                self.interactioncoeff[q] += C.interactionconstant
            else:
                self.friendarray.append(self.n.humNear[p][2])
                self.interactioncoeff.append(C.interactionconstant)

    class neighbours:
        # this function will also determine the mating (could)
        # this function will determine the above move function
        # call this class to

        def __init__(self, Entity):
            for i in range(-1, 1):
                for j in range(-1, 1):
                    while i != 0 and j != 0:
                        if world.Entity[Entity.posX + i][Entity.posY + j] == 1:
                            np.vstack(self.humNear, [Entity.posX + i, Entity.posY + j, id(Entity)])
                        elif world.Resource[Entity.posX + i][Entity.posY + j] != 0 and world.Entity[Entity.posX + i][
                            Entity.posY + j] == 0:
                            np.vstack(self.resNear, [Entity.posX + i, Entity.posY + j])
                            np.vstack(self.resNearval, world.Resource[Entity.posX + i][Entity.posY + j])
                        else:
                            np.vstack(self.emptyNear, [Entity.posX + i, Entity.posY + j])

    def move(self):
        # how to move a Human so that he doesnt come back to his original positions?
        # make Humans move to cells only that are not occupied
        # move where there are no Humans(in those cells), if interlocked? then
        # curiosity coeff could be transferred also the exploratory activity must have conditions of the people being
        # friends also moving together and if not coeff could be affected
        # will be called in each loop
        # moving should also change the Strenght of the Human
        n = self.neighbours(self)
        if n.resNear.size != 0:
            # find position of max reoucrce value in neighbours also reduce Strenght
            i = np.argmax(n.resNearval)
            self.setpos(n.resNear[i][0],n.resNear[i][1])
            if ([self.posX, self.posY]) in self.positionMat:
                self.setCuriosity(self.curiosity-C.trackedboredem)
            else:
                self.setCuriosity(self.curiosity-C.untrackedoboredom)
                np.vstack(self.positionMat, [self.posX, self.posY])
            self.friends()
            self.hStrenght.setStr(self.hStrenght.getStr - C.moveStrenght)
            self.hStrenght.increaseStr()
        elif n.emptyNear.size != 0 and self.curiosity > self.curiosityThres:
            # we will have a interaction matrix to call upon to minimize distance between them
            min_dis = np.array([100000])
            for w in range(n.emptyNear.size):
                for e in range(self.friendarray.size):
                    min_dis[w] += self.interactioncoeff[e] * distance(self.posX, self.posY,
                                                                      getObj(self.friendarray[e]).posX,
                                                                      getObj(self.friendarray[e]).posY) * self.Life
            misdisindex = min_dis.index(min_dis.min())
            self.setpos(n.emptyNear[misdisindex][0], n.emptyNear[misdisindex][1])
            if ([self.posX, self.posY]) in self.positionMat:
                self.setCuriosity(self.curiosity-C.trackedboredem)
            else:
                self.curiosity -= C.untrackedoboredom
                self.setpostionMat([self.posX, self.posY])
            self.friends()
            self.hStrenght.setStr(self.hStrenght.getStr - C.moveStrenght)
            self.hStrenght.increaseStr()
        else:
            # this loop will determine how staying on the same cell would affect the Human present
            # decrease in Strenght and increase in curiosity will result
            if self.curiosity > self.curiosityThres:
                self.setCuriosity(min(self.curiosity + C.bored, 1))
                self.hStrength.setStr(self.hStrength.getStr() - C.boreStrenght)
                # if we add the increaseStr method it will be back to square one but hence moving will be rewarding
                self.friends()
            else:
                self.setCuriosity(self.curiosity-C.bored)
                self.friends()



def distance(x1, y1, x2, y2):
    a = ((x1 - x2) ** 2) + ((y1 - y2) ** 2) ** (1 / 2)
    return a


def getObj(id_):
    for obj in gc.get_objects():
        if id(obj) == id_:
            return obj