package org.asrgroup.smartspeaker.controllers;

import org.asrgroup.smartspeaker.DTO.AudioRequest;
import org.asrgroup.smartspeaker.DTO.AudioResponse;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

/*
 * @author Neo0214
 */
@RestController
@CrossOrigin("*")
public class AudioController {
    @PostMapping("/message")
    public AudioResponse audio(@RequestBody AudioRequest audioRequest){

        // 获取回答
        String gptAnswer=getAnswer(audioRequest.getSentence()+"。回答不要出现英文，不超过50字。");
        if (gptAnswer.equals("error")){
            return new AudioResponse("0","error");
        }
        // 生成音频
        String path = "D:\\Smart-Speaker";
        String exeCommand="python infer.py --text=\""+gptAnswer+"\" --language=普通话 --spk=标准女声";
        String line="ok";
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /d " + path + " && python infer.py --text=\"" + gptAnswer + "\" --language=普通话 --spk=标准女声");
            builder.redirectErrorStream(true);
            Process process = builder.start();

            // 读取Python脚本的输出结果
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String nextLine = reader.readLine();
            while (nextLine != null) {
                line=nextLine;
                System.out.println(line);
                nextLine = reader.readLine();
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
            return new AudioResponse("0","error");
        }

        // 返回处理结果
        return new AudioResponse(getID(line),gptAnswer);
    }

    private String getAnswer(String sentence){
        String txtPath="D:/Smart-Speaker/transport/message.txt";
        String message="error";
        try {
            FileOutputStream fos = new FileOutputStream(txtPath);
            fos.write(sentence.getBytes(StandardCharsets.UTF_8));
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
            return "error";
        }
        String command="python D:/Smart-Speaker/generate.py";
        try{
            Process process=Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())); // 获取命令输出流
            String line=reader.readLine();
            if (line==null || line.equals("ok")){
                InputStreamReader insReader = new InputStreamReader(
                        new FileInputStream(txtPath), StandardCharsets.UTF_8);
                BufferedReader bufReader = new BufferedReader(insReader);

                String li = "";
                StringBuilder sb = new StringBuilder();
                while ((li = bufReader.readLine()) != null) {
                    sb.append(li);
                }
                bufReader.close();
                insReader.close();
                return sb.toString();
            }
        }catch (IOException e){
            e.printStackTrace();
            return "error";
        }
        return "error";
    }

    private String getID(String line){
        int pos=line.indexOf("output/")+7;
        int end=line.indexOf(".");
        return line.substring(pos,end);
    }
}
