1. InvalidatedToken

- InvalidatedToken is a blacklist of revoked/logged-out tokens.
- In JWT authentication, tokens are stateless - once issued, they remain valid until they expire. There's no built-in way to "cancel" a JWT token.
- The problem: When a user logs out, their token is still technically valid until it expires. If someone steals that token, they could still use it!
- The solution: Create a database table (InvalidatedToken) that stores IDs of tokens that should no longer be accepted, even if they haven't expired yet.

2. Refresh token

- Access Token (Short-lived)
- Duration: 10 seconds (VALID_DURATION = 10)
- Purpose: Used to access protected APIs
- Lifecycle: Very short for security
- Refresh Token Mechanism (Longer-lived)
- Duration: 15 seconds (REFRESHABLE_DURATION = 15)
- Purpose: Used to get a new access token without re-entering credentials

- Note: The durations in your config (10s and 15s) are unusually short - they're probably set for testing. In production, typical values are:
- Access token: 15-30 minutes
- Refresh token: 7-30 days
  -> FRONTENT: setTimeout(() => {
  refreshToken(token);
  }, (TOKEN_LIFETIME - REFRESH_BEFORE) \* 1000);

  -> refreshToken(currentToken) when call API from FE failed



./mvnw spring-boot:run
