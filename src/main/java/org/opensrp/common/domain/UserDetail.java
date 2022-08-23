package org.opensrp.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail implements Serializable {

    private static final long serialVersionUID = -3954547752263574520L;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("user_id")
    private String identifier;

    @JsonProperty
    private List<String> roles;

    @JsonProperty("preferred_name")
    private String preferredName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty
    private String email;

    @JsonProperty("email_verified")
    private boolean emailVerified;

}
