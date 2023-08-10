create table course
(
    id           int auto_increment
        primary key,
    name         varchar(20) not null,
    course_score int         not null,
    course_time  int         not null
);

create table user
(
    no       varchar(20)  not null,
    name     varchar(20)  not null,
    password varchar(100) not null,
    role     int          not null,
    primary key (no)
);

create table score
(
    id         int auto_increment
        primary key,
    teacher_no varchar(50) null,
    course_id  int         null,
    student_no varchar(50) not null,
    score      int         null,
    constraint score_ibfk_1
        foreign key (teacher_no) references user (no),
    constraint score_ibfk_2
        foreign key (course_id) references course (id),
    constraint score_user_no_fk
        foreign key (student_no) references user (no)
);

create index course_id
    on score (course_id);

create index teacher_no
    on score (teacher_no);

create table teach
(
    id         int auto_increment
        primary key,
    teacher_no varchar(50) null,
    course_id  int         null,
    constraint teach_ibfk_1
        foreign key (teacher_no) references user (no),
    constraint teach_ibfk_2
        foreign key (course_id) references course (id)
);

create index course_id
    on teach (course_id);

create index teacher_no
    on teach (teacher_no);

create definer = remoteuser@`%` trigger remove_from_score
    after delete
    on teach
    for each row
begin
    declare tno varchar(50);
    declare cid int;
    set tno = OLD.teacher_no;
    set cid = OLD.course_id;
    delete from score where score.teacher_no = tno and score.course_id = cid;
end;

create definer = remoteuser@`%` trigger remove_score_info
    before delete
    on user
    for each row
begin
    if OLD.role=1 then
        delete from score where score.teacher_no=OLD.no;
        delete from teach where teach.teacher_no=OLD.no;
    end if;
    if OLD.role=2 then
        delete from score where score.student_no=OLD.no;
    end if;
end;

insert into user values (1, 'admin', '356a192b7913b04c54574d18c28d46e6395428ab', 0)