-- creating Inception project is expensive so lets not delete it
delete from PROJECT_DEFINITION where PROJECT_ID <> 'Inception';
delete from USER_ATTRS where USER_ID <> 'root@skills.org' and USER_ID <> 'user1@skills.org';
delete from GLOBAL_BADGE_LEVEL_DEFINITION;
delete from SKILL_DEFINITION where PROJECT_ID is null;
