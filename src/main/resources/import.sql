-- Inserting user data
INSERT INTO `Blog_user`(`id`, `given_name`, `family_name`) values (1, 'Ria', 'Yu');

-- Inserting blog entries associated with the user
INSERT INTO `Blog`(`id`, `title`, `content`, `user_id`) values (1, 'test1', '11', 1);
INSERT INTO `Blog`(`id`, `title`, `content`, `user_id`) values (2, 'test2', '11111111111', 1);
INSERT INTO `Blog`(`id`, `title`, `content`, `user_id`) values (3, 'test3', '22222222222', 1);
INSERT INTO `Blog`(`id`, `title`, `content`, `user_id`) values (4, 'test4', '33333333333', 1);
