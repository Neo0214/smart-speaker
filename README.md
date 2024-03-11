# smart-speaker
This is the backend for ASR curriculum final assignment. The frontend is on https://github.com/Neo0214/my-question-answering-app
## 1 Environment
- Java17
- Spring Boot 3
- Python3.10
- package mvits,pytorch and so on
- nodejs,vue3 for front end
- Windows OS ( Linux is also ok, but need a little modification) 

## 2 Preparation

- Go and find your VITS model. In this project, we use a method from <https://github.com/yeyupiaoling/VITS-Pytorch>. Here we clone it in d:/Smart-Speaker
- In file AudioController.java, you can find line 30 is like this:

```java
ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /d " + path + " && python infer.py --text=\"" + gptAnswer + "\" --language=普通话 --spk=标准女声");
```

You may change <font color=Blue>path</font> to where you store your VITS project and make <font color=Blue>gptAnswer</font> to what you want to speak.

- move generate.py to d:/Smart-speaker and 

```bash
mkdir transport
cd transport
touch message.txt
```

Be aware that in generate.py, you need to set your 文心一言 token.

- At last, you can run it.

## 3 Problems May Occur

- A chat sentence is put in frontend and sent to backend. Then, the backend execute generate.py to get an answer from 文心一言 and the python script writes the answer in message.txt. So check out if your txt is encoded in `utf-8`.
- Then the backend execute VITS model with cmd line(you can change the model with different command). In this case, the audio is named by unix time. So that's the ID. When more than two users generating audios at the same time, it can be crushed.
