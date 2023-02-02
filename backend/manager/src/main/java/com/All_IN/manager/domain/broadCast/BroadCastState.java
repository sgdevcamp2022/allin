package com.All_IN.manager.domain.broadCast;

public enum BroadCastState {
    LIVE("LIVE"),
    END("END"),
    ;

    BroadCastState(String state) {
        this.state = state;
    }

    private String state;
}
