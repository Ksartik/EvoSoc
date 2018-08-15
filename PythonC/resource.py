import random as rd
import world
import Constant as C


class resource:
    # contain consumable resource value in a cluster that (should implement)
    # have a set of resource objects or rather have the resource object as a set
    # that can be put anywhere on the map maybe regrowth factor could be implemented
    # in the resource object
    # no cluster implementation of resources
    def __init__(self):
        self.value = (rd.gauss(80, 20))
        self.posX = rd.randint(0, C.gridCol - 1)
        self.posY = rd.randint(0, C.gridRow - 1)
        while world.ResourceP[self.posX][self.posY] >0:
            self.posX = rd.randint(0, C.gridCol - 1)
            self.posY = rd.randint(0, C.gridRow - 1)
        world.ResourceP[self.posX][self.posY] = self.value
