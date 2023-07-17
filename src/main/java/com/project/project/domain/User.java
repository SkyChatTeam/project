package com.project.project.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
@Getter
@Builder  //필드에 채워야 하는 값을 명확하게 알 수 있어서 실수 X
@AllArgsConstructor
@NoArgsConstructor  //Lombok 어노테이션으로 빈 생성자 만듬
@Entity(name="user") //JPA로 관리되는 엔티티 객체(테이블)
public class User implements UserDetails {

    @Id //테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 자동 생성(auto_increment)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="email", nullable = false, length = 50, unique = true) //해당 필드는 칼럼, null값 안됨. 길이 제한
    private String email; //아이디

    @Column(name="password", nullable = false, length = 100)
    private String password; //비밀번호

    @Column(name="username", nullable = false, length = 50)
    private String username; //이름

    @CreationTimestamp
    @Column(name="createDate", nullable = false)
    private Timestamp createDate;

    @Builder
    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Override //권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override //사용자 id 반환
    public String getUsername() {
        return email;
    }

    @Override //사용자 pw 반환
    public String getPassword() {
        return password;
    }

    //계정 만료 여부 반환
    @Override
    public  boolean isAccountNonExpired() {
        //만료 확인 로직
        return true; //true : 만료되지 않은것
    }

    //계정 잠금 여부 반환
    @Override
    public  boolean isAccountNonLocked() {
        //계정 잠금 확인 로직
        return true; //true : 잠금되지 않은것
    }
    
    //패스워드 만료 여부 반환
    @Override
    public  boolean isCredentialsNonExpired() {
        //패스워드 만료 확인 로직
        return true; //true : 만료되지 않은것
    }

    //계정 사용 가능 여부 반환
    @Override
    public  boolean isEnabled() {
        //계정 사용 가능 확인 로직
        return true; //true : 사용 가능
    }
    
}


