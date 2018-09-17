create table records_deleted(
  sid       varchar2(5) not null,
  term      varchar2(10) not null,
  lineno    number(4) not null,
  compname  varchar2(15) not null,
  points    number(4) check(points >= 0),
  primary key (sid,term,lineno,compname));
  
CREATE OR REPLACE TRIGGER enrolls_deleted
    BEFORE DELETE ON enrolls
    FOR EACH ROW
DECLARE
    c1 sys_refcursor;
    score_rec scores%rowtype;
    
BEGIN
  open c1 for
    select *
    from    scores
    where   sid=:old.sid AND term=:old.term AND lineno=:old.lineno;
  
loop
  fetch c1 into score_rec;
exit when c1%NOTFOUND;

INSERT INTO records_deleted VALUES (score_rec.sid, score_rec.term,
  score_rec.lineno, score_rec.compname, score_rec.points);
end loop;
close c1;
delete from scores WHERE sid=:old.sid AND term=:old.term AND
  lineno=:old.lineno;
end;

/

test

delete from enrolls where sid='3333';
SELECT * FROM RECORDS_DELETED;