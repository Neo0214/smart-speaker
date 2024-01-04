package org.asrgroup.smartspeaker.DTO;

import org.apache.tomcat.util.digester.SetPropertiesRule;

/*
 * @author Neo0214
 */
public class PlayRequest {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
