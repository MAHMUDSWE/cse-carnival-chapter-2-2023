package com.reachout.backend.config;

import com.reachout.backend.entity.Admin;
import com.reachout.backend.entity.Patient;
import com.reachout.backend.security.ApplicationUserAdmin;
import com.reachout.backend.security.ApplicationUserDoctor;
import com.reachout.backend.security.ApplicationUserPatient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${application.security.jtwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jtwt.refresh-token.expiration}")
    private long refreshTokenExpiration;
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T>T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignInKey()).build().parseClaimsJws(token).getPayload();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();

        if (userDetails instanceof ApplicationUserAdmin) {
            ApplicationUserAdmin adminUser = (ApplicationUserAdmin) userDetails;
            extraClaims.put("id", adminUser.getId());
            // Add other admin-specific claims if needed
        } else if(userDetails instanceof ApplicationUserDoctor) {
            ApplicationUserDoctor doctorUser = (ApplicationUserDoctor) userDetails;
            extraClaims.put("id", doctorUser.getId());
            // Add other doctor-specific claims if needed
        } else if(userDetails instanceof ApplicationUserPatient) {
            ApplicationUserPatient patientUser = (ApplicationUserPatient) userDetails;
            extraClaims.put("id", patientUser.getId());
            // Add other patient-specific claims if needed
        }
        return generateToken(extraClaims, userDetails);
    }


    //call buildToken method to generate jwt access token
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        System.out.println("generate token: " + userDetails);
        return buildToken(new HashMap<>(), userDetails, jwtExpiration);
    }

    //call buildToken method to generate refresh token
    public String generateRefreshToken(UserDetails userDetails) {
        System.out.println("generate refresh token: " + userDetails);
        return buildToken(new HashMap<>(), userDetails, refreshTokenExpiration);
    }

    public String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts.builder().claims(extraClaims).
                subject(userDetails.getUsername()).
                issuedAt(new Date(System.currentTimeMillis())).
                expiration(convertMillisToDate(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), Jwts.SIG.HS256).compact();
    }

    public static Date convertMillisToDate(long millis) {
        return new Date(millis);
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public Boolean isTokenValid(String token, UserDetails userDetails) {

        System.out.println("Token validating!");
        String username = extractUsername(token);

        System.out.println("UserDetails class: " + userDetails.getClass().getName());

        if (userDetails instanceof ApplicationUserPatient) {
            System.out.println("User token found!");
            ApplicationUserPatient patient = (ApplicationUserPatient) userDetails;
            return username.equals(patient.getUsername()) && !isTokenExpired(token) && validateToken(token);
        } else if (userDetails instanceof ApplicationUserAdmin) {
            System.out.println("Admin token found!");
            ApplicationUserAdmin admin = (ApplicationUserAdmin) userDetails;
            // Adjust this according to how you store usernames for admins
            System.out.println(username.equals(admin.getUsername()) && !isTokenExpired(token) && validateToken(token));
            return username.equals(admin.getUsername()) && !isTokenExpired(token) && validateToken(token);
        } else if(userDetails instanceof ApplicationUserDoctor) {
            System.out.println("doctor token found!");
            ApplicationUserDoctor doctor = (ApplicationUserDoctor) userDetails;
            // Adjust this according to how you store usernames for admins
            return username.equals(doctor.getUsername()) && !isTokenExpired(token) && validateToken(token);
        }else {
            System.out.println("Unknown user type!");
            // Handle other user types if necessary
            return true;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean validateToken(String token) {
        return true;
 //       try {
//            Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT sequence");
//        } catch (MalformedJwtException ex) {
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
//        } catch (BlogApiException ex) {
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT");
//        } catch (IllegalArgumentException ex) {
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
//        }


    }
}

