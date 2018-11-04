package com.oaxaca.turismo.mercados.clases;

import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    /**
     * se llama cuando se asigna un Token al dispositivo
     */
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

    }
}