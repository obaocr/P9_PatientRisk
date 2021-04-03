package com.ocr.p9PatientRisk.util;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String invoqueur, Response reponse) {

        if(reponse.status() == 404 ) {
            return new NotFoundRequestException(
                    "NOT FOUND"
            );
        }

        return defaultErrorDecoder.decode(invoqueur, reponse);
    }

}

