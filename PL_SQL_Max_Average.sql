create or replace view studentScores AS
SELECT SUM(points*weight/maxpoints) as Total,enrolls.sid, courses.lineno, components.term, catalogs.ctitle
FROM (((scores INNER JOIN enrolls ON (scores.sid=enrolls.sid) AND
	(scores.term=enrolls.term) AND (scores.lineno=enrolls.lineno))
	INNER JOIN components ON (scores.compname=components.compname) AND
	(score.term=components.term) AND (scores.lineno=components.lineno))
	INNER JOIN courses ON courses.term=component.term AND
	course.lineno=components.lineno)
	INNER JOIN catalogs ON catalogs.cno=courses.cno
Group BY enrolls.sid, courses.lineno,components.term, catalogs.ctitle;

create or replace procedure maxAverage(prc out sys_refcursor) is
begin
open prc for
Select students.sid,fname,lname,studMax.lineno,studMax.term,MAXTotal,ctitle
FROM ((SELECT MAX(Total) as MAXTotal,studentRecords.lineno,
	studentRecords.term
	FROM studentRecords
	Group BY studentScores.lineno,studentScores.term)
		studMax INNER JOIN
		(SELECT *
		FROM studentScores) studMark
			ON (MAXTotal=Total) and (studMax.lineno=studMark.lineno) AND (studMax.term=studMark.term))
		INNER JOIN students ON students.sid=studMark.sid;
end; 