# -*- coding: utf-8 -*-
"""
Created on Wed Jan  3 02:41:36 2024

@author: whk
"""

import erniebot

erniebot.api_type = 'aistudio'
erniebot.access_token =

file_path = 'D:/Smart-Speaker/transport/message.txt'

with open(file_path, 'r', encoding='utf-8', errors='ignore') as file:
    input = file.read()

response = erniebot.ChatCompletion.create(
    model='ernie-bot',
    messages=[{
        'role': 'user',
        'content': input
    }])

output = response.get_result()

file = open(file_path, 'w',encoding='utf-8')
file.write(output)
file.close()

print("ok",flush=True)
