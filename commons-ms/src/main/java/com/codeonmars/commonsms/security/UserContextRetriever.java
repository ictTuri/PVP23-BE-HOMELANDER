package com.codeonmars.commonsms.security;

import java.util.Optional;

public interface UserContextRetriever {
    Optional<UserContext> retrieveUserContext(String credential);
}
