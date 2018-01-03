package com.binarypursuits.android.webtroller;

import android.app.Application;
import io.socket.client.IO;
import io.socket.client.Socket;
import java.net.URISyntaxException;

public class ChatApplication extends Application {

    private String url;

    private Socket mSocket;

    public void setUrl(String newUrl) {
        this.url = newUrl;
        try {
            mSocket = IO.socket(Constants.CHAT_SERVER_URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket(String newUrl) {
        this.setUrl(newUrl);
        return mSocket;

    }

    public Socket getSocket() {
        return mSocket;
    }

}
