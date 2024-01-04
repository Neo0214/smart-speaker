package org.asrgroup.smartspeaker.DTO;

/*
 * @author Neo0214
 */
public class AudioResponse {
    private String id;
    private String message;
    public AudioResponse(String id,String message){
        this.id=id;
        this.message=message;
    }
    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
