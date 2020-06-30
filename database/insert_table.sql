use newservlet;
insert into role(code,name) values ('ADMIN','Quản trị hệ thống');
insert into role(code,name) values ('USER','Người dùng');

insert into user (username,password,fullname,status,roleid) values ('admin','123456','admin',1,1);
insert into user (username,password,fullname,status,roleid) values ('levanhung','123456','Lê Văn Hưng',1,2);
insert into user (username,password,fullname,status,roleid) values ('nguyenvanb','123456','Nguyễn Văn B',1,2);