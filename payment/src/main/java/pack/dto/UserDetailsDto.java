package pack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetailsDto {
    private String name;
    private String cardAuthorizationInfo;

    public UserDetailsDto(
            @JsonProperty("username") String name,
            @JsonProperty("cardAuthorizationInfo") String cardAuthorizationInfo) {
        this.name = name;
        this.cardAuthorizationInfo = cardAuthorizationInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardAuthorizationInfo() {
        return cardAuthorizationInfo;
    }

    public void setCardAuthorizationInfo(String cardAuthorizationInfo) {
        this.cardAuthorizationInfo = cardAuthorizationInfo;
    }
}