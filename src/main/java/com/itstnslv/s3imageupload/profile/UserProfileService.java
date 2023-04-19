package com.itstnslv.s3imageupload.profile;

import com.itstnslv.s3imageupload.bucket.BucketName;
import com.itstnslv.s3imageupload.datastore.FakeUserProfileDataStore;
import com.itstnslv.s3imageupload.filestore.FileStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.apache.http.entity.ContentType.IMAGE_PNG;

@Service
public class UserProfileService {

    private final FakeUserProfileDataStore dataStore;
    private final FileStore fileStore;

    public UserProfileService(FakeUserProfileDataStore dataStore, FileStore fileStore) {
        this.dataStore = dataStore;
        this.fileStore = fileStore;
    }

    public List<UserProfile> getUserProfiles() {
        return dataStore.getUserProfiles();
    }

    public void uploadProfileImage(String userProfileId, MultipartFile file) {
        isFileEmpty(file);
        isImage(file);
        if (dataStore.userExists(UUID.fromString(userProfileId))) {
            Map<String, String> metadata = constructMetadata(file);
            final String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfileId);
            final String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
            try {
                fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private static Map<String, String> constructMetadata(MultipartFile file) {
        return Map.of("Content-Type", file.getContentType(),
                "Content-Length", String.valueOf(file.getSize()));
    }

    private static void isImage(MultipartFile file) {
        if (!Arrays.asList(IMAGE_JPEG.toString(), IMAGE_PNG.toString()).contains(file.getContentType())) {
            throw new IllegalStateException("The file is not an image.");
        }
    }

    private static void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("The file is empty.");
        }
    }
}
