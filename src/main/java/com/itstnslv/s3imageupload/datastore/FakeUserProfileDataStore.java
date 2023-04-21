package com.itstnslv.s3imageupload.datastore;

import com.itstnslv.s3imageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("a4f6bd42-7d95-476e-92f8-04720a9558fa"), "janetjones", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("c4bfe7c3-3532-4110-8cdb-93fed060fa94"), "antoniojunior", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }

    public UserProfile retrieveUserBy(UUID userProfileId) {
        return USER_PROFILES.stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No such user exists in the database"));
    }
}
