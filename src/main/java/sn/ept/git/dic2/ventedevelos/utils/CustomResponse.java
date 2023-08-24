package sn.ept.git.dic2.ventedevelos.utils;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class CustomResponse {
    private String msg;

    public CustomResponse() {
    }

    public CustomResponse(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
