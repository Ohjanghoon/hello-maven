--========================
-- mybatis
--========================

create table student (
    no number,
    name varchar2(50) not null,
    tel char(11) not null,
    created_at date default sysdate,
    updated_at date,
    deleted_at date, -- 학생정보 제거시 deleted_at컬럼을 sysdate로 작성
    constraint pk_student_no primary key(no)
);

create sequence seq_student_no;
select * from student;

insert into student (no, name, tel) values(seq_student_no.nextval, '홍길동', '01012345678');
insert into student (no, name, tel) values(seq_student_no.nextval, '킹길동', '01012345678');
insert into student (no, name, tel) values(seq_student_no.nextval, '강감길동', '01012345678');
insert into student (no, name, tel) values(seq_student_no.nextval, '신사길동', '01012345678');
insert into student (no, name, tel) values(seq_student_no.nextval, '세종길동', '01012345678');

select * from student where deleted_at is null;
select * from student where deleted_at is null and no = 1;

-- 수정
update student set tel = '01022223333', updated_at = sysdate where no = 4;

-- 제거
update student set deleted_at = sysdate where name = '세종길동';

commit;