package com.zdominguez.sdksandbox;

public class Listing {

    private final Long mId;
    private final String mAddress;

    public Listing(Long id, String address) {
        mId = id;
        mAddress = address;
    }

    public String getAddress() {
        return mAddress;
    }

    public Long getId() {
        return mId;
    }
}
