# Write a Python Program to Extract Unique dictionary values
dict = {'A':[5,6,7,8], 'B':[10,11,7,5], 'C':[6,12,10,8], 'D':[1,2,5]}
dict['C']={4,5,6,7}
x=list(dict.values())
y=[]
res=[]
for i in x:
    y.extend(i)
for i in y:
    if i not in res:
        res.append(i)
res.sort()
print("The unique values list is:", res)
