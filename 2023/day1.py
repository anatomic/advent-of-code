# get the name of the current file

import os
import re
current_file = os.path.basename(__file__)
day, ext = os.path.splitext(current_file)

f = open(f"./input/{day}.txt", "r")
input = f.read()

test1 = """1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
"""

test2 = """two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
"""

numbers = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]

def part1(input: str):
    sum = 0
    for line in input.splitlines():
        numbers = [char for char in line if char.isdigit()]
        sum += int(f"{numbers[0]}{numbers[-1]}")
    print(sum)

def part2(input: str):
    sum = 0;
    for line in input.splitlines():
        matches = []
        for i, char in enumerate(line):
            if char.isdigit():
                matches.append(char)
            else:
                for j, number in enumerate(numbers):
                    if line[i:].startswith(number):
                        matches.append(str(j+1))

        sum += int(f"{matches[0]}{matches[-1]}")
    print(sum)

part1(input)
part2(input)
