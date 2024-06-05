create table members(
id       varchar2(8) primary key,
password varchar2(8),
name     varchar2(20),
role     varchar2(5),
reg_dt   date,
point    number);

insert into members values('user1','1111','나관리','Admin', '2024/01/01',0);
insert into members values('user2','2222','나사용','User', '2024/04/10',1000);

commit;