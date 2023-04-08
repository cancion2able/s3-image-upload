package com.itstnslv.s3imageupload.profile;

import com.itstnslv.s3imageupload.datastore.FakeUserProfileDataStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserProfileService {

    private final FakeUserProfileDataStore dataStore;

    public UserProfileService(FakeUserProfileDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<UserProfile> getUserProfiles() {
        return dataStore.getUserProfiles();
    }

    public void uploadProfileImage(String userProfileId, MultipartFile file) {

    }
}
