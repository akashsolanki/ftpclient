insert into User(created,username,password) values ('2013-10-04 10:00:00','admin','$2a$11$QGpodUb4P7y/4lHasGZCteRyS.5H9r4QE9d6nOVxgqufxb8.ZhKiu');
insert into User(created,username,password) values ('2013-10-04 10:00:00','super','$2a$11$2RXbxd4hrhelG5zP.nZMOumSFanclW2AdjEr2AHVGfUhIAwk0wThe');
insert into User(created,username,password) values ('2013-10-04 10:00:00','user','$2a$11$lDV1NoRLj//y20QM.rItu.VmpAKfS1TF2ySX9N1XdlGaw6wBLRh.2');
insert into Role(created,id,role) values ('2013-10-04 10:00:00',1,'ROLE_SUPER');
insert into Role(created,id,role) values ('2013-10-04 10:00:00',2,'ROLE_ADMIN');
insert into Role(created,id,role) values ('2013-10-04 10:00:00',3,'ROLE_USER');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',2,'admin');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',3,'admin');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',1,'super');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',2,'super');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',3,'super');
insert into UserRole(created,roleId,username) values ('2013-10-04 10:00:00',3,'user');
INSERT INTO USERROLE(ROLEID, USERNAME ) VALUES ( 1 , 'pegasus');
insert into folder(id,name,isfolder,path,parentid) values(1,'root',true,'/',null);
