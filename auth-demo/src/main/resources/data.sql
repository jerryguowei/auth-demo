insert into user_info (`first_name`, `last_name`, `email`, `password`, `active`) values ('Wei', 'Guo', 'jerryguowei@gmail.com', '$2y$10$k/NKimkHVHbBc37yB7ZRe.WJVcs5BD49.hdBw2d9kOYd3DuVCyvQ6
', '0');

insert into USER_AUTHORITY (`authority`, `user_id`) values('ROLE_USER', SELECT t.id from  user_info t where t.email='jerryguowei@gmail.com');

insert into USER_AUTHORITY (`authority`, `user_id`) values('ROLE_ADMIN', SELECT t.id from  user_info t where t.email='jerryguowei@gmail.com')