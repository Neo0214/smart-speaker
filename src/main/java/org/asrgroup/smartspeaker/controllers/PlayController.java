package org.asrgroup.smartspeaker.controllers;

import org.asrgroup.smartspeaker.DTO.PlayRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/*
 * @author Neo0214
 */
@RestController
@CrossOrigin("*")
public class PlayController {
    @GetMapping("/play")
    public ResponseEntity<byte[]> playMusic(@RequestParam String id){

        File file = new File("D:/Smart-Speaker/output/" + id +".wav");
        byte[] res = null;

        try {

            res = Files.readAllBytes(file.toPath());
            if (res == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(res);
        } catch (IOException e) {

            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
