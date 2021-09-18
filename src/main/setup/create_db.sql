CREATE TABLE "public".party (
	id			bigint NOT NULL,
	username	text NOT NULL,
	"password"	text NOT NULL,
	first_name	text NOT NULL,
	last_name	text NOT NULL,
	status		integer NOT NULL,
	CONSTRAINT pk_party PRIMARY KEY (id)
);

CREATE TABLE "public".exercise_def (
	id				bigint NOT NULL,
	name			text NOT NULL,
	exercise_type	integer NOT NULL,
	description		text,
	status			integer NOT NULL,
	created_by		text NOT NULL,
	created_on		timestamp NOT NULL,
	updated_on		timestamp,
	CONSTRAINT pk_exercise_def PRIMARY KEY (id)
);

CREATE TABLE "public".training_def (
	id			bigint NOT NULL,
	name		text NOT NULL,
	description	text,
	status		integer NOT NULL,
	created_by	text NOT NULL,
	created_on	timestamp NOT NULL,
	updated_on	timestamp,
	CONSTRAINT pk_training_def PRIMARY KEY (id)
);

CREATE TABLE "public".routine_def (
	id			bigint NOT NULL,
	name		text NOT NULL,
	description text,
	status      integer NOT NULL,
	created_by	text NOT NULL,
	created_on	timestamp NOT NULL,
	updated_on	timestamp,
	CONSTRAINT pk_routine_def PRIMARY KEY (id)
);

CREATE TABLE "public".exercise (
	id				bigint NOT NULL,
	exercise_def_id	bigint NOT NULL,
	party_id		bigint NOT NULL,
	training_id		bigint NOT NULL,
	CONSTRAINT pk_exercise PRIMARY KEY (id)
);

CREATE TABLE "public".training (
	id				bigint NOT NULL,
	training_def_id	bigint NOT NULL,
	party_id		bigint NOT NULL,
	routine_id		bigint NOT NULL,
	status          integer NOT NULL,
	started_on		timestamp NOT NULL,
	finished_on		timestamp,
	CONSTRAINT pk_training PRIMARY KEY (id)
);

CREATE TABLE "public".routine (
	id				bigint NOT NULL,
	routine_def_id	bigint NOT NULL,
	party_id		bigint NOT NULL,
	CONSTRAINT pk_routine PRIMARY KEY (id)
	CONSTRAINT udx_routine_def_party UNIQUE (routine_def_id, party_id)
);

CREATE TABLE "public".exercise_set (
	id				bigint NOT NULL,
	exercise_id		bigint NOT NULL,
	number_of_reps	bigint NOT NULL,
	distance_value	real,
	weight_value	real,
	time_value		text,
	status          integer NOT NULL,
	CONSTRAINT pk_exercise_set PRIMARY KEY (id)
);

CREATE TABLE "public".party_exercise_def (
	party_id		bigint NOT NULL,
	exercise_def_id	bigint NOT NULL,
	is_owner		bool NOT NULL,
	CONSTRAINT pk_party_exercise_def PRIMARY KEY (party_id, exercise_def_id)
);

CREATE TABLE "public".party_training_def (
	party_id		bigint NOT NULL,
	training_def_id	bigint NOT NULL,
	is_owner		bool NOT NULL,
	CONSTRAINT pk_party_training_def PRIMARY KEY (party_id, training_def_id)
);

CREATE TABLE "public".party_routine_def (
	party_id		bigint NOT NULL,
	routine_def_id	bigint NOT NULL,
	is_owner		bool NOT NULL,
	CONSTRAINT pk_party_routine_def PRIMARY KEY (party_id, routine_def_id)
);

CREATE TABLE "public".training_exercise_defs (
	training_def_id	bigint NOT NULL,
	exercise_def_id	bigint NOT NULL,
	CONSTRAINT pk_training_exercise_defs PRIMARY KEY (training_def_id, exercise_def_id)
);

