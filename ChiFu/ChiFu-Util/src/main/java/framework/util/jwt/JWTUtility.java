package framework.util.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import framework.model.CfUserM;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JWTUtility implements Serializable {

	private static final long serialVersionUID = 234234523523L;

	public static final String TOKEN_NAME = "chi_access_token";
    public static final String REFRESH_TOKEN_NAME = "refresh_chi_access_token";
	public static final long JWT_TOKEN_VALIDITY = 10 * 1000 ;
	public static final long REFRESH_TOKEN_VALIDITY = 6 * 60 * 1000;

	private String secretKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

	// retrieve username from jwt token
	public String getEmailFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public String getRoleIdFromToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		return claims.get("rid", String.class);
	}

	public String getUserIdFromToken(String token) {
		return getClaimFromToken(token, Claims::getId);
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		JwtParser parser = Jwts.parser();
		parser.setSigningKey(secretKey);
		Jws<Claims> jwtclaims = parser.parseClaimsJws(token);
		Claims body = jwtclaims.getBody();
		return body;
		// return
		// Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getEmailFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

//    //generate token for user
//    public String generateJwtToken(String username, long expireTime, String secretKey) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
//                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
//    }

	// generate token for user
	public String generateJwtToken(CfUserM user, long expireTime) {
		final long currdate = System.currentTimeMillis();
		return Jwts.builder().setId(String.valueOf(user.getUserId())).setSubject(user.getEmail()).claim("rid", user.getType()).setIssuedAt(new Date(currdate)).setExpiration(new Date(currdate + expireTime)).signWith(SignatureAlgorithm.HS512, this.secretKey).compact();
	}

	public Map<String, Object> parseToken(String token) {
		Key secretKey = Keys.hmacShaKeyFor(this.secretKey.getBytes());
		JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
		Claims claims = parser.parseClaimsJws(token).getBody();
		return claims.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
