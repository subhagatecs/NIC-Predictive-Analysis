def even_nums(numbers):
    for num in numbers:
        if num % 2 == 0:
            print(num)


# nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


while True:
    try:
        user_input = input("Enter nums :")
        input_list = user_input.split()
        nums = [int(num) for num in input_list]
        break
    except ValueError:
        print("Invalid input")

even_nums(nums)
