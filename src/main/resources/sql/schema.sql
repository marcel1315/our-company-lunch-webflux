CREATE TABLE `diner`
(
    `id`         BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name`       VARCHAR(20)   NOT NULL,
    `link`       VARCHAR(2048) NULL,
    `created_at` DATETIME      NULL
);
