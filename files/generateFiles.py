import numpy as np

def generate_static(n, l, name, r=0.25):
    f = open(name, 'w')
    one_value = '{}\n'
    f.write(one_value.format(n))
    f.write(one_value.format(l))
    prop = 1
    for x in range(0,n):
        f.write('{} {}\n'.format(r, prop))

def generate_dynamic(n, l, name):
    f = open(name, 'w')
    f.write('0\n')
    for x in range(0, n):
        f.write('{} {}\n'.format(np.random.uniform(0, l), np.random.uniform(0,l)))

def generate_files(n, l):
    generate_static(n, l,'static-' + str(n) + '.txt')
    generate_dynamic(n, l,'dynamic-' + str(n) + '.txt')

n = [10,20,50,100,200,300,500,1000]

i = 0
for x in n:
    i += 1
    generate_files(n[i-1], 20)