CREATE TABLE "public".routine_training_defs (
	routine_def_id	bigint NOT NULL,
	training_def_id	bigint NOT NULL,
	day_of_week		integer NOT NULL,
	CONSTRAINT pk_routine_training_defs PRIMARY KEY (routine_def_id, training_def_id),
	CONSTRAINT udx_routine_training_day	UNIQUE (routine_def_id, day_of_week)
);

CREATE SEQUENCE party_seq INCREMENT 1 START WITH 100;
CREATE SEQUENCE exercise_def_seq INCREMENT 1 START WITH 100;
CREATE SEQUENCE training_def_seq INCREMENT 1 START WITH 100;
CREATE SEQUENCE routine_def_seq INCREMENT 1 START WITH 100;
CREATE SEQUENCE exercise_seq INCREMENT 1 START WITH 100;
CREATE SEQUENCE training_seq INCREMENT 1 START WITH 100;
CREATE SEQUENCE routine_seq INCREMENT 1 START WITH 100;
CREATE SEQUENCE exercise_set_seq INCREMENT 1 START WITH 100;

ALTER TABLE "public".exercise ADD CONSTRAINT fk_exercise_exercise_def FOREIGN KEY (exercise_def_id) REFERENCES "public".exercise_def(id);
ALTER TABLE "public".exercise ADD CONSTRAINT fk_exercise_party FOREIGN KEY (party_id) REFERENCES "public".party(id);
ALTER TABLE "public".exercise ADD CONSTRAINT fk_exercise_training FOREIGN KEY (training_id) REFERENCES "public".training(id);

ALTER TABLE "public".training ADD CONSTRAINT fk_training_training_def FOREIGN KEY (training_def_id) REFERENCES "public".training_def(id);
ALTER TABLE "public".training ADD CONSTRAINT fk_training_party FOREIGN KEY (party_id) REFERENCES "public".party(id);
ALTER TABLE "public".training ADD CONSTRAINT fk_training_routine FOREIGN KEY (routine_id) REFERENCES "public".routine(id);

ALTER TABLE "public".routine ADD CONSTRAINT fk_routine_routine_def FOREIGN KEY (routine_def_id) REFERENCES "public".routine_def(id);
ALTER TABLE "public".routine ADD CONSTRAINT fk_routine_party FOREIGN KEY (party_id) REFERENCES "public".party(id);

ALTER TABLE "public".exercise_set ADD CONSTRAINT fk_exercise_set_exercise FOREIGN KEY (exercise_id) REFERENCES "public".exercise(id);

CREATE OR REPLACE FUNCTION create_exercise (exerciseDefId BIGINT, partyId BIGINT, trainingId BIGINT) RETURNS INTEGER AS
$BODY$

BEGIN
    INSERT INTO exercise (id, exercise_def_id, party_id, training_id) VALUES (nextval('exercise_seq'), exerciseDefId, partyId, trainingId);
    RETURN 1;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION create_training (trainingDefId BIGINT, partyId BIGINT, routineId BIGINT) RETURNS INTEGER AS
$BODY$
DECLARE
    trainingId BIGINT;
    ted RECORD;
BEGIN
    trainingId = (SELECT nextval('training_seq'));
    INSERT INTO training (id, training_def_id, party_id, routine_id, status, started_on, finished_on)
        VALUES (trainingId, trainingDefId, partyId, routineId, 101, current_timestamp, null);
    FOR ted IN SELECT exercise_def_id FROM training_exercise_defs WHERE training_def_id = trainingDefId
    LOOP
        PERFORM create_exercise(ted.exercise_def_id, partyId, trainingId);
    END LOOP;
    RETURN 1;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION add_exercise_def_to_party (partyId BIGINT, exerciseDefId BIGINT, isOwner BOOL) RETURNS INTEGER AS
$BODY$
BEGIN
    INSERT INTO party_exercise_def (party_id, exercise_def_id, is_owner) VALUES (partyId, exerciseDefId, isOwner);
    RETURN 1;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION add_training_def_to_party (partyId BIGINT, trainingDefId BIGINT, isOwner BOOL) RETURNS INTEGER AS
