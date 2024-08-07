package com.ziyao.ideal.security.core;

import lombok.Getter;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author ziyao
 */

public class UserInfo implements UserDetails, CredentialsContainer {


    @Serial
    private static final long serialVersionUID = 5236617915182892884L;

    /**
     * 用户id
     */
    @Getter
    private final Integer id;

    /**
     * 用户账号
     */
    private final String username;

    /**
     * 昵称
     */
    @Getter
    private final String nickname;

    /**
     * 用户凭证
     */
    private String password;

    /**
     * 账号状态
     */
    @Getter
    private final Integer status;
    /**
     * 手机号
     */
    @Getter
    private final String mobile;
    /**
     * 姓名
     */
    @Getter
    private final String idCardName;
    /**
     * 性别（M: 男, F: 女, O: 其他）
     */
    @Getter
    private final String gender;
    /**
     * 地址
     */
    @Getter
    private final String address;
    /**
     * 最后登录时间
     */
    @Getter
    private final LocalDateTime lastLogin;
    /**
     * 登录ip
     */
    @Getter
    private final String loginIp;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserInfo(Integer id, String username, String nickname,
                    String password, Integer status, String mobile,
                    String idCardName, String gender, String address, LocalDateTime lastLogin,
                    String loginIp, Collection<? extends GrantedAuthority> authorities) {
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
        this.authorities = authorities;
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
        return this.status == 2;
    }

    //账号状态 0正常 1锁定 2过期 3未启用
    @Override
    public boolean isAccountNonLocked() {
        if (this.status == null) {
            return true;
        }
        return this.status == 1;
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
        return this.status == 3;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
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
        private Integer id;
        private String username;
        private String nickname;
        private String password;
        private Integer status;
        private String mobile;
        private String idCardName;
        private String gender;
        private String address;
        private LocalDateTime lastLogin;
        private String loginIp;
        private List<GrantedAuthority> authorities = new ArrayList<>();

        public UserBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public UserBuilder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public UserBuilder idCardName(String idCardName) {
            this.idCardName = idCardName;
            return this;
        }

        public UserBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder lastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public UserBuilder loginIp(String loginIp) {
            this.loginIp = loginIp;
            return this;
        }

        public UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        public UserBuilder authorities(Consumer<Collection<GrantedAuthority>> authoritiesConsumer) {
            authoritiesConsumer.accept(this.authorities);
            return this;
        }

        public UserInfo build() {
            return new UserInfo(id, username, nickname, password, status,
                    mobile, idCardName, gender, address, lastLogin, loginIp, authorities);
        }
    }
}
