package com.mohsenoid.rickandmorty.data.service.network;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface NetworkHelper {

    String requestData(String endpoint, List<Param> params) throws IOException;

    void requestImageData(String imageUrl, File imageFile) throws IOException;

    class Param {
        private String key;
        private String value;

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
