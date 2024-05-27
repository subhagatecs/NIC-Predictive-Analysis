#convert tuple->list
tuplee = (1, 2, 3)

newlist = list(tuplee)
print(newlist)

# list -> tuple

def trans(list):
    return tuple(list)


# Driver function
listt = [1, 2, 3, 4]
print(trans(listt))