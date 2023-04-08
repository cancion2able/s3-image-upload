package com.itstnslv.s3imageupload.bucket;

public enum BucketName {

    PROFILE_IMAGE("cancion-image-upload");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
