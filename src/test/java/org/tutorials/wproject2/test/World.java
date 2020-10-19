package org.tutorials.wproject2.test;

import org.springframework.stereotype.Component;
import org.tutorials.wproject2.test.model.Group;


@Component
public class World {

    public String responseString="";
    public Group responseGroup=null;
    public String apiPath="initial";
    public Integer statusCode=-1;

    public String getResponseString() {
        return responseString;
    }
    public void setResponseString(String responseString) {
        responseString=responseString;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(Integer statusCode) {
        this.statusCode=statusCode;
    }

    public String getApiPath() {
        return apiPath;
    }
    public void setApiPath(String apiPath) {
        this.apiPath=apiPath;
    }

    public void reset() {
        responseString="";
        responseGroup=null;
        statusCode=-1;
        apiPath="initial";

    }

}
