CREATE TABLE `quiz` (  `id` integer PRIMARY KEY AUTOINCREMENT,  `quiz_id` numeric NOT NULL,  `unit_id` numeric NOT NULL,  `title` text NOT NULL,  `content` text NOT NULL,  `tof_quiz` numeric NOT NULL,  `essay_quiz` numeric NOT NULL,  `answer` text NOT NULL,  `state` numeric,  `long_date` numeric,  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP)