from functools import partial


# ======= Example of using partial to create a new function that squares a number
def power(base, exp):
    return base**exp


square = partial(power, exp=3)
print(square(5))  # 125


# ======= Example of using partial to create a new function that adds a fixed number
def make_adder(x):
    def adder(y):
        return x + y

    return adder


add5 = make_adder(5)
print(add5(10))  # 15


# ======= Example of using higher-order functions like map, filter, and reduce
nums = [1, 2, 3, 4, 5, 6, 7, 8]
res = list(map(lambda x: x * 2, nums))
print(res)  # [2, 4, 6, 8, 10, 12, 14, 16]


res = list(filter(lambda x: x % 2 == 0, nums))
print(res)  # [2, 4, 6, 8]


from functools import reduce

total = reduce(lambda x, y: x + y, nums)
print(total)  # 36


# ======= Example of combining map, filter, and reduce
nums = [1, 2, 3, 4, 5]

# Filter even numbers, double them, and sum the result
result = reduce(
    lambda x, y: x + y, map(lambda x: x * 2, filter(lambda x: x % 2 == 0, nums))
)
print(result)  # 12


# ======= Example of using a decorator to log function calls
def logger(func):
    def wrapper(*args, **kwargs):
        print(f"Call {func.__name__}")
        return func(*args, **kwargs)

    return wrapper


@logger
def hello():
    print("Hello!")


hello()  # Call hello，Hello!


# ======= Example of using a decorator as AOP
from functools import wraps


def aop_decorator_log(func):
    # Using wraps to preserve the original function's metadata
    @wraps(func)
    def wrapper(*args, **kwargs):
        """This wrapper docstring is preserved."""

        print(f"=== before call business_logic, logging args: {args}, kwargs: {kwargs}")

        result = func(*args, **kwargs)

        print("=== after call business_logic, logging result:", result)
        return result

    return wrapper


@aop_decorator_log
def business_logic(x):
    """This function does some business logic."""
    print(f"my business_logic with {x}")


business_logic(10)
print(business_logic.__name__)  # func name is business_logic, instend of wrapper
print(business_logic.__doc__)  # docs is preserved, instend of wrapper


import time
def my_timer(func):
    
    @wraps(func)
    def wrapper(*args, **kwargs):

        start_time = time.time()
        result = func(*args, **kwargs)
        end_time = time.time()

        print(f"=== Function {func.__name__} took {end_time - start_time:.4f} seconds")
        return result
    
    return wrapper


# Example of using a timer decorator to measure execution time
@my_timer
def processing_data(all_data):
    """Simulate processing a list of data."""
    res = []
    for item in all_data:
        # Simulate some processing
        res.append(item +1)

    return res


res = processing_data(range(1000000))
print("Result:", sum(res))


"""
@lru_cache

"""