$BODY$
BEGIN
    INSERT INTO party_training_def (party_id, training_def_id, is_owner) VALUES (partyId, trainingDefId, isOwner);
    RETURN 1;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION add_routine_def_to_party (partyId BIGINT, routineDefId BIGINT, isOwner BOOL) RETURNS INTEGER AS
$BODY$
BEGIN
    INSERT INTO party_routine_def (party_id, routine_def_id, is_owner) VALUES (partyId, routineDefId, isOwner);
    RETURN 1;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION share_training_def (partyId BIGINT, trainingDefId BIGINT) RETURNS INTEGER AS
$BODY$
DECLARE
    alreadyExists INTEGER;
    ted RECORD;
BEGIN
    PERFORM add_training_def_to_party(partyId, trainingDefId, false);
    FOR ted IN SELECT exercise_def_id FROM training_exercise_defs WHERE training_def_id = trainingDefId
    LOOP
        alreadyExists = (SELECT COUNT(*) FROM party_exercise_def WHERE exercise_def_id = ted.exercise_def_id AND party_id = partyId);

        IF (alreadyExists = 0) THEN
            PERFORM add_exercise_def_to_party(partyId, ted.exercise_def_id, false);
        END IF;
    END LOOP;
    RETURN 1;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION share_routine_def (partyId BIGINT, routineDefId BIGINT) RETURNS INTEGER AS
$BODY$
DECLARE
    alreadyExists INTEGER;
    rtd RECORD;
BEGIN
    INSERT INTO party_routine_def (party_id, routine_def_id, is_owner) VALUES (partyId, routineDefId, false);
    FOR rtd IN SELECT training_def_id FROM routine_training_defs WHERE routine_def_id = routineDefId
        LOOP
            alreadyExists = (SELECT COUNT(*) FROM party_training_def WHERE training_def_id = rtd.training_def_id AND party_id = partyId);

            IF (alreadyExists = 0) THEN
                PERFORM share_training_def(partyId, rtd.exercise_def_id);
            END IF;
        END LOOP;
        RETURN 1;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_exercise_def (name TEXT, exerciseType INTEGER, description TEXT, createdBy TEXT, partyId BIGINT) RETURNS INTEGER AS
$BODY$
DECLARE
    exerciseDefId BIGINT;
BEGIN
    exerciseDefId = (SELECT nextval('exercise_def_seq'));
    INSERT INTO exercise_def (id, name, exercise_type, description, status, created_by, created_on, updated_on)
        VALUES (exerciseDefId, name, exerciseType, description, 101, createdBy, current_timestamp, null);
    PERFORM add_exercise_def_to_party(partyId, exerciseDefId, true);
    RETURN 1;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_training_def (name TEXT, description TEXT, createdBy TEXT, partyId BIGINT) RETURNS INTEGER AS
$BODY$
DECLARE
    trainingDefId BIGINT;
BEGIN
    trainingDefId = (SELECT nextval('training_def_seq'));
    INSERT INTO training_def (id, name, description, status, created_by, created_on, updated_on)
        VALUES (trainingDefId, name, description, 101, createdBy, current_timestamp, null);
    PERFORM add_training_def_to_party(partyId, trainingDefId, true);
    RETURN 1;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_routine_def (name TEXT, description TEXT, createdBy TEXT, partyId BIGINT) RETURNS INTEGER AS
$BODY$
DECLARE
    routineDefId BIGINT;
BEGIN
    routineDefId = (SELECT nextval('routine_def_seq'));
    INSERT INTO routine_def (id, name, description, status, created_by, created_on, updated_on)
        VALUES (routineDefId, name, description, 101, createdBy, current_timestamp, null);
    PERFORM add_routine_def_to_party(partyId, routineDefId, true);
    RETURN 1;
END;
$BODY$
    LANGUAGE plpgsql;

INSERT INTO party (id, username, "password", first_name, last_name, status) VALUES (1, 'admin', 'admin', 'Administrator', 'User', 100);
INSERT INTO party (id, username, "password", first_name, last_name, status) VALUES (2, 'test', 'test', 'test', 'test', 100);