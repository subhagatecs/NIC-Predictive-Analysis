
# Write a Python program to check if a given string is pallindrome or not

def is_palindrome(s):
    normal_str = ''.join(char.lower() for char in s if char.isalnum())
    length = len(normal_str)

    for i in range(length // 2):
        if normal_str[i] != normal_str[length - 1 - i]:
            return False
    return True

input_string = input("Enter a string: ")
if is_palindrome(input_string):
    print("The string is a palindrome.")
else:
    print("The string is not a palindrome.")
