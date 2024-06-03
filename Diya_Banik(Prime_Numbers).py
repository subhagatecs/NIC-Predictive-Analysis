
# Write a python program to print all prime numbers in a interval

def is_prime(n):

    if n <= 1:
        return False
    for i in range(2, n):
        if n % i == 0:
            return False
    return True

def primes_in_interval(start, end):
    for num in range(start, end + 1):
        if is_prime(num):
            print(num, end=' ')


start_interval = int(input("Enter the start of the interval: "))
end_interval = int(input("Enter the end of the interval: "))

print(f"Prime numbers between {start_interval} and {end_interval}:")
primes_in_interval(start_interval, end_interval)
