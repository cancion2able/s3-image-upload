package com.itstnslv.s3imageupload.datastore;

import com.itstnslv.s3imageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "janetjones", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "antoniojunior", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }

    public boolean userExists(UUID userProfileId) {
        return USER_PROFILES.stream()
                .anyMatch(userProfile -> userProfile.getUserProfileId().equals(userProfileId));
    }
}
