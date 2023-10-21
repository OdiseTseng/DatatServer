CREATE TABLE `course` (  `id` integer PRIMARY KEY AUTOINCREMENT,  `course_id` numeric NOT NULL,  `course_type` numeric NOT NULL,  `course_name` text,  `course_schedule` numeric,  `course_desc` text,  `unit_list` text,  `credit_units` numeric,  `state` numeric,  `long_date` numeric,  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP)