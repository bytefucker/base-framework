-- ----------------------------
-- Table structure for permission
-- ----------------------------
CREATE TABLE IF NOT EXISTS `permission`
(
    `id`          bigint(11)   NOT NULL,
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
    `code`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限标识',
    `type`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
    `parent_id`   bigint(20)   NULL DEFAULT NULL COMMENT '父级权限id',
    `path`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
    `deleted`     tinyint(255) NULL DEFAULT 0 COMMENT '是否删除',
    `enable`      tinyint(255) NULL DEFAULT 0 COMMENT '是否启动',
    `order`       int(255) UNSIGNED NULL DEFAULT 0 COMMENT '排序',
    `create_time` datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
    `create_by`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission`
VALUES (1, '首页', 'home', 'menu', NULL, '/', 0, 1, 0, '2023-03-06 16:01:45', 'admin',
        '2023-03-06 16:01:49', 'admin');

-- ----------------------------
-- Table structure for role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `role`
(
    `id`          bigint(20) UNSIGNED NOT NULL,
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
    `code`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
    `deleted`     tinyint(4)  NULL DEFAULT NULL COMMENT '是否删除（0-未删除，1-删除）',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间\r\n',
    `create_by`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `update_by`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES (1, 'admin', 'admin', 0, '2023-03-06 15:59:43', 'admin', '2023-03-06 15:59:47', 'admin');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
CREATE TABLE IF NOT EXISTS `role_permission`
(
    `role_id`       bigint(20) NOT NULL,
    `permission_id` bigint(20) NOT NULL,
    PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission`
VALUES (1, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user`
(
    `id`          bigint(20)  NOT NULL,
    `login_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录用户名',
    `user_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `phone`       varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
    `enable`      tinyint(4)  NULL DEFAULT NULL COMMENT '是否启用（0-未启用，1-启动）',
    `deleted`     tinyint(4)  NULL DEFAULT NULL COMMENT '是否删除（0-未删除，1-删除）',
    `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `create_by`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `update_by`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_name_index`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, 'admin', 'admin', '$2a$10$WTEe/zucZVtWXRtnfs2ZjuEr6TcQx3Mbp/vSH8S2OiSUrvzzOn9qq',
        '12345678901', 1, 0, '2023-03-06 17:12:39', 'admin', '2023-03-06 17:12:39', 'admin');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_role`
(
    `user_id` bigint(20) NOT NULL,
    `role_id` bigint(20) NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`
VALUES (1, 1);

