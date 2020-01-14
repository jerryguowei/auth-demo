insert into user_info (`first_name`, `last_name`, `email`, `password`, `active`) values ('Wei', 'Guo', 'jerryguowei@gmail.com', '$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK', '1');

insert into USER_AUTHORITY (`authority`, `user_id`) values('ROLE_USER', SELECT t.id from  user_info t where t.email='jerryguowei@gmail.com');

insert into USER_AUTHORITY (`authority`, `user_id`) values('ROLE_ADMIN', SELECT t.id from  user_info t where t.email='jerryguowei@gmail.com');

insert into client(`client_id`, `acess_token_validity_seconds`, `authorities`, `authorized_grant_types`
, `client_redirect_uris`, `refresh_token_validity_secods`, `resource_ids`, `scopes`, `secret`) values ('front-end', 300, 'USER', 'refresh_token,password', '', 600, '','read,write','$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK');