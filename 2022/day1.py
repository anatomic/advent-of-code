import os
import re
current_file = os.path.basename(__file__)
current_path = os.path.relpath(__file__)
year = current_path.split("/")[0]
day, ext = os.path.splitext(current_file)

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()
