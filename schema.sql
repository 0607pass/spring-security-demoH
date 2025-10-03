CREATE TABLE `users` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `username` varchar(50) NOT NULL,
                         `password` varchar(100) NOT NULL,
                         `role` varchar(50) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

INSERT INTO `security_demo`.`users`(`id`, `username`, `password`, `role`) VALUES (1, 'admin', '$2a$10$8KQxj0nB3F/R6D1umCKZxO4uwXCJNkUApUIRrPzqYW/MUzghxTH4C', 'ROLE_ADMIN');
INSERT INTO `security_demo`.`users`(`id`, `username`, `password`, `role`) VALUES (2, 'user', '$2a$10$8KQxj0nB3F/R6D1umCKZxO4uwXCJNkUApUIRrPzqYW/MUzghxTH4C', 'ROLE_USER');
