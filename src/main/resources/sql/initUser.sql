CREATE TABLE `user` (  `id` integer PRIMARY KEY AUTOINCREMENT,  `username` text NOT NULL,  `password` text NOT NULL,  `name` text NOT NULL,  `o_auth_key` text,  `student_id` numeric,  `student_batch` numeric,  `level` numeric,  `ip` text,  `state` numeric,  `long_date` numeric,  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP)