# -*- coding: utf-8 -*-
"""
Created on Mon Aug 13 02:32:11 2018

@author: Jay
"""
import Constant as Con
import numpy as np

Environment = np.array((([0] * Con.gridCol) * Con.gridRow))
HumanP = np.zeros((Con.gridRow, Con.gridCol))
ResourceP = np.zeros((Con.gridRow, Con.gridCol))
