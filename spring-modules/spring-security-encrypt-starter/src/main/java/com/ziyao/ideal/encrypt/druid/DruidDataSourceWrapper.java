package com.ziyao.ideal.encrypt.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.ziyao.ideal.crypto.Codebook;
import com.ziyao.ideal.crypto.TextCipher;
import com.ziyao.ideal.crypto.utils.SmUtils;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ziyao zhang
 */
public class DruidDataSourceWrapper {

    private static final Set<DataSource> initiatedDS = Collections.synchronizedSet(new HashSet<>());

    public static void init(DataSource dataSource) {
        if (dataSource instanceof DruidDataSource) {
            if (isUninitiatedDS(dataSource)) {
                initRandomSecret((DruidDataSource) dataSource);
                markInitiatedDS(dataSource);
            }
        }
    }

    private static void initRandomSecret(DruidDataSource dataSource) {
        TextCipher textCipher = createRandomCipher();
        String username = dataSource.getUsername();
        String password = dataSource.getPassword();
        String encryptUsername = textCipher.encrypt(username);
        String encryptPassword = textCipher.encrypt(password);

        NameDecryptCallback nameCallback = new NameDecryptCallback("DruidDataSource#userName", textCipher);
        PasswordDecryptCallback passwordCallback = new PasswordDecryptCallback("DruidDataSource#password", true, textCipher);
        nameCallback.setName(encryptUsername);
        passwordCallback.setPd(encryptPassword);

        // 填充DataSource
        dataSource.setUsername(encryptUsername);
        dataSource.setUserCallback(nameCallback);
        dataSource.setPassword(encryptPassword);
        dataSource.setPasswordCallback(passwordCallback);
    }

    private static TextCipher createRandomCipher() {
        Codebook.KeyIv keyIv = SmUtils.generateSm4KeyIv();
        return SmUtils.createSm4CBCTextCipherWithZeroPaddingAndHexCodec(keyIv);
    }

    /**
     * 标记
     */
    private static void markInitiatedDS(DataSource dataSource) {
        if (isUninitiatedDS(dataSource)) {
            initiatedDS.add(dataSource);
        }
    }

    public static boolean isUninitiatedDS(DataSource dataSource) {
        return !initiatedDS.contains(dataSource);
    }
}
