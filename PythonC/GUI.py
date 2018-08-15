from tkinter import *
import Constant as C
import EvoSoc as E
from resource import *
import time

# main simulation function file
#


master = Tk()
w = Canvas(master, width=1920, height=1080)
w.pack()

for k in range(C.nResource):
    res = resource()
    x = res.posX
    y = res.posY
    w.create_rectangle(x, y, x + 5, y + 5, fill="GREEN")

Humans = []
HumansMat = []
for i in range(C.nHumans):
    humancreate = E.Human()
    Humans.append(humancreate)
    y = humancreate.posY
    x = humancreate.posX
    hum = w.create_rectangle(x, y, x + 5, y + 5, fill="RED")
    w.update()
    HumansMat.append(hum)

for i in range(C.generations):
    time.sleep(1)
    for People in Humans:
        # here the mate function will be called as it is priority number one even before the move and hence eat function
        # condition to mate when mated then next turn
        if len(People.neighbours("humNear")) > 1:
            for j in range(len(People.neighbours("humNear"))):
                h = E.mate(People, E.getObj(People.neighbours("humNear")[i]))
                if isinstance(h, E.Human):
                    Humans.append(h)
                    break
        else:
            People.move()
            People.lifeSpan -= 1
        x = People.posX
        y = People.posY
        # mate function will follow i guess
        w.move(HumansMat[Humans.index(People)], x, y)
        w.update()
        if People.hStrength.getStr() == 0 or People.lifeSpan == 0:
            print(id(People), "Died", len(Humans), Humans.index(People))
            People.die()
            del Humans[(Humans.index(People))]

