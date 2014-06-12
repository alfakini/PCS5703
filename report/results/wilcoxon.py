import numpy
from numpy import genfromtxt
from scipy import stats

SIM_DATA_FILES = ['simulation-data-1.csv', 'simulation-data-2.csv', 'simulation-data-3.csv', 'simulation-data-4.csv']

for filename in SIM_DATA_FILES:
    data = genfromtxt(filename, delimiter=',')
    x = list(data[:, 0])
    y = list(data[:, 1])

    print filename, '------ p-value:', stats.wilcoxon(x, y)[1]
