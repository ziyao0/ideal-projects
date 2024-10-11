package com.ziyao.ideal.security.core;

import com.ziyao.ideal.security.core.context.PrincipalClaims;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 存储当前当前用户的会话信息
 *
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
@Setter
public class User implements UserDetails, CredentialsContainer, PrincipalClaims {


    @Serial
    private static final long serialVersionUID = 5236617915182892884L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户凭证
     */
    private String password;

    /**
     * 账号状态
     */
    private Integer status;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 姓名
     */
    private String idCardName;
    /**
     * 性别（M: 男, F: 女, O: 其他）
     */
    private String gender;
    /**
     * 地址
     */
    private String address;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLogin;
    /**
     * 登录ip
     */
    private String loginIp;
    /**
     * 登录归属地
     */
    private String loginLocation;
    /**
     * 过期时间，以秒为单位
     */
    private long ttl;

    /**
     * 附加信息
     */
    private final Map<String, Object> claims = new HashMap<>();


    public User() {
    }

    public User(Integer id, String username, String nickname,
                String password, Integer status, String mobile,
                String idCardName, String gender, String address, LocalDateTime lastLogin, String loginIp) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.status = status;
        this.mobile = mobile;
        this.idCardName = idCardName;
        this.gender = gender;
        this.address = address;
        this.lastLogin = lastLogin;
        this.loginIp = loginIp;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        if (this.status == null) {
            return true;
        }
        return !(this.status == 2);
    }

    //账号状态 0正常 1锁定 2过期 3未启用
    @Override
    public boolean isAccountNonLocked() {
        if (this.status == null) {
            return true;
        }
        return !(this.status == 1);
    }

    /**
     * 密码是否未过期
     *
     * @return false未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (this.status == null) {
            return true;
        }
        return !(this.status == 3);
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Map<String, Object> getClaims() {
        return claims;
    }

    public static UserBuilder withId(Integer id) {
        return new UserBuilder().id(id);
    }

    public static UserBuilder withUsername(String username) {
        return new UserBuilder().username(username);
    }

    public static UserBuilder withUserDetails(final UserDetails userDetails) {
        return new UserBuilder().username(userDetails.getUsername()).password(userDetails.getPassword());
    }

    public static final class UserBuilder {
        private final User user = new User();

        public UserBuilder id(Integer id) {
            this.user.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.user.username = username;
            return this;
        }

        public UserBuilder nickname(String nickname) {
            this.user.nickname = nickname;
            return this;
        }

        public UserBuilder password(String password) {
            this.user.password = password;
            return this;
        }

        public UserBuilder status(Integer status) {
            this.user.status = status;
            return this;
        }

        public UserBuilder mobile(String mobile) {
            this.user.mobile = mobile;
            return this;
        }

        public UserBuilder idCardName(String idCardName) {
            this.user.idCardName = idCardName;
            return this;
        }

        public UserBuilder gender(String gender) {
            this.user.gender = gender;
            return this;
        }

        public UserBuilder address(String address) {
            this.user.address = address;
            return this;
        }

        public UserBuilder lastLogin(LocalDateTime lastLogin) {
            this.user.lastLogin = lastLogin;
            return this;
        }

        public UserBuilder loginIp(String loginIp) {
            this.user.loginIp = loginIp;
            return this;
        }


        public UserBuilder claims(Map<String, Object> claims) {
            this.user.claims.putAll(claims);
            return this;
        }

        public UserBuilder claims(Consumer<Map<String, Object>> claimsConsumer) {
            claimsConsumer.accept(this.user.claims);
            return this;
        }

        public User build() {
            return this.user;
        }
    }
}
