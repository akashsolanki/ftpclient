insert into User(created,username,password) values ('2013-10-04 10:00:00','admin','admin');
insert into User(created,username,password) values ('2013-10-04 10:00:00','super','super');
insert into User(created,username,password) values ('2013-10-04 10:00:00','user','user');
insert into Role(created,id,role) values ('2013-10-04 10:00:00',1,'ROLE_SUPER');
insert into Role(created,id,role) values ('2013-10-04 10:00:00',2,'ROLE_ADMIN');
insert into Role(created,id,role) values ('2013-10-04 10:00:00',3,'ROLE_USER');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',2,'admin');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',3,'admin');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',1,'super');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',2,'super');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',3,'super');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',3,'user');
INSERT INTO USERROLE(ROLEID, USERNAME ) VALUES ( 1 , 'pegasus')
